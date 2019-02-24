package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.annotation.Resources;
/**
 * Main controller controls the main scene
 * @author Sacheth
 *
 */
public class MainController implements Initializable {
	
	private ArrayList<Double> values; 
	private ArrayList<TextField> textFields;
	private double[] graph_values;
	private double[] graph_values_real;
	
	@FXML
    private MenuItem menu_save;

    @FXML
    private MenuItem menu_load;

    @FXML
    private MenuItem menu_reset;

    @FXML
    private MenuItem menu_about;

    @FXML
    private TextField tf_initial_amount;

    @FXML
    private TextField tf_annual_return;

    @FXML
    private TextField tf_tax_rate;

    @FXML
    private TextField tf_annual_inflation;

    @FXML
    private ChoiceBox<String> cb_type_of_fee;

    @FXML
    private TextField tf_annual_fee;

    @FXML
    private Button generate_button;
    
    @FXML
    private Button reset_button;
    
    @FXML
    private BarChart<String, Double> real_bar_graph;

    @FXML
    private BarChart<?, ?> inflation_bar_graph;
    
    @FXML
    private CategoryAxis inflation_x_axis;

    @FXML
    private NumberAxis inflation_y_axis;

    
    @FXML
    private CategoryAxis real_x_axis;

    @FXML
    private NumberAxis real_y_axis;

    
    
    /**
     * Generates the return on investments, and does error checking
     */
    void generateResultsClicked() {
    	//Go through each node, check if a Text Field has numbers or not
    	//Get input and store into a map
    	if(isGoodValues()) {
    		for(TextField t : textFields) {
    			double value = Double.parseDouble(t.getText().trim());
    			values.add(value);
    		}
    		calculateReturn(10);
    	}
    }
    /**
     * Initializes the nodes in the GUI
     * @param location 
     * @param resources 
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		values = new ArrayList<Double>(5) ;
		textFields = new ArrayList<TextField>(5);
		textFields.add(tf_initial_amount);
		textFields.add(tf_annual_return);
		textFields.add(tf_tax_rate);
		textFields.add(tf_annual_inflation);
		textFields.add(tf_annual_fee);
		//Setting up the choice box
		cb_type_of_fee.getItems().add("As a percentage of the interest earned");
		cb_type_of_fee.getItems().add("As a percentage of the invested amount");
		cb_type_of_fee.setValue("As a percentage of the interest earned");
		generate_button.setOnAction(e -> generateResultsClicked());
		reset_button.setOnAction(e -> resetValues());
		
		//Setting Up the graphs
		inflation_x_axis = new CategoryAxis();
		inflation_y_axis = new NumberAxis();
        inflation_x_axis.setLabel("Years");       
        inflation_y_axis.setLabel("Amount of money");
        inflation_bar_graph.setTitle("Inflation adjusted ROI");
        
        real_x_axis = new CategoryAxis();
		real_y_axis = new NumberAxis();
        real_x_axis.setLabel("Years");       
        real_y_axis.setLabel("Amount of money");
        real_bar_graph.setTitle("Real adjusted ROI");
        
        //Setting up menu functions
        menu_save.setOnAction(e -> saveToFile());;
        menu_load.setOnAction(e -> loadFromFile(e));
        menu_reset.setOnAction(e -> resetValues());       
        menu_about.setOnAction(e -> displayAbout());

	}
	/**
	 * Displays the about panel
	 * 
	 */
	private void displayAbout() {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("About");
        alert.setContentText("This App allows you to calculate a return on invesment\nand provides a graph");
        alert.showAndWait();
	}
	/**
	 * Reads in from a file
	 * @param event used to handle the button 
	 */
	private void loadFromFile(ActionEvent event) {
		// TODO Auto-generated method stub
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			File file = fileChooser.showOpenDialog(new Stage());
			//Check for individual errors
			if(file == null) {
				throw new IOException("File not choosen");
			}
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			if(!br.readLine().equals("ROI")) {
				br.close();
				throw new IOException("Incorrect File");
			}
			int index = 0;    
			String text;
			textFields.clear();
	        while((text = br.readLine()) != null){  
	        	textFields.add(new TextField(text));
	        }  
	        br.close();      
			
		}
		catch(IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Error");
	        alert.setHeaderText("Load from File");
	        alert.setContentText(e.getMessage());
	        alert.showAndWait();
		}
	}
	/**
	 * Saves the data in the input field to a file
	 */
	private void saveToFile() {
		// TODO Auto-generated method stub
		if(isGoodValues()) {
			try {
				String fileName = "ROI-" + values.get(0) + ".txt";
			    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			    writer.write("ROI\n");
			    for(TextField t : textFields) {
			    	writer.write(t.getText().trim() + "\n");
			    }
			    writer.close();
			    Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("Success");
		        alert.setHeaderText("Save to File");
		        alert.setContentText("File saved to \"" + fileName + "\" The number is the initial amount.");
		        alert.showAndWait();
			}catch(IOException e) {
				 Alert alert = new Alert(AlertType.INFORMATION);
			        alert.setTitle("Error");
			        alert.setHeaderText("Save to File");
			        alert.setContentText("Could not save to file");
			        alert.showAndWait();
			}
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Error");
	        alert.setHeaderText("Save to File");
	        alert.setContentText("Please input proper data values");
	        alert.showAndWait();
		}
	}
	
	/**
	 * Resets the values of the TextFields, and resets the graphs
	 */
    void resetValues() {
		for(TextField t : textFields) {
			t.setText("");
			t.setPromptText("");
		}
		inflation_bar_graph.getData().clear();
    	real_bar_graph.getData().clear();
    }

	/**
	 * Checks if the TextField has a number or not
	 * @param t
	 * @return a bool value based on if the input is correct or not
	 */
	public boolean isGoodInput(TextField t) {
		double input;
		try {
			if(t.getText().trim().contentEquals("")) {
				throw new NumberFormatException("Please Enter in a number");
			}
			input = Double.parseDouble(t.getText().trim());
			if(input <= 0) {
				throw new NumberFormatException("Please Enter in a positive non zero number");
			}
			
		}catch(NumberFormatException e){
			t.setText("");
			t.setPromptText(e.getMessage());
			return false;
		}
		return true;
	}
	public boolean isGoodValues() {
		int count = 0;
    	for(TextField t : textFields) {
    		if(isGoodInput(t)) {
    			
    			count++;
    		}
    	}
    	return count == 5;
	}
    /**
     * Calculates the return on investment and then graphs it
     * @param years the number of years that the ROI is calculated for
     */
    public void calculateReturn(int years) {
    	//Values for calculation
    	double initial_amount = values.get(0);
    	double real_initial_amount = values.get(0);
    	double annual_return = values.get(1) / 100.0;
    	double tax_rate = values.get(2) / 100.0;
    	double inflation_rate = values.get(3) / 100.0;
    	double annual_fee = values.get(4) / 100.0;
    	boolean percentage_of_intrest = (cb_type_of_fee.getValue() == "As a percentage of the interest earned");
    	graph_values = new double[years];
    	graph_values_real = new double[years];

    	//Creating data for graphs
    	XYChart.Series series1 = new XYChart.Series();
    	XYChart.Series series2 = new XYChart.Series();
    	inflation_bar_graph.getData().clear();
    	real_bar_graph.getData().clear();
    	
    	//Calculating and then increasing Initial Amount , inflated
    	for(int i = 0;i < years;i++) {
    		double return_val = (initial_amount * annual_return);
    		double real_return_val = 0;
    		return_val *=(1 - tax_rate);
    		return_val *= (1 + inflation_rate);
    		real_return_val = return_val / (1 + inflation_rate);
    		return_val -= (percentage_of_intrest)?(return_val * annual_fee):(initial_amount * annual_fee);
    		real_return_val -= (percentage_of_intrest)?(return_val * annual_fee):(initial_amount * annual_fee);
    		initial_amount += return_val;
    		graph_values[i] = initial_amount;
    		series1.getData().add(new XYChart.Data<String, Number>("" + (i+1), initial_amount));
    		
    	}
    	//For real adjustment
    	for(int i = 0;i < years;i++) {
    		double real_return_val = (real_initial_amount * annual_return);
    		real_return_val *=(1 - tax_rate);
    		real_return_val -= (percentage_of_intrest)?(real_return_val * annual_fee):(real_initial_amount * annual_fee);
    		real_return_val -= (percentage_of_intrest)?(real_return_val * annual_fee):(real_initial_amount * annual_fee);
    		System.out.println("Return for year " + (i + 1) + " " + real_initial_amount);
    		real_initial_amount += real_return_val;
    		graph_values_real[i] = real_initial_amount;
    		series2.getData().add(new XYChart.Data<String, Number>("" + (i+1), real_initial_amount));
    		
    	}
    	//Creating the graphs
    	inflation_bar_graph.getData().addAll(series1);
    	real_bar_graph.getData().addAll(series2);
        
    }
}





