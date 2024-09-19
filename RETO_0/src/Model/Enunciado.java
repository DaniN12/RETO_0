/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import utilidades.Util;

public class Enunciado {

    private int id;
    private String descripcion;
    private Dificultad nivel;
    private boolean disponible;
    private String ruta;
    private ArrayList<UnidadDidactica> ud;
    private ArrayList<ConvocatoriaExamen> ce;

    public Enunciado() {
        this.ud = new ArrayList<>();
         this.ce = new ArrayList<>();
    }

    public Enunciado(int i, String des, Dificultad ni, boolean dis, String ru) {

        this.id = i;
        this.descripcion = des;
        this.nivel = ni;
        this.disponible = dis;
        this.ruta = ru;
    }

    // Getter para id
    public int getId() {
        return id;
    }

    // Setter para id
    public void setId(int id) {
        this.id = id;
    }

    // Getter para descripcion
    public String getDescripcion() {
        return descripcion;
    }

    // Setter para descripcion
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter para nivel
    public Dificultad getNivel() {
        return nivel;
    }

    // Setter para nivel
    public void setNivel(Dificultad nivel) {
        this.nivel = nivel;
    }

    // Getter para disponible
    public boolean isDisponible() {
        return disponible;
    }

    // Setter para disponible
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Getter para ruta
    public String getRuta() {
        return ruta;
    }

    // Setter para ruta
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public ArrayList<UnidadDidactica> getUd() {
        return ud;
    }

    public void setUd(ArrayList<UnidadDidactica> ud) {
        this.ud = ud;
    }

    public ArrayList<ConvocatoriaExamen> getCe() {
        return ce;
    }

    public void setCe(ArrayList<ConvocatoriaExamen> ce) {
        this.ce = ce;
    }

    public void setDatos() {
        String str = "";
        Boolean error = false;

        System.out.println("Introduce el id: ");
        this.id = Util.leerInt();
        System.out.println("Introduce la descripcion: ");
        this.descripcion = Util.introducirCadena();

        do {
            System.out.println(
                    "Introduzca el nivel de dificultad: (ALTA/MEDIA/BAJA)");
            str = Util.introducirCadena();
            try {
                this.nivel = Dificultad.valueOf(str.toUpperCase());

                error = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e);
                error = true;
            }
        } while (error);
        System.out.println("Introduce la disponibilidad: (SI/NO)");
        String resp = Util.introducirCadena();
         this.disponible = false;
        if (resp.equalsIgnoreCase("SI")) {
            disponible = true;
        } else {
            disponible = false;
        }
        System.out.println("Introduce la ruta: ");
        this.ruta = Util.introducirCadena();

       
    }
}
