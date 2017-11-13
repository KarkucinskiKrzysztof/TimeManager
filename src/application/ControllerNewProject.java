package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ControllerNewProject {


    @FXML
    private ComboBox comboBoxCategoryInNewProject;

    @FXML
    private ComboBox comboBoxDurationInNewProject;

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


    ConnectionClass obj = new ConnectionClass();
    ObservableList<String> listCategoryObserwer = FXCollections.observableArrayList(
            obj.selectFromDB("SELECT DISTINCT category.name FROM category"));

//    private ArrayList<String> nne = new ArrayList<>(obj.selectFromDB("SELECT DISTINCT category.name FROM category"));
//    ObservableList<String> listDuration = FXCollections.
    @FXML
    public void initialize() {
        comboBoxCategoryInNewProject.setItems(listCategoryObserwer);
//        comboBoxCategoryInNewProject.getItems().addAll(listCategoryObserwer); ta linia tez działa i przekazuje parametry do listy
        comboBoxCategoryInNewProject.setValue("Java");


        comboBoxDurationInNewProject.setValue("25 min");
        comboBoxDurationInNewProject.getItems().addAll(
                "30 min", "35 min", "40 min", "45 min", "45 min", "50 min", "55 min", "60 min");
    }

    @FXML
    void handleAdd(ActionEvent event) {
        System.out.println(LabelName.getText());
        System.out.println(LabelDescription.getText());
    }

    @FXML
    public void handleCancel() {                    //ActionEvent event jako parametr wcale nie jest potrzebne !
        // get a handle to the stage
        Stage stage = (Stage) ButtonCancel.getScene().getWindow();
        // do what you have to do
        stage.close();
        // System.exit(0);
    }
    @FXML
    void onMouseEntered() {
        System.out.println("Najazd");
    }
}
