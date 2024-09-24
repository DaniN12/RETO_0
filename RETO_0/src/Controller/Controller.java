/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConvocatoriaExamen;
import Model.Dificultad;
import Model.UnidadDidactica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements IController {

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;
    private final String CREARenunciado = "INSERT INTO Enunciado (id, descripcion, nivel, disponible, ruta) VALUES (?, ?, ?, ?, ?)";
    private final String ANADIRenunciadoAUD = "INSERT INTO UD_Enunciado VALUES (?, ?)";
    private final String GETuds = "select * from UnidadDidactica";
    private final String ANADIRenunciadoACe = "UPDATE ConvocatoriaExamen SET id_Enunciado = ? WHERE convocatoria = ?";
    private final String GETces = "select * from ConvocatoriaExamen";

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
    public ArrayList<UnidadDidactica> getUDs() {

        this.openConnection();
        ArrayList<UnidadDidactica> uds = new ArrayList<>();

        try {
            sentencia = conexion.prepareStatement(GETuds);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String acronimo = resultado.getString("acronimo");
                String titulo = resultado.getString("titulo");
                String evaluacion = resultado.getString("evaluacion");
                String descripcion = resultado.getString("descripcion");
                UnidadDidactica ud = new UnidadDidactica(id, acronimo, titulo, evaluacion, descripcion);
                uds.add(ud);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return uds;

    }

    @Override
    public boolean anadirEnunciadoACe(String convocatoria, int id_E) {
        boolean added = false;
        this.openConnection();

        try {

            sentencia = conexion.prepareStatement(ANADIRenunciadoACe);
            sentencia.setInt(1, id_E);
            sentencia.setString(2, convocatoria);
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
    public ArrayList<ConvocatoriaExamen> getCEs() {
        this.openConnection();
        ArrayList<ConvocatoriaExamen> ces = new ArrayList<>();

        try {
            sentencia = conexion.prepareStatement(GETces);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                String convocatoria = resultado.getString("convocatoria");
                String descripcion = resultado.getString("descripcion");
                java.sql.Timestamp timestamp = resultado.getTimestamp("fecha");
                LocalDate fecha = timestamp.toLocalDateTime().toLocalDate();
                String curso = resultado.getString("curso");
                ConvocatoriaExamen ce = new ConvocatoriaExamen(convocatoria, descripcion, fecha, curso);
                ces.add(ce);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ces;
    }

}
