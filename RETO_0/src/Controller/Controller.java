/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Controller implements IController{

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;
    
    final String INSERTARud = "INSERT INTO UnidadDidactica VALUES (?,?,?,?,?)";
    final String INSERTARce = "INSERT INTO ConvocatoriaExamen VALUES (?,?,?,?,?)";
    
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
	public void registrarConvocatoria(Integer id, String convocatoria, String descripcion, LocalDate fecha, String curso) {
		this.openConnection();
		
		try {
			sentencia = conexion.prepareStatement(INSERTARce);
			
			sentencia.setString(2, descripcion);
			sentencia.setString(4, curso);
			sentencia.setString(3, fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			sentencia.setString(1, convocatoria);
			sentencia.setInt(5, id);
			
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
}
