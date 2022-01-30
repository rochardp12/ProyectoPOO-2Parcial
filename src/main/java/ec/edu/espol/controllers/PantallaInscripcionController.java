/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.PanelVacioException;
import ec.edu.espol.proyectopoo.App;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class PantallaInscripcionController implements Initializable {

    @FXML
    private TextField infNombre;
    @FXML
    private TextField infConcurso;
    @FXML
    private TextField infValor;
    @FXML
    private TextField infDia;
    @FXML
    private TextField infMes;
    @FXML
    private TextField infAn;
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
    private void enviarDatos(MouseEvent event) {
    }

    @FXML
    private void limpiar(MouseEvent event) {
        infNombre.setText("");
        infConcurso.setText("");
        infValor.setText("");
        infDia.setText("");
        infMes.setText("");
        infAn.setText("");
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
