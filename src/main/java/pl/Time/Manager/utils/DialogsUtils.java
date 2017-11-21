package pl.Time.Manager.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

import java.util.Optional;
import java.util.ResourceBundle;

public class DialogsUtils {
    static ResourceBundle bundle = FxmlUtils.getResourceBundle();

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
    public static void errorDialog(String error){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("error.title"));
        errorAlert.setHeaderText(bundle.getString("error.header"));
        // dodatkowa informacja
        // errorAlert.setContentText(bundle.getString("error.content"));

        TextArea textArea = new TextArea(error);
        errorAlert.getDialogPane().setContent(textArea);
        // errorAlert.getDialogPane().setContent(FxmlUtils.fxmlloader("/fxml/Projects.fxml"));  taka linie przy błędzie otwiera okno błędu i w nim
        errorAlert.showAndWait();
    }

}


