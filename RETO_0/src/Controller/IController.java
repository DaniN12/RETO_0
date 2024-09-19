/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Dificultad;


public interface IController {
    
public boolean anadirEnunciado(int id, String descripcion, Dificultad nivel, boolean disponible, String ruta);

public boolean anadirEnunciadoAUd( int id_E, int id_UD);

public boolean mostrarUD(int id, String acronimo, String titulo, String evaluacion, String descripcion);
}
