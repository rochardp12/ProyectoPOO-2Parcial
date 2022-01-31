/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.EmailJuradoException;
import ec.edu.espol.model.MiembroJurado;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PantallaMiembroJuradoController implements Initializable {

    @FXML
    private TextField infNombres;
    @FXML
    private TextField infApellidos;
    @FXML
    private TextField infTelefono;
    @FXML
    private TextField infEmail;
    @FXML
    private TextField infPerfil;
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
        try{
            if(Objects.equals(infNombres.getText(),"") || Objects.equals(infApellidos.getText(),"") || Objects.equals(infTelefono.getText(),"")
                    || Objects.equals(infEmail.getText(),"") || Objects.equals(infPerfil.getText(),""))
                throw new PanelVacioException("Obligatorio llenar todos los datos");
            if(MiembroJurado.verificarEmail(infEmail.getText()) != null)
                throw new EmailJuradoException("Email existente. Ingrese uno nuevo");
            MiembroJurado.crearMiembroJurado(infNombres.getText(), infApellidos.getText(), infTelefono.getText(), infEmail.getText(), infPerfil.getText());
        }
        catch(PanelVacioException | EmailJuradoException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    @FXML
    private void limpiar(MouseEvent event) {
        infNombres.setText("");
        infApellidos.setText("");
        infTelefono.setText("");
        infEmail.setText("");
        infPerfil.setText("");
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
    
}
