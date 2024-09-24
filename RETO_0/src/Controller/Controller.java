/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor.
 */
package Controller;

import Model.ConvocatoriaExamen;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utilidades.Util;

public class Controller implements IController {

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    final String getConvocatoria = "SELECT Convocatoria FROM ConvocatoriaExamen where id_Enunciado is null";
    final String getEnunciado = "SELECT id, descripcion FROM Enunciado";
    final String UPDATEConvocatoria ="UPDATE ConvocatoriaExamen SET id_Enunciado = ? WHERE convocatoria = ?";

    private void openConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/examendb?serverTimezone=Europe/Madrid&useSSL=false";
            conexion = DriverManager.getConnection(url, "root", "abcd*1234");
        } catch (SQLException error) {
            System.out.println("Error al intentar abrir la BD: " + error.getMessage());
            error.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            if (sentencia != null) {
                sentencia.close();
            }
            if (sentencia != null) {
                conexion.close();
            }
        } catch (SQLException error) {
            System.out.println("Error al intentar cerrar la conexión: " + error.getMessage());
            error.printStackTrace();
        }
    }

    @Override
    public void asignarEnunciado() {
        this.openConnection();
        try {
            // 1. Mostrar convocatorias sin enunciado asignado
            sentencia = conexion.prepareStatement(getConvocatoria);
            resultado = sentencia.executeQuery();
            ArrayList<String> convocatorias = new ArrayList<>();

            System.out.println("Convocatorias sin enunciado asignado:");
            while (resultado.next()) {
                String convocatoria = resultado.getString("convocatoria");
                convocatorias.add(convocatoria);
                System.out.println(convocatoria);
            }

            if (convocatorias.isEmpty()) {
                System.out.println("No hay convocatorias sin enunciado.");
                return;
            }

            // 2. Preguntar al usuario por la convocatoria a modificar
            System.out.println("Introduzca la convocatoria a la que quiere asignar un enunciado (nombre): ");
            String nombreConvocatoria = Util.introducirCadena();
            if (!convocatorias.contains(nombreConvocatoria)) {
                System.out.println("La convocatoria introducida no existe o ya tiene un enunciado asignado.");
                return;
            }

            // 3. Mostrar todos los enunciados disponibles
            sentencia = conexion.prepareStatement(getEnunciado);
            resultado = sentencia.executeQuery();
            ArrayList<Integer> enunciados = new ArrayList<>();

            System.out.println("Enunciados disponibles:");
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String descripcion = resultado.getString("descripcion");
                enunciados.add(id);
                System.out.println("ID: " + id + " - Descripción: " + descripcion);
            }
            if (enunciados.isEmpty()) {
                System.out.println("No hay enunciados disponibles para asignar.");
                return;
            }

            // 4. Preguntar al usuario por el ID del enunciado a asignar
            System.out.println("Introduzca el ID del enunciado que desea asignar: ");
            int idEnunciado = Util.leerInt(); 
            if (!enunciados.contains(idEnunciado)) {
                System.out.println("El ID de enunciado introducido no es válido.");
                return;
            }

            // 5. Asignar el enunciado a la convocatoria
            sentencia = conexion.prepareStatement(UPDATEConvocatoria);
            sentencia.setInt(1, idEnunciado);
            sentencia.setString(2, nombreConvocatoria);

            int convocatoriaActualizada = sentencia.executeUpdate();
            if (convocatoriaActualizada > 0) {
                System.out.println("Enunciado asignado correctamente a la convocatoria " + nombreConvocatoria);
            } else {
                System.out.println("No se pudo asignar el enunciado a la convocatoria.");
            }

        } catch (SQLException e) {
            System.out.println("Error al asignar enunciado a la convocatoria.");
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

}
