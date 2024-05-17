package bullethellgame;

import java.awt.image.BufferedImage;

public class EnemyRoom extends Room{

	public EnemyRoom(Game game, Handler handler, int room_number, Door[] doors) {
		super(game, handler, room_number, doors);
		this.room_type = Room.ROOM_TYPE.EnemyRoom;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void spawn() {
			for (int i = 1; i < room_number; i++) {
//				int x = r.nextInt(Game.WIDTH - 100);
//				int y = r.nextInt(Game.HEIGHT - 100);

//				Rectangle enemyView = new Rectangle(x - 200, y - 125, 450, 300);
				BufferedImage[] basicEnemyImages = {Game.flabbergasted, Game.burnedflabbergasted, Game.frozenflabbergasted};
//				handler.addEnemy(new BasicEnemy(200, 200, ID.BasicEnemy, 32, 32, handler, 32, 450, 300, basicEnemyImages));
//				handler.addObject(new Wall(0, 0, ID.Wall, 50, Game.HEIGHT, handler));

//				GameObject nuke = new NukeSpecialItem(800, 400, ID.Item, 32, 32, handler, Game.nuke, game);

//				handler.addObject(new Chest(50, 400, ID.Chest, 32, 32, handler, true, nuke));
//				public Chest(int x, int y, ID id, int length, int width, Handler handler, boolean isLocked) {
				
				if (!isCompleted()) {
					handler.addEnemy(new BasicEnemy(100 + 400*(i-1), 250, ID.BasicEnemy, 32, 32, handler, 32, 450, 300, basicEnemyImages));
				}

//				handler.addEnemy(new BeamEnemy(220, 250, ID.BasicEnemy, 32, 32, handler, 32, 450, 300, basicEnemyImages));

//				handler.addObject(new SecondEnemy(50, 50, ID.BasicEnemy, handler, 32, 450, 300, Game.pirate));

//				handler.addObject(new BasicEnemy(10*i, 10*i, ID.BasicEnemy, handler, 32));
			}
//			handler.addItem(new HeartItem(70,  100, ID.Item, 32, 32, handler, Game.heart));
//			handler.addItem(new KeyItem(20,  300, ID.Item,  20, 37, handler, Game.key));
	}

	@Override
	protected void clear() {

	}

}
