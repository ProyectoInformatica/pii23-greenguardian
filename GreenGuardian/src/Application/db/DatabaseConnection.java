package Application.db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	String BBDDName;
	String url;
    String user;
    String password;
	java.sql.Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public DatabaseConnection(String url, String user, String password, String BBDDName) {
		this.url = url;
        this.user = user;
        this.password = password;
        this.BBDDName = BBDDName;
		try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
	}  
	
	public boolean sentenciaSQL(String sql) {
		try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            //conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
	}
	
	public PreparedStatement prepareStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }
	
	public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
	
	public ResultSet executeQuery(String sql) throws SQLException {
        try {
        	stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return rs;
    }	
}
