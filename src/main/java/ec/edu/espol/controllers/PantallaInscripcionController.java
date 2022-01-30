/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.CostoException;
import ec.edu.espol.model.FechaInvalidaException;
import ec.edu.espol.model.Inscripcion;
import ec.edu.espol.model.Mascota;
import ec.edu.espol.model.NombreConcursoException;
import ec.edu.espol.model.NombreMascotaException;
import ec.edu.espol.model.PanelVacioException;
import ec.edu.espol.proyectopoo.App;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
        try{
            if(Objects.equals(infNombre.getText(),"") || Objects.equals(infConcurso.getText(),"") || Objects.equals(infValor.getText(),"")
                    || Objects.equals(infDia.getText(),"") || Objects.equals(infMes.getText(),"") || Objects.equals(infAn.getText(),""))
                throw new PanelVacioException("Obligatorio llenar todos los datos");
            if(Mascota.verificarNombre(infNombre.getText()) == null)
                throw new NombreMascotaException("Nombre de mascota no registrado. Ingresar correctamente o registrar primero");
            if(Concurso.verificarNombre(infConcurso.getText()) == null)
                throw new NombreConcursoException("Nombre de concurso no registrado. Ingresar correctamente o registrar primero");
            double valorPagar = Double.parseDouble(infValor.getText());
            int dia = Integer.parseInt(infDia.getText());
            int mes = Integer.parseInt(infMes.getText());
            int an = Integer.parseInt(infAn.getText());
            if((dia<=0) || (dia>31))
                throw new FechaInvalidaException("Fecha incorrecta ingresada. Verificar");
            if((mes<=0) || (mes>12))
                throw new FechaInvalidaException("Fecha incorrecta ingresada. Verificar");
            if(((dia >= 30)&&((mes == 2)||(mes == 02))))
                throw new FechaInvalidaException("Fecha incorrecta ingresada. Verificar");
            if(an < 2022)
                throw new FechaInvalidaException("Fecha incorrecta ingresada. Verificar");
            if(valorPagar < 0)
                throw new CostoException("Ingrese un valor a pagar correcto");
            Inscripcion.crearInscripcion(Mascota.verificarNombre(infNombre.getText()), Concurso.verificarNombre(infConcurso.getText()), valorPagar, LocalDate.of(an,mes,dia));
        }
        catch(PanelVacioException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();
        }
        catch(NumberFormatException ex){
            Alert a = new Alert(AlertType.ERROR, "Ingresar numeros correctos");
            a.show();
        }
        catch(NombreMascotaException | NombreConcursoException | FechaInvalidaException | CostoException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();
        }
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
