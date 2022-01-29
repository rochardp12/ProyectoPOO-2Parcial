/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.proyectopoo.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Richard
 */
public class PantallaPremioController implements Initializable {

    @FXML
    private AnchorPane panelPrincipal;
    @FXML
    private TextField infNombre;
    @FXML
    private Button btnBuscarConcurso;
    @FXML
    private TextField infPremio;
    @FXML
    private Button btnPremio;
    @FXML
    private Label textoDescripcion;
    @FXML
    private Button btnRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscarConcurso(MouseEvent event) {
    }

    @FXML
    private void cantidadPremios(MouseEvent event) {
    }

    @FXML
    private void enviarDatos(MouseEvent event) {
    }

    @FXML
    private void limpiar(MouseEvent event) {
        infNombre.setText("");
        infPremio.setText("");
        btnPremio.setDisable(true);
        textoDescripcion.setText("");
    }

    @FXML
    private void regresarPrincipal(MouseEvent event) {
        try {
            Stage stg = (Stage)btnRegresar.getScene().getWindow();
            stg.close();
            FXMLLoader loader = App.loadFXML("pantallaInicial");
            Scene sc = new Scene(loader.load(),600,400);
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.setTitle("Concurso de Mascotas");
            Image imagen = new Image("img\\icono.png");
            sg.getIcons().add(imagen);
            sg.show();
        } catch (IOException ex) {
            Alert a = new Alert(AlertType.ERROR, "No es posible regresar a la ventana principal");
            a.show();
        }
    }
    
}
