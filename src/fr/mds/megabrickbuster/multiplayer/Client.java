package fr.mds.megabrickbuster.multiplayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	
	private Socket socket;
	private DataOutputStream outClient;
	private DataInputStream inClient;
	float valeur;
	
	public Client() {
		
	}
	//open a socket in order to set the connection 
	public  boolean getServerConnection(String ip) {
		try {
			socket = new Socket(InetAddress.getByName(ip),2005);
			outClient = new DataOutputStream(socket.getOutputStream());
			inClient = new DataInputStream  (new DataInputStream  (socket.getInputStream()));
	        return true;
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	//send a float( this float is the x pos of the stick)
	public boolean sendFloatData(float pos) {
		try {
			outClient.writeFloat(pos);
			outClient.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	//get a float
	public float getFloatData() {
		try {
			valeur = inClient.readFloat();
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
			outClient.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
