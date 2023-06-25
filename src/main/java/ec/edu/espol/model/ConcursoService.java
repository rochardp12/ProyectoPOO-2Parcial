/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

/**
 *
 * @author kevin
 */
public interface ConcursoService {
    Concurso verificarNombre(String nombre) throws NombreConcursoException;
    // Otros métodos relacionados con el servicio de concursos
}
