package pl.Time.Manager.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.util.ResourceBundle;


public class FxmlUtils {
    public static Pane fxmlloader(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
        loader.setResources(getResourceBundle());
        try {
            return loader.load();
        } catch (Exception e) {
            DialogsUtils.errorDialog(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("bundles.messages");
    }
}
