package com.bigbreakfast.paulbearer.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.bigbreakfast.paulbearer.framework.GameObject;
import com.bigbreakfast.paulbearer.framework.ObjectId;
import com.bigbreakfast.paulbearer.framework.Texture;
import com.bigbreakfast.paulbearer.window.Game;
import com.bigbreakfast.paulbearer.window.Handler;

public class TextBox extends GameObject {
	
	Texture tex = Game.getInstance();
	
	private final List<String> startMenuChoices = Arrays.asList("Party", "Inventory", "Urn", "Save", "Options", "Quit");
	private final List<String> inventoryOptions = Arrays.asList("Use", "Equip", "Toss");
	private final List<String> quitConfirmationOptions = Arrays.asList("Quit", "Cancel");
	private final List<String> saveConfirmationOptions = Arrays.asList("Save", "Cancel");
	//private List<String> inventoryItems = Arrays.asList("Cockroach", "Lit Cigarette", "Half-Drank Beer", "Some Old Coins");
	private List<String> inventoryItemNames = new ArrayList<String>();
	private List<Item> inventoryItems;
	
	public int selected = 0;
	private boolean visible;
	private String boxType;
	private String text;
	
	private String fontType = "TerminalVector";
	private int fontSize = 18;
	private Color fontColor = Color.WHITE;
	private final Font formattedFont = new Font(fontType, Font.PLAIN, fontSize);
	
	public TextBox(float x, float y, String boxType, ObjectId id) {
		super(x, y, id);
		this.visible = true;
		this.boxType = boxType;
		this.setTextBoxType(boxType);
		
		if (boxType == "InventoryBox") {
			text = "Inventory";
		}
		
		
		if (boxType == "DialogBox")	text = "Dialog";		
		if (boxType == "Lootable") text = "Lootable";		
		if (boxType == "QuitConfirmationBox") text = "Exit The Game?";		
		if (boxType == "SaveConfirmationBox") text = "Save The Game?";
	}
	
	public TextBox(float x, float y, String boxType, List<Item> inventoryItems, ObjectId id) {
		super(x, y, id);
		this.visible = true;
		this.boxType = boxType;
		this.setTextBoxType(boxType);
		this.setInventoryItems(inventoryItems);
		
		if (boxType == "InventoryBox") {
			text = "Inventory";
		}
		
		
		if (boxType == "DialogBox")	text = "Dialog";		
		if (boxType == "Lootable") text = "Lootable";		
		if (boxType == "QuitConfirmationBox") text = "Exit The Game?";		
		if (boxType == "SaveConfirmationBox") text = "Save The Game?";
	}
	
	public void render(Graphics g) {		
		
		drawTextBox(g);
		
		//StartMenu
		if (this.boxType == "StartMenu") {
			
			//Text
			for (int i = 0; i < startMenuChoices.size(); i++) {
				
				if (i == this.selected)	g.setColor(Color.YELLOW);
				else g.setColor(fontColor);
				
				g.drawString(startMenuChoices.get(i), calcXPosition("ListChoices", x), calcYPosition("ListChoices", y) + (i *32));
			}
		}
		
		//DialogBox
		else if (this.boxType == "DialogBox") {
			
			//Text
			g.drawString(text, calcXPosition("MAINTEXT", x), calcYPosition("MAINTEXT", y));
		}
		
		else if (this.boxType == "PartyBox") {}
		
		//InventoryBox
		else if (this.boxType == "InventoryBox") {
			
			g.drawString(text, calcXPosition("ListChoices", x), calcYPosition("ListChoices", y));
			
			//Text
			for (int i = 0; i < inventoryItems.size(); i++) {
				
				if (i == this.selected)	g.setColor(Color.YELLOW);
				else g.setColor(fontColor);
				
				//g.drawString(inventoryItems.get(i), calcXPosition("ListChoices", x), calcYPosition("ListChoices", y) + ((i + 1) *32));
				g.drawString(inventoryItemNames.get(i), calcXPosition("ListChoices", x), calcYPosition("ListChoices", y) + ((i + 1) *32));

			}
		}
		else if (this.boxType == "UrnBox") {}
		else if (this.boxType == "SaveBox") {}
		else if (this.boxType == "OptionsBox") {}
		else if (this.boxType == "QuitBox") {}
		else if (this.boxType == "ConfirmationBox") {
			
		}
		else if (this.boxType == "DecisionBox") {}
		
		//SaveConfirmationBox
		else if (this.boxType == "SaveConfirmationBox") {
			
			g.drawString(text, calcXPosition("2ndListChoices", x), calcYPosition("2ndListChoices", y));
			
			//Text
			for (int i = 0; i < saveConfirmationOptions.size(); i++) {
				
				if (i == this.selected)	g.setColor(Color.YELLOW);
				else g.setColor(fontColor);
				
				
				g.drawString(saveConfirmationOptions.get(i), calcXPosition("2ndListChoices", x), calcYPosition("2ndListChoices", y) + ((i + 1) *32));
			}
		}
		
		//InventoryOptionsBox
		else if (this.boxType == "InventoryOptionsBox") {
			
			//Text
			for (int i = 0; i < inventoryOptions.size(); i++) {
				
				if (i == this.selected)	g.setColor(Color.YELLOW);
				else g.setColor(fontColor);
				
				g.drawString(inventoryOptions.get(i), calcXPosition("2ndListChoices", x), calcYPosition("2ndListChoices", y) + (i *32));
			}
		}
		
		//QuitConfirmationBox
		else if (this.boxType == "QuitConfirmationBox") {
			
			g.drawString(text, calcXPosition("2ndListChoices", x), calcYPosition("2ndListChoices", y));
			
			//Text
			for (int i = 0; i < quitConfirmationOptions.size(); i++) {
				
				if (i == this.selected)	g.setColor(Color.YELLOW);
				else g.setColor(fontColor);
				
				g.drawString(quitConfirmationOptions.get(i), calcXPosition("2ndListChoices", x), calcYPosition("2ndListChoices", y) + ((i + 1) *32));
			}
		}
	}

	
	public void tick(LinkedList<GameObject> gObject) {
	
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 0, 0);
	}
	
	private int calcWidth(String type) {
		
		if (type == "DialogBox") 
			return Game.WIDTH;
		
		if (type == "StartMenu") 
			return Game.WIDTH/3;
		
		if (type == "InventoryBox") 
			return Game.WIDTH/3;
		
		if (type == "SaveConfirmationBox") 
			return Game.HEIGHT/3;
		
		if (type == "QuitConfirmationBox") 
			return Game.HEIGHT/3;
		
		if (type == "InventoryOptionsBox") 
			return Game.HEIGHT/3;
			
		return 0;
	}
	
	private int calcHeight(String type) {
		
		if (type == "DialogBox") 
			return Game.HEIGHT/3;
		
		if (type == "StartMenu") 
			return Game.HEIGHT;
		
		if (type == "InventoryBox") 
			return Game.HEIGHT;
		
		if (type == "SaveConfirmationBox") 
			return Game.HEIGHT/3;
		
		if (type == "QuitConfirmationBox") 
			return Game.HEIGHT/3;
		
		if (type == "InventoryOptionsBox") 
			return Game.HEIGHT/3;
		
		return 0;
	}
	
	private int calcXPosition(String type, float x) {
		
		if (type == "DialogBox") 
			return (int) (x - (Game.WIDTH/2) + 16);
		
		if (type == "StartMenu") 
			return (int) (x - (Game.WIDTH/2) + 16);
		
		if (type == "InventoryBox") 
			return (int) (x - (Game.WIDTH/2) + 16);
		
		if (type == "SaveConfirmationBox") 
			return (int) ((x - (Game.WIDTH/2) + 16) + Game.WIDTH/3);
		
		if (type == "QuitConfirmationBox") 
			return (int) ((x - (Game.WIDTH/2) + 16) + Game.WIDTH/3);
		
		if (type == "InventoryOptionsBox") 
			return (int) ((x - (Game.WIDTH/2) + 16) + Game.WIDTH/3);
		
		if (type == "MAINTEXT") 
			return (int) (x - (Game.WIDTH/2)) + 20;
		
		if (type == "ListChoices") 
			return (int) (x - (Game.WIDTH/2)) + 20;
		
		if (type == "2ndListChoices") 
			return (int) ((x - (Game.WIDTH/2) + 20) + Game.WIDTH/3);
		
		//xposition of character portrait image?
		
		return 0;
	}
	
	private int calcYPosition(String type, float y) {
		
		if (type == "DialogBox") 
			return (int) (y + (Game.HEIGHT/2 - Game.HEIGHT/3));
		
		if (type == "StartMenu") 
			return (int) (y - (Game.HEIGHT/2));
		
		if (type == "InventoryBox") 
			return (int) (y - (Game.HEIGHT/2));
		
		if (type == "SaveConfirmationBox") 
			return (int) (y - (Game.HEIGHT/2));
		
		if (type == "QuitConfirmationBox") 
			return (int) (y - (Game.HEIGHT/2));
		
		if (type == "InventoryOptionsBox") 
			return (int) (y - (Game.HEIGHT/2));
		
		if (type == "MAINTEXT") 
			return (int) (y + (Game.HEIGHT/4));
		
		if (type == "ListChoices") 
			return (int) (y - (Game.HEIGHT/2) + 32);
		
		if (type == "2ndListChoices") 
			return (int) (y - (Game.HEIGHT/2) + 32);
		
		//xposition of character head image?
		
		return 0;
	}
	
	public int getSelected() {
		return this.selected;
	}
	
	public String getBoxType() {
		return this.boxType;
	}
	
	public List<String> getStartMenuChoices() {
		return this.startMenuChoices;
	}
	
	public List<String> getQuitConfirmationOptions() {
		return this.quitConfirmationOptions;
	}
	
	public List<String> getSaveConfirmationOptions() {
		return this.saveConfirmationOptions;
	}
	
	public List<String> getInventoryOptions() {
		return this.inventoryOptions;
	}
	
	private void drawTextBox(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.drawRect(calcXPosition(this.boxType, x),
				calcYPosition(this.boxType, y),
				calcWidth(this.boxType),
				calcHeight(this.boxType));
		g.setColor(Color.RED);
		g.fillRect(calcXPosition(this.boxType, x),
				calcYPosition(this.boxType, y),
				calcWidth(this.boxType),
				calcHeight(this.boxType));
		g.setColor(fontColor);
		g.setFont(formattedFont);
	}
	
	private void setInventoryItems(List<Item> inventoryItems) {
		
		this.inventoryItems = new ArrayList<Item>(inventoryItems);
		
		setInventoryItemNames(inventoryItems);
	}
	
	private List<String> setInventoryItemNames(List<Item> inventoryItems) {
		
		for (int i = 0; i < inventoryItems.size(); i++) {
			
			this.inventoryItemNames.add(inventoryItems.get(i).getItemName());
		}
		
		return this.inventoryItemNames;
		
	}
	
	public List<String> getInventoryItemNames() {
		
		return this.inventoryItemNames;
	}
}

