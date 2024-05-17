package bullethellgame;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class CoinItem extends Item{

	public CoinItem(int x, int y, ID id, int length, int width, Handler handler, BufferedImage item_image) {
		super(x, y, id, length, width, handler, item_image);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void use() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void pickup() {
		HUD.coins += 10;
		handler.removeObject(this);		
	}
	


}
