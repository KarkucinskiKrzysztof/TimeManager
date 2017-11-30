package pl.Time.Manager.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PomodoroController {

    @FXML
    private Label dynamicTimeDisplayLabel;

    @FXML
    private Button startButton;

    @FXML
    private ComboBox<?> categoryComboBox;

    @FXML
    private ComboBox<?> projectComboBox;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private ComboBox<?> durationWorkComboBox;

    @FXML
    private ComboBox<?> durationBreakComboBox;

    @FXML
    public void initialize() {


    }

    @FXML
    void cancelButtonOnAction(ActionEvent event) {

    }

    @FXML
    void startButtonOnAction(ActionEvent event) {

    }


}