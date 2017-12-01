package pl.Time.Manager.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import pl.Time.Manager.modelFx.CategoryFx;
import pl.Time.Manager.modelFx.ProjectFx;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

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
    public void initialize(){

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
                        new KeyFrame(Duration.seconds(STARTTIME+1),
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



//    Task dynamicTimeTask = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//                updateMessage(sdf.format(removeTime(new Date())));
//                Thread.sleep(100);
//
//
//                return null;
//            }
//        };
//
//        dynamicTimeDisplayLabel.textProperty().bind(dynamicTimeTask.messageProperty());
//        Thread t2 = new Thread(dynamicTimeTask);
//        t2.setName("Tesk Time Updater");
//        t2.setDaemon(true);
//        t2.start();