package com.github.chesslix.javachess.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.github.chesslix.javachess.game.Game;
import com.github.chesslix.javachess.game.Piece;
import com.github.chesslix.javachess.gui.GUIManager;
import com.github.chesslix.javachess.gui.screens.GameScreen;
import com.github.chesslix.javachess.gui.screens.StatusScreen;
import com.github.chesslix.javachess.networking.Packet.PacketType;
import com.github.chesslix.javachess.util.ChessColor;
import com.github.chesslix.javachess.util.GameSettings;
import com.github.chesslix.javachess.util.GameState;

/**
 * This class handles the communication between two instances of the game.
 * It sends json string called "Packet"
 * Further information about packets at {@link com.github.chesslix.javachess.networking.Packet}
 * The instances are told apart as Client (Connecting instance) and Server (accepting instance) but after the handshaking process they act the same
 *
 */
public class NetworkManager {
	private static NetworkManager instance;
	
	private ServerSocket serverSocket;
	private Client client;
	private GameSettings gameSettings;
	
	public static NetworkManager getInstance() {
		if (instance == null) {
			instance = new NetworkManager();
		}
		return instance;
	}
	
	/**
	 * Sends a packet to the remote, to let it know this client is going to leave, and then
	 * focefully shuts down the networkmanager by killing the thread
	 */
	public void shutdown() {
		if (this.client != null) this.client.sendPacket(new Packet(PacketType.ALL_DISCONNECT, "{}"));
		this.client = null;
		this.serverSocket = null;
		for (Thread thread : Thread.getAllStackTraces().keySet()) {
			if (thread.getName().equals("Networking")) {
				thread.interrupt();
			}
		}
		
//		if (this.client != null) {
//			this.client.destruct();
//			client = null;
//		}
//		if (this.serverSocket != null) {
//			try {this.serverSocket.close();} catch (IOException e) {e.printStackTrace();}
//		}
	}
	
	/**
	 * Connects to a game at a specific host and port
	 * @param host
	 * @param port
	 * @throws Exception
	 */
	public void connect(String host, int port) throws Exception {
		if (serverSocket != null || client != null) throw new RuntimeException("Close all sockets and servers first!");
		
		GUIManager.getInstance().changeScreen(new StatusScreen("Joining Game", new Runnable() {
			
			@Override
			public void run() {
				GUIManager.getInstance().changeScreen(new GameScreen());
			}
		}));
		
		try {
			this.client = new Client(new Socket(host, port));
			startCommunicationWithServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Listen as Server at a specific port
	 * @param port
	 * @param gameSettings
	 * @throws IOException
	 */
	public void startServer(int port, GameSettings gameSettings) throws IOException {
		if (serverSocket != null || client != null) throw new RuntimeException("Close all sockets and servers first!");
		
		// Create game
		Game.getInstance().setupDefaultBoard();
		
		serverSocket = new ServerSocket(port);
		this.gameSettings = gameSettings;
		
		GUIManager.getInstance().changeScreen(new StatusScreen("Listening on port: " + serverSocket.getLocalPort(), new Runnable() {
			
			@Override
			public void run() {
				GUIManager.getInstance().changeScreen(new GameScreen());
			}
		}));
		
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
	
	/**
	 * Handshaking process as Server
	 * @return
	 */
	public boolean startCommunication() {
		while(client.isOpen()) {
			var packet = client.readPacket();
			if (packet != null) {
				if (packet.getType() == PacketType.ALL_DISCONNECT) {
					Game.getInstance().setGameState(GameState.REMOTE_LEFT);
					Game.getInstance().endGame();
					this.shutdown();
					return false;
				};
				handlePacket(packet);
			};
		}
		// If we want to improve the game we can add a restart functionality by changing this return statement.
		return false;
	}
	
	/**
	 * Handshaking process as Client
	 */
	public void startCommunicationWithServer() {
		this.client.sendPacket(new Packet(PacketType.SB_HANDSHAKE, "{}"));
		this.client.sendPacket(new Packet(PacketType.SB_JOIN_GAME, "{}"));
		while(client.isOpen()) {
			var packet = client.readPacket();
			if (packet != null) {
				handlePacket(packet);
			};
		}
	}
	
	/**
	 * All packets that are not part of the handshaking process are sent to - and handled by this method
	 * @param packet
	 */
	public void handlePacket(Packet packet) {
		switch (packet.getType()) {
		case SB_JOIN_GAME:
			client.sendPacket(new Packet(PacketType.CB_SET_COLOR, "{\"color\": " + gameSettings.ownColor.getOpposite().toString() + "}"));
			client.sendPacket(new Packet(PacketType.CB_SETUP_DEFAULT_BOARD, "{}"));
			Game.getInstance().setupDefaultBoard();
			GUIManager.getInstance().event("solve");
			if (gameSettings.ownColor == ChessColor.BLACK) {
				Game.getInstance().finishTurn();
			} else {
				Game.getInstance().startRound();
			}
			break;
		case ALL_MOVE_PIECE:
			int startX = (int) packet.readProperty("start.x");
			int startY = (int) packet.readProperty("start.y");
			int x = (int) packet.readProperty("move.x");
			int y = (int) packet.readProperty("move.y");
			Piece piece = Game.getInstance().getBoard().getPieceByStartPos(startX, startY);
			if (piece == null) throw new RuntimeException("Illegal Packet: ALL_MOVE_PIECE\nPiece at given startPos not found or dead.\n" + packet.toJson());
			Game.getInstance().getBoard().movePiece(piece, x, y);
			break;
		case ALL_FINISH_TURN:
			Game.getInstance().startRound();
			break;
		case ALL_WIN_GAME:
			Game.getInstance().setGameState(GameState.GAME_LOST);
			Game.getInstance().endGame();
			break;
		case ALL_LOSE_GAME:
			Game.getInstance().setGameState(GameState.GAME_WON);
			Game.getInstance().endGame();
		case CB_SET_COLOR:
			ChessColor color = ChessColor.fromString((String) packet.readProperty("color"));
			Game.getInstance().setTeam(color);
			break;
		case CB_SETUP_DEFAULT_BOARD:
			Game.getInstance().setupDefaultBoard();
			GUIManager.getInstance().event("solve");
			break;
		case ALL_DISCONNECT:
			Game.getInstance().setGameState(GameState.REMOTE_LEFT);
			Game.getInstance().endGame();
			this.shutdown();
			break;
		default:
			throw new RuntimeException("Unsupportet packetId sent " + packet.getType());
		}
	}
	
	public Client getRemotePlayer() {
		return client;
	}
	
	/**
	 * This class contains the reader and writer to communicate with the remote
	 * Even though it's called Client it is used by the Server and the Client
	 *
	 */
	public static class Client {
		private Socket socket;
		private BufferedReader br;
		private OutputStreamWriter osr;
		private boolean open;
		
		public Client(Socket socket) {
			try {
				this.socket = socket;
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				osr = new OutputStreamWriter(socket.getOutputStream());
				this.open = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public Packet readPacket() {
			String msg = this.readLine();
			if (msg != null && !msg.equals("")) {
				Packet packet = new Packet(msg);
				return packet;
			} else {
				this.destruct();
				return null;
			}
		}
		
		public void sendPacket(Packet packet) {
			try {
				osr.write(packet.toJson() + "\r\n");
				osr.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private String readLine() {
			try {
				return br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public void destruct() {
			this.open = false;
			try {
				br.close();
				osr.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public boolean isOpen() {
			return open;
		}
	}
}
