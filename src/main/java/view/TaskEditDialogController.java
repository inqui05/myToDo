package main.java.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.DAO.PersonDAO;
import main.java.MainApp;
import main.java.entity.Person;
import main.java.entity.Task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Artsiom Tratsiuk on 23.04.2015.
 */
public class TaskEditDialogController {
    @FXML
    private TextField nameOfStatusField;
    @FXML
    private ChoiceBox statusChoiceBox;
    @FXML
    private ChoiceBox personChoiceBox;

    private Stage dialogStage;
    private PersonDAO personDAO;
    private Task task;
    private boolean okClicked = false;
    private MainApp mainApp;
    PersonOverviewController pc = new PersonOverviewController();

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() throws SQLException {
        personChoiceBox.setItems(FXCollections.observableArrayList());
        statusChoiceBox.setItems(FXCollections.observableArrayList("Выполнена", "Не выполнена"));
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param task
     */
    public void setTask(Task task) throws SQLException {
        this.task = task;

        nameOfStatusField.setText(task.getName());
        statusChoiceBox.setValue(task.getStatus());
        personChoiceBox.setValue(task.getPerson());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() throws SQLException {
        if (isInputValid()) {
            task.setName(nameOfStatusField.getText());
            if (null != statusChoiceBox.getValue()) {
                task.setStatus(String.valueOf(statusChoiceBox.getValue()));
            } else {
                task.setStatus("Не выполнена");
            }
            /*if (null != personChoiceBox.getValue()) {
                task.setPerson((Person) statusChoiceBox.getValue());
            } else {
                task.setPerson(personDAO.getPersonById(2));
            }*/

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameOfStatusField.getText() == null || nameOfStatusField.getText().length() == 0) {
            errorMessage += "Не введено название задачи!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        }         if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Не введены все данные!");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, введите все данные!");
            alert.showAndWait();
            return false;
        }
    }
}
