package pl.Time.Manager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import pl.Time.Manager.modelFx.CategoryFx;
import pl.Time.Manager.modelFx.CategoryModel;
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
    @FXML
    private TableView<ProjectFx> projectTableView;
    @FXML
    private TableColumn<ProjectFx, String> nameColumn;
    @FXML
    private TableColumn<ProjectFx, String> categoryColumn;
    @FXML
    private TableColumn<ProjectFx, String> colorColumn;
    @FXML
    private TableColumn<ProjectFx, String> totalTimeColumn;

    private ProjectModel projectModel;
    private CategoryModel categoryModel;

    @FXML
    public void initialize() {
        this.projectModel = new ProjectModel();
        this.categoryModel = new CategoryModel();
        try {
            this.categoryModel.init();
            this.projectModel.init();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        bindings();
        bindingsTableView();
        bindingsButtons();
    }

    private void bindings() {

        this.choiceCategoryForProject.setItems(this.categoryModel.getCategoryList());
        this.choiceProjectComboBox.setItems(this.projectModel.getProjectList());

        // pola
        this.projectModel.projectProperty().get().nameProperty().bind(this.nameTextBox.textProperty());
        this.projectModel.projectProperty().get().descriptionProperty().bind(this.descriptionTextBox.textProperty());
        this.projectModel.projectProperty().get().categoryProperty().bind(this.choiceCategoryForProject.valueProperty().asString());
        this.projectModel.projectProperty().get().colorProperty().bind(this.colorPicker.valueProperty().asString());
    }

    private void bindingsTableView() {

        this.projectTableView.setItems(projectModel.getProjectList());
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        this.colorColumn.setCellValueFactory(cellData -> cellData.getValue().colorProperty());
        // this.totalTimeColumn.setCellValueFactory(cellData->cellData.getValue().xxxx);    // dodać wartości

        // edit
        this.nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.colorColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // select
        this.projectTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.projectModel.setProjectEdit(newValue);
        });
    }

    private void bindingsButtons() {

        this.addProjectButton.disableProperty().bind(descriptionTextBox.textProperty().isEmpty()
                .or(nameTextBox.textProperty().isEmpty())
                .or(choiceCategoryForProject.valueProperty().isNull())
                .or(colorPicker.valueProperty().isNull()));
        this.editProjectButton.disableProperty().bind(this.choiceProjectComboBox.valueProperty().isNull());
        this.deleteProjectButton.disableProperty().bind(this.choiceProjectComboBox.valueProperty().isNull());
    }

    @FXML
    void onActionAddButton() {
        try {
            projectModel.saveProjectInDataBase();
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

    public void onEditCommitName(TableColumn.CellEditEvent<ProjectFx, String> projectFxStringCellEditEvent) {
        // projectFxStringCellEditEvent.getNewValue(); watrość zmienionego pola po enter
        this.projectModel.getProjectEdit().setName(projectFxStringCellEditEvent.getNewValue());
        updateInDataBase();
    }

    public void onEditCommitCategory(TableColumn.CellEditEvent<ProjectFx, String> projectFxStringCellEditEvent) {
        this.projectModel.getProjectEdit().setCategory(projectFxStringCellEditEvent.getNewValue());
        updateInDataBase();
    }


    public void onEditCommitColor(TableColumn.CellEditEvent<ProjectFx, String> projectFxStringCellEditEvent) {
        this.projectModel.getProjectEdit().setColor(projectFxStringCellEditEvent.getNewValue());
        updateInDataBase();
    }

    private void updateInDataBase() {
        try {
            this.projectModel.saveProjectEditInDataBase();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }
}
