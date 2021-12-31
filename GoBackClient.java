package CNLab;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class GoBackClient {

	public static void main(String[] args) throws IOException{
		InetAddress addr=InetAddress.getByName("localhost");
		System.out.println(addr);
		
		Socket connection=new Socket(addr,500);
		
		BufferedInputStream in=new BufferedInputStream(connection.getInputStream());
		DataOutputStream out=new DataOutputStream(connection.getOutputStream());
		Scanner sc=new Scanner(System.in);
		
		System.out.println("................Client...................");
		System.out.println("Connect");
		System.out.println("Enter the number of frames to be requested to the server");
		int c=sc.nextInt();
		
		out.write(c);
		out.flush();
		
		System.out.println("Enter the type of transmission.Error=1  ;  No Error=0");
		int choice =sc.nextInt();
		out.write(choice);
		
		int check=0;
		int i=0;
		int j=0;
		
		if(choice==0)
		{
			for(j=0;j<c;++j)
			{
				i=in.read();
				System.out.println("Received frame no "+i);
				System.out.println("Sending acknowledgement for frame no: "+i);
				out.write(i);
				out.flush();
			}
			out.flush();
		}
		else
		{
			for(j=0;j<c;j++)
			{
				i=in.read();
				if(i==check)
				{
					System.out.println("i: "+i+" ,check: "+check);
					System.out.println("received frame no: "+i);
					System.out.println("Sending acknowledgement for frame no: "+i);
					out.write(i);
					++check;
				}
				else
				{
					--j;//for repeating
					System.out.println("Discarded frame no: "+i);
					System.out.println("Sending NEGATIVE ack");
					out.write(-1);
					
				}
				out.flush();
				
			}
		}//end of else for error
		
		in.close();
		out.close();
		System.out.println("Quiting");

	}//end of main method

}