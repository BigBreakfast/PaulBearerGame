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

public class Item {
	
	//Handler handler;
	
	private String itemName;
	private int quantity;
	//private List<ItemAttributes> itemAttributes;
	private String description;
	
	private int strength;
	private int misery;
	private int intelligence;

	//Eventually a list of <attributes> will be passed
	public Item(String itemName, int strength, int misery, int intelligence, ObjectId id) {
		
		this.itemName = itemName;
		this.strength = strength;
		this.misery = misery;
		this.intelligence = intelligence;
		
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getMisery() {
		return misery;
	}

	public void setMisery(int misery) {
		this.misery = misery;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

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
