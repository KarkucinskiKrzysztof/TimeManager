package pl.Time.Manager.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;
import pl.Time.Manager.modelFx.*;
import pl.Time.Manager.utils.DialogsUtils;
import pl.Time.Manager.utils.exceptions.ApplicationException;
import java.time.LocalDate;

public class ActivityController {
    @FXML
    private ComboBox<CategoryFx> choiceCategoryForActivityComboBox;
    @FXML
    private ComboBox<ProjectFx> choiceProjectForActivityComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField durationTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField timeTextField;
    @FXML
    private Button addButton;
    @FXML
    private TableView<ActivityFx> activityTableView;
    @FXML
    private TableColumn<ActivityFx, CategoryFx> categoryColumn;
    @FXML
    private TableColumn<ActivityFx, ProjectFx> projectColumn;
    @FXML
    private TableColumn<ActivityFx, Number> durationColumn;
    @FXML
    private TableColumn<ActivityFx, String> descriptionColumn;
    @FXML
    private TableColumn<ActivityFx, LocalDate> dateColumn;
    @FXML
    private TableColumn<ActivityFx, String> timeColumn;

    private ActivityModel activityModel;
    private ProjectModel projectModel;
    private CategoryModel categoryModel;
    private SimpleIntegerProperty naszInt;
    @FXML
    public void initialize(){
        activityModel = new ActivityModel();
        try {
            this.activityModel.init();
            this.activityModel.initActivityFxList();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        bindings();
        bindingsTable();
    }

    private void bindingsTable() {
    activityTableView.setItems(this.activityModel.getActivityFxList());
    categoryColumn.setCellValueFactory(cellData->cellData.getValue().categoryFxProperty());
    projectColumn.setCellValueFactory(cellData->cellData.getValue().projectFxProperty());
    durationColumn.setCellValueFactory(cellData->cellData.getValue().durationProperty());
    dateColumn.setCellValueFactory(cellData->cellData.getValue().addDateProperty());
    timeColumn.setCellValueFactory(cellData->cellData.getValue().timeProperty());
    descriptionColumn.setCellValueFactory((cellData->cellData.getValue().discriptionProperty()));
    }


    private void bindings() {
        //inicjowanie comboboxów do tworzenia aktywności
        this.choiceCategoryForActivityComboBox.setItems(activityModel.getCategoryFxList());
        this.choiceProjectForActivityComboBox.setItems(activityModel.getProjectFxList());

        // comboBoxy
        this.naszInt = new SimpleIntegerProperty();
        Bindings.bindBidirectional(durationTextField.textProperty(), naszInt, new NumberStringConverter());

        this.activityModel.getActivityFx().categoryFxProperty().bind(this.choiceCategoryForActivityComboBox.valueProperty());
        this.activityModel.getActivityFx().projectFxProperty().bind(this.choiceProjectForActivityComboBox.valueProperty());
        this.activityModel.getActivityFx().addDateProperty().bind(this.datePicker.valueProperty());
        this.activityModel.getActivityFx().durationProperty().bind(naszInt);
        this.activityModel.getActivityFx().timeProperty().bind(this.timeTextField.textProperty());
        this.activityModel.getActivityFx().discriptionProperty().bind(this.descriptionTextField.textProperty());

        this.addButton.disableProperty().bind(timeTextField.textProperty().isEmpty()
                .or(descriptionTextField.textProperty().isEmpty())
                .or(choiceCategoryForActivityComboBox.valueProperty().isNull())
                .or(choiceProjectForActivityComboBox.valueProperty().isNull())
                .or(datePicker.valueProperty().isNull())
                .or(durationTextField.textProperty().isEmpty()));


    }

    @FXML
    void addActivityOnAction() {
        try {
            this.activityModel.saveActivityInDataBase();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        //System.out.println(this.activityModel.getActivityFx().toString());
    }
    @FXML
    void choiceCategoryForActivityComboBoxOnAction() {
    }
    @FXML
    void choiceProjectForActivityComboBoxOnAction() {
    }
    @FXML
    void datePickerOnAction() {
    }
}
