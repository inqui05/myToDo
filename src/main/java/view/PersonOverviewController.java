package main.java.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
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

        /* АКТИВИРОВАТЬ ПОСЛЕ ИЗМЕНЕНИЯ МЕТОДА showTasks() И showLogs()
        // Clear details.
        showTasks(null);
        showLogs(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTasks(newValue));
        taskTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showLogs(newValue));*/
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
        taskTable.setItems(mainApp.getTaskData());
        logTable.setItems(mainApp.getLogData());
    }

    /**
     * Fills all text fields to show details about tasks
     *
     *  person the person or null                        РАЗОБРАТЬСЯ И СДЕЛАТЬ (3 страница)!!! Задачи и логи должны выводиться с привязкой к конкретному человеку!
     */

    /*private void showTasks(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.


            // TODO: We need a way to convert the birthday into a String!
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    private void showLogs(Task task) {
    }
    */

    @FXML
    private void handleDeleteLog() {
        int selectedIndex = logTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            //прописаться условия
            logTable.getItems().remove(selectedIndex);
        } else {
            // if i'm selected nothing
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

    //Разобраться и возможно удалять вместе с задачей и все логи
    @FXML
    private void handleDeleteTask() {
        int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();
        //int deleteTaskId = taskTable.getItems().get(selectedIndex).getId(); получить id задачи

        if (selectedIndex >= 0) {
            taskTable.getItems().remove(selectedIndex);
        } else {
            // if i'm selected nothing
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
    //Разобраться и возможно при удалении человека удалять все его задачи и логи
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // if i'm selected nothing
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

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
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
