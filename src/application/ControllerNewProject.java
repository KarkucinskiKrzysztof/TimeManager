package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ControllerNewProject {


    ConnectionClass obj = new ConnectionClass();
    ObservableList<String> listCategory = FXCollections.observableArrayList(
            obj.selectFromDB("SELECT DISTINCT category.name FROM category"));

    @FXML
    private ComboBox comboBoxCategoryInNewProject;


    @FXML
    private Button ButtonAdd;

    @FXML
    private Button ButtonCancel;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField LabelDescription;

    @FXML
    private TextField LabelName;

    @FXML
        public void initialize(){
            comboBoxCategoryInNewProject.setItems(listCategory);
            comboBoxCategoryInNewProject.setValue("Java");
    }

    @FXML
    void handleAdd(ActionEvent event) {
        System.out.println(LabelName.getText());
        System.out.println(LabelDescription.getText());
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
