import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Calculadora  implements ICalculadora {

	private static final long serialVersionUID = 1L;
	
	private static int chamadas = 0;

	public double soma(double a, double b) throws RemoteException {
		System.out.println("M�todo soma chamado " + chamadas++);
		return a + b;
	}
	
	public double subtracao(double a, double b) throws RemoteException {
		System.out.println("M�todo subtra��o chamado " + chamadas++);
		return a - b;
	}
	
	public double divisao(double a, double b) throws RemoteException {
		System.out.println("M�todo divis�o chamado " + chamadas++);
		return a / b;
	}
	
	public double multiplicacao(double a, double b) throws RemoteException {
		System.out.println("M�todo multiplica��o chamado " + chamadas++);
		return a * b;
	}

	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException  {
		Calculadora calculadora = new Calculadora();		
		Registry reg = null;
		ICalculadora stub = (ICalculadora) UnicastRemoteObject.
				exportObject(calculadora, 1100);
		try {
			System.out.println("Creating registry...");
			reg = LocateRegistry.createRegistry(1099);
		} catch (Exception e) {
			try {
				reg = LocateRegistry.getRegistry(1099);
			} catch (Exception e1) {
				System.exit(0);
			}
		}
		reg.rebind("calculadora", stub);
	}
}
