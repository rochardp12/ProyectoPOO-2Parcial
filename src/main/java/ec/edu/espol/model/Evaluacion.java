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

/**
 *
 * @author Usuario
 */
public class Evaluacion {
    private int id;
    private int idInscripcion;
    private Inscripcion inscripcion;
    private int idMiembroJurado;
    private MiembroJurado miembroJurado;
    private double nota;
    private int idCriterio;
    private Criterio criterio;
    
    public Evaluacion(int id, MiembroJurado jurado, Inscripcion inscripcion, Criterio criterio, double nota){
        this.id = id;
        this.idInscripcion = inscripcion.getId();
        this.inscripcion = inscripcion;
        this.idMiembroJurado = jurado.getId();
        this.miembroJurado = jurado;
        this.nota = nota;
        this.idCriterio = criterio.getId();
        this.criterio = criterio;
        }
    //setters

    public void setId(int id) {
        try{
            if(verificarID(id) != null)
                throw new IDEvaluacionException("ID existente. Ingrese una nueva");
            this.id = id;
        }
        catch(IDEvaluacionException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    public void setIdInscripcion(int idInscripcion) {
        try{
            if(Inscripcion.verificarID(idInscripcion) == null)
                throw new IDInscripcionException("ID no existente. Ingrese correctamente");
            this.idInscripcion = idInscripcion;
        }
        catch(IDInscripcionException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    public void setInscripcion(Inscripcion inscripcion) {
        if(inscripcion != null)
            this.inscripcion = inscripcion;
    }

    public void setIdMiembroJurado(int idMiembroJurado) {
        try{
            if(MiembroJurado.verificarID(idMiembroJurado) == null)
                throw new IDMiembroJuradoException("ID no existente. Ingrese correctamente");
            this.idMiembroJurado = idMiembroJurado;
        }
        catch(IDMiembroJuradoException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    public void setMiembroJurado(MiembroJurado miembroJurado) {
        if(miembroJurado != null)
            this.miembroJurado = miembroJurado;
    }

    public void setNota(double nota) {
        try{
            if(nota < 0)
                throw new NotaException("Ingrese una Nota de evaluación correcta");
            this.nota = nota;
        }
        catch(NotaException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    public void setIdCriterio(int idCriterio) {
        try{
            if(Criterio.verificarID(idCriterio) == null)
                throw new IDCriterioException("ID no existente. Ingrese correctamente");
            this.idCriterio = idCriterio;
        }
        catch(IDCriterioException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    public void setCriterio(Criterio criterio) {
        if(criterio != null)
            this.criterio = criterio;
    }
    //getters

    public int getId() {
        return this.id;
    }

    public int getIdInscripcion() {
        return this.idInscripcion;
    }

    public Inscripcion getInscripcion() {
        return this.inscripcion;
    }

    public int getIdMiembroJurado() {
        return this.idMiembroJurado;
    }

    public MiembroJurado getMiembroJurado() {
        return this.miembroJurado;
    }

    public double getNota() {
        return this.nota;
    }

    public int getIdCriterio() {
        return this.idCriterio;
    }

    public Criterio getCriterio() {
        return this.criterio;
    }
    //comportamientos
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Evaluacion: ").append(this.id).append(" --> ");
        sb.append("ID Inscripcion: ").append(this.idInscripcion);
        sb.append(". ID Miembro del Jurado: ").append(this.idMiembroJurado);
        sb.append(". ID Criterio: ").append(this.idCriterio);
        sb.append("--> Nota: ").append(this.nota);
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
        Evaluacion evaluacion = (Evaluacion)obj;
        return (((evaluacion.idInscripcion == this.idInscripcion)&&(evaluacion.idMiembroJurado == this.idMiembroJurado))&&(evaluacion.idCriterio == this.idCriterio));
    }
    
    public void saveFile(String nomfile){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(nomfile,true))){
            bf.write(this.id + "|" + this.inscripcion.getId() + "|" + this.miembroJurado.getId() + "|" + this.criterio.getId() + "|" + this.nota + "\n");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Evaluación agregada con éxito");
            a.show();
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No es posible registrar la evaluación");
            a.show();
        }
    }
    
    public static void crearEvaluacion(MiembroJurado jurado, Inscripcion inscripcion, Criterio criterio, double nota){
        Evaluacion evaluacion = new Evaluacion(Util.nextID("evaluaciones.txt"), jurado, inscripcion, criterio, nota);
        evaluacion.saveFile("evaluaciones.txt");
    }
    
    public static ArrayList<Evaluacion> readFromFile(String nomfile){
        ArrayList<Evaluacion> evaluaciones = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(nomfile))){
            String linea;
            while((linea = bf.readLine()) != null){
                String[] arreglo = linea.split("\\|");
                Evaluacion evaluacion = new Evaluacion(Integer.parseInt(arreglo[0]), MiembroJurado.verificarID(Integer.parseInt(arreglo[2])),Inscripcion.verificarID(Integer.parseInt(arreglo[1])),Criterio.verificarID(Integer.parseInt(arreglo[3])),Double.parseDouble(arreglo[4]));
                evaluaciones.add(evaluacion);
            }
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No es posible obtener las evaluaciones");
            a.show();
        }
        return evaluaciones;
        }
    
    public static Evaluacion verificarID(int id){
        ArrayList<Evaluacion> evaluaciones = readFromFile("evaluaciones.txt");
        for(Evaluacion evaluacion: evaluaciones){
            if(evaluacion.id == id)
                return evaluacion;
        }
        return null;
    }
}

