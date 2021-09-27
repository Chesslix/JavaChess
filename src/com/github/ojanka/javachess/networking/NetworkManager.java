package com.github.ojanka.javachess.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.github.ojanka.javachess.networking.Packet.PacketType;

public class NetworkManager {
	private ServerSocket serverSocket;
	private Client client;
	
	public void connect() {
		// TODO
	}
	
	public void startServer(int port) throws IOException {
		if (serverSocket != null || client != null) throw new RuntimeException("Close all sockets and servers first!");
		
		serverSocket = new ServerSocket(port);
		
		/**
		 * Listens for connection until another game connects
		 */
		while(true) {
			var socket = serverSocket.accept();
			this.client = new Client(socket);
			String msg = this.client.readLine();
			Packet handshake = new Packet(msg);
			if (handshake.getType() == PacketType.SB_HANDSHAKE) {
				boolean listenAgain = startCommunication();
				if (listenAgain == false) break;
			} else {
				client.destruct();
				client = null;
			}
		}
	}
	
	public boolean startCommunication() {
		while(true) {
			Packet packet = new Packet(client.readLine());
			//TODO
			break;
		}
		return false;
	}
	
	private static class Client {
		private Socket socket;
		private BufferedReader br;
		private OutputStreamWriter osr;
		
		public Client(Socket socket) {
			try {
				this.socket = socket;
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				osr = new OutputStreamWriter(socket.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public String readLine() {
			try {
				return br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public void destruct() throws IOException {
			br.close();
			osr.close();
			socket.close();
		}
	}
}
