package pl.Time.Manager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class TopMenuButtonsController {

    public static final String CATEGORIES_FXML = "/fxml/Categories.fxml";
    public static final String POMODORO_FXML = "/fxml/Pomodoro.fxml";
    public static final String ACTIVITY_FXML = "/fxml/Activity.fxml";
    public static final String STATISTIC_FXML = "/fxml/Statistic.fxml";
    public static final String PROJECTS_FXML = "/fxml/Projects.fxml";
    public static final String TIMER_FXML = "/fxml/Timer.fxml";
    public static final String SEARCH_FXML = "/fxml/Search.fxml";
    private MainController mainController;

    @FXML
    private ToggleGroup ToggleButtons;

    @FXML
    void openActivity() {
        mainController.setCenter(ACTIVITY_FXML);
    }

    @FXML
    void openCategories() {
        mainController.setCenter(CATEGORIES_FXML);
    }

    @FXML
    void openPomodoro() {
        mainController.setCenter(POMODORO_FXML);
    }

    @FXML
    void openProjects() {
        mainController.setCenter(PROJECTS_FXML);
    }

    @FXML
    void openStatistics() {
        mainController.setCenter(STATISTIC_FXML);
    }

    @FXML
    void openTimer() {
        mainController.setCenter(TIMER_FXML);
    }

    @FXML
    void openSearch(){
        mainController.setCenter(SEARCH_FXML);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
