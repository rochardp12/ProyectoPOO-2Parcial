/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Richard
 */
public class PantallaDuenoController implements Initializable {

    @FXML
    private TextField infNombres;
    @FXML
    private TextField infApellidos;
    @FXML
    private TextField infDireccion;
    @FXML
    private TextField infTelefono;
    @FXML
    private TextField infEmail;
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
    private void regresarPrincipal(MouseEvent event) {
    }

    @FXML
    private void limpiar(MouseEvent event) {
    }
    
}