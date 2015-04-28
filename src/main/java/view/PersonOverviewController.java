package main.java.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
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
 * Created by Artsiom Tratsiuk
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
        personTable.setPlaceholder(new Text("В данный момент в программе отсутствуют пользователи"));
        taskTable.setPlaceholder(new Text("Пользователь не выбран либо выбранному пользователю не назначены задачи!"));
        logTable.setPlaceholder(new Text("Задача не выбрана либо к выбранной задаче комментарии отсутствуют!"));

        // Initialize the person table with the six columns
        personIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        secondNameColumn.setCellValueFactory(cellData -> cellData.getValue().secondNameProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        middleNameColumn.setCellValueFactory(cellData -> cellData.getValue().middleNameProperty());
        loginColumn.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());

        // Initialize the task table with the three columns
        taskIdColumn.setCellValueFactory(cellData -> cellData.getValue().taskIdProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        //Initialize the log table with the two columns
        commentColumn.setCellValueFactory(cellData -> cellData.getValue().commentProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());


        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> { try{showTasks(newValue);}catch (SQLException esql){
                System.err.println("esql");
            }});

        // Listen for selection changes and show the task details when changed.
        taskTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> { try{showLogs(newValue);}catch (SQLException esql){
                System.err.println("esql");
            }});
    }

    /**
     * Is called by the main application to give a reference back to itself.
     */
    public void setMainApp(MainApp mainApp) throws SQLException {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    /**
     * Fills all text fields to show details about tasks
     */

    private void showTasks(Person person) throws SQLException {
        if (person != null) {
            taskTable.setItems(mainApp.getTaskData(person));
        }
        else{
            taskTable.setItems(null);
        }
    }

    /**
     * Fills all text fields to show details about logs
     */
    private void showLogs(Task task) throws SQLException {
        if(task != null){
            logTable.setItems(mainApp.getLogData(task));
        }
        else{
            logTable.setItems(null);
        }
    }

    /*
    *Delete the selected log when the user push "Удалить"
    */
    @FXML
    private void handleDeleteLog() throws SQLException{
        int selectedIndex = logTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            logDAO.deleteLog(logTable.getItems().get(selectedIndex));
            logTable.getItems().remove(selectedIndex);
        } else {
            // if i selected nothing
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Заметка не выбрана!");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, выберите заметку в таблице!");

            alert.showAndWait();
        }
    }

    /*
    *Delete the selected task and its logs when the user push "Удалить"
    */
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
            // if i selected nothing
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Задача не выбрана!");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, выберите задачу в таблице!");

            alert.showAndWait();
        }
    }

    /*
    *Delete the selected person and his tasks and logs when the user push "Удалить"
    */
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
            // if i selected nothing
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Пользователь не выбран!");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, выберите пользователя в таблице!");

            alert.showAndWait();
        }
    }

                        //PERSON
    /**
     * Called when the user clicks the button "Добавить". Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() throws SQLException {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            personDAO.addPerson(tempPerson);
            personTable.setItems(mainApp.getPersonData());
        }
    }

    /**
     * Called when the user clicks the button "Изменить". Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() throws SQLException {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                personDAO.updatePerson(selectedPerson);
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Пользователь не выбран!");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, выберите пользователя в таблице!");
            alert.showAndWait();
        }
    }

                        //TASK
    /**
     * Called when the user clicks the button "Добавить". Opens a dialog to edit
     * details for a new task.
     */
    @FXML
    private void handleNewTask() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if(null == selectedPerson) {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Пользователь не выбран!");
            alert.setHeaderText(null);
            alert.setContentText("Выберите пользователя, которому необходимо назначить задачу!");
            alert.showAndWait();
        }
        else{
            Task tempTask = new Task();
            boolean okClicked = mainApp.showTaskEditDialog(tempTask);

            if (okClicked) {
                try {
                    tempTask.setPerson(selectedPerson);
                    taskDAO.addTask(tempTask);
                    taskTable.setItems(mainApp.getTaskData(selectedPerson));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Called when the user clicks the button "Изменить". Opens a dialog to edit
     * details for the selected task.
     */
    @FXML
    private void handleEditTask() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if(null == selectedPerson || null == selectedTask) {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Пользователь или задача не выбраны!");
            alert.setHeaderText(null);
            alert.setContentText("Выберите пользователя и задачу которую необходимо редактировать!");
            alert.showAndWait();
        }


        if (selectedTask != null) {
            boolean okClicked = mainApp.showTaskEditDialog(selectedTask);
            if (okClicked) {
                try {
                    taskDAO.updateTask(selectedTask);
                    taskTable.setItems(mainApp.getTaskData(selectedPerson));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

                        //LOG
    /**
     * Called when the user clicks the button "Добавить". Opens a dialog to edit
     * details for a new log.
     */
    @FXML
    private void handleNewLog() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if(null == selectedPerson || null == selectedTask) {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Задача не выбрана!");
            alert.setHeaderText(null);
            alert.setContentText("Выберите пользователя и задачу, к которой необходимо добавить комментарий");
            alert.showAndWait();
        }
        else{
            Log tempLog = new Log();
            boolean okClicked = mainApp.showLogEditDialog(tempLog);

            if (okClicked) {
                try {
                    tempLog.setTask(selectedTask);
                    logDAO.addLog(tempLog);
                    logTable.setItems(mainApp.getLogData(selectedTask));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Called when the user clicks the button "Изменить". Opens a dialog to edit
     * details for the selected log.
     */
    @FXML
    private void handleEditLog() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        Log selectedLog = logTable.getSelectionModel().getSelectedItem();
        if(null == selectedPerson || null == selectedTask || null == selectedLog) {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Комментарий не выбран!");
            alert.setHeaderText(null);
            alert.setContentText("Выберите комментарий, который необходимо редактировать!");
            alert.showAndWait();
        }
        if (selectedLog != null) {
            boolean okClicked = mainApp.showLogEditDialog(selectedLog);
            if (okClicked) {
                try {
                    logDAO.updateLog(selectedLog);
                    logTable.setItems(mainApp.getLogData(selectedTask));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
