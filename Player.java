package bullethellgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject {
	
	BufferedImageLoader loader = new BufferedImageLoader();
	
	BufferedImage playerImage = loader.loadImage("/player.png");
	
	public Player(int x, int y, ID id, int length, int width, Handler handler) {
		super(x, y, id, length, width, handler);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH-32);
		y = Game.clamp(y, 0, Game.HEIGHT-64);
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			
			if (tempObject.getID() == ID.EnemyBullet)
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					HUD.HEALTH -= 20;
					
					}
			if (tempObject.getID() == ID.BasicEnemy)
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
					}}
										
	
		}
	

	@Override
	public void render(Graphics g) {
		if(id == ID.Player) {g.setColor(Color.white);}
//		g.fillRect(x, y, 32, 32);
		g.fillOval(x, y, 32, 32);
		g.drawImage(playerImage, (int) x, (int) y,  null);
		
//		g.drawString(Integer.toString(this.x), 50, 100);
//		g.drawString(Integer.toString(this.y), 100, 100);
//		g.drawString(Integer.toString((int) this.velX), 50, 150);
//		g.drawString(Integer.toString((int) this.velY), 100, 150);

	}
	
}
