package bullethellgame;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class KeyItem extends Item{

	public KeyItem(int x, int y, ID id, int length, int width, Handler handler, BufferedImage item_image) {
		super(x, y, id, length, width, handler, item_image);
		this.item_name = "Key";
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void use() {
		// TODO Auto-generated method stub	
	}


	@Override
	protected void pickup() {
		HUD.keys += 1;
		handler.removeObject(this);		
	}

//	public Rectangle getBounds() {
//		return new Rectangle(x, y, 37, 20);
//	}

}
