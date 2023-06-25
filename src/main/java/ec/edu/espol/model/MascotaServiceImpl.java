/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import java.util.ArrayList;

public class MascotaServiceImpl implements MascotaService {
    private ArrayList<Mascota> mascotas;

    public MascotaServiceImpl() {
        this.mascotas = new ArrayList<>();
        // Lógica para cargar las mascotas desde el archivo o fuente de datos
        cargarMascotas();
    }

    private void cargarMascotas() {
        // Lógica para cargar las mascotas desde el archivo o fuente de datos
        // y agregarlas a la lista 'mascotas'
    }

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    // Implementa otros métodos de la interfaz MascotaService si los tienes

    @Override
    public Mascota verificarNombre(String nombre) throws NombreMascotaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
