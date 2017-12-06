package pl.Time.Manager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.Time.Manager.database.dbuitls.DbManager;
import pl.Time.Manager.utils.FxmlUtils;

import java.util.Locale;

public class Main extends Application {

    public static final String BORDER_PANE_MAIN_FXML = "/fxml/BorderPaneMain.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        // Locale.setDefault(new Locale("pl"));   //pl
        Pane borderPane = FxmlUtils.fxmlloader(BORDER_PANE_MAIN_FXML);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));
        primaryStage.show();
        DbManager.initDatabase();
    }
}
