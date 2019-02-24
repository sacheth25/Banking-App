package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.annotation.Resources;

public class MainController implements Initializable {
	
	TreeMap<TextField, Double> values; 
	ArrayList<TextField> textFields;

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
    
    /**
     * Generates the return on investments, and does error checking
     * @param event
     */
    @FXML
    void generateResultsClicked(ActionEvent event) {
    	
    	//Go through each node, check if a Text Field 
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
    			values.put(t,value);
    		}
    		calculateReturn();
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
		values = new TreeMap<TextField,Double>();
		textFields = new ArrayList<TextField>(5);
		textFields.add(tf_initial_amount);
		textFields.add(tf_annual_return);
		textFields.add(tf_tax_rate);
		textFields.add(tf_annual_inflation);
		textFields.add(tf_annual_fee);
		cb_type_of_fee.getItems().add("As a percentage of the interest earned");
		cb_type_of_fee.getItems().add("As a percentage of the invested amount");
		cb_type_of_fee.setValue("As a percentage of the interest earned");
		
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
     * Calculates the return on investment and then handles it
     */
    public void calculateReturn() {
    	for(TextField t: textFields) {
    		System.out.println(t.getText().trim());
    	}
    	
    }
    

}
