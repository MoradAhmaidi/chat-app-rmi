package chatapprmi.Common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientInterface extends Remote {
    void receiveMessage(String msg) throws RemoteException;
    void sendMessage(String msg) throws RemoteException;
    void exit() throws RemoteException;
}