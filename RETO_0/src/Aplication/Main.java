/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplication;

import Controller.Controller;
import Model.Enunciado;
import utilidades.Util;

/**
 *
 * @author 2dam
 */
public class Main {

    public static void main(String[] args) {

        Integer menu = 0;
        Controller c = new Controller();
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
                    break;
                case 2:
                    anadirEnunciado(c);
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

    public static void anadirEnunciado(Controller c) {

        String resp = "";
        Enunciado e = new Enunciado();

        do {
            e.setDatos();
            c.anadirEnunciado(e.getId(), e.getDescripcion(), e.getNivel(), e.isDisponible(), e.getRuta());
            System.out.println("Selecciona la unidad didactica a la que deseas añadir el enunciado: ");
            
            int id = Util.leerInt();
            c.anadirEnunciadoAUd(e.getId(), id);
            System.out.println("¿Desea añadir mas enunciados? SI/NO");
            resp = Util.introducirCadena();

        } while (!resp.equalsIgnoreCase("NO"));

    }
}
