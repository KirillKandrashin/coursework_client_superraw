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
import sample.models.*;
import sample.utils.ApiSessionUser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    public TextField txtUserLogin;
    public TextField txtUserPassword;
    public Label message;

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
        return true;
    }

    public void onSignInClick(ActionEvent actionEvent) {
        if (getUser()) {
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
            //stage.initOwner(this.mainTable.getScene().getWindow());
            MainBookController controller = (MainBookController) loader.getController();
            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
            stage.show();
        }
    }

    public void onSignUpClick(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample/view/signup.fxml"));
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
        SignUpController controller = (SignUpController) loader.getController();
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        stage.show();
    }


    public boolean getUser() {
        String errorMessage = "";
        message.setText(errorMessage);
        if (isInputValid()) {
            String login = this.txtUserLogin.getText();
            String password = this.txtUserPassword.getText();
            ApiSessionUser apiSessionUser = new ApiSessionUser();
            if (apiSessionUser.getUserByLogin(login) == null) {
                errorMessage += "Не найден user с таким логином";
                message.setText(errorMessage);
                return false;
            }
            if (!apiSessionUser.getUserByLogin(login).getPassword().equals(password)) {
                errorMessage += "Неверный логин для user с таким логином";
                message.setText(errorMessage);
                return false;
            }
        }else{
            return false;
        }
        return true;
    }
}
