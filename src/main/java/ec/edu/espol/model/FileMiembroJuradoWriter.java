/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.Alert;

/**
 *
 * @author kevin
 */
public class FileMiembroJuradoWriter implements MiembroJuradoWriter {
    private String nomfile;

    public FileMiembroJuradoWriter(String nomfile) {
        this.nomfile = nomfile;
    }

    @Override
    public void saveMiembroJurado(MiembroJurado miembroJurado) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(nomfile, true))) {
            bf.write(miembroJurado.getId() + "|" + miembroJurado.getNombres() + "|" + miembroJurado.getApellidos() + "|" + miembroJurado.getTelefono() + "|" + miembroJurado.getEmail() + "|" + miembroJurado.getPerfilProfesional() + "|" + miembroJurado.getEvaluaciones() + "\n");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Miembro del Jurado agregado con éxito");
            a.show();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No es posible registrar al Miembro del Jurado");
            a.show();
        }
    }
}
