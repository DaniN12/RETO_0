package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller implements IController {

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    private void openConnection() {
        try {
            // Registrar el driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String url = "jdbc:mysql://localhost:3306/examendb?serverTimezone=Europe/Madrid&useSSL=false";
            conexion = DriverManager.getConnection(url, "root", "abcd*1234");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC no encontrado: " + e.getMessage());
            e.printStackTrace();
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
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException error) {
            System.out.println("Error al intentar cerrar la conexión: " + error.getMessage());
            error.printStackTrace();
        }
    }

    @Override
    public String obtenerRutaDocumentoEnunciado(int idEnunciado) {
        String ruta = null;
        try {
            openConnection();  // Abrir conexión
            String query = "SELECT ruta FROM Enunciado WHERE id = ?";
            sentencia = conexion.prepareStatement(query);
            sentencia.setInt(1, idEnunciado);
            resultado = sentencia.executeQuery();
            
            if (resultado.next()) {
                ruta = resultado.getString("ruta");
            } else {
                System.out.println("Ruta no encontrada.");
            }
        } catch (SQLException error) {
            System.out.println("Error al consultar enunciado: " + error.getMessage());
            error.printStackTrace();
        } finally {
            closeConnection();  // Cerrar conexión
        }
        return ruta;
    }

    @Override
    public List<String> obtenerConvocatoriasDeEnunciado(int idEnunciado) {
        List<String> convocatorias = new ArrayList<>();
        try {
            openConnection();  // Abrir conexión
            String query = "SELECT c.convocatoria FROM ConvocatoriaExamen c " +
                           "JOIN Enunciado e ON c.id_Enunciado = e.id " +
                           "WHERE e.id = ?";
            sentencia = conexion.prepareStatement(query);
            sentencia.setInt(1, idEnunciado);
            resultado = sentencia.executeQuery();
            
            while (resultado.next()) {
                convocatorias.add(resultado.getString("convocatoria"));
            }
        } catch (SQLException error) {
            System.out.println("Error al consultar convocatorias: " + error.getMessage());
            error.printStackTrace();
        } finally {
            closeConnection();  // Cerrar conexión
        }
        return convocatorias;
    }
}
