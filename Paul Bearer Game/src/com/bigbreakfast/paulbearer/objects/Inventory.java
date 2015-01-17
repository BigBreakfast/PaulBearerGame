package com.bigbreakfast.paulbearer.objects;

import java.util.ArrayList;
import java.util.List;

//The Inventory contains a list of Items. LootableItems when acquired by the character, are added to Inventory as Items.

public class Inventory {

	private List<Item> inventoryItems = new ArrayList<Item>();
	
	public void addItem(Item i) {
		inventoryItems.add(i);
	}
	
	public void removeItem(Item i) {
		inventoryItems.remove(i);
	}
	
	public List<Item> getInventoryItems() {
		return this.inventoryItems;
	}
	
	
}
