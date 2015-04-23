package main.java.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.entity.Person;
import main.java.entity.Task;

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
    private Person person;
    private Task task;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
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
    public void setTask(Task task) {
        this.task = task;

        nameOfStatusField.setText(task.getName());

    }

}
