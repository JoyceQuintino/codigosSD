import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

public class CalculadoraClientHTTP {

	public static void main(String[] args) {
		
		int num=1;
		
		do {
			// TODO Auto-generated method stub
			Scanner read = new Scanner(System.in);
			double oper1,oper2;
			int operacao; 
			String result="";
			
			System.out.printf("Informe o primeiro valor: \n");
			oper1 = read.nextDouble();
			System.out.printf("Informe o segundo valor: \n");
			oper2 = read.nextDouble();
			
			//1-somar 2-subtrair 3-multiplicar 4-dividir
			System.out.printf("Informe a operação:\n "
					+ "1 - Soma\n 2 - Subtração\n 3 - Divisão\n 4 - Multiplicação\n");
			operacao = read.nextInt();
			//O programa deve solicitar novamente os dados caso do denominador igual a 0 e a operação de divisão escolhida.
			if(operacao == 3 && oper2 == 0) {
				System.out.printf("Divisão impossível de ser realizada.\n");
				continue;
			}
		    try {

		       URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR");
		       HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		        conn.setReadTimeout(10000);
		        conn.setConnectTimeout(15000);
		        conn.setRequestMethod("POST");
		        conn.setDoInput(true);
		        conn.setDoOutput(true);

		        //ENVIO DOS PARAMETROS
		        OutputStream os = conn.getOutputStream();
		        BufferedWriter writer = new BufferedWriter(
		                new OutputStreamWriter(os, "UTF-8"));
		        writer.write("oper1="+oper1+"&oper2="+oper2+"&operacao="+operacao); //1-somar 2-subtrair 3-multiplicar 4-dividir
		        writer.flush();
		        writer.close();
		        os.close();

		        int responseCode=conn.getResponseCode();
		        if (responseCode == HttpsURLConnection.HTTP_OK) {

		            //RECBIMENTO DOS PARAMETROS


		            BufferedReader br = new BufferedReader(
		                    new InputStreamReader(conn.getInputStream(), "utf-8"));
		            StringBuilder response = new StringBuilder();
		            String responseLine = null;
		            while ((responseLine = br.readLine()) != null) {
		                response.append(responseLine.trim());
		            }
		            result = response.toString();
		            System.out.println("Resposta do Servidor PHP="+result);
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	        System.out.printf("Deseja continuar?\n 0 - Encerrar\n 1 - Continuar\n");
			num = read.nextInt();
			if(num==0) {
				System.out.printf("Fim!\n");
			}
		}while(num>0);
	}
}
