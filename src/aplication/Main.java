/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import controller.Controller;
import controller.IController;
import utilidades.Util;

/**
 *
 * @author 2dam
 */
public class Main {

    public static void main(String[] args) {

        Integer menu = 0;
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
                    consultarEnunciado();
                    break;
                case 4:
                    consultarConvocatoria();
                    break;
                case 5:
                    visualizarDocumento();
                    break;
                case 6:
                    break;
                case 0:
                    System.out.println("Adiós, que Dios te bendiga.");
                    break;
                default:
                    System.out.println("El número introducido no es válido.");
            }

        } while (menu != 0);
    }

    private static void consultarEnunciado() {

    }

    private static void consultarConvocatoria() {
        int id;
        System.out.println("Introduzca el ID de un enunciado: ");
        id = Util.leerInt();
        
        IController controller = new Controller();  // Instanciar el controlador
        List<String> convocatorias = controller.obtenerConvocatoriasDeEnunciado(id);
        
        if (convocatorias.isEmpty()) {
            System.out.println("El enunciado no está asociado a ninguna convocatoria.");
        } else {
            System.out.println("Convocatorias asociadas al enunciado con ID " + id + ":");
            for (String convocatoria : convocatorias) {
                System.out.println("- " + convocatoria);
            }
        }
    }

    private static void visualizarDocumento() {
    int id;
    System.out.println("Introuzca el ID de un enunciado: ");
    id = Util.leerInt();
    
    IController controller = new Controller();  // Instanciar el controlador
    String ruta = controller.obtenerRutaDocumentoEnunciado(id);  // Obtener la ruta del documento desde la base de datos
    
    if (ruta != null) {
        File archivo = new File(ruta);
        if (archivo.exists() && archivo.isFile()) {
            System.out.println("Contenido del archivo:");
            try {
                BufferedReader reader = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);  // Mostrar cada línea del archivo
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo no existe o no es un archivo válido.");
        }
    } else {
        System.out.println("Enunciado no encontrado.");
    }
}


}