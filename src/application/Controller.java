package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {

    @FXML
    private Label LabelNew;
    @FXML
    private ChoiceBox<?> ChoiceBoxCategory;

    @FXML
    private ChoiceBox<?> ChoiceBoxProject;

    @FXML
    private Button ButtonNewCategory;

    @FXML
    private Button ButtonNewProject;

    @FXML
    private Button ButtonStart;

    @FXML
    private Button ButtonPause;

    @FXML
    private Button ButtonStop;

    @FXML
    private Label LabelTime;

    @FXML
    void handleButtonAction(ActionEvent event){
        if (event.getSource() == ButtonNewCategory) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewCategory.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                // czy nazwa root1 jest potrzebna ? jeśli damy root tez działa

                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(event.getSource()==ButtonNewProject) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewProject.fxml"));
                Parent root2 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root2));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(event.getSource()==ButtonStart){
            System.out.println("Start");
        }
        else if(event.getSource()==ButtonPause){
            System.out.println("Pause");
        }
        else if(event.getSource()==ButtonStop){
            System.out.println("Stop");
        }
    }
}
