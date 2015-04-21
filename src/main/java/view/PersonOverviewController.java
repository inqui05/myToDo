package main.java.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import main.java.DAO.DAOImpl.LogDAOImpl;
import main.java.DAO.DAOImpl.PersonDAOImpl;
import main.java.DAO.DAOImpl.TaskDaoImpl;
import main.java.DAO.LogDAO;
import main.java.DAO.PersonDAO;
import main.java.DAO.TaskDAO;
import main.java.MainApp;
import main.java.entity.Log;
import main.java.entity.Person;
import main.java.entity.Task;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by Artsiom Tratsiuk on 08.04.2015.
 */
public class PersonOverviewController {

    //Person.java
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, Number> personIdColumn;
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
    private TableColumn<Task, Number> taskIdColumn;
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
    private TableColumn<Log, Number> timeColumn;

    PersonDAO personDAO = new PersonDAOImpl();
    TaskDAO taskDAO = new TaskDaoImpl();
    LogDAO logDAO = new LogDAOImpl();

    // Reference to the main application.
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
    private void initialize()throws SQLException{
        // Initialize the person table with the six columns and the task table with the three columns and the log table with the two columns
        personIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        secondNameColumn.setCellValueFactory(cellData -> cellData.getValue().secondNameProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        middleNameColumn.setCellValueFactory(cellData -> cellData.getValue().middleNameProperty());
        loginColumn.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());

        taskIdColumn.setCellValueFactory(cellData -> cellData.getValue().taskIdProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        statusColumn.setCellFactory(column -> new TableCell<Task, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                } else {

                    setText(item);
                    if ("выполнена".equals(item)) {
                        setTextFill(Color.GREEN);
                    } else if ("не выполнена".equals(item)) {
                        setTextFill(Color.RED);
                    }
                }
            }
        });

        commentColumn.setCellValueFactory(cellData -> cellData.getValue().commentProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());


        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> { try{showTasks(newValue);}catch (SQLException esql){
                System.err.println("esql");
            }});

        taskTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> { try{showLogs(newValue);}catch (SQLException esql){
                System.err.println("esql");
            }});
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) throws SQLException {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    /**
     * Fills all text fields to show details about tasks
     *
     *  person the person or null
     */

    private void showTasks(Person person) throws SQLException {
        if (person != null) {
            taskTable.setItems(mainApp.getTaskData(person));
        }
        else{
            taskTable.setItems(null);
        }
    }

    private void showLogs(Task task) throws SQLException {
        if(task != null){
            logTable.setItems(mainApp.getLogData(task));
        }
        else{
            logTable.setItems(null);
        }
    }

    @FXML
    private void handleDeleteLog() throws SQLException{
        int selectedIndex = logTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            //ГОТОВО, НАДО ПРОТЕСТИРОВАТЬ
            logDAO.deleteLog(logTable.getItems().get(selectedIndex));
            logTable.getItems().remove(selectedIndex);
        } else {
            // if i will select nothing
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Заметка не выбрана!");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, выберите заметку в таблице!");

            /*Добавить если сделаю css или удалить
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Dialogs.class.getResource("DarkTheme.css").toExternalForm());*/

            alert.showAndWait();
        }
    }

    //удалять вместе с задачей и все логи
    @FXML
    private void handleDeleteTask() throws SQLException {
        int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            List<Log> logs = logDAO.getLogByTask(taskTable.getItems().get(selectedIndex));
            if(logs.size() > 0){
                for (Log log : logs) {
                    logDAO.deleteLog(log);
                }
            }
            taskDAO.deleteTask(taskTable.getItems().get(selectedIndex));
            taskTable.getItems().remove(selectedIndex);
        } else {
            // if i will select nothing
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Задача не выбрана!");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, выберите задачу в таблице!");

            /*Добавить если сделаю css или удалить
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Dialogs.class.getResource("DarkTheme.css").toExternalForm());*/

            alert.showAndWait();
        }
    }
    // ВРОДЕ НАСТРОИЛ, НО НАДО ПРОВЕРИТЬ!!!
    //при удалении человека удалять все его задачи и логи
    @FXML
    private void handleDeletePerson() throws SQLException {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            List<Task> tasks = taskDAO.getTasksByPerson(personTable.getItems().get(selectedIndex));
            for(Task task : tasks){
                List<Log> logs = logDAO.getLogByTask(task);
                for(Log log : logs){
                    logDAO.deleteLog(log);
                }
                taskDAO.deleteTask(task);
            }
            personDAO.deletePerson(personTable.getItems().get(selectedIndex));
            personTable.getItems().remove(selectedIndex);
        } else {
            // if i will select nothing
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Пользователь не выбран!");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, выберите пользователя в таблице!");

            /*Добавить если сделаю css или удалить
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(Dialogs.class.getResource("DarkTheme.css").toExternalForm());*/

            alert.showAndWait();
        }
    }
    // ДО СЮДА ВСЕ ИСПРАВЛЕНО
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() throws SQLException {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                //showTasks(selectedPerson);                               ИСПРАВИТЬ ПОСЛЕ НАПИСАНИЯ МЕТОДА showTasks() И showLogs(
            }
        } else {
            // Nothing selected.
            // if i'm selected nothing
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Пользователь не выбран!");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, выберите пользователя в таблице!");
            alert.showAndWait();
        }
    }
}
