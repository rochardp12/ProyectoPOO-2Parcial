/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import static ec.edu.espol.model.Inscripcion.readFromFile;
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

/**
 *
 * @author Usuario
 */
public class Criterio {
    private int id;
    private String descripcion;
    private ArrayList<Evaluacion> evaluaciones;
    private int idConcurso;
    private Concurso concurso;
    //constructor
    
    public Criterio(int id, String descripcion, Concurso concurso){
        this.id = id;
        this.descripcion = descripcion;
        this.evaluaciones = new ArrayList<>();
        this.idConcurso = concurso.getId();
        this.concurso = concurso;
    }
    //setters

    public void setId(int id) {
        try{
            if(verificarID(id) != null)
                throw new IDCriterioException("ID existente. Ingrese una nueva");
            this.id = id;
        }
        catch(IDCriterioException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    public void setDescripcion(String descripcion) {
        if(descripcion != null)
            this.descripcion = descripcion;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        if(evaluaciones != null)
            this.evaluaciones = evaluaciones;
    }

    public void setIdConcurso(int idConcurso) {
        try{
            if(Concurso.verificarID(idConcurso) == null)
                throw new IDConcursoException("ID no existente. Ingrese correctamente");
            this.idConcurso = idConcurso;
        }
        catch(IDConcursoException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
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

    public String getDescripcion() {
        return this.descripcion;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return this.evaluaciones;
    }

    public int getIdConcurso() {
        return this.idConcurso;
    }

    public Concurso getConcurso() {
        return this.concurso;
    }
    //comportamientos
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Criterio: ").append(this.id).append(" --> ");
        sb.append("Descripcion: ").append(this.descripcion);
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
        Criterio crit = (Criterio)obj;
        return Objects.equals(this.descripcion,crit.descripcion);
    }
    
    public void saveFile(String nomfile, int num){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(nomfile,true))){
            bf.write(this.id + "|" + this.descripcion + "|" + this.evaluaciones + "|" + this.concurso.getId() + "|" + this.concurso.getNombre() + "\n");
            if(num == 1){
                Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Criterio agregado con éxito");
                a.show();
            }
            else{
                Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Criterios agregados con éxito");
                a.show();
            }
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No es posible registrar los criterios");
            a.show();
        }
        }
    
    public static void crearCriterio(ArrayList<String> infoCriterios, Concurso concurso){
        for(int u=0; u<infoCriterios.size(); u++){
            Criterio criterio = new Criterio(Util.nextID("criterios.txt"), infoCriterios.get(u), concurso);
            criterio.saveFile("criterios.txt", infoCriterios.size());
        }
    }
    
    public static ArrayList<Criterio> readFromFile(String nomfile){
        ArrayList<Criterio> criterios = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(nomfile))){
            String linea;
            while((linea = bf.readLine()) != null){
                String[] arreglo = linea.split("\\|");
                Criterio criterio = new Criterio(Integer.parseInt(arreglo[0]), arreglo[1], Concurso.verificarNombre(arreglo[4]));
                criterios.add(criterio);
            }
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No es posible obtener a los criterios");
            a.show();
        }
        return criterios;
        }
    
    public static Criterio verificarID(int id){
        ArrayList<Criterio> criterios = readFromFile("criterios.txt");
        for(Criterio criterio: criterios){
            if(criterio.id == id)
                return criterio;
        }
        return null;
    }
    }
