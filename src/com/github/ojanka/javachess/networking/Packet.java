package com.github.ojanka.javachess.networking;

import org.json.JSONObject;

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
		SB_HANDSHAKE(0),
		SB_JOIN_GAME(1),
		CB_SET_COLOR(7),
		CB_SETUP_DEFAULT_BOARD(2),
		ALL_MOVE_PIECE(3),
		ALL_FINISH_TURN(4),
		ALL_WIN_GAME(5),
		SB_DISCONNECT(6),
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
				return SB_DISCONNECT;
			default:
				return UNSUPPORTED;
			}
		}
	}
}