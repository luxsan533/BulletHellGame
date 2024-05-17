package bullethellgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class SpecialItem extends Item {

	protected boolean isEquiped;
	protected Game game;

	public SpecialItem(int x, int y, ID id, int length, int width, Handler handler, BufferedImage item_image, Game game) {
		super(x, y, id, length, width, handler, item_image);
		this.game = game;
		boolean isEquiped = false;
		// TODO Auto-generated constructor stub
	}
	@Override
	protected abstract void use();
	
	public void pickup() {
		if (game.special_item != null) {
			game.special_item.isEquiped = false;
			game.special_item.setX(this.x - 40);
			game.special_item.setY(this.y + 10);
		}
		game.special_item = this;
		this.isEquiped = true;
		}
	
	public void render(Graphics g) {
//		super.render(g);
		if (isEquiped) {
			g.drawString(item_name, Game.WIDTH - 100, Game.HEIGHT - 160);
			g.drawImage(item_image.getScaledInstance(50, 100, 0), Game.WIDTH - 105, Game.HEIGHT - 150, null);
		} else {
			super.render(g);
		}
	}
}
