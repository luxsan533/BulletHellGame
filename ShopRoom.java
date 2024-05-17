package bullethellgame;

import java.awt.Graphics;
import java.util.LinkedList;

public class ShopRoom extends Room{

	private GameObject heart;
	private GameObject key;
	private GameObject healthpack;


	public ShopRoom(Game game, Handler handler, int room_number, Door[] doors) {
		super(game, handler, room_number, doors);
		this.room_type = Room.ROOM_TYPE.ShopRoom;
		this.room_items = new LinkedList<Item>();
	}

	@Override
	public void spawn() {
		while (handler.countObjectsById(ID.Player) > 1) {
			handler.removeById(ID.Player);
		}
		Item heart = new HeartItem(250,  100, ID.Item, 32, 32, handler, Game.heart);
		heart.inShop = true;
		heart.price = 10;
				
		Item key = new KeyItem(450,  100, ID.Item, 20, 37, handler, Game.key);
		key.inShop = true;
		key.price = 5;
		
		Item healthpack = new HealPackSpecialItem(650,  100, ID.SpecialItem, 20, 37, handler, Game.healthpack, game);
		healthpack.inShop = true;
		healthpack.price = 15;
	
		handler.addItem(heart);
		handler.addItem(key);
		handler.addItem(healthpack);
		
		room_items.add(heart);
		room_items.add(key);	
		room_items.add(healthpack);	
	}
		
	public void render(Graphics g) {
		super.render(g);
		for (int i = 0; i < room_items.size(); i++) {
			Item shop_item = room_items.get(i);
			g.drawString(shop_item.item_name, shop_item.getX(), shop_item.getY() - 10); 
			g.drawString("$" + Integer.toString(shop_item.price), shop_item.getX(), shop_item.getY() + shop_item.length + 10); 
			
			for (int j = 0; j < handler.objectList.size(); j++) {
				GameObject tempObject = handler.objectList.get(j);
				
				if (tempObject.getID() == ID.Player) {
					if (shop_item.shopBounds().intersects(tempObject.getBounds())) {
						g.drawString("Press R to buy", tempObject.getX() - 20, tempObject.getY() + 47);
					}
				}
		}
		}
	}

	@Override
	protected void clear() {
		handler.clear(ID.Item);
		if (Game.currentSpecial != "HealPack") {handler.clear(ID.SpecialItem);}
		room_items.remove(heart);
		room_items.remove(key);			
		room_items.remove(healthpack);			

//
//		handler.removeItem(heart);
//		handler.removeItem(key);
		
	}
}
