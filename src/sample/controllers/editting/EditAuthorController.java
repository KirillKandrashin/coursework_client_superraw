package sample.controllers.editting;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.Author;
import sample.models.AuthorModel;

import java.net.URL;
import java.util.ResourceBundle;

public class EditAuthorController implements Initializable {
    public TextField txtAuthorName;
    public AuthorModel authorModel;

    private Author author;

    public static void show() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setAuthor(Author author) {
        this.author = author;
        txtAuthorName.setText(author.getName());
    }

    private boolean isInputValid() {
        if (txtAuthorName.getText() == null || txtAuthorName.getText().length() == 0) {
            return false;
        }
        return true;
    }

    public void onSaveClick(ActionEvent actionEvent) {
        if (getAuthor().getName() != null) {
            this.authorModel.edit(getAuthor());
        }
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void onCancelClick(ActionEvent actionEvent) {
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }


    public Author getAuthor(){
        Author result = new Author();
        if (isInputValid()) {

            String name = this.txtAuthorName.getText();

            result = new Author(author.getId(), name);
        }
        return result;
    }
}
