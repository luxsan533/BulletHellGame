package bullethellgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import bullethellgame.GameObject;
import bullethellgame.ID;
import bullethellgame.Game;

public class BasicEnemy extends Enemy{

		private int greenValue = 255;
		private Random r;

		public BasicEnemy(int x, int y, ID id, int length, int width, Handler handler, int health, int viewWidth, int viewHeight, BufferedImage[] enemy_images) {
			super(x, y, id, length, width, handler, health, viewWidth, viewHeight, enemy_images);

			
			for (int i = 0; i < handler.objectList.size(); i++) {
				if(handler.objectList.get(i).getID()== ID.Player) {
					player = handler.objectList.get(i);
				}
			}
			
			xstep = 0;
			ystep = 0;
			nostep = 0;
			velX = 2;
			velY = 0; 
		}
		
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

			if(spawn == 0) handler.addObject(new EnemyBullet(x, y, ID.EnemyBullet, 16, 16, dirX, dirY, handler));
		}
		
		public void tick() {
			super.tick();
		}
				
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



		public void render(Graphics g) {
			super.render(g);
		}

		@Override
		public Rectangle getBounds() {
			return new Rectangle(x, y, 32, 32);
		}

	}
