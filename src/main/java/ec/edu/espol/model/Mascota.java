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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import javafx.scene.control.Alert;

/**
 *
 * @author Usuario
 */
public class Mascota {
    private int id;
    private String nombre;
    private String raza;
    private LocalDate fechaNacimiento;
    private String tipo;
    private int idDueno;
    private Dueno dueno;
    private ArrayList<Inscripcion> inscripciones;
    //constructor
    
    public Mascota(int id, String nombre, String raza, LocalDate fechaNacimiento, String tipo, Dueno dueno){
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
        this.idDueno = dueno.getId();
        this.dueno = dueno;
        this.inscripciones = new ArrayList<>();
    }
    //setters
    
    public void setId(int id) {
        try{
            if(verificarID(id) != null)
                throw new IDMascotaException("ID existente. Ingrese una nueva");
            this.id = id;
        }
        catch(IDMascotaException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    public void setNombre(String nombre) {
        if(nombre != null)
            this.nombre = nombre;
    }

    public void setRaza(String raza) {
        if(raza != null)
            this.raza = raza;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento){
        if(fechaNacimiento != null)
            this.fechaNacimiento = fechaNacimiento;
    }

    public void setTipo(String tipo) {
        if(tipo != null)
            this.tipo = tipo;
    }

    public void setIdDueno(int idDueno) {
        try{
            if(Dueno.verificarID(idDueno) == null)
                throw new IDDuenoException("ID no existente. Ingrese correctamente");
            this.idDueno = idDueno;
        }
        catch(IDDuenoException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    public void setDueno(Dueno dueno) {
        if(dueno != null)
            this.dueno = dueno;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        if(inscripciones != null)
            this.inscripciones = inscripciones;
    }
    //getters

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getRaza() {
        return this.raza;
    }

    public LocalDate getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public String getTipo() {
        return this.tipo;
    }

    public int getIdDueno() {
        return this.idDueno;
    }

    public Dueno getDueno() {
        return this.dueno;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return this.inscripciones;
    }
    //comportamientos
    
        
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Mascota: ").append(this.id).append(" --> ");
        sb.append("Nombre: ").append(this.nombre);
        sb.append(". Raza: ").append(this.raza);
        sb.append(". Fecha de Nacimiento: ").append(this.fechaNacimiento);
        sb.append(". Tipo: ").append(this.tipo);
        sb.append(". Inscripciones: ").append(this.inscripciones);
        sb.append("--> ID Dueño: ").append(this.idDueno);
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
        Mascota masc = (Mascota)obj;
        if(!(Objects.equals(masc.dueno.getEmail(), this.dueno.getEmail())))
            return false;
        return ((Objects.equals(masc.getNombre(),this.getNombre()))&&(Objects.equals(masc.getRaza(), this.getRaza())));
    }
    
    public void saveFile(String nomfile){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(nomfile,true))){
            bf.write(this.id + "|" + this.nombre + "|" + this.raza + "|" + this.fechaNacimiento + "|" + this.tipo + "|" + this.dueno.getId() + "|" + this.dueno.getEmail() + "\n");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Mascota agregada con éxito");
            a.show();
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No es posible registrar a la mascota");
            a.show();
        }
    }
    
    public static void crearMascota(String nombre, String raza, LocalDate nacimiento, String tipo, Dueno dueno){
        Mascota masc = new Mascota(Util.nextID("mascotas.txt"), nombre, raza, nacimiento, tipo, dueno);
        masc.saveFile("mascotas.txt");
    }
    
    public static ArrayList<Mascota> readFromFile(String nomfile){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(nomfile))){
            String linea;
            while((linea = bf.readLine()) != null){
                String[] arreglo = linea.split("\\|");
                String[] fecha = arreglo[3].split("-");
                LocalDate nacimiento = LocalDate.of(Integer.parseInt(fecha[0]),Integer.parseInt(fecha[1]),Integer.parseInt(fecha[2]));
                Mascota mascota = new Mascota(Integer.parseInt(arreglo[0]), arreglo[1], arreglo[2], nacimiento, arreglo[4], Dueno.verificarEmail(arreglo[6]));
                mascotas.add(mascota);
            }
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No es posible obtener a las mascotas");
            a.show();
        }
        return mascotas;
        }
    
    public static Mascota verificarNombre(String nombreMascota){
        ArrayList<Mascota> mascotas = readFromFile("mascotas.txt");
        for(Mascota mascota: mascotas){
            if(Objects.equals(mascota.nombre, nombreMascota))
                return mascota;
        }
        return null;
    }
    
    public static Mascota verificarID(int id){
        ArrayList<Mascota> mascotas = readFromFile("mascotas.txt");
        for(Mascota mascota: mascotas){
            if(mascota.id == id)
                return mascota;
        }
        return null;
    }
    
        public static void agregarImagen(String infoImagen,String nombreArchivo){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(nombreArchivo, true))){
            bf.write(Util.nextID("mascotas.txt") + "|" + infoImagen + "\n");
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"Error al subir imagen");
            a.show();
        }
    }
    
    public static String buscarImagen(Mascota mascota, String nombreArchivo) {
        try(BufferedReader bf = new BufferedReader(new FileReader(nombreArchivo))){
            String linea;
            while((linea = bf.readLine()) != null){
                String[] info = linea.split("\\|");
                if(Integer.parseInt(info[0]) == mascota.id)
                    return info[1];
            }
        } 
        catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No es posible cargar la imagen de la mascota");
            a.show();
        }
        return null;
    }
    
    public static void guardarImagen(File file){
        if(file != null){
                try{
                    String dest;
                    if(file.getPath().endsWith(".jpg")){
                        dest = System.getProperty("user.dir") + "/src/main/resources/imgMascotas/" + Util.nextID("mascotas.txt") + ".jpg";
                        agregarImagen(Util.nextID("mascotas.txt") + ".jpg", "imagenesMascotas.txt");
                    }
                    else if(file.getPath().endsWith(".png")){
                        dest = System.getProperty("user.dir") + "/src/main/resources/imgMascotas/" + Util.nextID("mascotas.txt") + ".png";
                        agregarImagen(Util.nextID("mascotas.txt") + ".png", "imagenesMascotas.txt");
                    }
                    else{
                        dest = System.getProperty("user.dir") + "/src/main/resources/imgMascotas/" + Util.nextID("mascotas.txt") + ".gif";
                        agregarImagen(Util.nextID("mascotas.txt") + ".gif", "imagenesMascotas.txt");
                    }
                    Path destino = Paths.get(dest);
                    String orig = file.getPath();
                    Path origen = Paths.get(orig);
                    Files.copy(origen, destino, REPLACE_EXISTING);
                }
                catch(IOException ex){
                    Alert a = new Alert(Alert.AlertType.ERROR, "ERROR al cargar imagen");
                    a.show();
                }
            }
    }
}
