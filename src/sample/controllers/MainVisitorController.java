package sample.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.controllers.adding.AddVisitorController;
import sample.controllers.editting.EditVisitorController;
import sample.models.Visitor;
import sample.models.VisitorModel;
import sample.utils.AlertUtil;
import sample.utils.ApiSessionVisitor;

import java.io.IOException;

public class MainVisitorController {
    public TableView<Visitor> mainTable;
    public TableColumn<Visitor, Long> idColumn;
    public TableColumn<Visitor, String> first_nameColumn;
    public TableColumn<Visitor, String> last_nameColumn;
    public TableColumn<Visitor, String> library_cardColumn;

    private Main main;
    private ApiSessionVisitor apiSessionVisitor = new ApiSessionVisitor();
    VisitorModel visitorModel = new VisitorModel();


    public void initialize() {
        this.idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        this.first_nameColumn.setCellValueFactory(new PropertyValueFactory("first_name"));
        this.last_nameColumn.setCellValueFactory(new PropertyValueFactory("last_name"));
        this.library_cardColumn.setCellValueFactory(new PropertyValueFactory("library_card"));
        this.visitorModel.addDataChangedListener((visitors) -> {
            this.mainTable.setItems(FXCollections.observableArrayList(visitors));
        });
        this.visitorModel.load();
    }

    public void onDeleteClick(ActionEvent actionEvent) {
        int selectedIndex = mainTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            Visitor visitor = mainTable.getItems().get(selectedIndex);
            apiSessionVisitor.deleteVisitor(visitor);
            mainTable.getItems().remove(selectedIndex);
        } else {
            AlertUtil.buildDialog(null,"Выберите строку, которую хотите удалить", Alert.AlertType.WARNING).showAndWait();
        }
    }

    public void onUpdateClick(ActionEvent actionEvent) {
        initialize();
    }

    public void onAddClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/view/addingvisitors.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Добавление нового посетителя");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());
        AddVisitorController controller = loader.getController();
        controller.visitorModel = this.visitorModel;
        stage.showAndWait();
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
        //stage.initOwner(this.mainTable.getScene().getWindow());
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
        stage.setTitle("Об авторе");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());
        InfoController controller = (InfoController) loader.getController();
        stage.showAndWait();
    }

    public void onEditClick(ActionEvent event) throws IOException {
        int selectedIndex = mainTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Visitor visitor = mainTable.getItems().get(selectedIndex);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/sample/view/edittingvisitors.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Изменение информации посетителя");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(this.mainTable.getScene().getWindow());
            EditVisitorController controller = loader.getController();
            controller.setVisitor(visitor);
            controller.visitorModel = this.visitorModel;
            stage.showAndWait();
        } else {
            AlertUtil.buildDialog(null,"Выберите строку, которую хотите изменить", Alert.AlertType.WARNING).showAndWait();
        }
    }
}

