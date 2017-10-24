package fr.mds.megabrickbuster.multiplayer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) {
		
		Socket socket;
		DataOutputStream out;
		
		try {
			String Ipv4 = InetAddress.getLocalHost().getHostAddress();
			socket = new Socket(InetAddress.getByName(Ipv4),2009);
			out = new DataOutputStream(socket.getOutputStream());
	        out.writeFloat(42);
	        out.flush();
	        socket.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
