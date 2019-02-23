package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private TextField tf_initial_amount;

    @FXML
    private TextField tf_annual_return;

    @FXML
    private TextField tf_tax_rate;

    @FXML
    private TextField tf_annual_inflation;

    @FXML
    private ComboBox<?> cb_type_of_fee;

    @FXML
    private TextField tf_annual_fee;

    @FXML
    void generateResultsClicked(ActionEvent event) {

    }

}
