package CNLab;
import java.io.File;
import java.io.FileInputStream;
//Java program to illustrate Client side
//Implementation using DatagramSocket
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDP_Client_File {
  public static void main(String args[]) throws IOException
  {
      Scanner sc = new Scanner(System.in);

      // Step 1:Create the socket object for
      // carrying the data.
      DatagramSocket ds = new DatagramSocket();

      InetAddress ip = InetAddress.getLocalHost();
      byte buf[] = null;

      File myFile = new File ("F:\\git.txt");
      FileInputStream fis = new FileInputStream(myFile);

      buf = fis.readAllBytes();

      // Step 2 : Create the datagramPacket for sending
          // the data.
          DatagramPacket DpSend =
                new DatagramPacket(buf, buf.length, ip, 1234);

          // Step 3 : invoke the send call to actually send
          // the data.
          ds.send(DpSend); 
  }
}