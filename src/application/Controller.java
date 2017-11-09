package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    ConnectionClass obj = new ConnectionClass();
    ObservableList<String> listCategory = FXCollections.observableArrayList(
            obj.selectFromDB("SELECT DISTINCT category.name FROM category"));

    ObservableList<String> listProject = FXCollections.observableArrayList(
            obj.selectFromDB("SELECT DISTINCT projects.name FROM projects"));



    @FXML
    private Label LabelNew;
    @FXML
    private ComboBox ComboBoxCategory;

    @FXML
    private ComboBox ComboBoxProject;

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
    void handleButtonNewCategory(ActionEvent event) {
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

    @FXML
    void handleButtonNewProject(ActionEvent event) {
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

    @FXML
    void handleButtonPause(ActionEvent event) {
        System.out.println("Pause");
    }

    @FXML
    void handleButtonStart(ActionEvent event) {
        System.out.println("Start");
    }

    @FXML
    void handleButtonStop(ActionEvent event) {
        System.out.println("Stop");
    }

    @FXML
    public void initialize() {
        ComboBoxCategory.setItems(listCategory);
        ComboBoxCategory.setValue("Java");

        ComboBoxProject.setItems(listProject);
        ComboBoxProject.setValue("Java");

        // TODO: 2017-11-09 zmienić na ostatnio otwierany projekt/kategorie

    }
}