package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.Book;
import sample.models.BookModel;

import java.net.URL;
import java.util.ResourceBundle;

public class EditBookController implements Initializable {
    public TextField txtBookTitle;
    public TextField txtBookGenre;
    public TextField txtBookType;
    public TextField txtBookNumbers_of_copies;
    public BookModel bookModel;

    private Book book;

    public static void show() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setBook(Book book) {
        this.book = book;
        txtBookTitle.setText(book.getTitle());
        txtBookGenre.setText(book.getGenre());
        txtBookType.setText(book.getType());
        txtBookNumbers_of_copies.setText(book.getNumber_of_copies().toString());
    }

    private boolean isInputValid() {
        if (txtBookTitle.getText() == null || txtBookTitle.getText().length() == 0) {
            return false;
        }
        if (txtBookGenre.getText() == null || txtBookGenre.getText().length() == 0) {
            return false;
        }
        if (txtBookType.getText() == null || txtBookType.getText().length() == 0) {
            return false;
        }
        try{
            Integer.parseInt(this.txtBookNumbers_of_copies.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void onSaveClick(ActionEvent actionEvent) {
        if (getBook().getTitle() != null) {
            this.bookModel.edit(getBook());
        }
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void onCancelClick(ActionEvent actionEvent) {
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }


    public Book getBook(){
        Book result = new Book();
        if (isInputValid()) {

            String title = this.txtBookTitle.getText();
            String type = this.txtBookType.getText();
            String genre = this.txtBookGenre.getText();
            Integer numbers_of_copies = Integer.parseInt(this.txtBookNumbers_of_copies.getText());

            result = new Book(book.getId(), title, type, genre, numbers_of_copies);
        }
        return result;
    }
}

