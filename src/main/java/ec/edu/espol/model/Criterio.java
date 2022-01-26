/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;

/**
 *
 * @author Issac Maza
 */
public class Criterio {
    private int id;
    private String descripcion;
    private ArrayList<Evaluacion> evaluaciones;
    private int idConcurso;
    private Concurso concurso;
    //constructor
    
    public Criterio(int id, String descripcion, Concurso concurso){
        this.id = id;
        this.descripcion = descripcion;
        this.evaluaciones = new ArrayList<>();
        this.idConcurso = concurso.getId();
        this.concurso = concurso;
    }

    //setters
    public void setId(int id) {
        try{
            if(verificarID(id) != null)
                throw new IDCriterioException("ID existente. Ingrese una nueva");
            this.id = id;
        }
        catch(IDCriterioException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();

        }
    }

    public void setDescripcion(String descripcion) {
        if(descripcion != null)
            this.descripcion = descripcion;   
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        if(evaluaciones != null)
            this.evaluaciones = evaluaciones;
    }

    public void setIdConcurso(int idConcurso) {
        try{
            if(Concurso.verificarID(idConcurso)==null)
                throw new IDConcursoException("Id no existente. Ingrese uno correctamente");
            this.idConcurso = idConcurso;
        }
        catch(IDConcursoException ex){
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.show();
        }
        
    }

    public void setConcurso(Concurso concurso) {
        if(concurso != null)
            this.concurso = concurso;
    }

    public int getId() {
        return this.id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return this.evaluaciones;
    }

    public int getIdConcurso() {
        return this.idConcurso;
    }

    public Concurso getConcurso() {
        return this.concurso;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID Criterio: ").append(this.id).append(" --> ");
        sb.append("Descripcion: ").append(this.descripcion);
        sb.append(" --> ID Concurso: ").append(this.idConcurso);
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(this.getClass() != obj.getClass())
            return false;
        Criterio crit = (Criterio)obj;
        return Objects.equals(this.descripcion, crit.descripcion);
    }
    
    public void saveFile(String nomFile, int num){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(nomfile, true))){
            bf.write(this.id + "|" + this.descripcion + "|" + this.evaluaciones + "|" + this.concurso.getId() + "|" + this.concurso.getNombre() + "\n");
            if(num == 1){
                Alert a = new Alert(AlertType.CONFIRMATION, "Criterio agregado con éxito");
                a.show();
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static void crearCriterio(ArrayList<String> infoCriterios, Concurso concurso){
        for(int u=0; u < infoCriterios.size(); u++){
            Criterio criterio = new Criterio(Util.nextID("criterios.txt"), infoCriterios.get(u), concurso);
            criterio.saveFile("criterio.txt", infoCriterios.size());
        }
    }
    
    public static ArrayList<Criterio> readFromFile(String nomfile){
        ArrayList<Criterio> criterios = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(nomfile))){
            String linea;
            while((linea = bf.readLine()) != null){
                String[] arreglo = linea.split("\\|");
                Criterio criterio = new Criterio(Integer.parseInt(arreglo[0]), arreglo[1], Concurso.verificarNombre(arreglo[4]));
                criterios.add(criterio);
            
        }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return criterios;
    }
    
    
    public static Criterio verificarID(int id){
        ArrayList<Criterio> criterios = readFromFile("criterios.txt");
        for(Criterio criterio: criterios){
            if(criterio.id == id)
                return criterio;
        }
        return null
    }
}
