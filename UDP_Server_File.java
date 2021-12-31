package CNLab;
import java.io.FileOutputStream;
//Java program to illustrate Server side
//Implementation using DatagramSocket
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDP_Server_File {
public static void main(String[] args) throws IOException
{
    // Step 1 : Create a socket to listen at port 1234
    DatagramSocket ds = new DatagramSocket(1234);
    byte[] receive = new byte[65535];

    DatagramPacket DpReceive = null;
    
        // Step 2 : create a DatgramPacket to receive the data.
        DpReceive = new DatagramPacket(receive, receive.length);

        // Step 3 : revieve the data in byte buffer.
        ds.receive(DpReceive);
       
        OutputStream os = new FileOutputStream("F:\\hello.txt");

        System.out.println("Client:-" + data(receive));

        os.write(receive);

        // Clear the buffer after every message.
        System.out.println("Got the file");
    
}

// A utility method to convert the byte array
// data into a string representation.
public static StringBuilder data(byte[] a)
{
    if (a == null)
        return null;
    StringBuilder ret = new StringBuilder();
    int i = 0;
    while (a[i] != 0)
    {
        ret.append((char) a[i]);
        i++;
    }
    return ret;
}
}