/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.CantidadException;
import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.NombreConcursoException;
import ec.edu.espol.model.PanelVacioException;
import ec.edu.espol.model.Premio;
import ec.edu.espol.proyectopoo.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
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
 * @author Usuario
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
    
    private int cantPremio;
    
    private int cantDescrip;
    
    private ArrayList<String> infoPremios;
    
    private TextField infDescripcion;
    
    private Button btnDescripcion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnPremio.setDisable(true);
        infPremio.setDisable(true);
    }    

    @FXML
    private void buscarConcurso(MouseEvent event) {
        try{
            if(Objects.equals(infNombre.getText(),""))
                throw new PanelVacioException("Ingrese un nombre de concurso");
            if(Concurso.verificarNombre(infNombre.getText()) == null)
                throw new NombreConcursoException("Nombre de concurso incorrecto. Verificar o registrarlo primero");
            btnPremio.setDisable(false);
            infPremio.setDisable(false);
        }catch(PanelVacioException | NombreConcursoException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    @FXML
    private void cantidadPremios(MouseEvent event) {
        try{
            if(Objects.equals(infPremio.getText(),""))
                throw new PanelVacioException("Ingrese cantidad de premios");
            cantPremio = Integer.parseInt(infPremio.getText());
            if(cantPremio <= 0)
                throw new CantidadException("Ingrese cantidad correcta de premios");
            infoPremios = new ArrayList<>();
            if(cantPremio > 1){
                cantDescrip = 1;
                textoDescripcion.setText("Ingrese descripción del premio 1");
                cantDescrip++;
            }
            else
                textoDescripcion.setText("Ingrese descripción del premio");
            infDescripcion = new TextField();
            infDescripcion.setLayoutX(145);
            infDescripcion.setLayoutY(278);
            panelPrincipal.getChildren().add(infDescripcion);
            btnDescripcion = new Button();
            btnDescripcion.setLayoutX(371);
            btnDescripcion.setLayoutY(278);
            panelPrincipal.getChildren().add(btnDescripcion);
            btnDescripcion.setText("Guardar");
            btnDescripcion.setOnMouseClicked((MouseEvent e) -> {
                try{
                    if(Objects.equals(infDescripcion.getText(),""))
                        throw new PanelVacioException("Ingresar premio");
                    infoPremios.add(infDescripcion.getText());
                    cantPremio--;
                    infDescripcion.setText("");
                    if(cantPremio == 0){
                        textoDescripcion.setText("Click en el botón Registrar");
                        btnDescripcion.setDisable(true);
                        infDescripcion.setDisable(true);
                        infPremio.setDisable(true);
                        btnPremio.setDisable(true);
                    }
                    else{
                        textoDescripcion.setText("Ingrese descripción del premio " + cantDescrip);
                        cantDescrip++;
                    }
                }
                catch(PanelVacioException ex){
                    Alert a = new Alert(AlertType.ERROR, ex.getMessage());
                    a.show();
                }
                });
        }
        catch(PanelVacioException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();
        }
        catch(NumberFormatException ex){
            Alert a = new Alert(AlertType.ERROR, "Ingresar números correctos");
            a.show();
        }
        catch(CantidadException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();
        }    
    }
    

    @FXML
    private void enviarDatos(MouseEvent event) {
        try{
            if(Objects.equals(infNombre.getText(), "") || Objects.equals(infPremio.getText(),""))
                throw new PanelVacioException("Obigatorio llenar todos los datos");
            if(!(btnPremio.isDisabled()))
                throw new PanelVacioException("Llenar las descripciones de los premios");
            Premio.crearPremio(infoPremios, Concurso.verificarNombre(infNombre.getText()));
        }
        catch(PanelVacioException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    @FXML
    private void limpiar(MouseEvent event) {
        infNombre.setText("");
        infPremio.setText("");
        btnPremio.setDisable(true);
        textoDescripcion.setText("");
        if((infDescripcion != null) && (btnDescripcion != null)){
            infDescripcion.setDisable(true);
            btnDescripcion.setDisable(true);
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
            Alert a = new Alert(AlertType.ERROR, "No es posible regresar a la ventana principal");
            a.show();
        }
    }
    
}
