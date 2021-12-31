package CNLab;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class UDP_Server {

	public static void main(String[] args) throws IOException{
		//creating datagram socket
		DatagramSocket server=new DatagramSocket(4160);
		byte[] buf=new byte[256];
		DatagramPacket  packet=new DatagramPacket(buf, buf.length);
		server.receive(packet);
		String response=new String(packet.getData());
		System.out.println("Response Data: "+response);
	}

}