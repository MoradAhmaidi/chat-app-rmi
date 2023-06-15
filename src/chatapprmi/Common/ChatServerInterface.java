package chatapprmi.Common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerInterface extends Remote {
    void registerClient(ChatClientInterface client) throws RemoteException;
    void unregisterClient(ChatClientInterface client) throws RemoteException;
    void broadcastMessage(ChatClientInterface client,String msg) throws RemoteException;
}
