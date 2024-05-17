package bullethellgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class UseNuke extends MouseMotionAdapter{
	
	private int mx;
	private int my;
	private Game game;
	private Gun gun;
	private Handler handler;

	
	public UseNuke(Game game, Gun gun, Handler handler) {
		this.game = game;
		this.gun = gun;
		this.handler = handler;
	}


	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}
	
	public void tick() {
	}
	
	public void explode() {
		ArrayList<Enemy> in_nuke_range = new ArrayList();
		
		for (int i = 0; i < handler.enemyList.size(); i++) {
			Enemy enemy = handler.enemyList.get(i);
			if (enemy.getX() > mx && enemy.getX() < mx + 100 && enemy.getY() > my && enemy.getY() < my + 100) in_nuke_range.add(enemy);
		}
		
		for (int i = 0; i < in_nuke_range.size(); i++) in_nuke_range.get(i).health -= 10;
		
		game.used_nuke = false;
		game.addMouseMotionListener(gun);
	}

	
	public void render(Graphics g) {
		g.drawRect(mx, my, 100, 100);

//			g.drawString(Integer.toString(getGunX()), 100, 200);
//			g.drawString(Integer.toString(getGunY()), 200, 200);

//			g.fillOval(getGunX() + 10, getGunY() + 10, 10, 10);
			}

	}

