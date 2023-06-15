package chatapprmi.Client;

import chatapprmi.Common.ChatServerInterface;
import chatapprmi.gui.CaptureView;
import chatapprmi.gui.ChatView;
import java.rmi.Naming;



public class Client {
    
    private String clientName;
    private final ChatView chatView;
    
    public Client(ChatView chatView) {
        this.chatView = chatView;
    }
    public String getClientName() {
        return clientName;
    }
    public void setClientName(String name) {
        clientName = name;
    }
    public static void main(String[] args) {
        try {
            ChatView chatView = new ChatView();
            Client client= new Client(chatView);
            
            CaptureView captureView = new CaptureView(chatView, true);
            captureView.setTitleText("Client login");
            captureView.setVisible(true);
            
            String clientName = captureView.getUsername();
            client.setClientName(clientName);
            
            chatView.setUsername(clientName);
             
            ChatServerInterface server =(ChatServerInterface)  Naming.lookup("rmi://localhost:1099/server");
            
            ChatClient chatClient = new ChatClient(server,chatView);

            chatView.setClient(chatClient);
            
            chatView.setVisible(true);
       
        } catch (Exception ex) {
            System.err.println("ERROR: " + ex.getMessage());
        } 
    }
}

