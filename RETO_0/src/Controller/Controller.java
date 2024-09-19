/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Dificultad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller implements IController {

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;
    private final String CREARenunciado = "INSERT INTO Enunciado (id, descripcion, nivel, disponible, ruta) VALUES (?, ?, ?, ?, ?)";
    private final String ANADIRenunciadoAUD = "INSERT INTO UD_Enunciado (id_Enunciado, id_UD) VALUES (?, ?)";
    private final String MOSTRARunidadDidactica = "SELECT * from UnidadDidactica";

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
    public boolean anadirEnunciado(int id, String descripcion, Dificultad nivel, boolean disponible, String ruta) {

        boolean added = false;
        this.openConnection();

        try {

            sentencia = conexion.prepareStatement(CREARenunciado);
            sentencia.setInt(1, id);
            sentencia.setString(2, descripcion);
            sentencia.setString(3, String.valueOf(nivel));
            sentencia.setBoolean(4, disponible);
            sentencia.setString(5, ruta);
            if (sentencia.executeUpdate() > 0) {
                added = true;
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        } finally {

            this.closeConnection();
        }

        return added;

    }

    @Override
    public boolean anadirEnunciadoAUd(int id_E, int id_UD) {

        boolean added = false;
        this.openConnection();

        try {

            sentencia = conexion.prepareStatement(ANADIRenunciadoAUD);
            sentencia.setInt(1, id_E);
            sentencia.setInt(2, id_UD);
            if (sentencia.executeUpdate() > 0) {
                added = true;
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        } finally {

            this.closeConnection();
        }

        return added;
    }

    @Override
    public boolean mostrarUD(int id, String acronimo, String titulo, String evaluacion, String descripcion) {

        boolean showed = false;

        this.openConnection();

        try {

            sentencia = conexion.prepareStatement(MOSTRARunidadDidactica);
            sentencia.setInt(1, id);
            sentencia.setString(2, acronimo);
            sentencia.setString(3, titulo);
            sentencia.setString(4, evaluacion);
            sentencia.setString(5, descripcion);
            if (sentencia.executeUpdate() > 0) {
                showed = true;
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        } finally {

            this.closeConnection();
        }

        return showed;
    }

}
