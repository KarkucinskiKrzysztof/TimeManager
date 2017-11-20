package pl.Time.Manager.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;
import java.util.ResourceBundle;

public class DialogsUtils {
    static ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");

    public static void dialogAboutApplication(){
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle(bundle.getString("about.title"));
        informationAlert.setHeaderText(bundle.getString("about.header"));
        informationAlert.setContentText(bundle.getString("about.content"));
        informationAlert.showAndWait();
    }
    public static Optional<ButtonType> confirmationDial(){
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(bundle.getString("about.title"));
        confirmationDialog.setHeaderText(bundle.getString("about.header"));
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result;
    }

}


