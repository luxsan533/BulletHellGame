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

public class StartScreen extends MouseAdapter{
	Game game;
	private Handler handler;
	private HUD hud;
	
	public StartScreen(Game game, Handler handler, HUD hud) {
		this.game = game; 
		this.handler = handler;
		this.hud = hud;
//		this.start_room = start_room;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
//		System.out.println("sup");
		//these get the x and y value of where the mouse is clicked
		
		if (game.gameState == STATE.StartScreen) {
			if (mouseOver(mx, my, game.WIDTH/2 - 100, 400, 200, 64)) {
				game.gameState = STATE.Game;
				game.current_room = game.rooms.getLast();
				game.current_room.setEntered_room(true);
				hud.HEALTH = 100;
				handler.addObject(new Player(game.WIDTH/2-32, game.HEIGHT/2-32, ID.Player, 32, 32, handler));
//				g.drawRect(game.WIDTH/2 - 100, 400, 200, 64);

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
		
		if (game.gameState == STATE.StartScreen) {
			Game.bg.render(g);
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Bullet Hell", game.WIDTH/2 - 130, 300);

			g.setFont(fnt3);

			g.drawString("By Luxsan Jeyasingam", game.WIDTH/2 - 112, 350);

					
			g.setFont(fnt2);
			g.drawRect(game.WIDTH/2 - 100, 400, 200, 64);
			g.drawString("Start", game.WIDTH/2 - 35, 440);				

	}}}
