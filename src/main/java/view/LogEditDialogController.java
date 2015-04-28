package main.java.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.entity.Log;

import java.sql.SQLException;

/**
 * Created by Artsiom Tratsiuk
 */
public class LogEditDialogController {

    @FXML
    private TextField commentField;
    @FXML
    private TextField timeField;

    private Stage dialogStage;
    private Log log;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() throws SQLException {
    }

    /**
     * Sets the stage of this dialog.
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the log to be edited in the dialog.
     */
    public void setLog(Log log) throws SQLException {
        this.log = log;

        commentField.setText(log.getComment());
        timeField.setText(String.valueOf(log.getTime()));
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
            log.setComment(commentField.getText());
            isInteger(timeField.getText());
            log.setTime(Integer.parseInt(timeField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }
    /**
     * Checked number when the user clicks ok.
     */
    private boolean isInteger(String value){
        try{
            Integer.parseInt(value);
        }catch(NumberFormatException exc)
        {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Не верно указано затраченное время!");
            alert.setHeaderText(null);
            alert.setContentText("Введите целое число затраченного времени!\nЗатраченное время не может превышать 2147483647 (245000 лет)!");
            alert.showAndWait();
            return false;
        }
        return true;
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

        if (commentField.getText() == null || commentField.getText().length() == 0) {
            errorMessage += "Не введен комментарий!\n";
        }
        if (timeField.getText() == null || timeField.getText().length() == 0) {
            errorMessage += "Не введено время выполнения задачи!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } if (errorMessage.length() == 0) {
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
