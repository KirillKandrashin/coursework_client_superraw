package sample.utils;

import javafx.scene.control.Alert;

public class AlertUtil {
    public static Alert buildDialog(String header, String body, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(header);
        alert.setContentText(body);
        return alert;
    }
}