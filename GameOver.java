package bullethellgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//import bullethellgame.AudioPlayer;
import bullethellgame.Game;
import bullethellgame.HUD;
import bullethellgame.Handler;
import bullethellgame.ID;
import bullethellgame.Player;
import bullethellgame.Game.STATE;

public class GameOver extends MouseAdapter{
	Game game;
	private Handler handler;
	private HUD hud;
	private Room start_room;
	
	public GameOver(Game game, Handler handler, HUD hud) {
		this.game = game; 
		this.handler = handler;
		this.hud = hud;
//		this.start_room = start_room;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (game.gameState == STATE.GameOver) {
			if (mouseOver(mx, my, game.WIDTH/2 - 100, 400, 185, 64)) {
				game.gameState = STATE.Game;
				game.current_room = game.rooms.getLast();
				game.current_room.setEntered_room(true);
				hud.HEALTH = 100;
				handler.addObject(new Player(game.WIDTH/2-32, game.HEIGHT/2-32, ID.Player, 32, 32, handler));				
			}
		}

		}
	
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}

	
	public void render(Graphics g) {
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		Font fnt3 = new Font("arial", 1, 20);

		if (game.gameState == STATE.GameOver) {
			Game.bg.render(g);
//			g.setFont(fnt);
//			g.setColor(Color.white);
//			g.drawString("Game Over", 180, 70);
//					
//			g.setFont(fnt2);
//			g.drawRect(210, 350, 200, 64);
//			g.drawString("Try Again", 240, 390);				

			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Game Over", game.WIDTH/2 - 140, 300);
					
			g.setFont(fnt2);
			g.drawRect(game.WIDTH/2 - 100, 400, 185, 64);
			g.drawString("Try Again", game.WIDTH/2 - 75, 440);				

	}}}
