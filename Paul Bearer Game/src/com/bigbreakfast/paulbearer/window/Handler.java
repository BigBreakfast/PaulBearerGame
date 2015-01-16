package com.bigbreakfast.paulbearer.window;

import java.awt.Graphics;
import java.util.LinkedList;

import com.bigbreakfast.paulbearer.framework.GameObject;
import com.bigbreakfast.paulbearer.framework.ObjectId;
import com.bigbreakfast.paulbearer.objects.TextBox;
import com.bigbreakfast.paulbearer.objects.Inventory;

public class Handler {
	
	private final Inventory inventory = new Inventory();
	
	public LinkedList<GameObject> gObject = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	private TextBox textBox;
	
	public void tick() {
		for (int i = 0; i < gObject.size(); i++) {
			
			tempObject = gObject.get(i);			
			tempObject.tick(gObject);
		}
	}
	
	public void render(Graphics g) {
		
		for (int i = 0; i < gObject.size(); i++) {
			
			tempObject = gObject.get(i);			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject gObject) {
		
		if (gObject.getId() == ObjectId.TextBox) {
			//get some text from state machine / xml
			//TextFeeder.getState();
		}
		
		this.gObject.add(gObject);
	}
	
	public void removeObject(GameObject gObject) {
		
		//Idea was, when you pick up an item, before it derenders it, it creates a copy in Inventory.
		//Then you can go ahead and derender it.
//		if (gObject.getId() == ObjectId.Item) {
//		}
		this.gObject.remove(gObject);
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
		return this.textBox.getBoxType();
	}
	
	public TextBox getTextBox(String textBoxType) {
		
		for (int i = 0; i < gObject.size(); i++) {
			if (gObject.get(i).getId() == ObjectId.TextBox) {
				
				if (hasTextBox() == 1) {
					try {
						return this.textBox = (TextBox) gObject.get(i);
					}
					catch(Exception e) {
						e.printStackTrace();
						new Exception("handler's TextBox is null!");
					}
				}
				
				else if (hasTextBox() > 1 && gObject.get(i).getTextBoxType() == textBoxType) {
					return this.textBox = (TextBox) gObject.get(i);
				}
			}
		}
		return null;
	}
}
