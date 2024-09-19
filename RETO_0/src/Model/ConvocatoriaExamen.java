/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import utilidades.Util;

public class ConvocatoriaExamen {

    private String convocatoria;
    private String descripcion;
    private LocalDate fecha;
    private String curso;

    public ConvocatoriaExamen() {

    }

    public ConvocatoriaExamen(String co, String des, LocalDate fe, String cu) {

        this.convocatoria = co;
        this.descripcion = des;
        this.fecha = fe;
        this.curso = cu;

    }

    // Getter para convocatoria
    public String getConvocatoria() {
        return convocatoria;
    }

    // Setter para convocatoria
    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    // Getter para descripcion
    public String getDescripcion() {
        return descripcion;
    }

    // Setter para descripcion
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter para fecha
    public LocalDate getFecha() {
        return fecha;
    }

    // Setter para fecha
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    // Getter para curso
    public String getCurso() {
        return curso;
    }

    // Setter para curso
    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    public void setDatos() {
        LocalDate fecha;
        Boolean bien = false;
        
        System.out.println("Convocatoria de Exámen: ");
        this.convocatoria = Util.introducirCadena();
        System.out.println("Descripción breve de la Convocatoria de Exámen: ");
        this.descripcion =  Util.introducirCadena();
        do {
            System.out.println("Fecha de la Convocatoria de Exámen: (dd/MM/yyyy)");
            try {
                fecha = Util.leerFechaDMA();
                this.fecha = fecha;
                bien = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Intenta de nuevo.");
            }
        } while (!bien);
        System.out.println("Curso de la Convocatoria de Exámen: ");
        this.curso =  Util.introducirCadena();
        
}
    
}
