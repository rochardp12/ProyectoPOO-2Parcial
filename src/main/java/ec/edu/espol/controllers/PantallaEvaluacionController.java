/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Criterio;
import ec.edu.espol.model.EmailJuradoException;
import ec.edu.espol.model.Evaluacion;
import ec.edu.espol.model.IDCriterioException;
import ec.edu.espol.model.IDInscripcionException;
import ec.edu.espol.model.Inscripcion;
import ec.edu.espol.model.Mascota;
import ec.edu.espol.model.MiembroJurado;
import ec.edu.espol.model.NotaException;
import ec.edu.espol.model.PanelVacioException;
import ec.edu.espol.proyectopoo.App;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Richard
 */
public class PantallaEvaluacionController implements Initializable {

    @FXML
    private TextField infIDIns;
    @FXML
    private Button btnIns;
    @FXML
    private TextField infEmail;
    @FXML
    private TextField infIDCriterio;
    @FXML
    private TextField infNota;
    @FXML
    private Button btnRegresar;
    @FXML
    private ImageView imgnMascota;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infEmail.setDisable(true);
        infIDCriterio.setDisable(true);
        infNota.setDisable(true);
    }    

    @FXML
    private void enviarDatos(MouseEvent event) {
        try{
            if(Objects.equals(infEmail.getText(),"") || Objects.equals(infIDCriterio.getText(),"") || Objects.equals(infNota.getText(),""))
                throw new PanelVacioException("Obligatorio ingresar todos los datos");
            if(imgnMascota.getImage() == null)
                throw new PanelVacioException("Obligatorio presionar el botón para Buscar Inscripción para cargar la imagen de la mascota a evaluar");
            if(MiembroJurado.obtenerEmail(infEmail.getText()) == null)
                throw new EmailJuradoException("Email no existe. Ingrese correctamente o registrese primero");
            if(Criterio.verificarID(Integer.parseInt(infIDCriterio.getText())) == null)
                throw new IDCriterioException("Criterio no existente. Ingrese correctamente o registrelo primero");
            double nota = Double.parseDouble(infNota.getText());
            if(nota < 0)
                throw new NotaException("Ingrese una Nota de evaluación correcta");
            Evaluacion.crearEvaluacion(MiembroJurado.obtenerEmail(infEmail.getText()),Inscripcion.verificarID(Integer.parseInt(infIDIns.getText())), Criterio.verificarID(Integer.parseInt(infIDCriterio.getText())), nota);
        }
        catch(PanelVacioException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();
        }
        catch(NumberFormatException ex){
            Alert a = new Alert(AlertType.ERROR, "Ingrese número válido");
            a.show();
        }
        catch(EmailJuradoException | IDCriterioException | NotaException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    @FXML
    private void limpiar(MouseEvent event) {
        infIDIns.setText("");
        infEmail.setText("");
        infIDCriterio.setText("");
        infNota.setText("");
        imgnMascota.setImage(null);
        infEmail.setDisable(true);
        infIDCriterio.setDisable(true);
        infNota.setDisable(true);
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
            String rut = System.getProperty("user.dir") + "/src/main/resources/img/icono.png";
            Path ruta = Paths.get(rut);
            Image imagen = new Image("file:" + ruta);
            sg.getIcons().add(imagen);
            sg.show();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No es posible regresar a la ventana principal");
            a.show();
        }
    }

    @FXML
    private void buscarInscripcion(MouseEvent event) {
        try{
            if(Objects.equals(infIDIns.getText(),""))
                throw new PanelVacioException("Obligatorio ingresar el ID de Inscripcion");
            int idIns = Integer.parseInt(infIDIns.getText());
            if(Inscripcion.verificarID(idIns) == null)
                throw new IDInscripcionException("ID incorrecta. Ingrese una inscripción válida");
            Mascota mascota = Inscripcion.verificarID(idIns).getMascota();
            String nombreImagen = Mascota.buscarImagen(mascota,"imagenesMascotas");
            String rut = System.getProperty("user.dir") + "/src/main/resources/imgMascotas/" + nombreImagen;
            Path ruta = Paths.get(rut);
            Image imagen = new Image("file:" + ruta);
            imgnMascota.setImage(imagen);
            infEmail.setDisable(false);
            infIDCriterio.setDisable(false);
            infNota.setDisable(false);
        }
        catch(PanelVacioException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();
        }
        catch(NumberFormatException ex){
            Alert a = new Alert(AlertType.ERROR, "Ingrese número válido");
            a.show();
        }
        catch(IDInscripcionException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }
    
}
