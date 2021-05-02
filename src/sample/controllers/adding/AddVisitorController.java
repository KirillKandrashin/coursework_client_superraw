package sample.controllers.adding;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import sample.models.Visitor;
import sample.models.VisitorModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AddVisitorController implements Initializable{
    public TextField txtVisitorFirstName;
    public TextField txtVisitorLastName;
    public TextField txtVisitorLibraryCard;
    public VisitorModel visitorModel;

    public static void show() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onSaveClick(ActionEvent actionEvent) {
        if (!getVisitor().equals("")) {
            this.visitorModel.add(getVisitor());
        }
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void onCancelClick(ActionEvent actionEvent) {
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    private boolean isInputValid() {
        if (txtVisitorFirstName.getText() == null || txtVisitorFirstName.getText().length() == 0) {
            return false;
        }
        if (txtVisitorLastName.getText() == null || txtVisitorLastName.getText().length() == 0) {
            return false;
        }
        if (txtVisitorLibraryCard.getText() == null || txtVisitorLibraryCard.getText().length() == 0) {
            return false;
        }
        return true;
    }


    public String getVisitor(){
        String result;

        if (isInputValid()) {
            String first_name = this.txtVisitorFirstName.getText();
            String last_name = this.txtVisitorLastName.getText();
            String library_card = this.txtVisitorLibraryCard.getText();
            result = new Visitor(first_name, last_name, library_card).toJson();
        }else{
            result = "";
        }
        return result;
    }
}