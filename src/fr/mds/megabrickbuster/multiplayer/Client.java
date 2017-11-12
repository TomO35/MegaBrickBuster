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
	
	public  boolean getServerConnection(String ip) {
		try {
			socket = new Socket(InetAddress.getByName(ip),2005);
			out = new DataOutputStream(socket.getOutputStream());
	        return true;
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sendFloatData() {
		try {
			out.writeFloat(42);
			out.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean sendIntData() {
		try {
			out.writeInt(42);
			out.flush();
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
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
