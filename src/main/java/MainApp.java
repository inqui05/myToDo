package main.java;
/**
 * Created by Artsiom Tratsiuk on 08.04.2015.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.entity.Log;
import main.java.entity.Person;
import main.java.entity.Task;
import main.java.view.PersonOverviewController;

import java.io.IOException;

public class MainApp extends Application {   //  http://code.makery.ch/library/javafx-8-tutorial/ru/part2/

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Менеджер задач ToDo");

        initRootLayout();

        showPersonOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private ObservableList<Person> personData = FXCollections.observableArrayList();
    private ObservableList<Task> taskData = FXCollections.observableArrayList();
    private ObservableList<Log> logData = FXCollections.observableArrayList();

    public ObservableList<Person> getPersonData() {
        return personData;
    }
    public ObservableList<Task> getTaskData() {
        return taskData;
    }
    public ObservableList<Log> getLogData() {
        return logData;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
