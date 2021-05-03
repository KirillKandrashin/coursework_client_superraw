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
import sample.models.*;
import sample.utils.AlertUtil;
import sample.utils.ApiSessionUser;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    public TextField txtUserLogin;
    public TextField txtUserPassword;

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
        return true;
    }

    public void onSignInClick(ActionEvent actionEvent) throws NoSuchAlgorithmException {
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


    public boolean getUser() throws NoSuchAlgorithmException {
        if (isInputValid()) {
            String login = this.txtUserLogin.getText();
            String password = this.txtUserPassword.getText();
            ApiSessionUser apiSessionUser = new ApiSessionUser();
            if (apiSessionUser.getUserByLogin(login) == null) {
                AlertUtil.buildDialog(null,"Не найден user с таким логином", Alert.AlertType.WARNING).showAndWait();
                return false;
            }
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(password.getBytes());
            System.out.println(bytes.toString());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes){
                sb.append(String.format("%02X ", b));
            }
            if (!apiSessionUser.getUserByLogin(login).getPassword().equals(sb.toString())) {
                AlertUtil.buildDialog(null,"Неверный пароль для данного user", Alert.AlertType.WARNING).showAndWait();
                return false;
            }
        }else{
            return false;
        }
        return true;
    }
}
