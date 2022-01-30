/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public static Dueno nextDueno(Scanner sc){
        sc.useDelimiter("\n");
        //ArrayList<Dueno> lista_d = Dueno.readFromFile("dueño.txt");
   
        //int id = lista_d.size() +1;
        int id = Util.nextID("dueños.txt");
        System.out.println("El id es:");
        System.out.println(id);
        
        System.out.println("Ingrese la direccion");
        String direccion = sc.next();
        
        System.out.println("Ingrese el nombres");
        String nombres = sc.next();
        
        System.out.println("Ingrese sus apellidos");
        String apellidos = sc.next();
        
        System.out.println("Ingrese su telefono ya sea movil o fijo");
        String telefono = sc.next();
        
        System.out.println("Ingrese un email");
        String email = sc.next();
        
        Dueno duen = new Dueno(id,nombres,apellidos,telefono,email,direccion);
        return duen;
    }
    
    public void saveFile(String nomFile) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile),true))){
            pw.println(Util.nextID(nomFile)+"|"+this.nombres + "|" + this.apellidos + "|" + this.telefono + "|" + this.email+"|"+this.direccion );
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static ArrayList<Dueno> readFromFile(String nomFile) {
        ArrayList<Dueno> dueños = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Dueno dueño = new Dueno(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],tokens[5]);
                dueños.add(dueño);
            }
        }
        catch(Exception ex){
                    System.out.println(ex.getMessage());

        }
        return dueños;
    }
    
    public static void  saveFile( ArrayList<Dueno> dueño , String nombres){
        try(PrintWriter pw= new PrintWriter(new FileOutputStream(new File(nombres),true))){
            for (Dueno d:  dueño ){
                pw.println(d.getId() + "|"+ d.getNombres()+ "|" + d.getApellidos() + "|"+ d.getTelefono()+ "|" + d.getEmail()+ "|"+ d.getDireccion());
            } 
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
}
