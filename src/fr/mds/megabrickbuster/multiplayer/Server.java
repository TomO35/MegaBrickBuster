package fr.mds.megabrickbuster.multiplayer;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
	
	private ServerSocket serversocket;
	private Socket socket;
	private DataInputStream in;
	float message_distant;
	
	public Server() {
		
	}
	
		public String getIpv4() {
			try {
				String Ipv4 = InetAddress.getLocalHost().getHostAddress();
				return Ipv4;
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public boolean getClientConnection(String ip) {
			try {
				serversocket = new ServerSocket(2005, 1, InetAddress.getByName(ip));
				socket = serversocket.accept();
				in = new DataInputStream  (new DataInputStream  (socket.getInputStream()));
		        return true;
			}catch (UnknownHostException e) {
				e.printStackTrace();
				return false;
			}catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean getIntData() {
			try {
				message_distant = in.readInt();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean getFloatData() {
			try {
				message_distant = in.readFloat();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean isConnected() {
			if (socket.isConnected()) {
				return true;
			} else {
				return false;
			}
		}
		
		public boolean closeConnection() {
				try {
					socket.close();
					serversocket.close();
					in.close();
					return true;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}

			
		}
		
}
