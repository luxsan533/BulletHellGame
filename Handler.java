//This handles all the objects in the game. Will individually update them and render them to the screen
package bullethellgame;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

public class Handler {
//	This will contain all the game objects of our game
	LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	LinkedList<Enemy> enemyList = new LinkedList<Enemy>();
	LinkedList<Item> itemList = new LinkedList<Item>();
	
//	performs the tick and render method of each game object onto every game object
	public void tick() {
		for (int i = 0; i < objectList.size(); i++) {
			GameObject tempObject = objectList.get(i);

			tempObject.tick();
		}
	}
	public void render(Graphics g) {
		for (int i = 0; i < objectList.size(); i++) {
			GameObject tempObject = objectList.get(i);
			tempObject.render(g);
		}
	}
	
//	linked lists have an add and remove method that easily adds and removes objects
	
	public void addEnemy(GameObject object) {
		this.objectList.add(object);
		this.enemyList.add((Enemy) object);
	}
	
	public void addItem(GameObject object) {
		this.objectList.add(object);
		this.itemList.add((Item) object);
	}
	public void removeItem(GameObject object) {
		this.objectList.remove(object);
		this.itemList.remove((Item) object);
	}
	
	public void addObject(GameObject object) {
		this.objectList.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.objectList.remove(object);
	}
	
	public void clear(ID id) {
		   Iterator<GameObject> iterator = objectList.iterator();
	        while (iterator.hasNext()) {
	            GameObject obj = iterator.next();
	            if (obj.getID() == id) {
	                iterator.remove();
	            }
	        }
	}
	
	public int countObjectsById(ID id) {
		   int i = 0;
		   Iterator<GameObject> iterator = objectList.iterator();
	        while (iterator.hasNext()) {
	            GameObject obj = iterator.next();
	            if (obj.getID() == id) {
	                i++;
	            }
	        }
	        return i;
	}
	
	public void removeById(ID id) {
		for (GameObject obj : objectList) {
            if (obj.getID() == id) {
                objectList.remove(obj);
                break; 
            }
		}
	}
}
