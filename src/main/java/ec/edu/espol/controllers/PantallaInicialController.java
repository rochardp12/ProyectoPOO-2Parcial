/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.proyectopoo.App;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Richard
 */
public class PantallaInicialController implements Initializable {

    @FXML
    private Button btnDueno;
    @FXML
    private Button btnMascota;
    @FXML
    private Button btnConcurso;
    @FXML
    private Button btnPremio;
    @FXML
    private Button btnCriterio;
    @FXML
    private Button btnInscripcion;
    @FXML
    private Button btnJurado;
    @FXML
    private Button btnEvaluacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void accesoDueno(MouseEvent event) {
        try {
            Stage stg = (Stage)btnDueno.getScene().getWindow();
            stg.close();
            FXMLLoader loader = App.loadFXML("pantallaDueno");
            Scene sc = new Scene(loader.load(),600,400);
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.setTitle("Registrar Dueño");
            Image imagen = new Image("img\\icono.png");
            sg.getIcons().add(imagen);
            sg.show();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No es posible registrar un nuevo Dueño");
            a.show();
        }
    }

    @FXML
    private void accesoMascota(MouseEvent event) {
    }

    @FXML
    private void accesoConcurso(MouseEvent event) {
    }

    @FXML
    private void accesoPremio(MouseEvent event) {
    }

    @FXML
    private void accesoCriterio(MouseEvent event) {
    }

    @FXML
    private void accesoInscripcion(MouseEvent event) {
    }

    @FXML
    private void accesoJurado(MouseEvent event) {
    }

    @FXML
    private void accesoEvaluacion(MouseEvent event) {
    }
    
}
