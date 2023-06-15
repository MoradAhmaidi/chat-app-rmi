package chatapprmi.Client;

import chatapprmi.Common.ChatClientInterface;
import chatapprmi.Common.ChatServerInterface;
import chatapprmi.gui.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClient extends UnicastRemoteObject implements ChatClientInterface {
    
    private final ChatServerInterface server;
    private final ChatView chatView;
    
    public ChatView getChatClient(){
        return chatView;
    }
    
    public ChatClient(ChatServerInterface server,ChatView chatView)throws  RemoteException
    {
        super();
        this.server=server;
        this.chatView=chatView;
                
        server.registerClient(this);
    }

    @Override
    public  void receiveMessage(String msg) throws RemoteException {
        chatView.addClientMessage(msg);
    }

    @Override
    public  void sendMessage(String msg) throws RemoteException {
        server.broadcastMessage(this,msg);
    }

    @Override
    public void exit() throws RemoteException {
        server.unregisterClient(this);
    }
}

