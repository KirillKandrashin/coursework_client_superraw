package sample.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.controllers.adding.AddBookController;
import sample.controllers.editting.EditBookController;
import sample.models.Book;
import sample.models.BookModel;
import sample.utils.ApiSessionBook;

import java.io.IOException;

public class MainBookController {
    public TableView<Book> mainTable;
    public TableColumn<Book, Long> idColumn;
    public TableColumn<Book, String> titleColumn;
    public TableColumn<Book, String> author_nameColumn;
    public TableColumn<Book, String> publisher_nameColumn;
    public TableColumn<Book, String> genreColumn;
    public TableColumn<Book, String> typeColumn;
    public TableColumn<Book, Integer> number_of_copiesColumn;
    public Label message;

    private Main main;
    private ApiSessionBook apiSessionBook = new ApiSessionBook();
    //private ObservableList<Book> bookList = FXCollections.observableArrayList();
    BookModel bookModel = new BookModel();
   // Book book_for_editting = new Book();

    public void initialize() {
        this.idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        this.titleColumn.setCellValueFactory(new PropertyValueFactory("title"));
        this.author_nameColumn.setCellValueFactory(new PropertyValueFactory("author_name"));
        this.publisher_nameColumn.setCellValueFactory(new PropertyValueFactory("publisher_name"));
        this.genreColumn.setCellValueFactory(new PropertyValueFactory("genre"));
        this.typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
        this.number_of_copiesColumn.setCellValueFactory(new PropertyValueFactory("number_of_copies"));
        this.bookModel.addDataChangedListener((books) -> {
            this.mainTable.setItems(FXCollections.observableArrayList(books));
        });
        this.bookModel.load();
    }

    public void onDeleteClick(ActionEvent actionEvent) {
        String errorMessage = "";
        message.setText(errorMessage);
        int selectedIndex = mainTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            Book book = mainTable.getItems().get(selectedIndex);
            apiSessionBook.deleteBook(book);
            mainTable.getItems().remove(selectedIndex);
        } else {
            errorMessage += "Выберите строку, которую хотите удалить.";
            message.setText(errorMessage);
        }
    }

    public void onUpdateClick(ActionEvent actionEvent) {
        initialize();
    }

    public void onAddClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/view/addingbooks.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());
        AddBookController controller = loader.getController();
        controller.bookModel = this.bookModel;
        stage.showAndWait();
    }

    public void onSwitchVClick(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/view/mainformvisitors.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        //stage.initOwner(this.mainTable.getScene().getWindow());
        MainVisitorController controller = (MainVisitorController) loader.getController();
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
        stage.show();
    }
    public void onSwitchAClick(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/view/mainformauthors.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        //stage.initOwner(this.mainTable.getScene().getWindow());
        MainAuthorController controller = (MainAuthorController) loader.getController();
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
        stage.show();
    }

    public void onSwitchPClick(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/view/mainformpublishers.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());
        MainPublisherController controller = (MainPublisherController)  loader.getController();
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
        stage.show();
    }

    public void onInfoClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/view/info.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());
        InfoController controller = (InfoController) loader.getController();
        stage.showAndWait();
    }

    public void onEditClick(ActionEvent event) throws IOException {
        String errorMessage = "";
        message.setText(errorMessage);
        int selectedIndex = mainTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Book book = mainTable.getItems().get(selectedIndex);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/sample/view/edittingbooks.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(this.mainTable.getScene().getWindow());
            EditBookController controller = loader.getController();
            controller.setBook(book);
            controller.bookModel = this.bookModel;
            stage.showAndWait();
        } else {
            errorMessage += "Выберите строку, которую хотите изменить.";
            message.setText(errorMessage);
        }
    }
}
