package pl.Time.Manager.controllers;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.Time.Manager.modelFx.CategoryFx;
import pl.Time.Manager.modelFx.ProjectFx;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class TimeController {

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


//
//    @FXML
//    public void initialize(){
//
//        dynamicTimeDisplayLabel.textProperty().bind(dynamicTimeProperty);
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            dynamicTimeProperty.set(sdf.format(new Date()));
//                        }
//                    });
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException ex) {
//                        break;
//                    }
//                }
//            }
//        });
//        t.setName("Runnable Time Updater");
//        t.setDaemon(true);
//        t.start();
//    }
    @FXML
    void pauseButtonOnAction() {

    }

    @FXML
    void startButtonOnAction() {

    }

    @FXML
    void stopButtonOnAction() {

    }

}
