package CNLab;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SelectiveRepeatClient {

	static Socket connection;
	public static void main(String[] args) throws IOException{
		try
		{
			int v[]=new int[8];
			int n=0;
			InetAddress addr=InetAddress.getByName("localhost");
			System.out.println(addr);
			connection=new Socket(addr,8011);
			DataOutputStream out=new DataOutputStream(connection.getOutputStream());
			DataInputStream in=new DataInputStream(connection.getInputStream());
			int p=in.read();
			System.out.println("No of frame is: "+p);
			for(int i=0;i<p;i++)
			{
				v[i]=in.read();
				System.out.println(v[i]);
				//g[i]=v[i];
				
			}
			v[5]=-1;
			for(int i=0;i<p;i++)
			{
				System.out.println("Received frame is: "+v[i]);
			}
			for(int i=0;i<p;i++)
			{
				if(v[i]==-1)
				{
					System.out.println("Request to transmit from packet no "+(i+1)+" again!");
					n=i;
					out.write(n);
					out.flush();
					System.out.println();
					v[n]=in.read();
					System.out.println("Received frame is "+v[n]);
				}
				
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

}
