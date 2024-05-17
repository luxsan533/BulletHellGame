package bullethellgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import bullethellgame.Game.STATE;

public class Door extends GameObject{

	private boolean open;
	private Game game;
	private Room room;
	boolean goingToClearedRoom;
	
	BufferedImageLoader loader = new BufferedImageLoader();
	
	BufferedImage closedDoorImage = loader.loadImage("/closeddoor.png");
	BufferedImage openDoorImage = loader.loadImage("/opendoor.png");
	


	public Door(int x, int y, ID id, int length, int width, Handler handler, Game game, Room room, boolean open, boolean goingToClearedRoom) {
		super(x, y, id, length, width, handler);
		this.game = game;
		this.room = room;
		this.open = open;
		this.goingToClearedRoom = goingToClearedRoom;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
			collision();
	}

	@Override
	public void render(Graphics g) {
		if (!this.isOpen()) g.drawImage(closedDoorImage, (int) x, (int) y,  null);
		else g.drawImage(openDoorImage, (int) x, (int) y,  null);
	}
	
	private void collision() {
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			
			if (tempObject.getID() == ID.Player)
				if(getBounds().intersects(tempObject.getBounds())) {
//					collision code
					if (this.isOpen()) {

						if (this.room == null ) {
							HUD.HEALTH = 100;
							handler.clear(ID.BasicEnemy);
							game.gameState = STATE.WinScreen;
	//						game.gameState = Game.STATE.GameOver;
						} else {
							if (goingToClearedRoom) {this.room.setCompleted(true);}
							game.current_room.setEntered_room(false);
							game.current_room.clear();
							
							
							game.current_room = this.room;
							game.current_room.setEntered_room(true);
							handler.removeObject(this);
							tempObject.setX(Game.WIDTH/2-32);
							tempObject.setY(Game.HEIGHT/2-32);
						}
						
					}
					else {
						block();
					}
				}
			}
		}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 32, 32);
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

}
