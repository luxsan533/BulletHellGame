package bullethellgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Item extends GameObject{

	protected BufferedImage item_image;
	protected boolean inShop;
	protected int price;
	protected boolean affordable;
	protected String item_name;
	protected boolean fullhealth;
	

	public Item(int x, int y, ID id, int length, int width, Handler handler, BufferedImage item_image) {
		super(x, y, id, length, width, handler);
		this.item_image = item_image;
		this.inShop = false;
		this.price = 0;
		this.affordable = true;
		this.fullhealth = false;
	}
	
	public boolean buyRange(GameObject player) {		
		return (player.getY() > this.getY() + this.length && 
						player.getY() < this.getY() + this.length + 20 &&
						player.getX() > this.getX() - this.width - 25  &&
						player.getX() < this.getX() + this.width + 25);
				}

	@Override
	public void tick() {
		if (startTimer) {
			this.timer += 1;
			if (this.timer == 100) {
				this.fullhealth = false;
				this.timer = 0;
				startTimer = false;
			}
		}
		if (inShop) block();
		else collision();	
	}

	protected abstract void use();
	
	protected abstract void pickup();

	public void collision() {
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			if (tempObject.getID() == ID.Player) {
				if(getBounds().intersects(tempObject.getBounds())) {
					pickup();
				} 
			}
			}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(item_image.getScaledInstance(width, length, 0), (int) x, (int) y, null);
		if (!affordable) g.drawString("You can't afford that!", x + width + 10, y + length/2);
		if (affordable && fullhealth) g.drawString("You're at full health!", x + width + 10, y + length);
		}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, this.width, this.length);
	}
	
	public Rectangle shopBounds() {
		return new Rectangle(x, y, this.width + 10, this.length + 10);
	}
}
