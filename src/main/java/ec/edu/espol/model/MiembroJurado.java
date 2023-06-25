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
 * @author Usuario
 */
public class MiembroJurado {
    private int id;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String email;
    private String perfilProfesional;
    private ArrayList<Evaluacion> evaluaciones;
    private MiembroJuradoWriter miembroJuradoWriter;

    //constructor
    
    public MiembroJurado(int id, String nombres, String apellidos, String telefono, String email, String perfilProfesional){
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.perfilProfesional = perfilProfesional;
        this.evaluaciones = new ArrayList<>();
    }
    public void save() {
        miembroJuradoWriter.saveMiembroJurado(this);
    }

    //setters

    public void setId(int id) {
        try{
            if(verificarID(id) != null)
                throw new IDMiembroJuradoException("ID existente. Ingrese ID nueva");
            this.id = id;
        }
        catch(IDMiembroJuradoException ex){
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

    public void setTelefono(String telefono) {
        if(telefono != null)
            this.telefono = telefono;
    }

    public void setEmail(String email) {
        if(email != null)
            this.email = email;
    }

    public void setPerfilProfesional(String perfilProfesional) {
        if(perfilProfesional != null)
            this.perfilProfesional = perfilProfesional;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        if(evaluaciones != null)
            this.evaluaciones = evaluaciones;
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

    public String getTelefono() {
        return this.telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPerfilProfesional() {
        return this.perfilProfesional;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return this.evaluaciones;
    }
    //comportamientos
    
    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if(this==obj)
            return true;
        if(this.getClass()!=obj.getClass())
            return false;
        MiembroJurado jurado = (MiembroJurado)obj;
        return Objects.equals(this.email,jurado.email);
    }
    
    public void saveFile(String nomfile){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(nomfile,true))){
            bf.write(this.id + "|" + this.nombres + "|" + this.apellidos + "|" + this.telefono + "|" + this.email + "|" + this.perfilProfesional + "|" + this.evaluaciones + "\n");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Miembro del Jurado agregado con éxito");
            a.show();
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No es posible registrar al Miembro del Jurado");
            a.show();
        }
    }
    
    public static void crearMiembroJurado(String nombres, String apellidos, String telefono, String email, String perfilProfesional){
        MiembroJurado jurado = new MiembroJurado(Util.nextID("miembroJurados.txt"), nombres, apellidos, telefono, email, perfilProfesional);
        jurado.saveFile("miembroJurados.txt");
    }
    
    public static ArrayList<MiembroJurado> readFromFile(String nomfile){
        ArrayList<MiembroJurado> jurados = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(nomfile))){
            String linea;
            while((linea = bf.readLine()) != null){
                String[] arreglo = linea.split("\\|");
                MiembroJurado jurado = new MiembroJurado(Integer.parseInt(arreglo[0]), arreglo[1], arreglo[2], arreglo[3], arreglo[4], arreglo[5]);
                jurados.add(jurado);
            }
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No es posible obtener a los Miembros del Jurado");
            a.show();
        }
        return jurados;
        }
    
    public static MiembroJurado verificarEmail(String email){
        ArrayList<MiembroJurado> jurados = readFromFile("miembroJurados.txt");
        for(MiembroJurado jurado: jurados){
            if(Objects.equals(jurado.email,email))
                return jurado;
        }
        return null;
    }
    
    public static MiembroJurado verificarID(int id){
        ArrayList<MiembroJurado> jurados = readFromFile("miembroJurados.txt");
        for(MiembroJurado jurado: jurados){
            if(jurado.id == id)
                return jurado;
        }
        return null;
    }

}
