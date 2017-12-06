package pl.Time.Manager.controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.Time.Manager.utils.DialogsUtils;
import pl.Time.Manager.utils.FxmlUtils;


import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.STYLESHEET_MODENA;

public class MainController {

    @FXML
    private BorderPane borderPane;
    @FXML
    private TopMenuButtonsController topMenuButtonsController;  // wstrzykniecie kontrolera pomimo iż w BorderPaneMain.fxml nazwa to topMenuButtons to trzeba ją zmieniać na topMenuButtonsController

    @FXML
    private void initialize() {
        // przekazuje całą referencje do TopMenuButtons
        topMenuButtonsController.setMainController(this);
    }

    public void setCenter(String fmlxPath) {
        borderPane.setCenter(FxmlUtils.fxmlloader(fmlxPath));
    }

    public void info() {
        DialogsUtils.dialogAboutApplication();
    }

    public void alwaysOnTop(ActionEvent actionEvent) {      //12:30  #28
        Stage stage = (Stage) borderPane.getScene().getWindow();
        boolean value = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
        stage.setAlwaysOnTop(value);

    }

    public void setModena() {
        Application.setUserAgentStylesheet(STYLESHEET_MODENA);
    }

    public void setCaspian() {
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
    }

    public void closeApplication() {
        Optional<ButtonType> result = DialogsUtils.confirmationDial();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }
}
