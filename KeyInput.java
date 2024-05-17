package bullethellgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;


public class KeyInput extends KeyAdapter{
	
	private Handler handler;
//	private boolean running = false;
	private Game game;
	private Gun current_gun;
	private PlayerBulletController newBulletController;
	private int gun_number;
	private int count = 0;
	private LinkedList<Gun> guns;
	private static boolean blocked;
	
	public KeyInput(Handler handler, Game game, LinkedList<Gun> guns) {
		this.handler = handler;
		this.game = game;
		this.guns = guns;
		gun_number = 0;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
//		this set key to a number value that corresponds to the pressed letter
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			
			if (tempObject.getID() == ID.Player) {
//				key events for player 1
				if (key == KeyEvent.VK_W && !isBlocked()) {tempObject.setvelY(-5);};
				if (key == KeyEvent.VK_S) {tempObject.setvelY(5);};
				if (key == KeyEvent.VK_A) {tempObject.setvelX(-5);};
				if (key == KeyEvent.VK_D) {tempObject.setvelX(5);};
				
				
			
//				Key to buy items in shop room
				if (game.current_room.room_type == Room.ROOM_TYPE.ShopRoom) {
				for (int j = 0; j < game.current_room.room_items.size() + 1; j++) {
					Item item = game.current_room.room_items.get(j);
					if (item.shopBounds().intersects(tempObject.getBounds())) {
						if (key == KeyEvent.VK_R) {
							if (HUD.coins - item.price >= 0) {
								if (item.item_name == "Heart" && HUD.HEALTH >= 100) {
									item.fullhealth = true;
								} else {
									HUD.coins -= item.price;
									item.pickup();
									game.current_room.room_items.remove(item);
									ShopRoom.room_items.remove(item);
									if (item.getID() == ID.SpecialItem) {Game.currentSpecial = "HealPack";}
								}
								} else item.affordable = false;
							}
					
					} else {
						item.affordable = true;
						item.fullhealth = false;}
			}}
//			Key to open chests
				for (int j = 0; j < handler.objectList.size(); j++) {
					GameObject lockedObject = handler.objectList.get(j);
					if(lockedObject.keyBounds().intersects(tempObject.getBounds())) {
						if (key == KeyEvent.VK_K) {
							lockedObject.isLocked = false;
						}
					}
				}

		}
	}
		if(key == KeyEvent.VK_SPACE) {
			if (game.special_item != null) {
			game.special_item.use();
			handler.removeObject(game.special_item);
			if (game.special_item.item_name == "Nuke") count = 0;
			game.special_item = null;}
			count += 1;
		
			if (count == 2) {
				game.use_nuke.explode();
				count = 0;}
		};
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		if(key == KeyEvent.VK_Q) {
			gun_number += 1;
			if (gun_number > guns.size() - 1) gun_number = 0;
			game.removeMouseListener(game.bulletController);
			game.removeMouseListener(newBulletController);
			game.gun = guns.get(gun_number);
			newBulletController = new PlayerBulletController(game, handler, guns.get(gun_number));
			game.bulletController = newBulletController;
			game.addMouseListener(newBulletController);
		}
		if(key == KeyEvent.VK_E) {
			gun_number -= 1;
			if (gun_number < 0) gun_number = guns.size() - 1;
			game.removeMouseListener(game.bulletController);
			game.removeMouseListener(newBulletController);
			game.gun = guns.get(gun_number);
			newBulletController = new PlayerBulletController(game, handler, guns.get(gun_number));
			game.bulletController = newBulletController;
			game.addMouseListener(newBulletController);
			}


//		if(key == KeyEvent.VK_P) {
//			if (game.gameState == STATE.Game) {
//				if (Game.pause) Game.pause = false;
//				else {Game.pause = true;}} 
//			}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
//		if (key == KeyEvent.VK_SPACE) game.used_nuke = false;
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			
			if (tempObject.getID() == ID.Player) {
//				key events for player 1
				if (key == KeyEvent.VK_W) {tempObject.setvelY(0);};
				if (key == KeyEvent.VK_S) {tempObject.setvelY(0);};
				if (key == KeyEvent.VK_A) {tempObject.setvelX(0);};
				if (key == KeyEvent.VK_D) {tempObject.setvelX(0);};
			}
			}
	}

	public static boolean isBlocked() {
		return blocked;
	}

	public static void setBlocked(boolean blocked) {
		KeyInput.blocked = blocked;
	}}


