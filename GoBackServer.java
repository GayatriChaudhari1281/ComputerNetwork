package CNLab;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GoBackServer {

	public static void main(String[] args) throws IOException{
		System.out.println("................Server.................");
		System.out.println("Waiting for connection......");
		InetAddress addr=InetAddress.getByName("localhost");
		ServerSocket ss=new ServerSocket(500);
		
		Socket client=new Socket();
		client=ss.accept();
		BufferedInputStream in=new BufferedInputStream(client.getInputStream());
		DataOutputStream out=new DataOutputStream(client.getOutputStream());
		
		System.out.println("Received request for sending frame");
		
		int p=in.read();
		
		boolean f[]=new boolean[p];
		
		int pc=in.read();//Choice ......error or non-error
		System.out.println("Sending....");
		
		if(pc==0)
		{
			for(int i=0;i<p;i++)
			{
				System.out.println("Sending frame number "+ i);
				out.write(i);
				out.flush();
				System.out.println("Waiting for acknowledgement");
				try
				{
					Thread.sleep(7000);
				}
				catch (Exception e)
				{}
				int a=in.read();
				System.out.println("Received acknowledgement for frame "+i+"as "+a);
			
			}
			out.flush();
		}
		else  //error case
		{
			for(int i=0;i<p;i++)
			{
				if(i==2)  //creating a scenario of error(not sending the frame)
				{
					System.out.println("Sending Frame no "+i);
				}
				else
				{
					System.out.println("Sending frame no "+i);
					out.write(i);
					out.flush();
					System.out.println("Waiting for acknowledgement ");
					try
					{
						Thread.sleep(7000);
					}
					catch(Exception e) {}
					int a=in.read();
					if(a!=255)//-1 is 255
					{
						System.out.println("Received ack for frame no "+i+" as "+a);
						f[i]=true;
					}
				}//end of innner else
			}//end of for 
			
			//check which frame have not been ack
			for(int a=0;a<p;++a)
			{
				if(f[a]==false)
				{
					System.out.println("Resending frame "+a);
					out.write(a);
					out.flush();
					System.out.println("Waiting for ack ");
					try
					{
						Thread.sleep(5000);
						
					}
					catch (Exception e) {}
					int b=in.read();
					System.out.println("Received ack for frame no: "+a+" as "+b);
					f[a]=true;
					
				}
			}
			out.flush();
		}//end of else which is for error
		in.close();
		out.close();
		System.out.println("Quiting");
	}//end of main method

}//end of main class
