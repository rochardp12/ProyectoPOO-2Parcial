/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import java.time.LocalDate;

/**
 *
 * @author kevin
 */
public class ConcursoServiceImpl implements ConcursoService {
    @Override
    public Concurso verificarNombre(String nombre) throws NombreConcursoException {
        if (nombre == null || nombre.isEmpty()) {
            throw new NombreConcursoException("El nombre del concurso no puede estar vacío");
        }
        
        // Lógica adicional para verificar el nombre del concurso
        
        // Si el nombre es válido, crear y retornar un nuevo objeto Concurso
        int id = generarIdConcurso(); // Supongamos que hay una lógica para generar un ID único para el concurso
        LocalDate fechaActual = LocalDate.now();
        Concurso concurso = new Concurso(id, nombre, fechaActual, fechaActual, fechaActual, "", 0.0);
        
        return concurso;
    }
    
    // Método ficticio para generar un ID único para el concurso
    private int generarIdConcurso() {
        // Lógica para generar un ID único
        return 0; // Implementa tu lógica adecuada para generar un ID único
    }
}






