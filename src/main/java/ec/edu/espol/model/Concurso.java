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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import javafx.scene.control.Alert;

/**
 *
 * @author Usuario
 */
public class Concurso {
    private int id;
    private String nombre;
    private LocalDate fecha;
    private LocalDate fechaInscripcion;
    private LocalDate fechaCierreInscripcion;
    private String tematica;
    private double costoInscripcion;
    private ArrayList<Inscripcion> inscripciones;
    private ArrayList<Premio> premios;
    private ArrayList<Criterio> criterios;
    //constructor
    
    public Concurso(int id, String nombre, LocalDate fecha, LocalDate fechaInscripcion, LocalDate fechaCierreInscripcion, String tematica, double costoInscripcion){
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaCierreInscripcion = fechaCierreInscripcion;
        this.tematica = tematica;
        this.costoInscripcion = costoInscripcion;
        this.inscripciones = new ArrayList<>();
        this.premios = new ArrayList<>();
        this.criterios = new ArrayList<>();
    }
    //setters

    public void setId(int id) {
        try{
            if(verificarID(id) != null)
                throw new IDConcursoException("ID existente. Ingrese una nueva");
            this.id = id;
        }
        catch(IDConcursoException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    public void setNombre(String nombre){
        if(nombre != null)
            this.nombre = nombre;
    }

    public void setFecha(LocalDate fecha) {
        if(fecha != null)
            this.fecha = fecha;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        if(fechaInscripcion != null)
            this.fechaInscripcion = fechaInscripcion;
    }

    public void setFechaCierreInscripcion(LocalDate fechaCierreInscripcion) {
        if(fechaCierreInscripcion != null)
            this.fechaCierreInscripcion = fechaCierreInscripcion;
    }

    public void setTematica(String tematica) {
        if(tematica != null)
            this.tematica = tematica;
    }

    public void setCostoInscripcion(double costoInscripcion) {
        try{
            if(costoInscripcion < 0)
                throw new CostoException("Ingrese un costo correcto");
            this.costoInscripcion = costoInscripcion;
        }
        catch(CostoException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        if(inscripciones != null)
            this.inscripciones = inscripciones;
    }

    public void setPremios(ArrayList<Premio> premios) {
        if(premios != null)
            this.premios = premios;
    }

    public void setCriterios(ArrayList<Criterio> criterios) {
        if(criterios != null)
            this.criterios = criterios;
    }
    //getters

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public LocalDate getFechaInscripcion() {
        return this.fechaInscripcion;
    }

    public LocalDate getFechaCierreInscripcion() {
        return this.fechaCierreInscripcion;
    }

    public String getTematica() {
        return this.tematica;
    }

    public double getCostoInscripcion() {
        return this.costoInscripcion;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return this.inscripciones;
    }

    public ArrayList<Premio> getPremios() {
        return this.premios;
    }

    public ArrayList<Criterio> getCriterios() {
        return this.criterios;
    }
    //comportamientos
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Concurso: ").append(this.id).append(" --> ");
        sb.append("Nombre: ").append(this.nombre);
        sb.append(". Fecha: ").append(this.fecha);
        sb.append(". Tematica: ").append(this.tematica);
        sb.append(". Costo de inscripcion: ").append(this.costoInscripcion);
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
        Concurso concurso = (Concurso)obj;
        if(!(Objects.equals(this.nombre, concurso.nombre)))
            return false;
        return Objects.equals(this.tematica,concurso.tematica);
    }
    
    public void saveFile(String nomfile){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(nomfile,true))){
            bf.write(this.id + "|" + this.nombre + "|" + this.fecha + "|" + this.tematica + "|" + this.fechaInscripcion + "|" + this.fechaCierreInscripcion + "|" + this.costoInscripcion + "\n");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Concurso agregado con éxito");
            a.show();
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No es posible registrar al concurso");
            a.show();
        }
    }
    
    public static void crearConcurso(String nombreConcurso, LocalDate fecha, LocalDate fechaIns, LocalDate fechaCie, String tematica, double costo){ 
        Concurso concurso = new Concurso(Util.nextID("concursos.txt"), nombreConcurso, fecha, fechaIns, fechaCie, tematica, costo);
        concurso.saveFile("concursos.txt");
    }
    
    public static ArrayList<Concurso> readFromFile(String nomfile){
        ArrayList<Concurso> concursos = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(nomfile))){
            String linea;
            while((linea = bf.readLine()) != null){
                String[] arreglo = linea.split("\\|");
                String[] fech = arreglo[2].split("-");
                LocalDate fecha1 = LocalDate.of(Integer.parseInt(fech[0]), Integer.parseInt(fech[1]), Integer.parseInt(fech[2]));
                String[] fechIns = arreglo[4].split("-");
                LocalDate fecha2 = LocalDate.of(Integer.parseInt(fechIns[0]),Integer.parseInt(fechIns[1]),Integer.parseInt(fechIns[2]));
                String[] fechCie = arreglo[5].split("-");
                LocalDate fecha3 = LocalDate.of(Integer.parseInt(fechCie[0]),Integer.parseInt(fechCie[1]),Integer.parseInt(fechCie[2]));
                Concurso concurso = new Concurso(Integer.parseInt(arreglo[0]), arreglo[1], fecha1, fecha2, fecha3, arreglo[3], Double.parseDouble(arreglo[6]));
                concursos.add(concurso);
            }
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No es posible obtener a los concursos");
            a.show();
        }
        return concursos;
        }
    
    public static Concurso verificarNombre(String nombreConcurso){
        ArrayList<Concurso> concursos = readFromFile("concursos.txt");
        for(Concurso concurso: concursos){
            if(Objects.equals(concurso.nombre,nombreConcurso))
                return concurso;
        }
        return null;
    }
    
    public static Concurso verificarID(int id){
        ArrayList<Concurso> concursos = readFromFile("concursos.txt");
        for(Concurso concurso: concursos){
            if(concurso.id == id)
                return concurso;
        }
        return null;
    }
}
