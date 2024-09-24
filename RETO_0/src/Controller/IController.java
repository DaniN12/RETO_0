/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConvocatoriaExamen;
import Model.Dificultad;
import Model.UnidadDidactica;
import java.util.ArrayList;


public interface IController {
    
public boolean anadirEnunciado(int id, String descripcion, Dificultad nivel, boolean disponible, String ruta);

public boolean anadirEnunciadoAUd( int id_E, int id_UD);

public ArrayList<UnidadDidactica> getUDs();

public boolean anadirEnunciadoACe( String convocatoria, int id_E);

public ArrayList<ConvocatoriaExamen> getCEs();
  
public void asignarEnunciado();
    

}
