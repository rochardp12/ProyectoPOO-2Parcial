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
import ec.edu.espol.model.ConcursoFileHandler;
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

    public Concurso(int id, String nombre, LocalDate fecha, LocalDate fechaInscripcion,
                    LocalDate fechaCierreInscripcion, String tematica, double costoInscripcion) {
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

    // Getters y setters
    public void setId(int id) throws ConcursoException {
        if (verificarID(id) != null) {
            throw new ConcursoException("ID existente. Ingrese un nuevo ID.");
        }
        this.id = id;
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
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (this.getClass() != obj.getClass())
            return false;
        Concurso concurso = (Concurso) obj;
        if (!(Objects.equals(this.nombre, concurso.nombre)))
            return false;
        return Objects.equals(this.tematica, concurso.tematica);     
    }
    public static void crearConcurso(String nombreConcurso, LocalDate fecha, LocalDate fechaIns, LocalDate fechaCie,
                                     String tematica, double costo) {
        int id = Util.nextID("concursos.txt");
        Concurso concurso = new Concurso(id, nombreConcurso, fecha, fechaIns, fechaCie, tematica, costo);
        ConcursoFileHandler.saveToFile(concurso);
        // Mostrar mensaje de éxito o manejar excepciones si es necesario
    }
     public void agregarPremio(Premio premio) {
        premios.add(premio);
    }
    public static Concurso verificarID(int id) {
        ArrayList<Concurso> concursos = ConcursoFileHandler.readFromFile("concursos.txt");
        for (Concurso concurso : concursos) {
            if (concurso.getId() == id) {
                return concurso;
            }
        }
        return null;
    }
    
    public static Concurso verificarNombre(String nombreConcurso) {
    ArrayList<Concurso> concursos = ConcursoFileHandler.readFromFile("concursos.txt");
    for (Concurso concurso : concursos) {
        if (concurso.getNombre().equals(nombreConcurso)) {
            return concurso;
        }
    }
    return null;
    }


}