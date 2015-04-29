package main.java.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.entity.Task;

import java.sql.SQLException;


/**
 * Created by Artsiom Tratsiuk
 */
public class TaskEditDialogController {
    @FXML
    private TextField nameOfStatusField;
    @FXML
    private ChoiceBox<String> statusChoiceBox;

    private Stage dialogStage;
    private Task task;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() throws SQLException {
        statusChoiceBox.setItems(FXCollections.observableArrayList("Выполнена", "Не выполнена"));
    }

    /**
     * Sets the stage of this dialog.

     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the task to be edited in the dialog.
     */
    public void setTask(Task task) throws SQLException {
        this.task = task;

        nameOfStatusField.setText(task.getName());
        statusChoiceBox.setValue(task.getStatus());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
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
