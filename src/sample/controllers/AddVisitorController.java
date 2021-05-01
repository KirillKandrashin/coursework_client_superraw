package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.Author;
import sample.models.Visitor;
import sample.models.VisitorModel;
import sample.models.Publisher;
import sample.utils.ApiSessionAuthor;
import sample.utils.ApiSessionPublisher;
import sample.utils.ApiSessionSection;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AddVisitorController implements Initializable{
    public TextField txtVisitorFirstName;
    public TextField txtVisitorLastName;
    public TextField txtVisitorLibraryCard;
    public VisitorModel visitorModel;
    private boolean modalResult = false;

    public static void show() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onSaveClick(ActionEvent actionEvent) {
        this.visitorModel.add(getVisitor());
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void onCancelClick(ActionEvent actionEvent) {
        this.modalResult = false;
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public Boolean getModalResult() {
        return modalResult;
    }


    public String getVisitor(){
        String result = new String();

        String first_name = this.txtVisitorFirstName.getText();
        String last_name = this.txtVisitorLastName.getText();
        String library_card = this.txtVisitorLibraryCard.getText();
        result = new Visitor(first_name, last_name, library_card).toJson();
        return result;
    }
}