package main.java;

/**
 * Created by Artsiom Tratsiuk
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.DAO.DAOImpl.LogDAOImpl;
import main.java.DAO.DAOImpl.PersonDAOImpl;
import main.java.DAO.DAOImpl.TaskDaoImpl;
import main.java.DAO.LogDAO;
import main.java.DAO.PersonDAO;
import main.java.DAO.TaskDAO;
import main.java.entity.Log;
import main.java.entity.Person;
import main.java.entity.Task;
import main.java.util.Factory;
import main.java.util.NewDB;
import main.java.view.LogEditDialogController;
import main.java.view.PersonEditDialogController;
import main.java.view.PersonOverviewController;
import main.java.view.TaskEditDialogController;

import java.io.IOException;
import java.sql.*;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws SQLException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Менеджер задач");

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
    public void showPersonOverview() throws SQLException {
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
    PersonDAO personDAO = new PersonDAOImpl();
    TaskDAO taskDAO = new TaskDaoImpl();
    LogDAO logDAO = new LogDAOImpl();

    /*
    *Read lists of People/Tasks/Logs from the DB
    */
    public ObservableList<Person> getPersonData() throws SQLException {
        if(!personData.isEmpty())personData.clear();
        personData = FXCollections.observableList(personDAO.getAllPeople());
        return personData;
    }
    public ObservableList<Task> getTaskData(Person person) throws SQLException {
        if(!taskData.isEmpty())taskData.clear();
        taskData = FXCollections.observableList(taskDAO.getTasksByPerson(person));
        return taskData;
    }
    public ObservableList<Log> getLogData(Task task) throws SQLException {
        if(!logData.isEmpty()) logData.clear();
        logData =  FXCollections.observableList(logDAO.getLogByTask(task));
        return logData;
    }
                    //Person
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Пользователь");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
                     //Task
    /**
     * Opens a dialog to edit details for the task. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     */
    public boolean showTaskEditDialog(Task task) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TaskEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Задача");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the task into the controller.
            TaskEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTask(task);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

                     //Log
    /**
     * Opens a dialog to edit details for the log. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     */
    public boolean showLogEditDialog(Log log) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/LogEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Комментарий");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the log into the controller.
            LogEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLog(log);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        /*
        *Check and create DB if it doesn't exist
         */
        Connection conn = null;
        Statement stmt = null;

        try{
            //Register JDBC driver
            Class.forName(NewDB.getJDBC_DRIVER());

            //Open a connection and execute a query
            conn = DriverManager.getConnection(NewDB.getDB_URL(), NewDB.getUSER(), NewDB.getPASS());
            stmt = conn.createStatement();

            String nameOfDB = "MYTODO2015";

        if(NewDB.checkDBExists(nameOfDB)){
            stmt.executeUpdate("CREATE DATABASE MYTODO2015 CHARACTER SET UTF8");
        }

        }catch(SQLException se){
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){/*NOP*/
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

        Factory factory = Factory.getInstance();
        launch(args);

    }
}
