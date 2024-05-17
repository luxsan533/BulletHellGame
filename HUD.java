package bullethellgame;

import java.awt.Color;
import java.awt.Graphics;

import bullethellgame.Game;

public class HUD {
//	this is the health bar in game
	public static int HEALTH = 100;
	
	private int greenValue;
	public static int coins = 10;
	public static int keys = 0;
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 255);
		greenValue = Game.clamp(greenValue, 0, 255);
		greenValue = HEALTH*2;
	}

	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75, HEALTH * 2, 0));
		g.fillRect(15, 15, (int) HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, (int) HEALTH * 2, 32);
		
		g.drawString("Coins:" + coins, 15, 64);
		g.drawString("Keys:" + keys, 15, 80);
	}
	

}
