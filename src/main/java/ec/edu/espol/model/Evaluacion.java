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
        return nota;
    }

    public int getIdCriterio() {
        return idCriterio;
    }

    public Criterio getCriterio() {
        return criterio;
    }
    
}
