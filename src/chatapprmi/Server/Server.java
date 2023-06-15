package chatapprmi.Server;

import chatapprmi.Common.ChatServerInterface;
import chatapprmi.gui.CaptureView;
import chatapprmi.gui.ChatView;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    
    private String serverName;
    private final ChatView chatView;
    
    public Server(ChatView chatView) {
        this.chatView = chatView;
    }
    public String getServerName() {
        return serverName;
    }
    public void setServerName(String name) {
        serverName = name;
    }
    public static void main(String[] args) {
        try {
            ChatView chatView = new ChatView();
            Server server = new Server(chatView);
            
            CaptureView captureView = new CaptureView(chatView, true);
            captureView.setTitleText("Server login");
            captureView.setVisible(true);
            
            String serverName = captureView.getUsername();
            server.setServerName(serverName);
            
            
            chatView.setUsername(serverName);
            chatView.setVisible(true); 
            
            ChatServerInterface chatServer= new ChatServer(chatView);
            Registry registry= LocateRegistry.createRegistry(1099);
            registry.rebind("server",chatServer);
            chatView.setServer((ChatServer) chatServer);
            System.out.println("Chat server started.");
        } catch (Exception ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }
    }
}

