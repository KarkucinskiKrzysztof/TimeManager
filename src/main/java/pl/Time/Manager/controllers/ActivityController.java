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
    private ComboBox twoComboBox;
    @FXML
    private ComboBox oneComboBox;
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
        bindingsFilter();

    }

    private void bindingsTable() {
    this.activityTableView.setItems(this.activityModel.getActivityFxList());
    this.categoryColumn.setCellValueFactory(cellData->cellData.getValue().categoryFxProperty());
    this.projectColumn.setCellValueFactory(cellData->cellData.getValue().projectFxProperty());
    this.durationColumn.setCellValueFactory(cellData->cellData.getValue().durationProperty());
    this.dateColumn.setCellValueFactory(cellData->cellData.getValue().addDateProperty());
    this.timeColumn.setCellValueFactory(cellData->cellData.getValue().timeProperty());


    }

    private void bindingsFilter(){
        // inicjowanie comboboxów do filtrowania

        this.oneComboBox.setItems(activityModel.getCategoryFxList());
        this.twoComboBox.setItems(activityModel.getProjectFxList());

        // wybranie w comboboxie jakiejs opcji powoduje zapisanie jej w obiekcie categoryFxFilter lub projectFxFilter z modelu
        this.activityModel.categoryFxFilterProperty().bind(this.oneComboBox.valueProperty());
        this.activityModel.projectFxFilterProperty().bind(this.twoComboBox.valueProperty());




    }
    private void bindings() {
        //inicjowanie comboboxów do tworzenia aktywności
        this.choiceCategoryForActivityComboBox.setItems(activityModel.getCategoryFxList());
        this.choiceProjectForActivityComboBox.setItems(activityModel.getProjectFxList());

        // comboBoxy

        naszInt = new SimpleIntegerProperty();
        Bindings.bindBidirectional(durationTextField.textProperty(), naszInt, new NumberStringConverter());

        this.activityModel.getActivityFx().categoryFxProperty().bind(this.choiceCategoryForActivityComboBox.valueProperty());
        this.activityModel.getActivityFx().projectFxProperty().bind(this.choiceProjectForActivityComboBox.valueProperty());
        this.activityModel.getActivityFx().addDateProperty().bind(this.datePicker.valueProperty());
        this.activityModel.getActivityFx().durationProperty().bind(naszInt);
        this.activityModel.getActivityFx().timeProperty().bind(this.timeTextField.textProperty());
        this.activityModel.getActivityFx().discriptionProperty().bind(this.descriptionTextField.textProperty());



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
    @FXML
    void filterOnAction() {
        // spr System.out.println(this.activityModel.categoryFxFilterProperty().get());
    }
}
