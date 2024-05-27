package Application.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Application.db.DatabaseConnection;
import Application.model.Session;
import Application.model.Usuario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControllerChat {
	@FXML
    private TextArea conversation;

    @FXML
    private TextField msg;
    
    
    private DatabaseConnection bbdd = new DatabaseConnection("jdbc:mariadb://195.235.211.197/piigreenguardian","piigreenguardian","gr33nguard1an","piigreenguardian");
    private int idRec;
    private boolean connectionSet = false;
    private boolean idSet = false;
    private ScheduledExecutorService executor;
    
    
    Usuario usuarioActual = Session.getUsuarioActual();
    
    public void setId(int idRec2) throws SQLException {
    	this.idRec = idRec2;
        //System.out.println("ID receptor establecido en ControllerChat: " + idRec);
        idSet = true;
        checkInitialization();
	}
    
    @FXML
    public void initialize() throws SQLException {
    	//System.out.println("initialize() en ControllerChat llamado");
    	connectionSet = true;
        checkInitialization();
        
    }
    
    private void checkInitialization() throws SQLException {
    	if (connectionSet && idSet) {
            try {
                initializeChat();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void initializeChat() throws SQLException {
    	 if (bbdd != null && bbdd.conn != null && !bbdd.conn.isClosed()) {
             loadChatHistory();
             executor = Executors.newScheduledThreadPool(1);
             executor.scheduleAtFixedRate(() -> Platform.runLater(this::loadChatHistory), 0, 5, TimeUnit.SECONDS);
             //System.out.println("Chat Actualizado");
         } else {
             System.err.println("Error: La conexión a la base de datos es nula en initializeChat.");
         }
    }
    
    private void loadChatHistory() {
    	try {
            if (bbdd == null || bbdd.conn == null || bbdd.conn.isClosed()) {
                System.err.println("Error: La conexión a la base de datos es nula en loadChatHistory.");
                return;
            }

            String query = "SELECT * FROM CHAT WHERE (ID_REMITENTE = ? AND ID_DESTINATARIO = ?) OR (ID_REMITENTE = ? AND ID_DESTINATARIO = ?) ORDER BY FECHA";
            try (PreparedStatement stmt = bbdd.prepareStatement(query)) {
                stmt.setInt(1, usuarioActual.getId());
                stmt.setInt(2, idRec);
                stmt.setInt(3, idRec);
                stmt.setInt(4, usuarioActual.getId());
                ResultSet rs = stmt.executeQuery();

                conversation.clear();
                while (rs.next()) {
                    String message = rs.getString("TEXTO");
                    conversation.appendText(message + "\n");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void SendMessege(ActionEvent event) {
    	try {
            if (bbdd == null || bbdd.conn == null || bbdd.conn.isClosed()) {
                System.err.println("Error: La conexión a la base de datos es nula en enviar un mensaje.");
                return;
            }

            String message = msg.getText();
            if (message.isEmpty()) {
                return;
            }

            String query = "INSERT INTO CHAT (ID_REMITENTE, ID_DESTINATARIO, TEXTO) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = bbdd.prepareStatement(query)) {
                stmt.setInt(1, usuarioActual.getId());
                stmt.setInt(2, idRec);
                stmt.setString(3, usuarioActual.getNombre() + ": " + message);
                stmt.executeUpdate();
            }

            msg.clear();
            loadChatHistory();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
    	if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }
    	
        try {
            if (bbdd != null) {
                bbdd.closeConnection();
                //System.out.println("Conexión a la base de datos cerrada en ControllerChat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
