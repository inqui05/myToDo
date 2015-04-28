package main.java.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.entity.Person;

/**
 * Created by Artsiom Tratsiuk
 */
public class PersonEditDialogController {
    @FXML
    private TextField secondNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField middleNameField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     */
    public void setPerson(Person person) {
        this.person = person;

        secondNameField.setText(person.getSecondName());
        firstNameField.setText(person.getFirstName());
        middleNameField.setText(person.getMiddleName());
        loginField.setText(person.getLogin());
        passwordField.setText(person.getPassword());
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
    private void handleOk() {
        if (isInputValid()) {
            person.setSecondName(secondNameField.getText());
            person.setFirstName(firstNameField.getText());
            person.setMiddleName(middleNameField.getText());
            person.setLogin(loginField.getText());
            person.setPassword(passwordField.getText());

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

        if (secondNameField.getText() == null || secondNameField.getText().length() == 0) {
            errorMessage += "Не введена фамилия!\n";
        }
        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Не введено имя!\n";
        }
        if (middleNameField.getText() == null || middleNameField.getText().length() == 0) {
            errorMessage += "Не введено отчество!\n";
        }
        if (loginField.getText() == null || loginField.getText().length() == 0) {
            errorMessage += "Не введен логин!\n";
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "Не введен пароль!\n";
        }
        if (errorMessage.length() == 0) {
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
