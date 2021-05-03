package sample.controllers.adding;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.Author;
import sample.models.Publisher;
import sample.models.PublisherModel;
import sample.models.Publisher;
import sample.utils.AlertUtil;
import sample.utils.ApiSessionAuthor;
import sample.utils.ApiSessionPublisher;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AddPublisherController implements Initializable {
    public TextField txtPublisherName;

    public PublisherModel publisherModel;

    public static void show() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private boolean isInputValid() {
        if (String.join(" ", txtPublisherName.getText()).length() == 0) {
            AlertUtil.buildDialog(null,"Вы не ввели необходимые данные ", Alert.AlertType.ERROR).showAndWait();
            return false;
        }
        return true;
    }

    public void onSaveClick(ActionEvent actionEvent) {
        if (!getPublisher().equals("")) {
            this.publisherModel.add(getPublisher());
            ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
        }
    }

    public void onCancelClick(ActionEvent actionEvent) {
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }


    public String getPublisher(){
        String result = new String();
        if (isInputValid()) {

            String name = this.txtPublisherName.getText();

            result = new Publisher(name).toJson();
        }else{
            result = "";
        }
        return result;
    }
}
