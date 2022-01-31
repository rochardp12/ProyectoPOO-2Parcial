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
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import javafx.scene.control.Alert;

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
    //constructor
    
    public Dueno(int id, String nombres, String apellidos, String direccion, String telefono, String email){
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.mascotas = new ArrayList<>();
    }
    //setters

    public void setId(int id) {
        try{
            if(verificarID(id) != null)
                throw new IDDuenoException("ID existente. Ingrese una nueva");
            this.id = id;
        }
        catch(IDDuenoException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    public void setNombres(String nombres) {
        if(nombres != null)
            this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        if(apellidos != null)
            this.apellidos = apellidos;
    }

    public void setDireccion(String direccion){
        if(direccion != null)
            this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        if(telefono != null)
            this.telefono = telefono;
    }

    public void setEmail(String email) {
        try{
            if(verificarEmail(email) != null)
                throw new EmailDuenoException("E-mail existente. Ingrese uno nuevo");
            this.email = email;
        }
        catch(EmailDuenoException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            a.show();
        }
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        if(mascotas != null)
            this.mascotas = mascotas;
    }
    //getters

    public int getId() {
        return this.id;
    }

    public String getNombres() {
        return this.nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public ArrayList<Mascota> getMascotas() {
        return this.mascotas;
    }
    //comportamientos 
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Dueño: ").append(this.id).append(" --> ");
        sb.append("Nombres: ").append(this.nombres);
        sb.append(". Apellidos: ").append(this.apellidos);
        sb.append(". Direccion: ").append(this.direccion);
        sb.append(". Telefono: ").append(this.telefono);
        sb.append(". Email: ").append(this.email);
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
        Dueno dueno = (Dueno)obj;
        return Objects.equals(this.email,dueno.email);
    }
    
    public void saveFile(String nomfile){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(nomfile,true))){
            bf.write(this.id + "|" + this.nombres + "|" + this.apellidos + "|" + this.direccion + "|" + this.telefono + "|" + this.email + "\n");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Dueño agregado con éxito");
            a.show();
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No es posible registrar al dueño");
            a.show();
        }
    }
    
    public static void crearDueno(String nombres, String apellidos, String direccion, String telefono, String email){
        Dueno dueno = new Dueno(Util.nextID("dueños.txt"),nombres, apellidos, direccion, telefono, email);
        dueno.saveFile("dueños.txt");
    }
    
    public static ArrayList<Dueno> readFromFile(String nomfile){
        ArrayList<Dueno> duenos = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(nomfile))){
            String linea;
            while((linea = bf.readLine()) != null){
                String[] arreglo = linea.split("\\|");
                Dueno dueno = new Dueno(Integer.parseInt(arreglo[0]), arreglo[1], arreglo[2], arreglo[3], arreglo[4], arreglo[5]);
                duenos.add(dueno);
            }
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No es posible obtener a los dueños");
            a.show();
        }
        return duenos;
        }
    
    public static Dueno verificarEmail(String email){
        ArrayList<Dueno> duenos = readFromFile("dueños.txt");
        for(Dueno dueno: duenos){
            if(Objects.equals(dueno.email,email))
                return dueno;
        }
        return null;
    }
    
    public static Dueno verificarID(int id){
<<<<<<< HEAD
       ArrayList<Dueno> duenos = readFromFile("dueños.txt");
       for(Dueno dueno: duenos){
           if(dueno.id == id)
               return dueno;
       }
       return null; 
   } 
}
=======
        ArrayList<Dueno> duenos = readFromFile("dueños.txt");
        for(Dueno dueno: duenos){
            if(dueno.id == id)
                return dueno;
        }
        return null;
    }
}
>>>>>>> 6444ffadd01960597549916b08fd0a435b9e6c6f
