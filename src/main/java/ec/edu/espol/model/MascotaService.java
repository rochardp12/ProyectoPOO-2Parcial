/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

public interface MascotaService {
    Mascota verificarNombre(String nombre) throws NombreMascotaException;
    // Otros métodos relacionados con el servicio de mascotas
}
