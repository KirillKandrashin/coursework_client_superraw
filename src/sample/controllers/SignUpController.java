package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.models.BookModel;
import sample.models.User;
import sample.models.UserModel;
import sample.utils.ApiSessionUser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    public TextField txtUserLogin;
    public TextField txtUserPassword;
    public TextField txtUserPassword2;
    public Label message;

    UserModel userModel = new UserModel();
    ApiSessionUser apiSessionUser = new ApiSessionUser();

    public static void show() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private boolean isInputValid() {
        String errorMessage = "";
        message.setText(errorMessage);
        if (txtUserLogin.getText() == null || txtUserLogin.getText().length() == 0) {
            errorMessage += "Введите логин";
            message.setText(errorMessage);
            return false;
        }
        if (txtUserPassword.getText() == null || txtUserPassword.getText().length() == 0) {
            errorMessage += "Введите пароль";
            message.setText(errorMessage);
            return false;
        }
        if (!txtUserPassword.getText().equals(txtUserPassword2.getText())) {
            errorMessage += "Пароли не совпадают";
            message.setText(errorMessage);
            return false;
        }
        if (apiSessionUser.getUserByLogin(txtUserLogin.getText()) != null) {
            errorMessage += "User с таким логином уже существуют";
            message.setText(errorMessage);
            return false;
        }
        return true;
    }

    public void onSignInClick(ActionEvent actionEvent) {
        if (!getUser().equals("")) {
            this.userModel.add(getUser());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/sample/view/signin.fxml"));
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
            SignInController controller = (SignInController) loader.getController();
            ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
            stage.show();
        }
    }

    public void onBackClick(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/view/signin.fxml"));
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
        SignInController controller = (SignInController) loader.getController();
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
        stage.show();
    }


    public String getUser(){
        String result = "";
        if (isInputValid()) {
            String login = this.txtUserLogin.getText();
            String password = this.txtUserPassword.getText();
            result = new User(login, password).toJson();
        }
        return result;
    }
}
