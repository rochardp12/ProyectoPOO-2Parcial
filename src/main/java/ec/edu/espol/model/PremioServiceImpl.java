/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class PremioServiceImpl implements PremioService {
    public void crearPremio(List<String> descripciones, Concurso concurso) throws CantidadException {
        if (descripciones == null || descripciones.isEmpty()) {
            throw new CantidadException("La lista de descripciones de premios está vacía");
        }
        
        for (String descripcion : descripciones) {
            Premio premio = new Premio(descripcion);
            concurso.agregarPremio(premio);
        }
    }

    @Override
    public void crearPremio(ArrayList<String> descripciones, Concurso concurso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}



