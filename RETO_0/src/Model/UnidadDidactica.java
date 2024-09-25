/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Controller;
import utilidades.Util;

public class UnidadDidactica {

    private int id;
    private String acronimo;
    private String titulo;
    private String evaluacion;
    private String descripcion;
    
    public UnidadDidactica(){
        
    }
    
    public UnidadDidactica(int i, String ac, String ti, String ev, String des){
        
        this.id = i;
        this.acronimo = ac;
        this.titulo = ti;
        this.evaluacion = ev;
        this.descripcion = des;
    }

     // Getter para id
    public int getId() {
        return id;
    }

    // Setter para id
    public void setId(int id) {
        this.id = id;
    }

    // Getter para acronimo
    public String getAcronimo() {
        return acronimo;
    }

    // Setter para acronimo
    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    // Getter para titulo
    public String getTitulo() {
        return titulo;
    }

    // Setter para titulo
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Getter para evaluacion
    public String getEvaluacion() {
        return evaluacion;
    }

    // Setter para evaluacion
    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    // Getter para descripcion
    public String getDescripcion() {
        return descripcion;
    }

    // Setter para descripcion
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void getDatos(){
        
        System.out.println("");
    }
    public void setDatos() {
                System.out.println("ID de la Unidad Didáctica: ");
		this.id = Util.leerInt();
		System.out.println("Acrónimo de la Unidad Didáctica: ");
		this.acronimo =  Util.introducirCadena();
		System.out.println("Título de la Unidad Didáctica: ");
		this.titulo =  Util.introducirCadena();
		System.out.println("Evaluación de la Unidad Didáctica: ");
		this.evaluacion =  Util.introducirCadena();
                System.out.println("Descripción breve de la Unidad Didáctica: ");
		this.descripcion =  Util.introducirCadena();
	}
}
