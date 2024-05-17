package bullethellgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class SecondEnemy extends Enemy{
	public SecondEnemy(int x, int y, ID id, int length, int width, Handler handler, int health, int viewWidth, int viewHeight, BufferedImage[] enemy_images) {
		super(x, y, id, length, width, handler, health, viewWidth, viewHeight, enemy_images);
		}

	@Override
	public void fire() {
		int spawn = r.nextInt(40);
		int dirX = 0;
		int dirY = 0;
		for (int i = 0; i < handler.objectList.size(); i++) {
			GameObject tempObject = handler.objectList.get(i);
			if (tempObject.getID() == ID.Player) {
				dirX = (tempObject.getX() - x)/25;
				dirY = (tempObject.getY() - y)/25;
			}
			}

		if(spawn == 0) {
			int new_y = (int) ((y/x + 0.5) * x);
			
			handler.addObject(new EnemyBullet(x, y, ID.EnemyBullet, 16, 16, dirX, dirY, handler));
			handler.addObject(new EnemyBullet(x, new_y, ID.EnemyBullet, 16, 16, dirX, dirY, handler));

//			handler.addObject(new EnemyBullet(x + 50, y + 50, ID.EnemyBullet, dirX, dirY, handler));
//			handler.addObject(new EnemyBullet(x + 100, y + 100, ID.EnemyBullet, dirX, dirY, handler));
//			handler.addObject(new EnemyBullet(x - 50, y - 50, ID.EnemyBullet, dirX, dirY, handler));
//			handler.addObject(new EnemyBullet(x + 100, y + 100, ID.EnemyBullet, dirX, dirY, handler));
			//			handler.addObject(new EnemyBullet(x, y, ID.EnemyBullet, dirX - 1, dirY - 1, handler));
//			handler.addObject(new EnemyBullet(x, y, ID.EnemyBullet, dirX - 2, dirY - 2, handler));

			}		
	}

	@Override
	public void tick() {
		super.tick();	
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		
	}

	@Override
	protected void move() {
		r = new Random();
		
		if (velX != 0) {
			xstep += 1;
		}
		
		if (velY != 0) {
			ystep += 1;
		}
		
		if (velX == 0 && velY == 0) {
			nostep += 1;
		}
		
		if (xstep == 100)  {
			velX = 0;
			velY = 0;
			xstep = 0;
			};
			
		if (ystep == 100) {
			nostep += 1;

			velX = 0;
			velY = 0;
			ystep = 0;
			}
		
		if (nostep == 50) {
			nostep = 0;
			int num = r.nextInt(4);
			if (num == 0) {
				velX = 2;
				velY = 0;
			}
			if (num == 1) {
				velX = 0;
				velY = 2;
			}
			if (num == 2) {
				velX = -2;
				velY = 0;
			}
			if (num == 3) {
				velX = 0;
				velY = -2;
			}
		}		
	}

}
