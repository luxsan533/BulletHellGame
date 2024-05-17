//abstract class for all the game objects
package bullethellgame;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
//	protected means only objects that extend this class can use the protected attributes
	protected int x, y;
	protected ID id;
	protected double velX, velY;
	public int damage;
	public Gun gun;
	protected Handler handler;
	protected int length;
	protected int width;
	protected int timer = 0;
	protected boolean startTimer = false;
	protected boolean isLocked = false;
	protected int bulletBoundLength = 0;
	protected int bulletBoundWidth = 0;

	public GameObject(int x, int y, ID id, int length, int width, Handler handler) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.length = length;
		this.width = width;
		this.handler = handler;
	}
	
	public abstract void tick();
	
//	the render method handles all the visual aspects of the object
	public abstract void render(Graphics g);
	
	public void block() {
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			if(getBounds().intersects(tempObject.getBounds())) {
			if (tempObject.getID() == ID.Bullet) {
				handler.removeObject(tempObject);
			}
			else if (tempObject.getID() == ID.Player) {
				
//				if (((tempObject.getX() >= this.x)  && (tempObject.getX() <= this.x + this.width) && 
//						tempObject.getVelY() < 0 && tempObject.getVelX() != 0) && (tempObject.getY() >= this.y) && (tempObject.getY() <= this.y + this.length) && 
//						(tempObject.getVelX() < 0) && (tempObject.getVelY() != 0)){
//					tempObject.setX(tempObject.getX() + 10);
//					tempObject.setY(tempObject.getY() + 10);
//						}  
				
				if ((tempObject.getX() >= this.x)  && (tempObject.getX() <= this.x + this.width) && 
						tempObject.getVelY() < 0 && tempObject.getVelX() != 0) {
				tempObject.setX(tempObject.getX() + 10);
				
				}
				if ((tempObject.getY() >= this.y) && (tempObject.getY() <= this.y + this.length) && 
						(tempObject.getVelX() < 0) && (tempObject.getVelY() != 0)) {
				tempObject.setY(tempObject.getY() + 10);}

				
				if ((tempObject.getX() >= this.x)  && (tempObject.getX() <= this.x + this.width) && 
						tempObject.getVelY() < 0 && tempObject.getVelX() != 0) {
					System.out.println("test");
					tempObject.setY(Game.clamp(y, this.y + this.length, this.y + this.length));
					tempObject.setvelY(0);
				}
				
				else if ((tempObject.getY() >= this.y) && (tempObject.getY() <= this.y + this.length) && 
						(tempObject.getVelX() < 0) && (tempObject.getVelY() != 0)) {
					System.out.println("test");
					System.out.println(tempObject.getY());
					System.out.println(this.y);
					System.out.println(this.y + this.length);
					
					tempObject.setX(Game.clamp(x, this.x + this.width, this.x + this.width));
					tempObject.setvelX(0);
				}
			else if ((tempObject.getVelX() > 0) && 
							(tempObject.getY() >= this.y)  && (tempObject.getY() <= this.y + this.length)) {
							tempObject.setX(Game.clamp(x, this.x - tempObject.width, this.x - tempObject.width));
							tempObject.setvelX(0);
						}
					
					else if ((tempObject.getVelX() < 0) && 
							(tempObject.getY() >= this.y)  && (tempObject.getY() <= this.y + this.length)) {
							tempObject.setX(Game.clamp(x, this.x + this.width, this.x + this.width));
							tempObject.setvelX(0);
						}
					
					else if ((tempObject.getVelY() > 0) && 
							(tempObject.getX() >= this.x)  && (tempObject.getX() < this.x + this.width)) {
						tempObject.setY(Game.clamp(y, this.y - tempObject.length, this.y - tempObject.length));
							tempObject.setvelY(0);
						}
					
					else if ((tempObject.getVelY() < 0) && 
							(tempObject.getX() >= this.x)  && (tempObject.getX() < this.x + this.width)) {
							tempObject.setY(Game.clamp(y, this.y + this.length, this.y + this.length));
							tempObject.setvelY(0);
							}
					else if ((tempObject.getVelY() > 0) && (tempObject.getVelX() < 0)) {
						
					}
			}
		}}}


	public Rectangle getBounds() {
		return new Rectangle(x, y, length, width);
	}
	
	public Rectangle keyBounds() {
		return new Rectangle(x, y, this.width + 10, this.length + 10);
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public void setvelX(double velX) {
		this.velX = velX;
	}
	
	public void setvelY(double velY) {
		this.velY = velY;
	}

	
	public void setID(ID id) {
		this.id = id;
	}
	
	public int getX() {
		return x;
	}
	
	public double getVelX() {
		return velX;
	}
	
	public int getY() {
		return y;
	}
	
	public double getVelY() {
		return velY;
	}
	
	public ID getID() {
		return id;
	}
	
	public int getBulletBoundLength() {
		return bulletBoundLength;
	}

	public void setBulletBoundLength(int bulletBoundLength) {
		this.bulletBoundLength = bulletBoundLength;
	}

	public int getBulletBoundWidth() {
		return bulletBoundWidth;
	}

	public void setBulletBoundWidth(int bulletBoundWidth) {
		this.bulletBoundWidth = bulletBoundWidth;
	}

}
