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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;

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
	private double[] graph_values_inflation;

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
    private BarChart<String, Number> inflation_bar_graph;
    
    @FXML
    private CategoryAxis inflation_x_axis;

    @FXML
    private NumberAxis inflation_y_axis;

    
    @FXML
    private CategoryAxis real_x_axis;

    @FXML
    private NumberAxis real_y_axis;

    XYChart.Series series1;
    
    /**
     * Generates the return on investments, and does error checking
     */
    void generateResultsClicked() {
    	//Go through each node, check if a Text Field has numbers or not
    	//Get input and store into a map
    	int count = 0;
    	for(TextField t : textFields) {
    		if(isGoodInput(t)) {
    			
    			count++;
    		}
    	}
    	if(count == 5) {
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
		cb_type_of_fee.getItems().add("As a percentage of the interest earned");
		cb_type_of_fee.getItems().add("As a percentage of the invested amount");
		cb_type_of_fee.setValue("As a percentage of the interest earned");
		generate_button.setOnAction(e -> generateResultsClicked());
		reset_button.setOnAction(e -> resetValues());
		
//		inflation_x_axis = new CategoryAxis();
//		inflation_y_axis = new NumberAxis();
//        inflation_x_axis.setLabel("Years");       
//        inflation_y_axis.setLabel("Amount of money");
//        inflation_bar_graph.setTitle("Inflation adjusted ROI");
//		inflation_bar_graph = new BarChart<String,Number>(inflation_x_axis,inflation_y_axis);
//		series1 = new XYChart.Series();
//		for(int i = 0;i<10;i++) {
//			series1.getData().add(new XYChart.Data("Year " + (i+1), 100));
//		}
//		inflation_bar_graph.getData().add(series1);
//		inflation_bar_graph.animatedProperty();
		
		
	/**
	 * Resets the values of the TextFields, and resets the graphs
	 */
    void resetValues() {
		for(TextField t : textFields) {
			t.setText("");
			t.setPromptText("");
		}
    }

	/**
	 * Checks if the TextField has a number or not
	 * @param t
	 * @return a bool value based on if the input is correct or not
	 */
	public boolean isGoodInput(TextField t) {
		double input;
		try {
			input = Double.parseDouble(t.getText().trim());
			System.out.println(input);
		}catch(NumberFormatException e){
			t.setText("");
			t.setPromptText("Please Enter In a Number!!!");
			return false;
		}
		return true;
	}
    /**
     * Calculates the return on investment and then graphs it
     * @param years the number of years that the ROI is calculated for
     */
    public void calculateReturn(int years) {
    	System.out.println("Calculating Return on investment for " + years + " years");
    	//Values for calculation
    	double initial_amount = values.get(0);
    	double annual_return = values.get(1) / 100.0;
    	double tax_rate = values.get(2) / 100.0;
    	double inflation_rate = values.get(3) / 100.0;
    	double annual_fee = values.get(4) / 100.0;
    	boolean percentage_of_intrest = (cb_type_of_fee.getValue() == "As a percentage of the interest earned");
    	graph_values = new double[years];
    	graph_values_inflation = new double[years];
    	
    	//Calculating and then increasing Initial Amount
    	series1.getData().clear();
    	for(int i = 0;i < years;i++) {
    		double return_val = (initial_amount * annual_return);
    		return_val *=(1 - tax_rate);
    		return_val *= (1 + inflation_rate);
    		return_val -= (percentage_of_intrest)?(return_val * annual_fee):(initial_amount * annual_fee);
    		System.out.println("Return for year " + (i + 1) + " " + initial_amount);
    		initial_amount += return_val;
    		graph_values[i] = initial_amount;
//    		series1.getData().add(new XYChart.Data("Year " + (i+1), initial_amount));
//    		XYChart.Data<Number, Number> data = 
//                    (XYChart.Data<Number, Number>)series1.getData().get(i);
//            data.setYValue(initial_amount);
    	}
    	//Creating the graphs
    	//series1.getData().clear();
    	//inflation_bar_graph.getData().addAll(series1);
        
    }
    
    

}





