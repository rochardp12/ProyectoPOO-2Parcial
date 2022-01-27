/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author ROMMEL ZAMORA
 */
public class Concurso {
    private int id;
    private String nombre;
    private LocalDate fecha;
    private LocalDate fechaInscripcion;
    private LocalDate fechaCierreInscripcion;
    private String tematica;
    private Double costo;
    private ArrayList<Inscripcion> inscripciones;
    private ArrayList<Premio> premios;
    private ArrayList<Criterio> criterios;

    public Concurso(int id, String nombre, String fecha, String fechaInscripcion, String fechaCierreInscripcion, String tematica, Double costo) {
        this.id = id;
        this.nombre = nombre;
        
        this.fecha = LocalDate.parse(fecha);
        this.fechaInscripcion = LocalDate.parse(fechaInscripcion);
        this.fechaCierreInscripcion = LocalDate.parse(fechaCierreInscripcion);
        
        this.tematica = tematica;
        this.costo = costo;
        this.inscripciones = new ArrayList<>();
        this.premios = new ArrayList<>();
        this.criterios = new ArrayList<>();
    }
    

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public LocalDate getFechaCierreInscripcion() {
        return fechaCierreInscripcion;
    }

    public String getTematica() {
        return tematica;
    }

    public Double getCosto() {
        return costo;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public ArrayList<Premio> getPremios() {
        return premios;
    }

    public ArrayList<Criterio> getCriterios() {
        return criterios;
    }
    
    //##########Setters##########

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public void setCosto(Double costo) {
        if (costo>=0){
            this.costo=costo;
        }
    }

    public void setFecha(String fecha) {
        this.fecha = LocalDate.parse(fecha);
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = LocalDate.parse(fechaInscripcion);
    }

    public void setFechaCierreInscripcion(String fechaCierreInscripcion) {
        this.fechaCierreInscripcion = LocalDate.parse(fechaCierreInscripcion);
    }
    
    public void addInscripcion(Inscripcion i1){
        this.inscripciones.add(i1);
    }
    
    public void addPremio(Premio p1){
        this.premios.add(p1);
    }
    
    public void addCriterio(Criterio c1){
        this.criterios.add(c1);
    }
    
    @Override
    public String toString(){
        
        StringBuilder sb = new StringBuilder();
        for (Inscripcion i : this.inscripciones){
            sb.append("Inscripcion ");
            sb.append(i.getId());
            sb.append(":<Id de Mascota: ");
            sb.append(i.getIdMascotas());
            sb.append(", Descuento: ");
            sb.append(i.getDescuento());
            sb.append(">, "); 
        }
        
        StringBuilder sb_premio = new StringBuilder();
        for (Premio p : this.premios){
            sb_premio.append("Premio: ");
            sb_premio.append(p.getId());
            sb_premio.append(", ");
        }
        
        StringBuilder sb_criterio = new StringBuilder();
        for (Criterio c : this.criterios){
            sb_criterio.append("Criterio: ");
            sb_criterio.append(c.getId());
            sb_criterio.append(",");
        }
        
        return "Concurso "+this.id+" :{Nombre: "+this.nombre+", Fecha: "+this.fecha+", Fecha de inscripcion: [desde: "+this.fechaInscripcion+", hasta: "+this.fechaCierreInscripcion+"], Tematica: "+this.tematica+", Costo de Inscripcion: "+this.costo+", Inscripciones:["+sb.toString()+"], Premios:["+sb_premio.toString()+"], Criterios:["+sb_criterio.toString()+"]}";
    }
    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Concurso c=(Concurso)obj;
        return this.id==c.id;
    }
    
    public static Concurso nextConcurso(Scanner sc){
        int id_next;
        String nombre_next;
        String fecha_next;
        String fechaInscripcion_next;
        String fechaCierreInscripcion_next;
        String tematica_next;
        double costo_next;
        
        System.out.println("Ingrese el ID del Concurso:");
        id_next=sc.nextInt();
        System.out.println("Ingrese el nombre del Concurso:");
        nombre_next = sc.next();
        System.out.println("Ingrese la fecha en la que ocurrirá el Concurso:");
        fecha_next = sc.next();
        System.out.println("Ingrese la fecha de INICIO de las inscripciones del Concurso:");
        fechaInscripcion_next = sc.next();
        System.out.println("Ingrese la fecha de CIERRE de las inscripciones del Concurso:");
        fechaCierreInscripcion_next = sc.next();
        System.out.println("Ingrese la tematica del Concurso:");
        tematica_next = sc.next();
        System.out.println("Ingrese el costo del Concurso:");
        costo_next = sc.nextDouble();
        
        Concurso concurso_nuevo = new Concurso(id_next,nombre_next,fecha_next,fechaInscripcion_next,fechaCierreInscripcion_next,tematica_next,costo_next);
        return concurso_nuevo;
    }
    
    //files
    
    public void saveFile(String nomFile){
        try(PrintWriter pw=new PrintWriter(new FileOutputStream(new File(nomFile),true))){
            pw.println(this.toString());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        } 
    }
    
    public static ArrayList<Concurso> readFromFile(String nomFile){
        ArrayList<Concurso> lista=new ArrayList<>();
        try(Scanner sc=new Scanner(new File(nomFile))){
            while(sc.hasNextLine()){
                String linea=sc.nextLine();
                String[] tokens=linea.split("|");
                Concurso c=new Concurso(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],Double.parseDouble(tokens[6]));
                lista.add(c);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
}
