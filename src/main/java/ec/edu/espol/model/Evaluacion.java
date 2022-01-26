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
public class Evaluacion {
    private int id;
    private int idInscripcion;
    private Inscripcion inscripcion;
    private int idMiembroJurado;
    private MiembroJurado miembroJurado;
    private double nota;
    private int idCriterio;
    private Criterio criterio;
    
    public Evaluacion(int id, MiembroJurado jurado, Inscripcion inscripcion, Criterio criterio, double nota){
        this.id = id;
        this.idInscripcion = idInscripcion;
        this.inscripcion = inscripcion;
        this.idMiembroJurado = jurado;
        this.miembroJurado = jurado;
        this.nota = nota;
        this.idCriterio = idCriterio;
        this.criterio = criterio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    public void setIdMiembroJurado(int idMiembroJurado) {
        this.idMiembroJurado = idMiembroJurado;
    }

    public void setMiembroJurado(MiembroJurado miembroJurado) {
        this.miembroJurado = miembroJurado;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }

    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }

    public int getId() {
        return id;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public int getIdMiembroJurado() {
        return idMiembroJurado;
    }

    public MiembroJurado getMiembroJurado() {
        return miembroJurado;
    }

    public double getNota() {
        return this.nota;
    }

    public int getIdCriterio() {
        return this.idCriterio;
    }

    public Criterio getCriterio() {
        return this.criterio;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Id Evaluacion: ").append(" --> ");
        sb.append("Id Inscripcion: ");
        sb.append(". ID Miembro del Jurado: ");
        sb.append(". ID Criterio: ");
        sb.append("--> Nota: ");
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj==null)
            return false;
        if(this == obj)
            return true;
        if(this.getClass()!=obj.getClass())
            return false;
        Evaluacion evaluacion = (Evaluacion)obj;
        return (((evaluacion.idInscripcion == this.idInscripcion)&&(evaluacion.idMiembroJurado == this.idMiembroJurado))&&(evaluacion.idCriterio == this.idCriterio));
    }
    
    public void saveFile(String nomfile){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(nomfile, true))){
            bf.write(this.id + "|" + this.inscripcion.getId() + "|" + this.miembroJurado.getId() + "|" + this.criterio.getId() + "|" + this.nota + "\n");
            Alert a = new Alert(AlertType.CONFIRMATION,"Evaluación agregada con éxito");
            a.show();
        }
        catch(IOException ex){
            Alert a = new Alert(AlertType.ERROR,"No es posible registrar la evaluación");
            a.show();
        }
    }
    
   public static void crearEvaluacion(MiembroJurado jurado, Inscripcion inscripcion, Criterio criterio, double nota){
        Evaluacion evaluacion = new Evaluacion(Util.nextID("evaluaciones.txt"), jurado, inscripcion, criterio, nota);
        evaluacion.saveFile("evaluaciones.txt");
   }
}
