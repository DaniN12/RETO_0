/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplication;

import Controller.Controller;
import Model.ConvocatoriaExamen;
import Model.UnidadDidactica;
import java.time.LocalDate;
import utilidades.Util;

/**
 *
 * @author 2dam
 */
public class Main {

    public static void main(String[] args) {
        Controller c = new Controller();
        Integer menu = 0;
        do {

            System.out.println("1.- Crear una unidad didáctica (Unidad) y convocatoria (Convocatoria) de examen. ");
            System.out.println("2.- Crear un enunciado de examen agregando las unidades didácticas que va a referir.\n"
                    + "\tTambién se asociará a este enunciado la convocatoria para la que se crea.  ");
            System.out.println("3.- Consultar los enunciados de examen en los que se trata una unidad didáctica concreta. ");
            System.out.println("4.- Consultar en que convocatorias se ha utilizado un enunciado concreto. ");
            System.out.println("5.- Visualizar el documento de texto asociado a un enunciado.  ");
            System.out.println("6.- Asignar un enunciado a una convocatoria. ");
            System.out.println("0.- Salir ");

            menu = Util.leerInt(0, 6);

            switch (menu) {

                case 1:
                    insertarUDyCE(c);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 0:

            }

        } while (menu != 0);
    }

    public static void insertarUDyCE (Controller c) {
        UnidadDidactica ud = new UnidadDidactica();
        ConvocatoriaExamen ce = new ConvocatoriaExamen();
        Integer id;
        /*
        do {
            ud.setDatos();

            c.registrarUD(ud.getId(), ud.getAcronimo(), ud.getTitulo(), ud.getEvaluacion(), ud.getDescripcion());

            System.out.println("¿Quieres añadir más Unidades Didácticas? ");
        } while (!Util.introducirCadena().equalsIgnoreCase("no"));  */      
        
        do {
            ce.setDatos();

            c.registrarConvocatoria(ce.getConvocatoria(), ce.getDescripcion(), ce.getFecha(), ce.getCurso());

            System.out.println("¿Quieres añadir más Convocatorias de Exámen? ");
        } while (!Util.introducirCadena().equalsIgnoreCase("no"));     
        
}

}
