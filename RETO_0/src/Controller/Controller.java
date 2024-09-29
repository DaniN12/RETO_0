/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor.
 */
package Controller;

import Model.ConvocatoriaExamen;
import Model.Dificultad;
import Model.Enunciado;
import Model.UnidadDidactica;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import utilidades.Util;
import java.util.List;

public class Controller implements IController {

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;
    private final String CREARenunciado = "INSERT INTO Enunciado (id, descripcion, nivel, disponible, ruta) VALUES (?, ?, ?, ?, ?)";
    private final String ANADIRenunciadoAUD = "INSERT INTO UD_Enunciado VALUES (?, ?)";
    private final String GETuds = "select * from UnidadDidactica";
    private final String ANADIRenunciadoACe = "UPDATE ConvocatoriaExamen SET id_Enunciado = ? WHERE convocatoria = ?";
    private final String GETces = "select * from ConvocatoriaExamen";
    final String getConvocatoria = "SELECT Convocatoria FROM ConvocatoriaExamen where id_Enunciado is null";
    final String getEnunciado = "SELECT id, descripcion FROM Enunciado";
    private final String GETEnunciadoPorUD = "SELECT e.* "
            + "FROM Enunciado e "
            + "JOIN UD_Enunciado ud_en ON e.id = ud_en.id_Enunciado "
            + "JOIN UnidadDidactica ud ON ud_en.id_UD = ud.id "
            + "WHERE ud.id = ?";
    final String UPDATEConvocatoria = "UPDATE ConvocatoriaExamen SET id_Enunciado = ? WHERE convocatoria = ?";
    final String INSERTARud = "INSERT INTO UnidadDidactica VALUES (?,?,?,?,?)";
    final String INSERTARce = "INSERT INTO ConvocatoriaExamen (convocatoria, descripcion, fecha, curso) VALUES (?,?,?,?)";
    final String GetUltimoIdUD = "call GetNextIDFromUD(?)";

    @Override
    public void registrarUD(Integer id, String acronimo, String titulo, String evaluacion, String descripcion) {
        this.openConnection();

        try {
            sentencia = conexion.prepareStatement(INSERTARud);

            sentencia.setString(5, descripcion);
            sentencia.setString(4, evaluacion);
            sentencia.setString(3, titulo);
            sentencia.setString(2, acronimo);
            sentencia.setInt(1, id);

            sentencia.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }

    }

    @Override
    public void registrarConvocatoria(String convocatoria, String descripcion, LocalDate fecha, String curso) {
        this.openConnection();

        try {
            sentencia = conexion.prepareStatement(INSERTARce);

            sentencia.setString(2, descripcion);
            sentencia.setString(4, curso);
            sentencia.setDate(3, Date.valueOf(fecha));
            sentencia.setString(1, convocatoria);

            sentencia.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }

    }

    private void openConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/examendb?serverTimezone=Europe/Madrid&useSSL=false";
            conexion = DriverManager.getConnection(url, "root", "abcd*1234");
        } catch (SQLException error) {
            System.out.println("Error al intentar abrir la BD: " + error.getMessage());
            error.printStackTrace();
        }
    }

    @Override
    public String[] getUltimoIdUD(String titulo) {
        this.openConnection();
        ResultSet rs = null;
        String acronimo = "";
        int id = 0;

        try {
            sentencia = conexion.prepareStatement(GetUltimoIdUD);

            sentencia.setString(1, titulo);

            rs = sentencia.executeQuery();

            if (rs.next()) {
                acronimo = rs.getString("NextID");
                id = rs.getInt("LastID");
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta de la BD");
            e.printStackTrace();
        }

        return new String[]{acronimo, Integer.toString(id)};
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

    @Override
    public ArrayList<Enunciado> getEnunciados(int id_UD) {

        this.openConnection();
        ArrayList<Enunciado> enunciados = new ArrayList<>();

        try {
            sentencia = conexion.prepareStatement(GETEnunciadoPorUD);
            sentencia.setInt(1, id_UD);
            resultado = sentencia.executeQuery();

            System.out.println("Enunciados:");
            while (resultado.next()) {

                int id = resultado.getInt("id");
                String descripcion = resultado.getString("descripcion");
                String dificultadStr = resultado.getString("nivel");
                Boolean disponible = resultado.getBoolean("disponible");
                String ruta = resultado.getString("ruta");
                Dificultad dificultad = Dificultad.valueOf(dificultadStr);
                Enunciado en = new Enunciado(id, descripcion, dificultad, disponible, ruta);
                enunciados.add(en);
            }
            if (enunciados.isEmpty()) {
                System.out.println("No hay enunciados disponibles para asignar.");

            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enunciados;
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
