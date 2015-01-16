package com.bigbreakfast.paulbearer.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.bigbreakfast.paulbearer.framework.GameObject;
import com.bigbreakfast.paulbearer.framework.ObjectId;
import com.bigbreakfast.paulbearer.framework.Texture;
import com.bigbreakfast.paulbearer.window.Animation;
import com.bigbreakfast.paulbearer.window.Game;
import com.bigbreakfast.paulbearer.window.Handler;

//This object is not rendered and is instead added to the inventory list of items.
//This object should also never be removed from the handler unless the Inventory says so.

public class Item extends GameObject {
	
	Handler handler;
	
	private String itemName;
	private int quantity;
	//private List<ItemAttributes> itemAttributes;
	private String description;

	public Item(float x, float y, String itemName, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.itemName = itemName;
	}

	@Override
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 32, 32);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick(LinkedList<GameObject> gObject) {
		// TODO Auto-generated method stub
		
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setQuantity(int i) {
		
		if (this.quantity + i <= 0) System.out.println(this.itemName + "quantity = " + this.quantity);
		else this.quantity = this.quantity + i;
		
	}
	
	public int getQuantity() {
		return this.quantity;
	}

}
