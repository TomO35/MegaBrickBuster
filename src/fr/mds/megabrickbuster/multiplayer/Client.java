package fr.mds.megabrickbuster.multiplayer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	
	private Socket socket;
	private DataOutputStream out;
	
	public Client() {
		
	}
	
	public void getServerConnection(String Ipv4) {
		try {
			socket = new Socket(InetAddress.getByName(Ipv4),2005);
			out = new DataOutputStream(socket.getOutputStream());
			out.writeFloat(42);
			out.flush();
	        socket.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
