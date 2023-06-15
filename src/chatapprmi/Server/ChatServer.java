package chatapprmi.Server;

import chatapprmi.Common.ChatClientInterface;
import chatapprmi.Common.ChatServerInterface;
import chatapprmi.gui.ChatView;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


public class ChatServer extends UnicastRemoteObject implements ChatServerInterface {
    
    private final List<ChatClientInterface> clients;
    private final ChatView chatView;
    
    public ChatServer(ChatView chatView) throws RemoteException
    {
        super();
        this.chatView=chatView;
        clients= new ArrayList<>();
    }
    @Override
    public synchronized  void registerClient(ChatClientInterface client) throws RemoteException {
        clients.add(client);
        
        System.out.println("Client registered: " + client.toString());
    }
    @Override
    public synchronized  void unregisterClient(ChatClientInterface client) throws RemoteException {
        clients.remove(client);
        
        System.out.println("Client Unregistered: " + client.toString());
    }

    @Override
    public synchronized void broadcastMessage(ChatClientInterface client,String msg) throws RemoteException {
        
        if( client!=null)
        {
            chatView.addClientMessage(msg);
        }
        for (ChatClientInterface cli:clients) {
            if( client==null || client.hashCode()!=cli.hashCode())
            {
                cli.receiveMessage(msg);
            }

        }
        
    }

}

