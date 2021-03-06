/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.CostoException;
import ec.edu.espol.model.FechaInvalidaException;
import ec.edu.espol.model.PanelVacioException;
import ec.edu.espol.proyectopoo.App;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
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
public class PantallaConcursoController implements Initializable {

    @FXML
    private TextField infNombre;
    @FXML
    private TextField infDiaFecha;
    @FXML
    private TextField infMesFecha;
    @FXML
    private TextField infAnFecha;
    @FXML
    private TextField infDiaIns;
    @FXML
    private TextField infdiaCie;
    @FXML
    private TextField infMesIns;
    @FXML
    private TextField infMesCie;
    @FXML
    private TextField infAnIns;
    @FXML
    private TextField infAnCie;
    @FXML
    private TextField infTematica;
    @FXML
    private TextField infCostoIns;
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
            if(Objects.equals(infNombre.getText(),"") || Objects.equals(infDiaFecha.getText(),"") || Objects.equals(infMesFecha.getText(),"") || Objects.equals(infAnFecha.getText(),"") 
                    || Objects.equals(infDiaIns.getText(),"") || Objects.equals(infMesIns.getText(),"") || Objects.equals(infAnIns.getText(),"") || Objects.equals(infdiaCie.getText(),"")
                    || Objects.equals(infMesCie.getText(),"") || Objects.equals(infAnCie.getText(),"") || Objects.equals(infTematica.getText(),"") || Objects.equals(infCostoIns.getText(),""))
                throw new PanelVacioException("Obligatorio llenar todos los datos");
            int diaFe = Integer.parseInt(infDiaFecha.getText());
            int mesFe = Integer.parseInt(infMesFecha.getText());
            int anFe = Integer.parseInt(infAnFecha.getText());
            int diaIns = Integer.parseInt(infDiaIns.getText());
            int mesIns = Integer.parseInt(infMesIns.getText());
            int anIns = Integer.parseInt(infAnIns.getText());
            int diaCie = Integer.parseInt(infdiaCie.getText());
            int mesCie = Integer.parseInt(infMesCie.getText());
            int anCie = Integer.parseInt(infAnCie.getText());
            double costo = Double.parseDouble(infCostoIns.getText());
            if((diaFe<=0) || (diaFe>31) || (diaIns<=0) || (diaIns>31) || (diaCie<=0) || (diaCie>31))
                throw new FechaInvalidaException("Fecha incorrecta ingresada. Verificar");
            if((mesFe<=0) || (mesFe>12) || (mesIns<=0) || (mesIns>12) || (mesCie<=0) || (mesCie>12))
                throw new FechaInvalidaException("Fecha incorrecta ingresada. Verificar");
            if(((diaFe >= 30)&&((mesFe == 2)||(mesFe == 02))) || ((diaIns >= 30)&&((mesIns == 2)||(mesIns == 02))) || ((diaCie >= 30)&&((mesCie == 2)||(mesCie == 02))))
                throw new FechaInvalidaException("Fecha incorrecta ingresada. Verificar");
            if((anFe < 2022) || (anIns < 2022) || (anCie < 2022))
                throw new FechaInvalidaException("Fecha incorrecta ingresada. Verificar");
            if(costo < 0)
                throw new CostoException("Ingrese un costo correcto");
            Concurso.crearConcurso(infNombre.getText(), LocalDate.of(anFe,mesFe,diaFe), LocalDate.of(anIns,mesIns,diaIns), LocalDate.of(anCie,mesCie,diaCie), infTematica.getText(), costo);
        }
        catch(PanelVacioException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
        catch(NumberFormatException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, "Ingresar numeros correctos");
            a.show();
        }
        catch(FechaInvalidaException | CostoException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    @FXML
    private void limpiar(MouseEvent event) {
        infNombre.setText("");
        infDiaFecha.setText("");
        infMesFecha.setText("");
        infAnFecha.setText("");
        infDiaIns.setText("");
        infMesIns.setText("");
        infAnIns.setText("");
        infdiaCie.setText("");
        infMesCie.setText("");
        infAnCie.setText("");
        infTematica.setText("");
        infCostoIns.setText("");
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

