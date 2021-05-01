package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoController implements Initializable {
    private boolean modalResult = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public static void show() {
    }

    public void onCloseClick(ActionEvent actionEvent) {
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public Boolean getModalResult() {
        this.modalResult = false;
        return modalResult;
    }
}
