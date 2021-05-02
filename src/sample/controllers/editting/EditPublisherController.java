package sample.controllers.editting;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.Publisher;
import sample.models.PublisherModel;

import java.net.URL;
import java.util.ResourceBundle;

public class EditPublisherController implements Initializable {
    public TextField txtPublisherName;
    public PublisherModel publisherModel;

    private Publisher publisher;

    public static void show() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
        txtPublisherName.setText(publisher.getName());
    }

    private boolean isInputValid() {
        if (txtPublisherName.getText() == null || txtPublisherName.getText().length() == 0) {
            return false;
        }
        return true;
    }

    public void onSaveClick(ActionEvent actionEvent) {
        if (getPublisher().getName() != null) {
            this.publisherModel.edit(getPublisher());
        }
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void onCancelClick(ActionEvent actionEvent) {
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }


    public Publisher getPublisher(){
        Publisher result = new Publisher();
        if (isInputValid()) {

            String name = this.txtPublisherName.getText();

            result = new Publisher(publisher.getId(), name);
        }
        return result;
    }
}