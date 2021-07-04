import java.rmi.Remote;
import java.rmi.RemoteException;

/*Toda interface que extende de Remote deve usar o RemoteException.
Existem um conjunto de problemas que podem acontecer, por exemplo, 
a rede cair, o objeto remoto cair e o RemoteException alerta sobre esses problemas.
*/
public interface ICalculadora extends Remote{

	public double soma(double a, double b) throws RemoteException;
	
	public double subtracao(double a, double b) throws RemoteException;
	
	public double divisao(double a, double b) throws RemoteException;
	
	public double multiplicacao(double a, double b) throws RemoteException;
}
