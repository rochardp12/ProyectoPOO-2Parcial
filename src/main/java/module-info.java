module ec.edu.espol.proyectopoo {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.proyectopoo to javafx.fxml;
    exports ec.edu.espol.proyectopoo;
}
