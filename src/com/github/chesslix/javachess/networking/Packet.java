package com.github.chesslix.javachess.networking;

import org.json.JSONObject;

/**
 * Wrapper for a packet
 * Packets are used to communicate between the two instances
 * Every packet contains an id (PacketType) and a body for parameters.
 * The possible parameters are defined by the PacketType.
 *
 */
public class Packet {
	private JSONObject jsonObject;
	
	public Packet(String json) {
		this.jsonObject = new JSONObject(json);
	}
	
	public Packet(PacketType type, String body) {
		this.jsonObject = new JSONObject();
		jsonObject.put("id", type.toInt());
		jsonObject.put("body", new JSONObject(body));
	}
	
	public Object readProperty(String path) {
		String[] pathNodes = path.split("\\.");
		Object currentNode = jsonObject.getJSONObject("body");
		for (int i = 0; i < pathNodes.length; i++) {
			if (currentNode != null && currentNode instanceof JSONObject) {
				currentNode = ((JSONObject) currentNode).get(pathNodes[i]);
			} else break;
		}
		return currentNode;
	}
	
	public PacketType getType() {
		return PacketType.fromId(jsonObject.getInt("id"));
	}
	
	public String toJson() {
		return jsonObject.toString();
	}
	
	/**
	 * Packet Types
	 * 
	 * Prefixes:
	 * SB:  Serverbound:             server <-  client
	 * CB:  Clientbound:             server  -> client
	 * ALL: Server- and Clientbound: server <-> client
	 * @author Oliver
	 *
	 */
	public static enum PacketType {
		/**
		 * Lets the server know that this is an instance of javachess that wants to connect
		 */
		SB_HANDSHAKE(0),
		
		/**
		 * Lets the server know that this instance is ready to join.
		 * This packet can only once be sent and will result in an exception if it is sent twice
		 */
		SB_JOIN_GAME(1),
		
		/**
		 * With this packet the Server tells the client its chesscolor
		 * parameter: color
		 */
		CB_SET_COLOR(7),
		
		/**
		 * With this packet the Server tells the client, that it should set up the default chess board
		 * This packet mainly exists, to allow the future possibility of loading old games
		 */
		CB_SETUP_DEFAULT_BOARD(2),
		
		/**
		 * This packet tells the remote that a piece have been moved. The receiver will then move this peace on the board without validation.
		 */
		ALL_MOVE_PIECE(3),
		
		/**
		 * This packet tells the remote that the turn of the sender is over and that the turn of the receiver begins.
		 * This packet exists to allow multiple movements (when towering)
		 */
		ALL_FINISH_TURN(4),
		
		/**
		 * This packet tells the remote that the sender has won the game
		 */
		ALL_WIN_GAME(5),
		
		/**
		 * This packet is called right before closing the socket. It should be the last packet in the communication
		 */
		ALL_DISCONNECT(6),
		
		/**
		 * This packet tells the remote that the sender has lost the game
		 */
		ALL_LOSE_GAME(8),
		/**
		 * Unsuported packet
		 */
		UNSUPPORTED(-1);
		
		private int type;

		PacketType(int type) {
			this.type = type;
		}
		
		public int toInt() {
			return type;
		}
		
		public static PacketType fromId(int id) {
			switch (id) {
			case 0:
				return SB_HANDSHAKE;
			case 1:
				return SB_JOIN_GAME;
			case 7:
				return CB_SET_COLOR;
			case 2:
				return CB_SETUP_DEFAULT_BOARD;
			case 3:
				return ALL_MOVE_PIECE;
			case 4:
				return ALL_FINISH_TURN;
			case 5:
				return ALL_WIN_GAME;
			case 6:
				return ALL_DISCONNECT;
			case 8:
				return ALL_LOSE_GAME;
			default:
				return UNSUPPORTED;
			}
		}
	}
}
