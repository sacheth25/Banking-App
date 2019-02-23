package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.util.TreeMap;

public class MainController {
	
	TreeMap<String, Double> values; 

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
     * Generates the return on investments, and does the neccesary error checking
     * @param event
     */
    @FXML
    void generateResultsClicked(ActionEvent event) {
    	
    }

}
