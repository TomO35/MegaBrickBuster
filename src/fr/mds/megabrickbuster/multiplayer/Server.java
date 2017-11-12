package fr.mds.megabrickbuster.multiplayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
	
	private ServerSocket serversocket;
	private Socket socket;
	private DataInputStream inServer;
	private DataOutputStream OutServer;
	float valeur;
	
	public Server() {
		
	}
		//get the localhost adress for the server
		public String getIpv4() {
			try {
				String Ipv4 = InetAddress.getLocalHost().getHostAddress();
				return Ipv4;
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			return null;
		}
		//set connection using socket with client at port 2005
		public boolean getClientConnection(String ip) {
			try {
				serversocket = new ServerSocket(2005, 1, InetAddress.getByName(ip));
				socket = serversocket.accept();
				inServer = new DataInputStream  (new DataInputStream  (socket.getInputStream()));
				OutServer = new DataOutputStream(socket.getOutputStream());
		        return true;
			}catch (UnknownHostException e) {
				e.printStackTrace();
				return false;
			}catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean sendFloatData(float pos) {
			try {
				OutServer.writeFloat(pos);
				OutServer.flush();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			
		}
		
		public float getFloatData() {
			try {
				valeur = inServer.readFloat();
				return valeur;
			} catch (IOException e) {
				e.printStackTrace();
				return 666;
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
					inServer.close();
					return true;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}

			
		}
		
}
