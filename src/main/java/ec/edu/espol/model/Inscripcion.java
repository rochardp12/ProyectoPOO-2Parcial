/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import javafx.scene.control.Alert;

/**
 *
 * @author Usuario
 */
public class Inscripcion {
    private int id;
    private int idMascota;
    private Mascota mascota;
    private int idConcurso;
    private Concurso concurso;
    private double valor;
    private LocalDate fechaInscripcion;
    private ArrayList<Evaluacion> evaluaciones;
    //constructor
    
    public Inscripcion(int id, Mascota mascota, Concurso concurso, double valor, LocalDate fechaInscripcion){
        this.id = id;
        this.idMascota = mascota.getId();
        this.mascota = mascota;
        this.idConcurso = concurso.getId();
        this.concurso = concurso;
        this.valor = valor;
        this.fechaInscripcion = fechaInscripcion;
        this.evaluaciones = new ArrayList<>();
    }
    //setters

    public void setId(int id) {
        try{
            if(verificarID(id) != null)
                throw new IDInscripcionException("ID existente. Ingrese una nueva");
            this.id = id;
        }
        catch(IDInscripcionException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    public void setIdMascota(int idMascota) {
        try{
            if(Mascota.verificarID(idMascota) == null)
                throw new IDMascotaException("ID no existente. Ingrese correctamente");
            this.idMascota = idMascota;
        }
        catch(IDMascotaException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    public void setMascota(Mascota mascota) {
        if(mascota != null)
            this.mascota = mascota;
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

    public void setValor(double valor) {
        try{
            if(valor < 0)
                throw new CostoException("Ingrese un valor a pagar correcto");
            this.valor = valor;
        }
        catch(CostoException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        if(fechaInscripcion != null)
            this.fechaInscripcion = fechaInscripcion;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        if(evaluaciones != null)
            this.evaluaciones = evaluaciones;
    }
    //getters

    public int getId() {
        return this.id;
    }

    public int getIdMascota() {
        return this.idMascota;
    }

    public Mascota getMascota() {
        return this.mascota;
    }

    public int getIdConcurso() {
        return this.idConcurso;
    }

    public Concurso getConcurso() {
        return this.concurso;
    }

    public double getValor() {
        return this.valor;
    }

    public LocalDate getFechaInscripcion() {
        return this.fechaInscripcion;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return this.evaluaciones;
    }
    //comportamientos
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Inscripcion: ").append(this.id).append(" --> ");
        sb.append("ID Mascota: ").append(this.idMascota);
        sb.append(". ID Concurso: ").append(this.idConcurso);
        sb.append(". Valor: ").append(this.valor);
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
        Inscripcion ins = (Inscripcion)obj;
        return ((this.idMascota == ins.idMascota)&&(this.idConcurso == ins.idConcurso));
    }
    
    public void saveFile(String nomfile){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(nomfile,true))){
            bf.write(this.id + "|" + this.mascota.getId() + "|" + this.mascota.getNombre() + "|" + this.concurso.getId() + "|" + this.concurso.getNombre() + "|" + this.valor + "|" + this.fechaInscripcion + "|" + this.evaluaciones + "\n");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Inscripción agregada con éxito");
            a.show();
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No es posible registrar la inscripción");
            a.show();
        }
    }
    
    public static void crearInscripcion(Mascota mascota, Concurso concurso, double valor, LocalDate fechaInscripcion){
        Inscripcion inscripcion = new Inscripcion(Util.nextID("inscripciones.txt"), mascota, concurso, valor, fechaInscripcion);
        inscripcion.saveFile("inscripciones.txt");
    }
    
    public static ArrayList<Inscripcion> readFromFile(String nomfile){
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(nomfile))){
            String linea;
            while((linea = bf.readLine()) != null){
                String[] arreglo = linea.split("\\|");
                String[] fecha = arreglo[6].split("-");
                LocalDate fechaInscripcion = LocalDate.of(Integer.parseInt(fecha[0]),Integer.parseInt(fecha[1]),Integer.parseInt(fecha[2]));
                Inscripcion inscripcion = new Inscripcion(Integer.parseInt(arreglo[0]), Mascota.verificarNombre(arreglo[2]), Concurso.verificarNombre(arreglo[4]), Double.parseDouble(arreglo[5]), fechaInscripcion);
                inscripciones.add(inscripcion);
            }
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No es posible obtener las inscripciones");
            a.show();
        }
        return inscripciones;
    }
    
    public static Inscripcion verificarID(int id){
        ArrayList<Inscripcion> inscripciones = readFromFile("inscripciones.txt");
        for(Inscripcion inscripcion: inscripciones){
            if(inscripcion.id == id)
                return inscripcion;
        }
        return null;
    }
    

}
