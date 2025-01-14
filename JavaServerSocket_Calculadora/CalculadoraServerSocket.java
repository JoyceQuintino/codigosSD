import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraServerSocket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket welcomeSocket;
		DataOutputStream socketOutput;   	
	    DataInputStream socketInput;
	    BufferedReader socketEntrada;
	    //Inst�ncia da classe Calculadora que possui opera��es b�sicas de soma, subtra��o, divis�o e multiplica��o.
	    Calculadora calc = new Calculadora(); 
		try {
		  welcomeSocket = new ServerSocket(9090);
		  int i = 0; //n�mero de clientes
	  
	      System.out.println("Servidor no ar");
	      while(true) { 
	    	   //connectionSocket 
	           Socket connectionSocket = welcomeSocket.accept(); 
	           i++;
	           System.out.println ("Nova conex�o");
	           
	           //Interpretando dados do servidor
	           socketEntrada = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
               String operacao= socketEntrada.readLine();
               String oper1=socketEntrada.readLine();
               String oper2=socketEntrada.readLine();
               
               //Chamando a calculadora
               String result = "";
               if(Integer.parseInt(operacao)==1) {
               		result= ""+calc.soma(Double.parseDouble(oper1),Double.parseDouble(oper2));
               }else if(Integer.parseInt(operacao)==2) {
            	    result= ""+calc.subtracao(Double.parseDouble(oper1),Double.parseDouble(oper2));
               }else if(Integer.parseInt(operacao)==3) {
           	    	result= ""+calc.divisao(Double.parseDouble(oper1),Double.parseDouble(oper2));
               }else if(Integer.parseInt(operacao)==4) {
          	    	result= ""+calc.multiplicacao(Double.parseDouble(oper1),Double.parseDouble(oper2));
               }
               
               //Enviando dados para o servidor
               socketOutput= new DataOutputStream(connectionSocket.getOutputStream());     	
	           socketOutput.writeBytes(result+ '\n');
	           System.out.println (result);	           
	           socketOutput.flush();
	           socketOutput.close();
	                    
	      }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
	}

}
