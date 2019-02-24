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
    private ChoiceBox<?> cb_type_of_fee;

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
    	values = new TreeMap<TextField,Double>();
    	//Go through each node, check if a Text Field 
    	//Get input and store into a map
    	int count = 0;
    	for(TextField t : textFields) {
    		if(isGoodInput(t)) {
    			count++;
    		}
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
		textFields = new ArrayList<TextField>(5);
		textFields.add(tf_initial_amount);
		textFields.add(tf_annual_return);
		textFields.add(tf_tax_rate);
		textFields.add(tf_annual_inflation);
		textFields.add(tf_annual_fee);
		
	}
	/**
	 * Detects when a text field has been changed, and then changes the color to black
	 * @param textField
	 */
//	@FXML
//	public void textTyped(ActionEvent event) {
//		for(TextField t : textFields) {
//			if(!isGoodInput(t)) {
//				t.setStyle("-fx-text-inner-color: black");
//				t.setText("");
//			}
//		}
//	}
	
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
    
    
    

}
