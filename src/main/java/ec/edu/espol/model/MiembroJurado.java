/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

/**
 *
 * @author Issac Maza
 */
public class MiembroJurado {
    private String perfil;
    private ArrayList<Evaluacion> evaluaciones;
    

    public MiembroJurado(int id, String nombres, String apellidos, String telefono, String email, String perfil) {
        super(id,nombres,apellidos,telefono,email);
        this.perfil = perfil;
        this.evaluaciones=new ArrayList<>();
    }
    
    public void addEvaluaciones(){

        ArrayList<Evaluacion> tEvaluaciones=Evaluacion.readFromFile("evaluaciones.txt");
        for(Evaluacion e:tEvaluaciones){
            if(this.id==e.getIdMiembroJurado())
                evaluaciones.add(e);

      
        }
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

    public String getPerfil() {
        return perfil;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
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

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    @Override
    public String toString() {
        return id + "|" + nombres + "|" + apellidos + "|" + telefono + "|" + email + "|" + perfil;
    }

    @Override
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
        MiembroJurado r=(MiembroJurado) obj;
        return this.id == r.id;
    }
    
    public void saveFile(String nomFile){
        try(PrintWriter pw=new PrintWriter(new FileOutputStream(new File(nomFile),true))){
            pw.println(this.toString());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<MiembroJurado> readFromFile(String nomFile){
        ArrayList<MiembroJurado> lista=new ArrayList<>();
        try(Scanner sc=new Scanner(new File(nomFile))){
            while(sc.hasNextLine()){
                String linea=sc.nextLine();
                String[] tokens=linea.split("|");
                MiembroJurado mV=new MiembroJurado(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],tokens[5]);
                lista.add(mV);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
        
    public static MiembroJurado nextMiembroJ(Scanner sc){
        sc.useDelimiter("\n");
        int id=Util.nextID("miembroJurados.txt");
        System.out.println("Nombres");
        String nombres=sc.next();
        System.out.println("Apellidos");
        String apellidos=sc.next();
        System.out.println("Telefono");
        String telefono=sc.next();
        System.out.println("email");
        String email=sc.next();
        System.out.println("Perfil");
        String perfil=sc.next();
        MiembroJurado r=new MiembroJurado(id,nombres,apellidos,telefono,email,perfil);
        return r;
    }

}
