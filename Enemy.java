package bullethellgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Enemy extends GameObject{
	protected int health;
	protected int xstep;
	protected int ystep;
	protected int nostep;
	protected int viewWidth;
	protected int viewHeight;
	protected int greenValue = 255;
	protected Random r;
	protected BufferedImage enemy_image;
	protected Rectangle enemyView;
	protected GameObject player;
	protected boolean onFire;
	protected boolean isFrozen;

	private int count;
	private BufferedImage current_image;
	private BufferedImageLoader loader;
	private BufferedImage burned_enemy_image;	private BufferedImage frozen_enemy_image;
	private int freeze;

	public Enemy(int x, int y, ID id, int length, int width, Handler handler, int health, int viewWidth, int viewHeight, BufferedImage[] enemy_images) {
		super(x, y, id, length, width, handler);
		this.health = health;
		this.enemy_image = enemy_images[0];
		this.burned_enemy_image = enemy_images[1];
		this.frozen_enemy_image = enemy_images[2];

		this.viewWidth = viewWidth;
		this.viewHeight = viewHeight;

		for (int i = 0; i < handler.objectList.size(); i++) {
			if(handler.objectList.get(i).getID()== ID.Player) {
				player = handler.objectList.get(i);
			}
		}
		
		xstep = 0;
		ystep = 0;
		nostep = 0;
		velX = 2;
		velY = 0;
		count = 0;
		freeze = 1;
		this.onFire = false;
		this.isFrozen = false;
	}
	
	public abstract void fire();
	
	public void onFire() {
		count += 1;
		if (count % 10 == 0) this.health -= 1;
		if (count == 100) {
			onFire = false;
			count = 0;
			}
	}
	
	public void isFrozen() {
		count += 1;
		freeze += 2;
		if (count == 100) {
			isFrozen = false;
			freeze = 1;
			count = 0;
			}
	}
	
	public void tick() {
		r = new Random();
		health = Game.clamp(health, 0, 255);
		greenValue = Game.clamp(greenValue, 0, 255);
		greenValue = 255;

		x += velX/freeze;
		y += velY/freeze;
		
		move();
		
		if (onFire) onFire();
		if (isFrozen) isFrozen();

		seesPlayer();
		
		if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		if (y <= 0 || y >= Game.HEIGHT - 64) velY *= -1;

		collision();
	}
		
	public void collision() {
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			
			if (tempObject.getID() == ID.Bullet) // temp object is now bullet
				if(getBounds().intersects(tempObject.getBounds())) {
//					collision code
					this.health -= tempObject.damage;
					tempObject.gun.effect(this);
					if (tempObject.damage != 0) handler.removeObject(tempObject);
					if (this.health <= 0) {
						handler.removeObject(this);
						handler.addObject(new CoinItem(x, y, ID.CoinItem, 38, 37, handler, Game.coin));
					}
					}
			}
		}

	protected abstract void move();

	public void seesPlayer() {
		Rectangle enemyView = new Rectangle(x - 200, y - 125, viewWidth, viewHeight);

		if (enemyView.intersects(player.getBounds())) {
			float diffX = x - player.getX() - 16;
			float diffY = y - player.getY() - 16;
			float distance = (float) Math.sqrt( (x-player.getX())*(x-player.getX())+ (y-player.getY())*(y-player.getY()));
			
			velX = (int) ((-1/distance) * diffX * 3);
			velY = (int) ((-1/distance) * diffY * 3);
			fire();
		}
	}


	
	public void render(Graphics g) {
		if (onFire) g.drawImage(this.burned_enemy_image, (int) x, (int) y, null);
		else if (isFrozen) g.drawImage(this.frozen_enemy_image, (int) x, (int) y, null);
		else g.drawImage(this.enemy_image, (int) x, (int) y, null);
						
//		g.drawRect(x - 200, y - 105, viewWidth, viewHeight);
//
//		g.setColor(Color.gray);
		g.fillRect(x+5, y+50, 32, 6);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(x+5, y+50, (int) health, 6);
		g.setColor(Color.white);
		g.drawRect(x+5, y+50, (int) health, 6);
	} 

	public BufferedImage getCurrent_image() {
		return current_image;
	}

	public void setCurrent_image(BufferedImage current_image) {
		this.current_image = current_image;
	}

}
