package bullethellgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BlackHoleGeneratorGun extends Gun{

	private int count = 200;
	
	public BlackHoleGeneratorGun(Game game, Handler handler, int damage, BufferedImage gun_image, String gun_name,
			int speed, int ammo, int reload) {
		super(game, handler, 0, gun_image, gun_name, speed, ammo, reload);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effect(Enemy enemy) {
		
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			
			if (tempObject.getID() == ID.Bullet) { // temp object is now bullet
				float diffX = enemy.getX() - tempObject.getX() - 16;
				float diffY = enemy.getY() - tempObject.getY() - 16;
				float distance = (float) Math.sqrt( (enemy.getX() - tempObject.getX())*(enemy.getX() - tempObject.getX()) + (enemy.getY() - tempObject.getY() - 16)*(enemy.getY() - tempObject.getY() - 16));
				
				enemy.setvelX((int) ((-1/distance) * diffX * 3));
				enemy.setvelY((int) ((-1/distance) * diffY * 3));		

				}
			}
		
	}
	
	public void render(Graphics g) {
		super.render(g);
//		g.drawString(Integer.toString(count), 100, 100);
	}
	
	public void tick() {
//		if (Game.gun == this)  {
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			
			if (tempObject.getID() == ID.Bullet) { // temp object is now bullet
					tempObject.setBulletBoundLength(200);
					tempObject.setBulletBoundWidth(200);
					tempObject.setvelX(tempObject.getVelX()/1.25);
					tempObject.setvelY(tempObject.getVelY()/1.25);
					
					Game.startCount = true;
					Game.setCount(count);
//					Game.startCount(count);
//					count -= 1;
					if (Game.getCount() == 0) {
						handler.removeObject(tempObject);
						count = 200;
					}
					
				}
			}
//		}
	}
}
