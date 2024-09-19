/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.time.LocalDate;


public interface IController {
    
    public void registrarUD(Integer id, String acronimo, String titulo, String evaluacion, String descripcion);
    
    public void registrarConvocatoria(String convocatoria, String descripcion, LocalDate fecha, String curso);
    
}
