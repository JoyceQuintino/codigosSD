import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalculadoraCliente {
	
	public static void main(String[] args) {
		Registry reg = null;
		ICalculadora calc;
		double oper1,oper2;
		int operacao;
		
		int num=1; //inicializa com 1 apenas para o laço executar
		
		do {
			//Solicita os dados ao usuário a partir do teclado
			Scanner read = new Scanner(System.in);
			System.out.printf("Informe o primeiro valor: \n");
			oper1 = read.nextDouble();
			System.out.printf("Informe o segundo valor: \n");
			oper2 = read.nextDouble();
			//1-somar 2-subtrair 3-dividir 4-multiplicar
			System.out.printf("Informe a operação:\n "
					+ "1 - Soma\n 2 - Subtração\n 3 - Divisão\n 4 - Multiplicação\n");
			operacao = read.nextInt();
			//O programa deve solicitar novamente os dados caso do denominador igual a 0 e a operação de divisão escolhida.
			if(operacao == 3 && oper2 == 0) {
				System.out.printf("Divisão impossível de ser realizada.\n");
				continue;
			}
			try {
				reg = LocateRegistry.getRegistry(1099);
				calc = (ICalculadora) reg.lookup("calculadora");
				if(operacao==1) {
					System.out.println(calc.soma(oper1,oper2));
				}else if(operacao==2) {
					System.out.println(calc.subtracao(oper1,oper2));
				}else if(operacao==3) {
					System.out.println(calc.divisao(oper1,oper2));
				}else if(operacao==4) {
					System.out.println(calc.multiplicacao(oper1,oper2));
				}
			} catch (RemoteException | NotBoundException e) {
					System.out.println(e);
					System.exit(0);
			}
			//Pergunta ao usuário se ele deseja continuar ou encerrar 
			System.out.printf("Deseja continuar?\n 0 - Encerrar\n 1 - Continuar\n");
			num = read.nextInt();
			//Se a variável num for 0, o programa para de executar
			if(num==0) {
				System.out.printf("Fim!\n");
				break;
			}
		}while(num==1);
	}		
}
