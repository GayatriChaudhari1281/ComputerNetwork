package CNLab;
import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCP_Client {

	static Socket connection;
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		String msg;
		try {
			InetAddress addr= InetAddress.getByName("Localhost");
			System.out.println(addr);
			connection=new Socket(addr,8037);
			DataOutputStream out=new DataOutputStream(connection.getOutputStream());
			DataInputStream in =new DataInputStream(connection.getInputStream());
			do
			{
				msg=sc.nextLine();
				out.writeUTF(msg);
				out.flush();
				String server=in.readUTF();
				System.out.println(server);
			}while(!msg.equalsIgnoreCase("exit"));
			
	      }
	      catch(Exception e) {
	         System.out.print("Whoops! It didn't work!\n");
	      }

	}

}
