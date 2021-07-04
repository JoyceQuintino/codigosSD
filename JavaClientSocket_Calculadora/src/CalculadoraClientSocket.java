import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CalculadoraClientSocket {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		double oper1=10,oper2=20;
		int operacao=3; //1-somar 2-subtrair 3-dividir 4-multiplicar
		String result="";
		
        try {
        	//Conex�o com o Servidor
            Socket clientSocket = new Socket("192.168.0.123", 9090);
            //Cria um fluxo de sa�da dos dados
            //O getOutputStream() envia os dados para o servidor em bytes
            DataOutputStream socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream());
            
            //Enviando os dados
            socketSaidaServer.writeBytes(operacao+"\n");
            socketSaidaServer.writeBytes(oper1+ "\n");
            socketSaidaServer.writeBytes( oper2+ "\n");
            socketSaidaServer.flush(); //fo�a que os dados sejam enviados

            //Recebendo a resposta
            //� atrav�s do m�todo getInputStream() que o servidor consegue capturar o que o cliente est� enviado.
            //O objeto messageFromServer recebe os dados enviados para o servidor
            BufferedReader messageFromServer = new BufferedReader
                    (new InputStreamReader(clientSocket.getInputStream()));
            //Ler uma linha por vez dos dados enviados para o servidor e armazena na vari�vel result.
            result=messageFromServer.readLine();
            
            //Exibe os dados
            System.out.println("resultado="+result);
            //Fecha a conex�o com o servidor
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
