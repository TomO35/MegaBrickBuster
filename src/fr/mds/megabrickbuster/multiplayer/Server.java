package fr.mds.megabrickbuster.multiplayer;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
	
	public static void main(String[] args) {
		
		ServerSocket serversocket;
		Socket socket;
		DataInputStream in;
		
		try {
			String Ipv4 = InetAddress.getLocalHost().getHostAddress();
			serversocket = new ServerSocket(2009, 1, InetAddress.getByName(Ipv4));
			socket = serversocket.accept();
			in = new DataInputStream  (new DataInputStream  (socket.getInputStream()));
	        float message_distant = in.readInt();
	        System.out.println("ça marche !!!! : " +message_distant);
	        socket.close();
	        serversocket.close();
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
