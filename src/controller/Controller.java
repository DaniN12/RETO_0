package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

    @Override
    public List<String> obtenerConvocatoriasDeEnunciado(int idEnunciado) {
        // Aquí va la lógica para obtener las convocatorias desde la base de datos o servicio

        // Simulamos algunos datos para el enunciado con ID 1
        if (idEnunciado == 1) {
            List<String> convocatorias = new ArrayList<>();
            convocatorias.add("Convocatoria Enero 2024");
            convocatorias.add("Convocatoria Junio 2024");
            return convocatorias;
        } else {
            return new ArrayList<>();  // Si el enunciado no se ha usado en ninguna convocatoria
        }
    }

}
