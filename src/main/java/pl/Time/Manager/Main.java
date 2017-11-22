package pl.Time.Manager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.Time.Manager.database.dbuitls.DbManager;
import pl.Time.Manager.utils.FxmlUtils;

public class Main extends Application {

    public static final String BORDER_PANE_MAIN_FXML = "/fxml/BorderPaneMain.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        // Locale.setDefault(new Locale("en"));   //pl
        // do Pane podczepiamy borderPane i działą bo Pane jest nadklasą BorderPane
        Pane borderPane = FxmlUtils.fxmlloader(BORDER_PANE_MAIN_FXML);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));
        primaryStage.show();
        DbManager.initDatabase();
    }
}
