package bullethellgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class EnemyBullet extends GameObject{

	BufferedImageLoader loader = new BufferedImageLoader();
	
	BufferedImage bulletImage = loader.loadImage("/enemyBullet.png");
	
	private int damage;

	public EnemyBullet(int x, int y, ID id, int length, int width, int velX, int velY, Handler handler) {
		super(x, y, id, length, width, handler);
		this.velX = velX;
		this.velY = velY;
//		this.damage = damage;
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
	}
	

	@Override
	public void render(Graphics g) {
		g.drawImage(bulletImage, (int) x, (int) y, null);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 16, 16);
	}

}
