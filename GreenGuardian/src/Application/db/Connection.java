package Application.db;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class Connection {
	String BBDD;
	java.sql.Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	
	public Connection(String path) {
		BBDD = path;
	}
	
}
