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
import java.time.LocalDate;

public interface IController {
    
public boolean anadirEnunciado(int id, String descripcion, Dificultad nivel, boolean disponible, String ruta);

public boolean anadirEnunciadoAUd( int id_E, int id_UD);

public ArrayList<UnidadDidactica> getUDs();

public boolean anadirEnunciadoACe( String convocatoria, int id_E);

public ArrayList<ConvocatoriaExamen> getCEs();
  
public void asignarEnunciado();

public void registrarUD(Integer id, String acronimo, String titulo, String evaluacion, String descripcion);
    
public void registrarConvocatoria(String convocatoria, String descripcion, LocalDate fecha, String curso);

}
