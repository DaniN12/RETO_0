/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplication;

import Controller.Controller;
import Controller.IController;
import Model.ConvocatoriaExamen;
import Model.Enunciado;
import java.util.ArrayList;
import Model.UnidadDidactica;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
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
                    insertarUDyCE(c);
                    break;
                case 2:
                    anadirEnunciado(c);
                    break;
                case 3:
                    mostrarEnunciadosPorUD(c);
                    break;
                case 4:
                     consultarConvocatoria();
                    break;
                case 5:
                    visualizarDocumento();
                    break;
                case 6:
                    InsertarEnunciados(c);
                    break;
                case 0:
                     System.out.println("Adiós, que Dios te bendiga.");

            }

        } while (menu != 0);
    }

    public static void anadirEnunciado(Controller c) {

        Enunciado e = new Enunciado();

        do {
            e.setDatos();
            c.anadirEnunciado(e.getId(), e.getDescripcion(), e.getNivel(), e.isDisponible(), e.getRuta());

            do {
                System.out.println("Selecciona la unidad didactica a la que deseas añadir el enunciado: ");
                int id = arrayUDs(c);
                c.anadirEnunciadoAUd(e.getId(), id);
                System.out.println("¿Desea añadir mas enunciados a otras unidades didacticas? SI/NO");

            } while (!Util.introducirCadena().equalsIgnoreCase("NO"));

            do {
                String conv = arrayCEs(c);
                c.anadirEnunciadoACe(conv, e.getId());
                System.out.println("¿Desea añadir mas enunciados a otras convocatorias de examen? SI/NO");

            } while (!Util.introducirCadena().equalsIgnoreCase("NO"));

            System.out.println("¿Desea añadir mas enunciados? SI/NO");

        } while (!Util.introducirCadena().equalsIgnoreCase("NO"));

    }

    public static int arrayUDs(Controller c) {

        ArrayList<UnidadDidactica> uds = c.getUDs();

        for (UnidadDidactica ud : uds) {
            System.out.println(uds.indexOf(ud) + 1 + "- " + ud.getTitulo());

        }
        int id = Util.leerInt();
        return id;

    }

    public static ArrayList<Enunciado> arrayEnunciados(Controller c, int id) {

        ArrayList<Enunciado> ens = c.getEnunciados(id);
        for (Enunciado en : ens) {
            System.out.println("ID: " + en.getId());
            System.out.println("Descripción: " + en.getDescripcion());
            System.out.println("Nivel: " + en.getNivel());
            System.out.println("Disponible: " + en.isDisponible());
            System.out.println("Ruta: " + en.getRuta());
        }
        return ens;

    }

    public static String arrayCEs(Controller c) {

        ArrayList<ConvocatoriaExamen> ces = c.getCEs();

        System.out.println("Selecciona la convocatoria de examen a la que deseas añadir el enunciado: ");
        for (ConvocatoriaExamen ce : ces) {
            System.out.println(ces.indexOf(ce) + 1 + "- " + ce.getConvocatoria());

        }
        int conv = Util.leerInt();
        ConvocatoriaExamen ceSeleccionada = ces.get(conv - 1);

        return ceSeleccionada.getConvocatoria();

    }

    public static void InsertarEnunciados(Controller controller) {
        // Preguntar al usuario por la convocatoria
        controller.asignarEnunciado();
    }

    public static void insertarUDyCE(Controller c) {
        UnidadDidactica ud = new UnidadDidactica();
        ConvocatoriaExamen ce = new ConvocatoriaExamen();

        System.out.println("Quieres añadir unidad/es didáctica/s?");
        if (Util.introducirCadena().equalsIgnoreCase("si")) {
            do {
                ud.setDatos(c);

                c.registrarUD(ud.getId(), ud.getAcronimo(), ud.getTitulo(), ud.getEvaluacion(), ud.getDescripcion());

                System.out.println("¿Quieres añadir más Unidades Didácticas? ");
            } while (Util.introducirCadena().equalsIgnoreCase("si"));
        }

        System.out.println("Quieres añadir convocatoria/s de exámen?");
        if (Util.introducirCadena().equalsIgnoreCase("si")) {
            do {
                ce.setDatos();

                c.registrarConvocatoria(ce.getConvocatoria(), ce.getDescripcion(), ce.getFecha(), ce.getCurso());

                System.out.println("¿Quieres añadir más Convocatorias de Exámen? ");
            } while (Util.introducirCadena().equalsIgnoreCase("si"));

        }

    }

    public static void mostrarEnunciadosPorUD(Controller c) {

        ArrayList<Enunciado> ens = new ArrayList();

        do {

            System.out.println("Seleccione la unidad didactica deseada");
            int id = arrayUDs(c);
            ens = arrayEnunciados(c, id);
            System.out.println("¿Desea ver mas enunciados? SI/NO");

        } while (!Util.introducirCadena().equalsIgnoreCase("NO"));

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
    System.out.println("Introduzca el ID de un enunciado: ");
    id = Util.leerInt();  // Asegúrate de que leerInt maneja correctamente errores de entrada.

    Controller controller = new Controller();  // Instanciar el controlador
    String ruta = controller.obtenerRutaDocumentoEnunciado(id);  // Obtener la ruta del documento desde la base de datos

    // Imprimir la ruta para depuración
    System.out.println("Ruta del archivo: " + ruta);

    if (ruta != null && !ruta.isEmpty()) {
        File archivo = new File(ruta);
        System.out.println("Verificando existencia del archivo...");

        if (archivo.exists() && archivo.isFile()) {
            try {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(archivo);  // Abrir el archivo en la aplicación predeterminada
            } catch (IOException e) {
                System.out.println("Error al abrir el archivo: " + e.getMessage());
            } catch (UnsupportedOperationException e) {
                System.out.println("La operación no es soportada en este sistema.");
            }
        } else {
            System.out.println("El archivo no existe o no es un archivo válido.");
        }
    } else {
        System.out.println("Enunciado no encontrado o la ruta es inválida.");
    }
}
}
