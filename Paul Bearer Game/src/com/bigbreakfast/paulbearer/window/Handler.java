package com.bigbreakfast.paulbearer.window;

import java.awt.Graphics;
import java.util.LinkedList;

import com.bigbreakfast.paulbearer.framework.GameObject;
import com.bigbreakfast.paulbearer.framework.ObjectId;
import com.bigbreakfast.paulbearer.objects.TextBox;
import com.bigbreakfast.paulbearer.objects.Inventory;

public class Handler {
	
	public static LinkedList<GameObject> gObject = new LinkedList<GameObject>();
	private static Inventory inventory;
	private static GameObject tempObject;
	private static TextBox textBox;
	
	public Handler() {}
	
	public void tick() {
		for (int i = 0; i < gObject.size(); i++) {
			
			tempObject = gObject.get(i);			
			tempObject.tick(gObject);
		}
	}
	
	public void render(final Graphics g) {
		
		for (int i = 0; i < gObject.size(); i++) {
			
			tempObject = gObject.get(i);			
			tempObject.render(g);
		}
	}
	
	public void addObject(final GameObject gObject) {
		Handler.gObject.add(gObject);
	}
	
	public void addInventory(final Inventory inventory) {
		Handler.inventory = inventory;
	}
	
	public Inventory getInventory() {
		return Handler.inventory;
	}
	
	public void removeObject(final GameObject gObject) {
		Handler.gObject.remove(gObject);
	}
	
	public int hasTextBox() {
		int count = 0;
		
		for (int i = 0; i < gObject.size(); i++) {
			if (gObject.get(i).getId() == ObjectId.TextBox) {
				//increase count
				count++;
			}
		}
		
		return count;
	}
	
	public String getTextBoxType() {
		return Handler.textBox.getBoxType();
	}
	
	public TextBox getTextBox(final String textBoxType) {
		
		for (int i = 0; i < gObject.size(); i++) {
			if (gObject.get(i).getId() == ObjectId.TextBox) {
				
				if (hasTextBox() == 1) {
					try {
						//return this.textBox = (TextBox) gObject.get(i);
						Handler.textBox = (TextBox) gObject.get(i);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				else if (hasTextBox() > 1 && gObject.get(i).getTextBoxType() == textBoxType) {
					//return this.textBox = (TextBox) gObject.get(i);
					Handler.textBox = (TextBox) gObject.get(i);
				}
			}
		}
		return Handler.textBox;
	}
}
