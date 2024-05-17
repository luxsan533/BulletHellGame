package bullethellgame;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class HeartItem extends Item{


	public HeartItem(int x, int y, ID id, int length, int width, Handler handler, BufferedImage item_image) {
		super(x, y, id, length, width, handler, item_image);
		this.item_name = "Heart";
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void use() {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	protected void pickup() {
		if (HUD.HEALTH < 100) {
			if (HUD.HEALTH + 25 > 100) HUD.HEALTH = 100; else HUD.HEALTH += 25;
			handler.removeObject(this);
			}
		else {
			this.fullhealth = true;
			this.startTimer = true;
			block();
			}		
	}

}
