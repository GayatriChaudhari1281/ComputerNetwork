package CNLab;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCP_Server_File {

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(5000);

		System.out.println("Server started");
		System.out.println("Waiting for a client ...");

		Socket socket = server.accept();

		System.out.println("Client has joined sharing ");

		DataInputStream readInput = new DataInputStream(socket.getInputStream());
		DataOutputStream writeOutput = new DataOutputStream(socket.getOutputStream());

		Scanner ip = new Scanner(System.in);

		// get Stream socket input
		String lines = readInput.readUTF();

		// write to files
		FileWriter myWriter = new FileWriter("F:\\hello.txt");
		myWriter.write(lines);
		myWriter.close();

		server.close();
		socket.close();
		writeOutput.close();
		readInput.close();
		ip.close();

	}
}
