package ec.edu.espol.util;

import javafx.scene.control.Alert;

public class AlertHelper {
    public static void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Otros métodos para diferentes tipos de alertas (información, confirmación, etc.)
}
