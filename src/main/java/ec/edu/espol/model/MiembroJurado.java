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
    private int id;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String email;
    private String perfilProfesional;
    private ArrayList<Evaluacion> evaluaciones;
    

    public MiembroJurado(int id, String nombres, String apellidos, String telefono, String email, String perfilProfesional) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.perfilProfesional = perfilProfesional;
        this.evaluaciones=new ArrayList<>();
    }
    
    public void addEvaluaciones(){

        ArrayList<Evaluacion> tEvaluaciones=Evaluacion.readFromFile("evaluaciones.txt");
        for(Evaluacion e:tEvaluaciones){
            if(this.id==e.getIdMiembroJurado())
                evaluaciones.add(e);

      
        }
    }

    public void setId(int id) {
        try{
            if(obtenerID(id) != null)
                throw new IDMiembroJuradoException("ID existente. Ingrese ID nueva");
            
        }
        catch(IDMiembroJuradoException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
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

    public void setPerfil(String perfil) {
        if(perfilProfesional != null)
            this.perfilProfesional = perfilProfesional;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        if(evaluaciones != null)
            this.evaluaciones = evaluaciones;
    }
    
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

    public String toString() {
        return id + "|" + nombres + "|" + apellidos + "|" + telefono + "|" + email + "|" + perfil;
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
        MiembroJurado jurado =(MiembroJurado) obj;
        return Objects.equals(this.email,jurado.email);
    }
    
    public void saveFile(String nomFile){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(nomFile,true))){
            bf.write(this.id + "|" + this.nombres + "|" + this.apellidos + "|" + this.telefono + "|" + this.email + "|" + this.perfilProfesional + "|" + this.evaluaciones + "\n");
            Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Miembro del Jurado agregado con éxito");
            a.show();
        }
        catch(Exception ex){
            Alert a = new Alert(AlertType.ERROR,"No es posible registrar al Miembro del Jurado");
            a.show();
        }
    }
    
    public static void crearMiembroJurado(String nombres, String apellidos, String telefono, String email, String perfilProfesional){
        MiembroJurado jurado = new MiembroJurado(Util.nextID("miembroJurados.txt"), nombres, apellidos, telefono, email, perfilProfesional);
        jurado.saveFile("miembroJurados.txt");
    }
    
    public static ArrayList<MiembroJurado> readFromFile(String nomfile){
        ArrayList<MiembroJurado> lista=new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new File(nomFile))){
            String linea ;
            while(sc.hasNextLine()){
                String[] arreglo = linea.split("|");
                MiembroJurado jurado = new MiembroJurado(Integer.parseInt(arreglo[0]), arreglo[1], arreglo[2], arreglo[3], arreglo[4], arreglo[5]);
                lista.add(jurado);
            }
        }
        catch(Exception ex){
            Alert a = new Alert(AlertType.ERROR,"No es posible obtener a los Miembros del Jurado");
            a.show();
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
