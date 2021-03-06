package pl.Time.Manager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.Time.Manager.modelFx.*;
import pl.Time.Manager.utils.DialogsUtils;
import pl.Time.Manager.utils.exceptions.ApplicationException;
import java.time.LocalDate;

public class SearchController {

    @FXML
    private ComboBox projectFilterComboBox;
    @FXML
    private ComboBox categoryFilterComboBox;
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


    @FXML
    public void initialize(){
        activityModel = new ActivityModel();
        try {
            this.activityModel.init();
            this.activityModel.initActivityFxList();
        } catch (ApplicationException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
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
        this.descriptionColumn.setCellValueFactory((cellData->cellData.getValue().discriptionProperty()));
    }

    private void bindingsFilter(){

        this.categoryFilterComboBox.setItems(activityModel.getCategoryFxList());
        this.projectFilterComboBox.setItems(activityModel.getProjectFxList());


        this.activityModel.categoryFxFilterProperty().bind(this.categoryFilterComboBox.valueProperty());
        this.activityModel.projectFxFilterProperty().bind(this.projectFilterComboBox.valueProperty());
    }

    @FXML
    void filterOnAction() {
        // spr System.out.println(this.activityModel.categoryFxFilterProperty().get());
        this.activityModel.filterActivityList();
    }
    @FXML
    void clearCategoryFilterOnAction() {
        this.categoryFilterComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    void clearProjectFilterOnAction() {
        this.projectFilterComboBox.getSelectionModel().clearSelection();
    }
}
