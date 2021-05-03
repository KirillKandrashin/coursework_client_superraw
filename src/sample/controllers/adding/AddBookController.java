package sample.controllers.adding;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.*;
import sample.utils.AlertUtil;
import sample.utils.ApiSessionAuthor;
import sample.utils.ApiSessionPublisher;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {
    public TextField txtBookTitle;
    public TextField txtBookAuthor;
    public TextField txtBookPublisher;
    public TextField txtBookGenre;
    public TextField txtBookType;
    public TextField txtBookNumbers_of_copies;
    public BookModel bookModel;

    public static void show() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private boolean isInputValid() {
        if (txtBookTitle.getText() == null || txtBookTitle.getText().length() == 0) {
            AlertUtil.buildDialog(null,"Вы не ввели необходимые данные в строку название", Alert.AlertType.ERROR).showAndWait();
            return false;
        }
        if (txtBookAuthor.getText() == null || txtBookAuthor.getText().length() == 0) {
            AlertUtil.buildDialog(null,"Вы не ввели необходимые данные в строку авторство", Alert.AlertType.ERROR).showAndWait();
            return false;
        }
        if (txtBookPublisher.getText() == null || txtBookPublisher.getText().length() == 0) {
            AlertUtil.buildDialog(null,"Вы не ввели необходимые данные в строку издательтво", Alert.AlertType.ERROR).showAndWait();
            return false;
        }
        if (txtBookGenre.getText() == null || txtBookGenre.getText().length() == 0) {
            AlertUtil.buildDialog(null,"Вы не ввели необходимые данные в строку жанр издания", Alert.AlertType.ERROR).showAndWait();
            return false;
        }
        if (txtBookType.getText() == null || txtBookType.getText().length() == 0) {
            AlertUtil.buildDialog(null,"Вы не ввели необходимые данные в строку тип издания", Alert.AlertType.ERROR).showAndWait();
            return false;
        }
        try{
            Integer.parseInt(this.txtBookNumbers_of_copies.getText());
        } catch (NumberFormatException e) {
            AlertUtil.buildDialog(null,"Вы ввели неверные данные в строку количество", Alert.AlertType.ERROR).showAndWait();
            return false;
        }
        return true;
        }

     public void onSaveClick(ActionEvent actionEvent) {
         if (!getBook().equals("")) {
             this.bookModel.add(getBook());
             ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
         }
    }

    public void onCancelClick(ActionEvent actionEvent) {
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }


    public String getBook(){
        String result = new String();
        if (isInputValid()) {

            String title = this.txtBookTitle.getText();
            String authors = this.txtBookAuthor.getText();
            String publishers = this.txtBookPublisher.getText();
            String type = this.txtBookType.getText();
            String genre = this.txtBookGenre.getText();
            Integer numbers_of_copies = Integer.parseInt(this.txtBookNumbers_of_copies.getText());

            ApiSessionPublisher apiSessionPublisher = new ApiSessionPublisher();
            ApiSessionAuthor apiSessionAuthor = new ApiSessionAuthor();
            List<String> authorList = new ArrayList<String>();
            List<String> publisherList = new ArrayList<String>();
            List<String> authorsList = new ArrayList<>(Arrays.asList(authors.split(",")));
            for (int l = 0; l < authorsList.size(); l++) {
                String authors_name = authorsList.get(l);
                if (apiSessionAuthor.getAuthorByName(authors_name) == null) {
                    Author h = new Author(authors_name);
                    apiSessionAuthor.createAuthor(h.toJson());
                }
                authorList.add(apiSessionAuthor.getAuthorByName(authors_name).getLink());
            }
            List<String> publishersList = new ArrayList<>(Arrays.asList(publishers.split(",")));
            for (int v = 0; v < publishersList.size(); v++) {
                String publishers_name = publishersList.get(v);
                if (apiSessionPublisher.getPublisherByName(publishers_name) == null) {
                    Publisher n = new Publisher(publishers_name);
                    apiSessionPublisher.createPublisher(n.toJson());
                }
                publisherList.add(apiSessionPublisher.getPublisherByName(publishers_name).getLink());
            }
            result = new Book(title, type, genre, numbers_of_copies, authorList, publisherList).toJson();
        }else{
            result = "";
        }
        return result;
    }
}

