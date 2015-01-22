package com.bigbreakfast.paulbearer.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.bigbreakfast.paulbearer.framework.GameObject;
import com.bigbreakfast.paulbearer.framework.ObjectId;
import com.bigbreakfast.paulbearer.framework.Texture;
import com.bigbreakfast.paulbearer.window.Game;

//LootableItem is the rendered item or block which returns an item on loot
//When the item is interacted with, a loot confirmation appears "This looks interesting > Check, Done" > "Found cockroach [20]"
//The object of type item is also created in handler and added to the inventory

//The item could also possibly be generated XML, which defines its name, attributes, and description

public class LootableItem extends GameObject {
	
	Texture tex = Game.getInstance();
	
	private String itemName;
	private int type;

	public LootableItem(float x, float y, String itemName, int type, ObjectId id) {
		super(x, y, id);
		this.itemName = itemName;
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	@Override
	public void render(Graphics g) {
		
		if (type == 7)
			g.drawImage(tex.block[7], (int) x, (int) y, null);
		
	}

	@Override
	public void tick(LinkedList<GameObject> gObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BufferedImage getObjectImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
