/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class Enunciado {

    private int id;
    private String descripcion;
    private Dificultad nivel;
    private boolean disponible;
    private String ruta;

    public Enunciado() {

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
}
