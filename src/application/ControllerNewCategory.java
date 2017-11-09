package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerNewCategory {


    @FXML
    private Button ButtonAdd;

    @FXML
    private Button ButtonCancel;

    @FXML
    private ColorPicker choiceboxCategoryinNewCategory;

    @FXML
    private TextField LabelDescription;

    @FXML
    private TextField LabelName;



    @FXML
    void handleAdd(ActionEvent event) {
        System.out.println(LabelName.getText());
        System.out.println(LabelDescription.getText());
        ConnectionClass one = new ConnectionClass();
        one.categoryInsert("KolorAP","234234","red",23);;
    }

    @FXML
    void handleCancel(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) ButtonCancel.getScene().getWindow();
        // do what you have to do
        stage.close();
        // System.exit(0);
    }


}
