package CNLab;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCP_Server {
   public static void main(String args[]) {
	   ServerSocket Serversocket;
	   String msg;
	   Scanner sc=new Scanner(System.in);
      try {
    	  Serversocket=new ServerSocket(8037);
			System.out.println("Waiting for connection");
			Socket client=Serversocket.accept();
			System.out.println("connection established");
			DataOutputStream out=new DataOutputStream(client.getOutputStream());
			DataInputStream in =new DataInputStream(client.getInputStream());
			
			do
			{
				msg=in.readUTF();
				System.out.println(msg);
				out.writeUTF(sc.nextLine());
				out.flush();
				
			}while(!msg.equalsIgnoreCase("exit"));
    	  
      }
      catch(Exception e) {
         System.out.print("Whoops! It didn't work!\n");
      }
   }
}