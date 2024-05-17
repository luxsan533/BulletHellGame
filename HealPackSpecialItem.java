package bullethellgame;

import java.awt.image.BufferedImage;

public class HealPackSpecialItem extends SpecialItem{

	public HealPackSpecialItem(int x, int y, ID id, int length, int width, Handler handler, BufferedImage item_image, Game game) {
		super(x, y, id, length, width, handler, item_image, game);
		this.item_name = "Heal Pack";
	}

	@Override
	protected void use() {
		HUD.HEALTH += 20;
	}

}
