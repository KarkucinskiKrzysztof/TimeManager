package pl.Time.Manager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.Time.Manager.modelFx.CategoryFx;
import pl.Time.Manager.modelFx.ProjectFx;
import pl.Time.Manager.modelFx.ProjectModel;
import pl.Time.Manager.utils.DialogsUtils;
import pl.Time.Manager.utils.exceptions.ApplicationException;

public class ProjectController {

    @FXML
    private ComboBox<ProjectFx> choiceProjectComboBox;
    @FXML
    private ComboBox<CategoryFx> choiceCategoryForProject;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField descriptionTextBox;
    @FXML
    private TextField nameTextBox;
    @FXML
    private Button editProjectButton;
    @FXML
    private Button deleteProjectButton;
    @FXML
    private Button addProjectButton;

    private ProjectModel projectModel;

    @FXML
    public void initialize() {
        this.projectModel = new ProjectModel();
        try {
            this.projectModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        this.choiceProjectComboBox.setItems(this.projectModel.getProjectList());
        initBindings();
    }

    private void initBindings() {
        this.editProjectButton.disableProperty().bind(this.projectModel.projectProperty().isNull());
        this.deleteProjectButton.disableProperty().bind(this.projectModel.projectProperty().isNull());
        this.addProjectButton.disableProperty().bind(descriptionTextBox.textProperty().isEmpty().or(nameTextBox.textProperty().isEmpty()));
    }

    @FXML
    void onActionAddButton() {
        try {
            projectModel.saveProjectInDataBase(nameTextBox.getText(), descriptionTextBox.getText(), "sss", colorPicker.getValue().toString());
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        descriptionTextBox.clear();
        nameTextBox.clear();
    }


    @FXML
    void onActionDeleteButton() {
        try {
            this.projectModel.deleteProjectById();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }



    public void onActionComboBox() {
        this.projectModel.setProject(this.choiceProjectComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onActionEditButton() {

        String newProjectName = DialogsUtils.editDialog(this.projectModel.getProject().getName());
        if (newProjectName != null) {
            this.projectModel.getProject().setName(newProjectName);
            try {
                this.projectModel.updateProjectInDataBase();
            } catch (ApplicationException e) {
                DialogsUtils.errorDialog(e.getMessage());
            }
        }
    }
}
