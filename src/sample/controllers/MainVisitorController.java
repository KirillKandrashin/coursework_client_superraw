package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import sample.models.Visitor;
import sample.models.VisitorModel;
import sample.utils.ApiSessionVisitor;

import java.io.IOException;

public class MainVisitorController {
    public TableView<Visitor> mainTable;
    public TableColumn<Visitor, Long> idColumn;
    public TableColumn<Visitor, String> first_nameColumn;
    public TableColumn<Visitor, String> last_nameColumn;
    public TableColumn<Visitor, String> library_cardColumn;
    @FXML
    private Label message;

    private Main main;
    private ApiSessionVisitor apiSessionVisitor = new ApiSessionVisitor();
    private ObservableList<Visitor> visitorList = FXCollections.observableArrayList();
    VisitorModel visitorModel = new VisitorModel();
    //Visitor visitor_for_editting = new Visitor();

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
        String errorMessage = "";
        message.setText(errorMessage);
        int selectedIndex = mainTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            Visitor visitor = mainTable.getItems().get(selectedIndex);
            apiSessionVisitor.deleteVisitor(visitor);
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
        loader.setLocation(this.getClass().getResource("/sample/view/addingvisitors.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());
        AddVisitorController controller = (AddVisitorController) loader.getController();
        controller.visitorModel = this.visitorModel;
        stage.showAndWait();
    }

    public void onSwitchClick(ActionEvent actionEvent) {
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
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());
        MainBookController controller = (MainBookController) loader.getController();
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
        stage.show();
    }
}

