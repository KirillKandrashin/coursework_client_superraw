package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.models.BookModel;
import sample.models.User;
import sample.models.UserModel;
import sample.utils.AlertUtil;
import sample.utils.ApiSessionUser;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    public TextField txtUserLogin;
    public TextField txtUserPassword;
    public TextField txtUserPassword2;

    UserModel userModel = new UserModel();
    ApiSessionUser apiSessionUser = new ApiSessionUser();

    public static void show() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private boolean isInputValid() {
        if (txtUserLogin.getText() == null || txtUserLogin.getText().length() == 0) {
            AlertUtil.buildDialog(null,"Введите логин", Alert.AlertType.WARNING).showAndWait();
            return false;
        }
        if (txtUserPassword.getText() == null || txtUserPassword.getText().length() == 0) {
            AlertUtil.buildDialog(null,"Введите пароль", Alert.AlertType.WARNING).showAndWait();
            return false;
        }
        if (!txtUserPassword.getText().equals(txtUserPassword2.getText())) {
            AlertUtil.buildDialog(null,"Пароли не совпадают", Alert.AlertType.WARNING).showAndWait();
            return false;
        }
        if (apiSessionUser.getUserByLogin(txtUserLogin.getText()) != null) {
            AlertUtil.buildDialog(null,"User с таким логином уже существует", Alert.AlertType.WARNING).showAndWait();
            return false;
        }
        return true;
    }

    public void onSignInClick(ActionEvent actionEvent) throws NoSuchAlgorithmException {
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
            stage.setTitle("Информационно-справочная система библиотеки");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
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
        stage.setTitle("Информационно-справочная система библиотеки");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        SignInController controller = (SignInController) loader.getController();
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
        stage.show();
    }


    public String getUser() throws NoSuchAlgorithmException {
        String result = "";
        if (isInputValid()) {
            String login = this.txtUserLogin.getText();
            String password = this.txtUserPassword.getText();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes){
                sb.append(String.format("%02X ", b));
            }
            result = new User(login, sb.toString()).toJson();
        }
        return result;
    }
}
