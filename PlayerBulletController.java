package bullethellgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import bullethellgame.Game;
import bullethellgame.Handler;
import bullethellgame.Game.STATE;

public class PlayerBulletController extends MouseAdapter{
	Game game;
	private Handler handler;
	private Gun gun;
	private int delay;
	private int count;
	
	public PlayerBulletController(Game game, Handler handler, Gun gun) {
		this.game = game; 
		this.handler = handler;
		this.gun = gun;
		this.count = -1;
		int delay = 1;
	}

	public void mousePressed(MouseEvent e) {
		if (game.gameState == STATE.Game) {

			gun.setAmmo(gun.getAmmo() - 1);
			gun.setReload(gun.getReload() - 1);
	//		
			if (gun.getReload() == 0) {
				game.removeMouseListener(this);
				this.count = 100;
			}
			
			if (gun.getAmmo() == 0) game.removeMouseListener(this);
			
	//		if (delay == gun.getSpeed()) {
			handler.addObject(new Bullet(16, 16, handler, gun));
		}
//			delay = 0;}
//		delay += 1;
		//these get the x and y value of where the mouse is clicked
	}
	
	public void tick() {
		if (count > 0) count -= 1;
		if (count == 0 && gun.getAmmo() > 0) {
			gun.setReload(gun.getMax_load());
			game.addMouseListener(this);
			count = -1;
		}
	}

	
	public void render(Graphics g) {
//		g.drawString(Integer.toString(gun.getReload()), 350, 300);
//		g.drawString(Integer.toString(count), 300, 300);
		if (count > 0 && gun.getAmmo() > 0) g.drawString("Reloading", 105, Game.HEIGHT - 130);
}}
