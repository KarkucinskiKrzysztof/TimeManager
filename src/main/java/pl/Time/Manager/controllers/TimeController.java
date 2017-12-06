package pl.Time.Manager.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Calendar;
import java.util.Date;


public class TimeController {


    private static final Integer STARTTIME = 222;
    private Timeline timeline;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);


    @FXML
    private Label dynamicTimeDisplayLabel;
    @FXML
    private Button pauseButton;
    @FXML
    private Button startButton;
    @FXML
    private ComboBox<?> categoryComboBox;
    @FXML
    private ComboBox<?> projectComboBox;
    @FXML
    private Button stopButton;
    @FXML
    private TextField desctiptionTextField;

    @FXML
    public void initialize() {

        dynamicTimeDisplayLabel.textProperty().bind(timeSeconds.asString());
        dynamicTimeDisplayLabel.setTextFill(Color.RED);
        dynamicTimeDisplayLabel.setStyle("-fx-font-size: 4em;");

        startButton.setText("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if (timeline != null) {
                    timeline.stop();
                }
                timeSeconds.set(STARTTIME);
                timeline = new Timeline();
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(STARTTIME + 1),
                                new KeyValue(timeSeconds, 0)));
                timeline.playFromStart();
            }
        });


    }

    @FXML
    void pauseButtonOnAction() {

    }

    @FXML
    void startButtonOnAction() {

    }

    @FXML
    void stopButtonOnAction() {

    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
