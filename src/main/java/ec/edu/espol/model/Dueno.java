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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Issac Maza
 */
public class Dueno {
    private int id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String email;
    private ArrayList<Mascota> mascotas;
    
    public  Dueno(int id, String nombres, String apellidos,String direccion, String telefono, String email){
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion=direccion;
        this.telefono = telefono;
        this.email = email;
        this.mascotas= new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
    
    public int getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
}

    public String getApellidos() {
        return apellidos;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }
    
    public ArrayList<Mascota> getMascotas() {
        return this.mascotas;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dueño{ id= ").append(this.id);
        sb.append(", Nombres= ").append(this.nombres);
        sb.append(", Apellidos= ").append(this.apellidos);
        sb.append(", Telefono= ").append(this.telefono);
        sb.append(", Email = ").append(this.email);
        sb.append(", Direccion= ").append(this.direccion);
        sb.append(", Macotas= ");
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj==null)
           return false;
        if(this==obj)
           return true;
        if(this.getClass()!=obj.getClass())
           return false;
        Dueno dueno = (Dueno)obj;
        return Objects.equals(this.email, dueno.email);
    }
    
    public void saveFile(String nomfile) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(nomfile,true))){
            bf.write(this.id +"|"+this.nombres + "|" + this.apellidos + "|" + this.direccion + "|" + this.telefono + "|" + this.email+ "\n");
            Alert a = new Alert(AlertType.CONFIRMATION,"Dueño agregado con éxito");
            a.show();
        } catch (Exception ex) {
            Alert a = new Alert(AlertType.ERROR,"No es posible registrar al dueño");
            a.show();
        }
    }
    
    public static void crearDueno(String nombres, String apellidos, String direccion, String telefono, String email){
        Dueno dueno = new Dueno(Util.nextID("dueños.txt"),nombres, apellidos, direccion, telefono, email);
        dueno.saveFile("dueños.txt");
    }
    
    
    public static ArrayList<Dueno> readFromFile(String nomfile) {
        ArrayList<Dueno> duenos = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(nomfile))){
            String linea ;
            while((linea = bf.readLine()) != null){
                String[] arreglo = linea.split("\\|");
                Dueno dueno = new Dueno(Integer.parseInt(arreglo[0]), arreglo[1], arreglo[2], arreglo[3], arreglo[4], arreglo[5]);
                duenos.add(dueno);
            }
        }
        catch(Exception ex){
            Alert a = new Alert(AlertType.ERROR,"No es posible obtener a los dueños");
            a.show();

        }
        return duenos;
    }
    
    public static Dueno obtenerDuenoEmail(String email){
        ArrayList<Dueno> duenos = readFromFile("dueños.txt");
        for(Dueno dueno: duenos){
            while( dueno.email == email)
                return dueno;
        }
        return null;
    }
   
    public static Dueno verificarID(int id){
       ArrayList<Dueno> duenos = readFromFile("dueños.txt");
       for(Dueno dueno: duenos){
           while(Objects.equals(dueno.id, id))
               return dueno;
       }
       return null; 
   } 
}
