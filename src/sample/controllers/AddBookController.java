package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.*;
import sample.utils.ApiSessionAuthor;
import sample.utils.ApiSessionPublisher;
import sample.utils.ApiSessionSection;

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
    public TextField txtBookSection;
    public BookModel bookModel;
    private boolean modalResult = false;

    public static void show() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

     public void onSaveClick(ActionEvent actionEvent) {
       this.bookModel.add(getBook());
     ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void onCancelClick(ActionEvent actionEvent) {
        this.modalResult = false;
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public Boolean getModalResult() {
        return modalResult;
    }


    public String getBook(){
        String result = new String();

        String title = this.txtBookTitle.getText();
        String authors = this.txtBookAuthor.getText();
        String publishers = this.txtBookPublisher.getText();
        String type = this.txtBookType.getText();
        String genre = this.txtBookGenre.getText();
        Integer numbers_of_copies = Integer.parseInt(this.txtBookNumbers_of_copies.getText());
        Long section_id = Long.valueOf(this.txtBookSection.getText());

        ApiSessionPublisher apiSessionPublisher = new ApiSessionPublisher();
        ApiSessionAuthor apiSessionAuthor = new ApiSessionAuthor();
        ApiSessionSection apiSessionSection = new ApiSessionSection();
        List<String> authorList = new ArrayList<String>();
        List<String> publisherList = new ArrayList<String>();
        List<String> authorsList = new ArrayList<>(Arrays.asList(authors.split(",")));
        for (int l = 0; l < authorsList.size(); l++){
            String authors_name = authorsList.get(l);
            if (apiSessionAuthor.getAuthorByName(authors_name) == null) {
                Author h = new Author(authors_name);
                apiSessionAuthor.createAuthor(h);
            }
                authorList.add(apiSessionAuthor.getAuthorByName(authors_name).getLink());
        }
        System.out.println(authorList);
        List<String> publishersList = new ArrayList<>(Arrays.asList(publishers.split(",")));
        for (int v = 0; v < publishersList.size(); v++){
            String publishers_name = publishersList.get(v);
            if (apiSessionPublisher.getPublisherByName(publishers_name) == null) {
                Publisher n = new Publisher(publishers_name);
                apiSessionPublisher.createPublisher(n);
            }
                publisherList.add(apiSessionPublisher.getPublisherByName(publishers_name).getLink());
        }
        if (apiSessionSection.getSectionById(section_id) != null) {
            String section_link = apiSessionSection.getSectionById(section_id).getLink();
            result = new Book(title, type, genre, numbers_of_copies, authorList, publisherList, section_link).toJson();
           // System.out.println(result.getId());
        }
 //       System.out.println(result.getTitle());
 //       System.out.println(result.getType());
 //       System.out.println(result.getGenre());
 //       System.out.println(result.getNumber_of_copies());
 //       System.out.println(result.getAuthors_link());
 //       System.out.println(result.getPublishers_link());
 //       System.out.println(result.getSection_link());
        return result;
    }
}

