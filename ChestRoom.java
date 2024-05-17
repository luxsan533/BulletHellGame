package bullethellgame;

import java.awt.image.BufferedImage;

public class ChestRoom extends Room{

	private GameObject chest;

	public ChestRoom(Game game, Handler handler, int room_number, Door[] doors) {
		super(game, handler, room_number, doors);
		this.room_type = Room.ROOM_TYPE.EnemyRoom;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void spawn() {
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImage nukeImage = loader.loadImage("/nukegame.jpg");
		NukeSpecialItem nuke = new NukeSpecialItem(800, 400, ID.Item, 32, 32, handler, nukeImage, game);
		chest = new Chest((int) Game.WIDTH / 2, (int) Game.HEIGHT / 4, ID.Chest, 50, 50, handler, true, nuke);
		handler.addObject(chest);
	}

	@Override
	protected void clear() {
		handler.removeObject(chest);
	}

}
