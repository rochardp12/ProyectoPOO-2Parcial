/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Dueno;
import ec.edu.espol.model.EmailDuenoException;
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
 * @author Issac Maza
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
    }    

    @FXML
    private void enviarDatos(MouseEvent event) {
        try{
            if(Objects.equals(infNombres.getText(),"") || Objects.equals(infApellidos.getText(),"") || Objects.equals(infDireccion.getText(),"")
                    || Objects.equals(infTelefono.getText(),"") || Objects.equals(infEmail.getText(),""))
                throw new PanelVacioException("Obligatorio llenar todos los datos");
            if(Dueno.verificarEmail(infEmail.getText()) != null)
                throw new EmailDuenoException("Email existente. Ingrese uno nuevo");
            Dueno.crearDueno(infNombres.getText(), infApellidos.getText(), infDireccion.getText(), infTelefono.getText(), infEmail.getText());
        }
        catch(PanelVacioException | EmailDuenoException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();
        }
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
            Alert a = new Alert(Alert.AlertType.ERROR, "No es posible regresar a la ventana principal");
            a.show();
        }
    }

    @FXML
    private void limpiar(MouseEvent event) {
        infNombres.setText("");
        infApellidos.setText("");
        infDireccion.setText("");
        infTelefono.setText("");
        infEmail.setText("");
    }
}
