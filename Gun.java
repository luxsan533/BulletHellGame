package bullethellgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import bullethellgame.Game;
import bullethellgame.Handler;

public abstract class Gun extends MouseMotionAdapter {
	Game game;
	protected Handler handler;
	private int mx;
	private int my;
	private int gunX;
	private int gunY;
	private int playerX;
	private int playerY;
	private int directionX;
	private int directionY;
	private int damage;
	private BufferedImage gun_image;
	private String gun_name;
	private int speed;
	private int ammo;
	private int reload;
	private int max_load;
	private Bullet bullet;
//	private int bullet;

	public Gun(Game game, Handler handler, int damage, BufferedImage gun_image, String gun_name, int speed, int ammo, 
			int reload) {
//		int bullet_length, int bullet_width
		this.game = game; 
		this.handler = handler;
		this.gun_image = gun_image;
		this.gun_name = gun_name;
		this.setAmmo(ammo);
		this.setReload(reload);
		this.setMax_load(this.getReload());
		this.setDamage(damage);
		this.setSpeed(speed);
//		this.bullet = new Bullet(bullet_length, bullet_width, handler, this);
	}
	
	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}

	public void tick() {
	}

	
	public void render(Graphics g) {
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			
			if (tempObject.getID() == ID.Player) {
				
				int radius = 16;
				
				int playerX = (int) tempObject.getX();
				int playerY = (int) tempObject.getY();

				setGunX((int) (playerX + 2.1*radius*((mx - playerX)/Math.sqrt(Math.pow((mx-playerX), 2) + Math.pow((my - playerY), 2)))));
				setGunY((int) (playerY + 2.1*radius*((my - playerY)/Math.sqrt(Math.pow((mx-playerX), 2) + Math.pow((my - playerY), 2)))));
				
				this.setDirectionX(this.getGunX() - playerX);
				this.setDirectionY(this.getGunY() - playerY);
			}
		g.setColor(Color.red);
						
		g.drawImage(gun_image, (int) getGunX() + 10, (int) getGunY() + 10, null);
			
		g.drawString(gun_name, 50, Game.HEIGHT - 165);
		g.drawImage(gun_image.getScaledInstance(50, 100, 0), 50, Game.HEIGHT - 150, null);
		
		double size = 70/getMax_load();
		for (int j = 0; j < this.getReload(); j ++) {
			g.setColor(Color.gray);
			g.drawRect(104, Game.HEIGHT - 150 + j*(int) size, 12, (int) size);
			
		}
		g.drawString(String.valueOf(ammo), 103, Game.HEIGHT - 60);		
	}

	}

	public abstract void effect(Enemy enemy);

	
	public int getGunX() {
		return gunX;
	}
	public void setGunX(int gunX) {
		this.gunX = gunX;
	}
	public int getGunY() {
		return gunY;
	}
	public void setGunY(int gunY) {
		this.gunY = gunY;
	}
	public int getDirectionX() {
		return directionX;
	}
	public void setDirectionX(int directionX) {
		this.directionX = directionX;
	}
	public int getDirectionY() {
		return directionY;
	}
	public void setDirectionY(int directionY) {
		this.directionY = directionY;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}

	public int getReload() {
		return reload;
	}

	public void setReload(int reload) {
		this.reload = reload;
	}

	public int getMax_load() {
		return max_load;
	}

	public void setMax_load(int max_load) {
		this.max_load = max_load;
	}}
