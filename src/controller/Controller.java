package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Controller implements IController{

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

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
    public String obtenerRutaDocumentoEnunciado(int idEnunciado) {
        String ruta = null;
        try {
            openConnection();  // Abrir conexión
            String query = "SELECT ruta FROM Enunciado WHERE id_Enunciado = ?";
            sentencia = conexion.prepareStatement(query);
            sentencia.setInt(1, idEnunciado);
            resultado = sentencia.executeQuery();
            
            if (resultado.next()) {
                ruta = resultado.getString("ruta");
            } else {
                System.out.println("Enunciado no encontrado.");
            }
        } catch (SQLException error) {
            System.out.println("Error al consultar enunciado: " + error.getMessage());
            error.printStackTrace();
        } finally {
            closeConnection();  // Cerrar conexión
        }
        return ruta;
    }

}
