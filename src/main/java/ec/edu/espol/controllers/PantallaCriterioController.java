/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.CantidadException;
import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.Criterio;
import ec.edu.espol.model.NombreConcursoException;
import ec.edu.espol.model.PanelVacioException;
import ec.edu.espol.proyectopoo.App;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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
 * @author Issac Maza
 */
public class PantallaCriterioController implements Initializable {

    @FXML
    private TextField infNombre;
    @FXML
    private Button btnBuscarConcurso;
    @FXML
    private TextField infCriterio;
    @FXML
    private Button btnCriterio;
    @FXML
    private Label textoDescripcion;
    @FXML
    private Button btnRegresar;
    
    private TextField infDescripcion;
    
    private Button btnDescripcion;
    
    private int cantCriterio;
    
    private int cantDescrip;
    
    private ArrayList<String> infoCriterios;
    
    @FXML
    private AnchorPane panelPrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCriterio.setDisable(true);
        infCriterio.setDisable(true);
    }    

    @FXML
    private void buscarConcurso(MouseEvent event) {
        try{
            if(Objects.equals(infNombre.getText(),""))
                throw new PanelVacioException("Ingrese un nombre de concurso");
            if(Concurso.verificarNombre(infNombre.getText()) == null)
                throw new NombreConcursoException("Nombre de concurso incorrecto. Verificar o registrarlo primero");
            btnCriterio.setDisable(false);
            infCriterio.setDisable(false);
        }
        catch(PanelVacioException | NombreConcursoException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }



    

    @FXML
    private void cantidadCriterios(MouseEvent event) {
        try{
            if(Objects.equals(infCriterio.getText(),""))
                throw new PanelVacioException("Ingrese cantidad de criterios");
            cantCriterio = Integer.parseInt(infCriterio.getText());
            if(cantCriterio <= 0)
                throw new CantidadException("Ingrese cantidad correcta de criterios");
            infoCriterios = new ArrayList<>();
            if(cantCriterio > 1){
                cantDescrip = 1;
                textoDescripcion.setText("Ingrese descripción del criterio 1");
                cantDescrip++;
            }
            else
                textoDescripcion.setText("Ingrese descripción del criterio");
            infDescripcion = new TextField();
            infDescripcion.setLayoutX(145);
            infDescripcion.setLayoutY(278);
            panelPrincipal.getChildren().add(btnDescripcion);
            btnDescripcion = new Button();
            btnDescripcion.setLayoutX(371);
            btnDescripcion.setLayoutY(278);
            btnDescripcion.setText("Guardar");
            btnDescripcion.setOnMouseClicked((MouseEvent e) -> {
                try{
                    if(Objects.equals(infDescripcion.getText(),""))
                        throw new PanelVacioException("Ingresar criterio");
                    infoCriterios.add(infDescripcion.getText());
                    cantCriterio--;
                    infDescripcion.setText("");
                    if(cantCriterio == 0){
                        textoDescripcion.setText("Click en el botón Registrar");
                        btnDescripcion.setDisable(true);
                        infDescripcion.setDisable(true);
                        infCriterio.setDisable(true);
                        btnCriterio.setDisable(true);
                    }
                    else{
                        textoDescripcion.setText("Ingrese descripción del criterio " + cantDescrip);
                        cantDescrip++;
                    }
                }
                catch(PanelVacioException ex){
                    Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                    a.show();
                }
            });
        }
        catch(PanelVacioException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
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
            if(Objects.equals(infNombre.getText(), "") || Objects.equals(infCriterio.getText(),""))
                throw new PanelVacioException("Obigatorio llenar todos los datos");
            if(!(btnCriterio.isDisabled()))
                throw new PanelVacioException("Llenar las descripciones de los premios");
            Criterio.crearCriterio(infoCriterios, Concurso.verificarNombre(infNombre.getText()));
        }
        catch(PanelVacioException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }
    
    @FXML
    private void limpiar(MouseEvent event) {
        infNombre.setText("");
        infCriterio.setText("");
        btnCriterio.setDisable(true);
        textoDescripcion.setText("");
        if((infDescripcion != null) && (btnDescripcion != null)){
            infDescripcion.setDisable(true);
            btnDescripcion.setDisable(true);
        }   ;
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
