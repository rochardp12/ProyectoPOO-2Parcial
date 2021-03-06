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
 * @author Usuario
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
        try {
            Stage stg = (Stage)btnDueno.getScene().getWindow();
            stg.close();
            FXMLLoader loader = App.loadFXML("pantallaMascota");
            Scene sc = new Scene(loader.load(),600,400);
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.setTitle("Registrar Mascota");
            Image imagen = new Image("img\\icono.png");
            sg.getIcons().add(imagen);
            sg.show();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No es posible registrar una nueva Mascota");
            a.show();
        }
    }

    @FXML
    private void accesoConcurso(MouseEvent event) {
        try {
            Stage stg = (Stage)btnDueno.getScene().getWindow();
            stg.close();
            FXMLLoader loader = App.loadFXML("pantallaConcurso");
            Scene sc = new Scene(loader.load(),600,400);
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.setTitle("Registrar Concurso");
            Image imagen = new Image("img\\icono.png");
            sg.getIcons().add(imagen);
            sg.show();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No es posible registrar un nuevo Concurso");
            a.show();
        }
    }

    @FXML
    private void accesoPremio(MouseEvent event) {
        try {
            Stage stg = (Stage)btnDueno.getScene().getWindow();
            stg.close();
            FXMLLoader loader = App.loadFXML("pantallaPremio");
            Scene sc = new Scene(loader.load(),600,400);
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.setTitle("Registrar Premio");
            Image imagen = new Image("img\\icono.png");
            sg.getIcons().add(imagen);
            sg.show();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No es posible registrar un nuevo Premio");
            a.show();
        }
    }

    @FXML
    private void accesoCriterio(MouseEvent event) {
        try {
            Stage stg = (Stage)btnDueno.getScene().getWindow();
            stg.close();
            FXMLLoader loader = App.loadFXML("pantallaCriterio");
            Scene sc = new Scene(loader.load(),600,400);
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.setTitle("Registrar Criterio");
            Image imagen = new Image("img\\icono.png");
            sg.getIcons().add(imagen);
            sg.show();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No es posible registrar un nuevo Criterio");
            a.show();
        }
    }

    @FXML
    private void accesoInscripcion(MouseEvent event) {
        try {
            Stage stg = (Stage)btnDueno.getScene().getWindow();
            stg.close();
            FXMLLoader loader = App.loadFXML("pantallaInscripcion");
            Scene sc = new Scene(loader.load(),600,400);
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.setTitle("Registrar Inscripción");
            Image imagen = new Image("img\\icono.png");
            sg.getIcons().add(imagen);
            sg.show();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No es posible registrar una nueva Inscripción");
            a.show();
        }
    }

    @FXML
    private void accesoJurado(MouseEvent event) {
        try {
            Stage stg = (Stage)btnDueno.getScene().getWindow();
            stg.close();
            FXMLLoader loader = App.loadFXML("pantallaMiembroJurado");
            Scene sc = new Scene(loader.load(),600,400);
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.setTitle("Registrar Miembro del Jurado");
            Image imagen = new Image("img\\icono.png");
            sg.getIcons().add(imagen);
            sg.show();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No es posible registrar un nuevo Miembro del Jurado");
            a.show();
        }
    }

    @FXML
    private void accesoEvaluacion(MouseEvent event) {
        try {
            Stage stg = (Stage)btnDueno.getScene().getWindow();
            stg.close();
            FXMLLoader loader = App.loadFXML("pantallaEvaluacion");
            Scene sc = new Scene(loader.load(),600,400);
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.setTitle("Registrar Evaluación");
            Image imagen = new Image("img\\icono.png");
            sg.getIcons().add(imagen);
            sg.show();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No es posible registrar una nueva Evaluación");
            a.show();
        }
    }
    
}
