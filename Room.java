package bullethellgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import bullethellgame.Game;
import bullethellgame.Handler;

public abstract class Room {
	Game game;
	protected Handler handler;
	protected int room_number;
	protected Door[] doors;
	protected Random r;
	protected boolean completed = false;

	protected static LinkedList<Item> room_items;

//	private RoomID room_up;
	public static BufferedImage flabbergasted;

	private boolean entered_room = false;
	
	public enum ROOM_TYPE {
		EnemyRoom,
		ShopRoom,
		BossRoom
	};

	protected ROOM_TYPE room_type;
//	private boolean exit = false;

	
	public Room(Game game, Handler handler, int room_number, Door[] doors) {
		this.game = game; 
		this.handler = handler;
		this.room_number = room_number;
		this.doors = doors;
		r = new Random();
		BufferedImageLoader loader = new BufferedImageLoader();
		flabbergasted = loader.loadImage("/flabbergastedgame.jpg");
//		this.room_up = room_id;
	}
	
	public void tick() {
		
		if (isEntered_room()) {
			game.used_nuke = false;
			spawn();
			}
		setEntered_room(false);
//		public BasicEnemy(int x, int y, ID id, int length, int width, Handler handler, int health, int viewWidth, int viewHeight, BufferedImage[] enemy_images) {

		for (int i=0; i < doors.length; i++) {
			doors[i].tick();
		}
	}

	public abstract void spawn();
	
	public void render(Graphics g) {
//		g.drawString("Room " + Integer.toString(getRoom_number()), 240, 70);
		for (int i=0; i < doors.length; i++) {
			doors[i].render(g);
		}	
	}
	
	public void setDoors(boolean open) {
		for (int i=0; i < doors.length; i++) {
			doors[i].setOpen(open);
		}
	}
	

	public int getRoom_number() {
		return room_number;
	}

	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}

	public boolean isEntered_room() {
		return entered_room;
	}

	public void setEntered_room(boolean entered_room) {
		this.entered_room = entered_room;
	}

	public void clearEnemies() {
		handler.clear(ID.BasicEnemy);		
	}
	
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	protected abstract void clear();

//	public boolean isExit() {
//		return exit;
//	}
//
//	public void setExit(boolean exit) {
//		this.exit = exit;
//	}

}
