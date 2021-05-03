package sample.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.controllers.adding.AddAuthorController;
import sample.controllers.editting.EditAuthorController;
import sample.models.Author;
import sample.models.AuthorModel;
import sample.utils.AlertUtil;
import sample.utils.ApiSessionAuthor;

import java.io.IOException;

public class MainAuthorController {
    public TableView<Author> mainTable;
    public TableColumn<Author, Long> idColumn;
    public TableColumn<Author, String> nameColumn;
    public TableColumn<Author, String> bookListColumn;

    private Main main;
    private ApiSessionAuthor apiSessionAuthor = new ApiSessionAuthor();
    AuthorModel authorModel = new AuthorModel();

    public void initialize() {
        this.idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        this.bookListColumn.setCellValueFactory(new PropertyValueFactory("bookList"));
        this.authorModel.addDataChangedListener((authors) -> {
            this.mainTable.setItems(FXCollections.observableArrayList(authors));
        });
        this.authorModel.load();
    }

    public void onUpdateClick(ActionEvent actionEvent) {
        initialize();
    }

    public void onAddClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/view/addingauthors.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Информационно-справочная система библиотеки");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());
        AddAuthorController controller = loader.getController();
        controller.authorModel = this.authorModel;
        stage.showAndWait();
    }

    public void onSwitchVClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/view/mainformvisitors.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Информационно-справочная система библиотеки");
        stage.initModality(Modality.WINDOW_MODAL);
        MainVisitorController controller = (MainVisitorController) loader.getController();
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
        stage.show();
    }

    public void onSwitchBClick(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/view/mainformbooks.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Информационно-справочная система библиотеки");
        stage.initModality(Modality.WINDOW_MODAL);
        //stage.initOwner(this.mainTable.getScene().getWindow());
        MainBookController controller = (MainBookController) loader.getController();
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
        stage.setTitle("Информационно-справочная система библиотеки");
        stage.initModality(Modality.WINDOW_MODAL);
        MainPublisherController controller = (MainPublisherController) loader.getController();
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
        stage.show();
    }

    public void onInfoClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/view/info.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Об авторе");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());
        InfoController controller = (InfoController) loader.getController();
        stage.showAndWait();
    }

    public void onEditClick(ActionEvent event) throws IOException {
        int selectedIndex = mainTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Author author = mainTable.getItems().get(selectedIndex);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/sample/view/edittingauthors.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Изменение информации автора");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(this.mainTable.getScene().getWindow());
            EditAuthorController controller = loader.getController();
            controller.setAuthor(author);
            controller.authorModel = this.authorModel;
            stage.showAndWait();
        } else {
            AlertUtil.buildDialog(null,"Выберите строку, которую хотите изменить", Alert.AlertType.WARNING).showAndWait();
        }
    }
}
