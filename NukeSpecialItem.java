package bullethellgame;

import java.awt.image.BufferedImage;

public class NukeSpecialItem extends SpecialItem{

	public NukeSpecialItem(int x, int y, ID id, int length, int width, Handler handler, BufferedImage item_image,
			Game game) {
		super(x, y, id, length, width, handler, item_image, game);
		this.item_name = "Nuke";
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void use() {
			game.used_nuke = true;
//		if (click == 1) {
//			game.used_nuke = false;
//			click = 0;
//		}
	}

}
