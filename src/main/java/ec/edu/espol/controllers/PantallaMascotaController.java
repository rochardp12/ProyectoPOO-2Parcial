/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Dueno;
import ec.edu.espol.model.EmailDuenoException;
import ec.edu.espol.model.FechaInvalidaException;
import ec.edu.espol.model.Mascota;
import ec.edu.espol.model.PanelVacioException;
import ec.edu.espol.proyectopoo.App;
import java.io.File;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class PantallaMascotaController implements Initializable {

    @FXML
    private TextField infNombre;
    @FXML
    private TextField infRaza;
    @FXML
    private TextField infTipo;
    @FXML
    private TextField infEmail;
    @FXML
    private TextField infDia;
    @FXML
    private TextField infMes;
    @FXML
    private TextField infAn;
    @FXML
    private ImageView imgnMascota;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnRegresar;
    
    private File archivoImagen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscarImagen(MouseEvent event) {
        FileChooser fc = new FileChooser();
            fc.setTitle("Buscar Imagen de Mascota");
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif"));
            Stage stage = (Stage)btnBuscar.getScene().getWindow();
            archivoImagen = fc.showOpenDialog(stage);
            if(archivoImagen != null){
                    String orig = archivoImagen.getPath();
                    Path origen = Paths.get(orig);
                    Image imagen = new Image("file:" + origen);
                    imgnMascota.setImage(imagen);
                }
    }

    @FXML
    private void enviarDatos(MouseEvent event) {
        try{
            if(Objects.equals(infNombre.getText(),"") || Objects.equals(infRaza.getText(),"") || Objects.equals(infTipo.getText(),"") || Objects.equals(infEmail.getText(),"")
                    || Objects.equals(infDia.getText(),"") || Objects.equals(infMes.getText(),"") || Objects.equals(infAn.getText(),""))
                throw new PanelVacioException("Obligatorio llenar todos los datos");
            if(imgnMascota.getImage() == null)
                throw new PanelVacioException("Obligatorio subir imagen de la mascota");
            int dia = Integer.parseInt(infDia.getText());
            int mes = Integer.parseInt(infMes.getText());
            int an = Integer.parseInt(infAn.getText());
            if((dia<=0) || (dia>31))
                throw new FechaInvalidaException("Fecha incorrecta ingresada. Verificar");
            if((mes<=0) || (mes>12))
                throw new FechaInvalidaException("Fecha incorrecta ingresada. Verificar");
            if(((dia >= 30)&&((mes == 2)||(mes == 02))))
                throw new FechaInvalidaException("Fecha incorrecta ingresada. Verificar");
            if(an > 2022)
                throw new FechaInvalidaException("Fecha incorrecta ingresada. Verificar");
            Dueno dueno = Dueno.verificarEmail(infEmail.getText());
            if(dueno == null)
                throw new EmailDuenoException("Email no existente. Ingresar correctamente o registrar Dueño primero");
            Mascota.guardarImagen(archivoImagen);
            Mascota.crearMascota(infNombre.getText(), infRaza.getText(), LocalDate.of(an,mes,dia), infTipo.getText(), dueno);
        }
        catch(PanelVacioException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
        catch(NumberFormatException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, "Ingresar numeros correctos");
            a.show();
        }
        catch(FechaInvalidaException | EmailDuenoException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    @FXML
    private void limpiar(MouseEvent event) {
        infNombre.setText("");
        infRaza.setText("");
        infTipo.setText("");
        infEmail.setText("");
        infDia.setText("");
        infMes.setText("");
        infAn.setText("");
        imgnMascota.setImage(null);
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
