/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import ec.edu.espol.model.Persona;

/**
 *
 * @author Issac Maza
 */
public class Dueno extends Persona{
    private String direccion;
    private ArrayList<Mascota> mascotas;
    
    public  Dueno(int id, String nombres, String apellidos, String telefono, String email,String direccion){
        super(id,nombres,apellidos,telefono,email);
        this.direccion=direccion;
        this.mascotas= new ArrayList<>();
    }
       
    
   

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Override
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getNombres() {
        return nombres;
}

    @Override
    public String getApellidos() {
        return apellidos;
    }

    @Override
    public String getTelefono() {
        return telefono;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        StringBuilder bui = new StringBuilder();
        bui.append("Dueño{ id= ");
        bui.append(this.id);
        bui.append(", Nombres= ");
        bui.append(this.nombres);
        bui.append(", Apellidos= ");
        bui.append(this.apellidos);
        bui.append(", Telefono= ");
        bui.append(this.telefono);
        bui.append(", Email = ");
        bui.append(this.email);
        bui.append(", Direccion= ");
        bui.append(this.direccion);
        bui.append(", Macotas= ");
        for (Mascota masc: this.mascotas){
            bui.append(masc.toString());
            if(this.mascotas.size()!=this.mascotas.size()-1)
                bui.append(";");               
            }
        bui.append("]");
        return bui.toString();
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
