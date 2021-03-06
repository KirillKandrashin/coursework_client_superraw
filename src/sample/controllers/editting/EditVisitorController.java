package sample.controllers.editting;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.*;
import sample.utils.AlertUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class EditVisitorController implements Initializable {
    public TextField txtVisitorFirstName;
    public TextField txtVisitorLastName;
    public TextField txtVisitorLibraryCard;
    public VisitorModel visitorModel;

    private Visitor visitor;

    public static void show(Visitor visitor) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
        txtVisitorFirstName.setText(visitor.getFirst_name());
        txtVisitorLastName.setText(visitor.getLast_name());
        txtVisitorLibraryCard.setText(visitor.getLibrary_card());
    }

    private boolean isInputValid() {
        if (String.join(" ", txtVisitorFirstName.getText()).length() == 0) {
            AlertUtil.buildDialog(null,"Вы не ввели данные в строку имени", Alert.AlertType.ERROR).showAndWait();
            return false;
        }
        if (String.join(" ", txtVisitorLastName.getText()).length() == 0) {
            AlertUtil.buildDialog(null,"Вы не ввели данные в строку фамилии", Alert.AlertType.ERROR).showAndWait();
            return false;
        }
        if (String.join(" ", txtVisitorLibraryCard.getText()).length() == 0) {
            AlertUtil.buildDialog(null,"Вы не ввели данные в строку №чит.билета", Alert.AlertType.ERROR).showAndWait();
            return false;
        }
        return true;
    }

    public void onSaveClick(ActionEvent actionEvent) {
        if (getVisitor().getId() != null) {
            this.visitorModel.edit(getVisitor());
            ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
        }
    }

    public void onCancelClick(ActionEvent actionEvent) {
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }


    public Visitor getVisitor(){
        Visitor result = new Visitor();
        if (isInputValid()) {

            String first_name = this.txtVisitorFirstName.getText();
            String last_name = this.txtVisitorLastName.getText();
            String library_card = this.txtVisitorLibraryCard.getText();
            result = new Visitor(visitor.getId(), first_name, last_name, library_card);
            }
        return result;
    }
}