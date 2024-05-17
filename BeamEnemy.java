package bullethellgame;

import java.awt.image.BufferedImage;

public class BeamEnemy extends Enemy{

	public BeamEnemy(int x, int y, ID id, int length, int width, Handler handler, int health, int viewWidth,
			int viewHeight, BufferedImage[] enemy_images) {
		super(x, y, id, length, width, handler, health, viewWidth, viewHeight, enemy_images);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fire() {
		int spawn = r.nextInt(25);
		int dirX = 0;
		int dirY = 0;
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			if (tempObject.getID() == ID.Player) {
				dirX = (tempObject.getX() - x)/10;
				dirY = (tempObject.getY() - y)/10;
			}
			}

		if(spawn == 0) {
			for (int i = 0; i < 100; i++) handler.addObject(new EnemyBullet(x+i, y+i, ID.EnemyBullet, 16, 16, dirX, dirY, handler));
		}
	}

	@Override
	protected void move() {
		// TODO Auto-generated method stub
		
	}

}
