package main.java.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.java.MainApp;
import main.java.entity.Log;
import main.java.entity.Person;
import main.java.entity.Task;

/**
 * Created by Artsiom Tratsiuk on 08.04.2015.
 */
public class PersonOverviewController {

    //Person.java
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, Integer> personIdColumn;
    @FXML
    private TableColumn<Person, String> secondNameColumn;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> middleNameColumn;
    @FXML
    private TableColumn<Person, String> loginColumn;
    @FXML
    private TableColumn<Person, String> passwordColumn;

    //Task.java
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, Integer> taskIdColumn;
    @FXML
    private TableColumn<Task, String> nameColumn;
    @FXML
    private TableColumn<Task, String> statusColumn;

    //Log.java
    @FXML
    private TableView<Log> logTable;
    @FXML
    private TableColumn<Log, String> commentColumn;
    @FXML
    private TableColumn<Log, Integer> timeColumn;

    // Reference to the main application./////////////////////////////////////////////////////////////////////////////
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        //personIdColumn.setCellValueFactory(cellData -> cellData.getValue().getId());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        //lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        //personTable.setItems(mainApp.getPersonData());
    }
}
