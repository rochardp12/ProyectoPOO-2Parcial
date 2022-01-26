/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import static ec.edu.espol.model.Dueno.readFromFile;
import ec.edu.espol.util.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Usuario
 */
public class Premio {
    private int id;
    private int lugar;
    private String descripcion;
    private int idConcurso;
    private Concurso concurso;
    //constructor
    public Premio(int id, int lugar, String descripcion, Concurso concurso){
        this.id = id;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.idConcurso = concurso.getId();
        this.concurso = concurso;
    }
    //setters
    
    public void setId(int id) {
        try{
            if(verificarID(id) != null)
                throw new IDPremioException("ID existente. Ingrese una nueva");
            this.id = id;
        }
        catch(IDPremioException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }
    
    public void setLugar(int lugar) {
        if(lugar > 0)
            this.lugar = lugar;
    }

    public void setDescripcion(String descripcion) {
        if(descripcion != null)
            this.descripcion = descripcion;
    }

    public void setIdConcurso(int idConcurso) {
        try{
            if(Concurso.verificarID(idConcurso) == null)
                throw new IDConcursoException("ID no existente. Ingrese correctamente");
            this.idConcurso = idConcurso;
        }
        catch(IDConcursoException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    public void setConcurso(Concurso concurso) {
        if(concurso != null)
            this.concurso = concurso;
    }
    
    //getters

    public int getId() {
        return this.id;
    }

    public int getLugar() {
        return this.lugar;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public int getIdConcurso() {
        return this.idConcurso;
    }

    public Concurso getConcurso() {
        return this.concurso;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Premio: ").append(this.id).append(" --> ");
        sb.append("Lugar: ").append(this.lugar);
        sb.append(". Descripcion: ").append(this.descripcion);
        sb.append("--> ID Concurso: ").append(this.idConcurso);
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if(this==obj)
            return true;
        if(this.getClass()!=obj.getClass())
            return false;
        Premio premio = (Premio)obj;
        return Objects.equals(this.descripcion,premio.descripcion);
    }
    
    public void saveFile(String nomfile, int num){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(nomfile,true))){
            bf.write(this.id + "|" + this.lugar + "|" + this.descripcion + "|" + this.concurso.getId() + "|" + this.concurso.getNombre() + "\n");
            if(num == 1){
                Alert a = new Alert(AlertType.CONFIRMATION,"Premio agregado con éxito");
                a.show();
            }
            else{
                Alert a = new Alert(AlertType.CONFIRMATION,"Premios agregados con éxito");
                a.show();
            }
        }
        catch(IOException ex){
            Alert a = new Alert(AlertType.ERROR,"No es posible registrar los premios");
            a.show();
        }
    }
}
    
    
