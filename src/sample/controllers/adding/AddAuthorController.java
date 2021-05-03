package sample.controllers.adding;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.Author;
import sample.models.AuthorModel;
import sample.utils.AlertUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAuthorController implements Initializable {
    public TextField txtAuthorName;

    public AuthorModel authorModel;

    public static void show() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private boolean isInputValid() {
        if (txtAuthorName.getText() == null || txtAuthorName.getText().length() == 0) {
            AlertUtil.buildDialog(null,"Вы не ввели необходимые данные в строку автор", Alert.AlertType.ERROR).showAndWait();
            return false;
        }
        return true;
    }

    public void onSaveClick(ActionEvent actionEvent) {
        if (!getAuthor().equals("")) {
            this.authorModel.add(getAuthor());
            ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
        }
    }

    public void onCancelClick(ActionEvent actionEvent) {
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }


    public String getAuthor(){
        String result = new String();
        if (isInputValid()) {

            String name = this.txtAuthorName.getText();

            result = new Author(name).toJson();
        }else{
            result = "";
        }
        return result;
    }
}
