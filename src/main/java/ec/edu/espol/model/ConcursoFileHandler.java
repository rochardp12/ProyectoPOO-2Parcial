package ec.edu.espol.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ConcursoFileHandler {
    private static final String FILE_NAME = "concursos.txt";

    public static void saveToFile(Concurso concurso) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bf.write(concurso.getId() + "|" + concurso.getNombre() + "|" + concurso.getFecha() + "|" + concurso.getTematica()
                    + "|" + concurso.getFechaInscripcion() + "|" + concurso.getFechaCierreInscripcion()
                    + "|" + concurso.getCostoInscripcion() + "\n");
        } catch (IOException ex) {
            // Manejo de excepciones específicas o lanzamiento de excepciones personalizadas
        }
    }

    public static ArrayList<Concurso> readFromFile(String nomfile) {
        ArrayList<Concurso> concursos = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(nomfile))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String[] arreglo = linea.split("\\|");
                String[] fech = arreglo[2].split("-");
                LocalDate fecha1 = LocalDate.of(Integer.parseInt(fech[0]), Integer.parseInt(fech[1]), Integer.parseInt(fech[2]));
                String[] fechIns = arreglo[4].split("-");
                LocalDate fecha2 = LocalDate.of(Integer.parseInt(fechIns[0]), Integer.parseInt(fechIns[1]), Integer.parseInt(fechIns[2]));
                String[] fechCie = arreglo[5].split("-");
                LocalDate fecha3 = LocalDate.of(Integer.parseInt(fechCie[0]), Integer.parseInt(fechCie[1]), Integer.parseInt(fechCie[2]));
                Concurso concurso = new Concurso(Integer.parseInt(arreglo[0]), arreglo[1], fecha1, fecha2, fecha3, arreglo[3], Double.parseDouble(arreglo[6]));
                concursos.add(concurso);
            }
        } catch (IOException ex) {
            System.out.println("No es posible obtener los concursos: " + ex.getMessage());
        }
        return concursos;
    }

}
