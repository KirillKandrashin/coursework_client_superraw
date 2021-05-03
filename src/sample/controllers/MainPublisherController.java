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
import sample.controllers.adding.AddPublisherController;
import sample.controllers.editting.EditPublisherController;
import sample.models.Publisher;
import sample.models.PublisherModel;
import sample.utils.AlertUtil;
import sample.utils.ApiSessionPublisher;

import java.io.IOException;

public class MainPublisherController {
    public TableView<Publisher> mainTable;
    public TableColumn<Publisher, Long> idColumn;
    public TableColumn<Publisher, String> nameColumn;
    public TableColumn<Publisher, String> bookListColumn;

    private Main main;
    private ApiSessionPublisher apiSessionPublisher = new ApiSessionPublisher();
    PublisherModel publisherModel = new PublisherModel();

    public void initialize() {
        this.idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        this.bookListColumn.setCellValueFactory(new PropertyValueFactory("bookList"));
        this.publisherModel.addDataChangedListener((publishers) -> {
            this.mainTable.setItems(FXCollections.observableArrayList(publishers));
        });
        this.publisherModel.load();
    }

    public void onUpdateClick(ActionEvent actionEvent) {
        initialize();
    }

    public void onAddClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/view/addingpublishers.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Добавление нового издательства");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());
        AddPublisherController controller = loader.getController();
        controller.publisherModel = this.publisherModel;
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
        stage.setTitle("Информационно-справочная система библиотеки");
        stage.initModality(Modality.WINDOW_MODAL);
        //stage.initOwner(this.mainTable.getScene().getWindow());
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
        stage.setTitle("Информационно-справочная система библиотеки");
        stage.initModality(Modality.WINDOW_MODAL);
        //stage.initOwner(this.mainTable.getScene().getWindow());
        MainAuthorController controller = (MainAuthorController) loader.getController();
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
            Publisher publisher = mainTable.getItems().get(selectedIndex);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/sample/view/edittingpublishers.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Изменение информации издательства");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(this.mainTable.getScene().getWindow());
            EditPublisherController controller = loader.getController();
            controller.setPublisher(publisher);
            controller.publisherModel = this.publisherModel;
            stage.showAndWait();
        } else {
            AlertUtil.buildDialog(null,"Выберите строку, которую хотите изменить", Alert.AlertType.WARNING).showAndWait();
        }
    }
}
