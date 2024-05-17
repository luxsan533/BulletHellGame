package bullethellgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject{

	private int damage;
	private Gun gun;

	BufferedImageLoader loader = new BufferedImageLoader();
	
	BufferedImage bulletImage = loader.loadImage("/playerbullet.png");
	
	
	public Bullet(int length, int width, Handler handler, Gun gun) {
		super(gun.getGunX(), gun.getGunY(), ID.Bullet, length, width, handler);
		this.velX = gun.getDirectionX()/gun.getSpeed();
		this.velY = gun.getDirectionY()/gun.getSpeed();
		this.length = length;
		this.width = width;
		super.gun = gun;
		super.damage = gun.getDamage();
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
//		collision();
	}
	
	public void effect(Enemy enemy) {
		gun.effect(enemy);
	}
	
	@Override
	public void render(Graphics g) {
//		g.setColor(Color.blue);
//		g.fillRect(x, y, length, width);	
//		g.drawRect(x - bulletBoundLength, y - bulletBoundWidth, length + bulletBoundLength * 2, width + bulletBoundWidth * 2);
		g.drawImage(bulletImage, (int) x - bulletBoundLength, (int) y - bulletBoundWidth,  null);

	}

//	@Override
//	public Rectangle getBounds() {
//		return new Rectangle(x, y, length + bulletBoundLength, width + bulletBoundWidth);
//	}
}
