package Application.db;

import java.sql.Connection;
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
	public java.sql.Connection conn = null;
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
	
	
	
	//METODOS PARA EL CHAT
	public static String getMesseges(int id_e, int id_r){
        String cadena = "";
        Connection conn = null;
        Statement stmt = null;
        String sql;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://195.235.211.197/piigreenguardian","piigreenguardian","gr33nguard1an");
            System.out.println("Connectado a la Base de Datos...");

            sql = "SELECT * FROM CHAT WHERE ID_REMITENTE= " + id_e + " AND ID_DESTINATARIO= " + id_r + " ORDER BY FECHA;";
            System.out.println("sql Select: "+sql);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            while(rs.next()){
                cadena = cadena + rs.getString("TEXTO") + "\n";
            }

            rs.close();
            stmt.close();

            //STEP 6: Cerrando conexion.
            conn.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return cadena;
    };
	
    public static String setMessege(int id_e, int id_r, String mensaje){
        String cadena = "";
        Connection conn = null;
        Statement stmt = null;
        String sql;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://195.235.211.197/piigreenguardian","piigreenguardian","gr33nguard1an");
            System.out.println("Connectado a la Base de Datos...");

            sql = "INSERT INTO CHAT (ID_REMITENTE, ID_DESTINATARIO, TEXTO) values("+id_e+", "+id_r+", '" + mensaje + "');";
            System.out.println("sql Insert: "+sql);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();

            //STEP 6: Cerrando conexion.
            conn.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return cadena;
    };
	
	
}
