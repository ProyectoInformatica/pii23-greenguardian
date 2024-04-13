package Application.db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
	String BBDDName;
	java.sql.Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Connection(String path) {
		BBDDName = path;
	}
	
	public boolean sentenciaSQL(String sql) {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:"+BBDDName);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return false;
		}
		return true;
	}
	
	public boolean sentenciaSQL_param(String sql, String nombre, String apellido, String dni, String telf,
			String contra, String rol, int id_user_asing) {
		try {
			Class.forName("org.sqlite.JDBC");
	        conn = DriverManager.getConnection("jdbc:sqlite:"+BBDDName);
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, nombre);
	        pstmt.setString(2, apellido);
	        pstmt.setString(3, dni);
	        pstmt.setString(4, telf);
	        pstmt.setString(5, contra);
	        pstmt.setString(6, rol);
	        pstmt.setInt(7, id_user_asing);
	        pstmt.executeUpdate();
	        conn.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return false;
		}
		return true;
	}
	
	public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
	
	public ResultSet executeQuery(String sql) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + BBDDName);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql); // Ejecutar la consulta y obtener el ResultSet
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return rs;
    }
}
