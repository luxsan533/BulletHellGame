package bullethellgame;

import java.awt.image.BufferedImage;

public class IceWandGun extends Gun{

	public IceWandGun(Game game, Handler handler, int damage, BufferedImage gun_image, String gun_name, int speed, int ammo, int reload) {
		super(game, handler, damage, gun_image, gun_name, speed, ammo, reload);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effect(Enemy enemy) {
		enemy.isFrozen = true;
	}

}
