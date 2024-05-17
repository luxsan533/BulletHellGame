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

public class WinScreen extends MouseAdapter{
	Game game;
	private Handler handler;
	private HUD hud;
	
	public WinScreen(Game game, Handler handler, HUD hud) {
		this.game = game; 
		this.handler = handler;
		this.hud = hud;
//		this.start_room = start_room;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		//these get the x and y value of where the mouse is clicked
		
		if (game.gameState == STATE.WinScreen) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Game;
				game.current_room = game.rooms.getLast();
				game.current_room.setEntered_room(true);
				hud.HEALTH = 100;
				handler.addObject(new Player(game.WIDTH/2-32, game.HEIGHT/2-32, ID.Player, 32, 32, handler));
//				handler.addObject(new BasicEnemy(game.WIDTH/2-32 + 50, game.HEIGHT/2-32 + 50, ID.BasicEnemy, handler, 32));
				
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

//		game.WIDTH/2-32, game.HEIGHT/2-32
		
		if (game.gameState == STATE.WinScreen) {
			Game.bg.render(g);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("You Won!", game.WIDTH/2 - 120, 300);
					
			g.setFont(fnt2);
			g.drawRect(game.WIDTH/2 - 100, 400, 200, 64);
			g.drawString("Play Again", game.WIDTH/2 - 75, 440);				

	}}}
