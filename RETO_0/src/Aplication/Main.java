/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplication;

import Controller.Controller;
import utilidades.Util;

/**
 *
 * @author 2dam
 */
public class Main {

    public static void main(String[] args) {

        Integer menu = 0;
        Controller controller = new Controller();
        do {
            System.out.println("\t\tBIENVENIDO A FLUTTER\n"
                    + "MENÚ:\n"
                    + "1.- Crear unidad didáctica y convocatoria de examen.\n"
                    + "2.- Crear enunciado de examen agregando las unidades didácticas.\n"
                    + "3.- Consultar enunciados examen\n"
                    + "4.- Consultar convocatorias enunciado.\n"
                    + "5.- Visualizar el documento de texto asociado a un enunciado.\n"
                    + "6.- Asignar un enunciado a una convocatoria.\n"
                    + "0.- Salir\n"
                    + "Por favor seleccione una opción:");

            menu = Util.leerInt(0, 6);
            switch (menu) {

                case 1:
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
                    InsertarEnunciados(controller);
                    break;
                case 0:

            }

        } while (menu != 0);
    }

    public static void InsertarEnunciados(Controller controller) {
        // Preguntar al usuario por la convocatoria
        controller.asignarEnunciado();
    }

}
