package bullethellgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Random;

import bullethellgame.BufferedImageLoader;
import bullethellgame.Game.STATE;
import bullethellgame.HUD;


public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 6943403019105714293L;

	
	public static final int WIDTH = 1000, HEIGHT = WIDTH/12 * 9;
	private Thread thread;
	private boolean running = false;

	private Random r;
	private static Handler handler;
	public LinkedList<Room> rooms;
	public static LinkedList<Gun> guns;
//	public static Room current_room;
//	private Spawn spawner;
//	private Menu menu;
	public static boolean pause = false;
	
	public static BufferedImage flabbergasted;
	public static BufferedImage pirate;
	public static BufferedImage heart;
	public static BufferedImage flamewand;
	public static BufferedImage icewand;

	public static BufferedImage burnedflabbergasted;
	public static BufferedImage frozenflabbergasted;
	public static BufferedImage key;
	public static BufferedImage coin;
	public static BufferedImage healthpack;
	public static BufferedImage nuke;

	public static Gun gun;
	public static PlayerBulletController bulletController;

	private HUD hud;
	
	public enum STATE {
		Game,
		GameOver,
		WinScreen,
		StartScreen
	};
	
	
	public STATE gameState = STATE.StartScreen;
	
	public Room current_room;
	public SpecialItem special_item;
	
	private static int count = 0;
	public static boolean startCount = false;

	private GameOver gameover;
	private WinScreen winscreen;


	public UseNuke use_nuke;
	public boolean used_nuke = false;


	private GameObject player;


	private StartScreen startscreen;


	private boolean startGame = true;

	static BufferedImageLoader loader = new BufferedImageLoader();

	
	static BufferedImage bgImage = loader.loadImage("/background.png");

	public static Background bg = new Background(0, 0, ID.Background, HEIGHT, WIDTH, handler, bgImage);

	public static Gun icewandgun;


	public static Gun flamewandgun;


	public static String currentSpecial;

	public Game() throws IOException {
		LinkedList<Gun> guns = new LinkedList<Gun>();
		rooms = new LinkedList<Room>();		


				
		flabbergasted = loader.loadImage("/enemy.png");
		burnedflabbergasted = loader.loadImage("/enemyburned.png");
		frozenflabbergasted = loader.loadImage("/enemyfrozen.png");

		BufferedImage snatcher = loader.loadImage("/snatcherfacegame.jpg");
//		pirate = loader.loadImage("/pepegrin.jpg");
		heart = loader.loadImage("/heartgame.jpg");
		key = loader.loadImage("/keygame.jpg");
		coin = loader.loadImage("/coingame.jpg");
		healthpack = loader.loadImage("/healthpackgame.png");
		nuke = loader.loadImage("/nukegame.jpg");

		flamewand = loader.loadImage("/flamewandgame.jpg");
		icewand = loader.loadImage("/icewandgame.jpg");
		
		
		handler = new Handler();
//		public Gun(Game game, Handler handler, int damage, BufferedImage gun_image, String gun_name, int speed, int ammo, int reload) {

		flamewandgun = new FlameWandGun(this, handler, 8, flamewand, "Flame Wand", 1, 50, 10);
		icewandgun = new IceWandGun(this, handler, 1, icewand, "Ice Wand", 1, 50, 10);
//		Gun testgun = new TestGun(this, handler, 8, pirate, "Test Gun", 10, 50, 10);
//		Gun blackholegun = new BlackHoleGeneratorGun(this, handler, 0, snatcher, "Black Hole Generator", 10, 20, 5);
		
		guns.add(flamewandgun);
		guns.add(icewandgun);
//		guns.add(testgun);
//		guns.add(blackholegun);
		
		gun = guns.get(0);
		
		bulletController = new PlayerBulletController(this, handler, gun);
		hud = new HUD();
		startscreen = new StartScreen(this, handler, hud);
		gameover = new GameOver(this, handler, hud);
		winscreen = new WinScreen(this, handler, hud);
		
		
		this.addKeyListener(new KeyInput(handler, this, guns));		
		this.addMouseListener(startscreen);
		this.addMouseListener(bulletController);
		this.addMouseListener(winscreen);
		this.addMouseListener(gameover);
		
		for (int i = 0; i < guns.size(); i++) {
			Gun gun = guns.get(i);
			this.addMouseMotionListener(gun);
		}
		
		use_nuke = new UseNuke(this, gun, handler);
		this.addMouseMotionListener(use_nuke);

//		this makes sure the game knows we're listening for mouse clicks
//		you have to pass in the class that uses mouse clicks
				
//		Door door_3 = new Door(WIDTH/2, 50, ID.Door, 32, 32, handler, this, null, false);
//		Room room_3 = new EnemyRoom(this, handler, 3, door_3);
//		rooms.add(room_3);
//
//		

		Door door_4 = new Door(WIDTH/2 - 30, 50, ID.Door, 32, 32, handler, this, null, false, false);
		Door [] doors_4 = {door_4};
		Room room_4 = new EnemyRoom(this, handler, 4, doors_4);
		rooms.add(room_4);

		
		Door [] chest_room_doors = {};

		Room chest_room = new ChestRoom(this, handler, 0, chest_room_doors);
		Door chest_room_door = new Door(WIDTH-90, HEIGHT/2-32, ID.Door, 32, 32, handler, this, chest_room, false, false);

		
		Door door_3 = new Door(WIDTH/2 - 30, 50, ID.Door, 32, 32, handler, this, room_4, false, false);
		Door [] doors_3 = {door_3, chest_room_door};
		Room room_3 = new EnemyRoom(this, handler, 3, doors_3);
		rooms.add(room_3);
		
		Door chest_exit_door = new Door(50, HEIGHT/2-32, ID.Door, 32, 32, handler, this, room_3, false, true);
		Door [] new_chest_doors = {chest_exit_door};

		chest_room.doors = new_chest_doors;
		
		Door [] shop_doors = {};

		Room shop_room = new ShopRoom(this, handler, 0, shop_doors);
		Door shop_door = new Door(WIDTH-90, HEIGHT/2-32, ID.Door, 32, 32, handler, this, shop_room, false, false);

		
		Door door_2 = new Door(WIDTH/2 - 30, 50, ID.Door, 32, 32, handler, this, room_3, false, false);
		Door [] doors_2 = {door_2, shop_door};
		Room room_2 = new EnemyRoom(this, handler, 2, doors_2);
		rooms.add(room_2);

		Door shop_exit_door = new Door(50, HEIGHT/2-32, ID.Door, 32, 32, handler, this, room_2, false, true);
		Door [] new_shop_doors = {shop_exit_door};

		shop_room.doors = new_shop_doors;

		
		Door door_1 = new Door(WIDTH/2 - 30, 50, ID.Door, 32, 32, handler, this, room_2, false, false);
		Door [] doors_1 = {door_1};
		Room room_1 = new EnemyRoom(this, handler, 1, doors_1);
		rooms.add(room_1);
		
		Door door_back_to_1 = new Door(WIDTH/2 - 30, HEIGHT - 100, ID.Door, 32, 32, handler, this, room_2, false, true);
		Door [] doors_3_new = {door_3, chest_room_door, door_back_to_1};
		room_3.doors = doors_3_new;
		
//		Door door_2 = new Door(WIDTH/2, 50, ID.Door, 32, 32, handler, this, null, false);
//		Door [] doors_2 = {door_2};
//		Room room_2 = new EnemyRoom(this, handler, 2, doors_2);
//		rooms.add(room_2);
//		
//		Door [] shop_doors = {};
//
//		Room shop_room = new ShopRoom(this, handler, 0, shop_doors);
//		Door shop_door = new Door(WIDTH-50, HEIGHT/2-32, ID.Door, 32, 32, handler, this, shop_room, false);
//
//		Door door_1 = new Door(WIDTH/2, 50, ID.Door, 32, 32, handler, this, room_2, false);
//		Door [] doors_1 = {door_1, shop_door};
//		Room room_1 = new EnemyRoom(this, handler, 1, doors_1);
//		rooms.add(room_1);
//		
//		Door shop_exit_door = new Door(50, HEIGHT/2-32, ID.Door, 32, 32, handler, this, room_1, false);
//		Door [] new_shop_doors = {shop_exit_door};
//
//		shop_room.doors = new_shop_doors;

		
//		current_room = rooms.get(rooms.size() - 1);
		current_room = room_1;
		current_room.setEntered_room(true);
		
		special_item = null;

//		AudioPlayer.load();
//		
//		AudioPlayer.getMusic("music").loop();
		new Window(WIDTH, HEIGHT, "Bullet Hell Game", this);
		
		r = new Random();
//		spawner = new Spawn(handler, hud);
		
//		if (gameState == STATE.Game) {
		
//		BufferedImage bgImage = loader.loadImage("/background.png");

//		BufferedImage leftTile = loader.loadImage("/leftwalltile.png");
//		BufferedImage topTile = loader.loadImage("/topwalltile.png");
//		BufferedImage rightTile = loader.loadImage("/rightwalltile.png");
//		BufferedImage bottomTile = loader.loadImage("/bottomwalltile.png");
//		BufferedImage floortile = loader.loadImage("/floortile.png");

//		
//		for (int i = 0; i<15; i++) {
//		bg = new Background(0, 0, ID.Background, HEIGHT, WIDTH, handler, bgImage, this);
		handler.addObject(new Background(0, 0, ID.Background, HEIGHT, WIDTH, handler, bgImage));
//		}
//		
//		for (int i = 1; i<19; i++) {
//			handler.addObject(new Background(50*i, 0, ID.Background, 50, 50, handler, topTile, this));
//		}
//		
//		for (int i = 0; i<15; i++) {
//			handler.addObject(new Background(WIDTH - 50, i*50, ID.Background, 50, 50, handler, rightTile, this));
//		}
//		
//		for (int i = 1; i<19; i++) {
//			handler.addObject(new Background(50*i, HEIGHT - 50, ID.Background, 50, 50, handler, bottomTile, this));
//		}
//		
//		for (int i = 1; i<19; i++) {
//			for (int j = 1; j<14; j++) {
//			handler.addObject(new Background(50*i, 50*j, ID.Background, 50, 50, handler, floortile, this));
//		}}
//
		this.player = new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, 32, 32, handler);
		handler.addObject(player);
//		handler.addObject(new HealPackSpecialItem(800, 100, ID.Item, 32, 32, handler, healthpack, this));
//		handler.addObject(new NukeSpecialItem(800, 400, ID.Item, 32, 32, handler, nuke, this));

	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
//		this sets up the game loop so that the game can update itself
//		It repeats the tick method over and over on each object
		
		this.requestFocus(); // this makes it so you don't have to click on screen to get controls
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		
		if (gameState == STATE.StartScreen) {
			startscreen.tick();
		}
		
		if (gameState == STATE.Game) {
			if (startGame) {handler.removeById(ID.Bullet);}
			while (handler.countObjectsById(ID.Player) > 1) {
				handler.removeById(ID.Player);
			}
			startGame = false;
			bulletController.tick();
			handler.tick();
			gun.tick();
			startCount();
			if (hud.HEALTH <= 0) {
				HUD.HEALTH = 100;
				handler.clear(ID.BasicEnemy);
				handler.removeObject(player);
				this.gameState = STATE.GameOver;
			}		
			
			LinkedList<ID> ids = new LinkedList<ID>();
			
			for (int i = 0; i < handler.objectList.size(); i++) {
				GameObject tempObject = handler.objectList.get(i);
				ids.add(tempObject.getID());
			}
			
			if (ids.contains(ID.BasicEnemy)) {
				current_room.setDoors(false);
			}
			else {
				current_room.setDoors(true);
			}
			current_room.tick();
		}		
		if (gameState == STATE.GameOver) {
			gameover.tick();
		}
		if (gameState == STATE.WinScreen) {
			winscreen.tick();
		}
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
//			the arguments decides how many buffer strategies to create. 3 is recommended
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
//		creates game background. Everything you want to render must be after the next 3 lines
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	
//		renders all the game objects
		
		if (gameState == STATE.StartScreen) {
			startscreen.render(g);
		}

		if (gameState == STATE.Game) {
			handler.render(g);
			hud.render(g);
			gun.render(g);
			bulletController.render(g);
			current_room.render(g);

			if (used_nuke) {
				this.removeMouseMotionListener(gun);
				use_nuke.render(g);
			}
		}
		
		if (gameState == STATE.GameOver) {
			gameover.render(g);
		}
		
		if (gameState == STATE.WinScreen) {
			winscreen.render(g);
		}
				
		g.dispose();
		bs.show();}
		
	
//	creates constraints for game objects places they can move
	public static int clamp(int x, int min, int max) {
		if(x >= max) 
			return x = max;
		else if(x <= min)
			return x = min;
		else
			return x;
	}
	
	public static void startCount() {
		if (startCount) {
//			if (count == 0)	setCount(countNum);
			count -= 1;
		} else {count = 0;}
	}
	

	public static void main(String[] args) throws IOException {
		new Game();
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Game.count = count;
	}

}
