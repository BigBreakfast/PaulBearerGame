package com.bigbreakfast.paulbearer.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.bigbreakfast.paulbearer.objects.TextBox;
import com.bigbreakfast.paulbearer.window.Handler;

public class KeyInput extends KeyAdapter {
	
	Handler handler;
	private float playerX;
	private float playerY;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.gObject.size(); i++){
			GameObject tempObject = handler.gObject.get(i);
			
			if(tempObject.getId() == ObjectId.Player) {
				
				playerX = tempObject.getX();
				playerY = tempObject.getY();				
						
				if(handler.hasTextBox() == 0) {
					System.out.println("hasTextBox == " +handler.hasTextBox());
					
					if(key == KeyEvent.VK_D) tempObject.setVelX(4);  //move right
					if(key == KeyEvent.VK_A) tempObject.setVelX(-4); //move left
					if(key == KeyEvent.VK_W) tempObject.setVelY(-4); //move up
					if(key == KeyEvent.VK_S) tempObject.setVelY(4);  //move down
				}
				
				//if player is colliding with an object
				if(key == KeyEvent.VK_E && tempObject.isColliding())
				{
					//if it's a 'speakable' object (lootables, NPCs) open textbox with options
					System.out.println("Player Colliding");
				}
				
				//if player is not colliding with an object, open start menu
				else if (key == KeyEvent.VK_SPACE && !tempObject.isColliding()) {
					
					if (handler.hasTextBox() == 0) {
						handler.addObject(new TextBox(playerX, playerY, "StartMenu", ObjectId.TextBox));
						System.out.println(tempObject.getId().toString() + " " +tempObject.isColliding());
					}
				}
				//just to test DialogBox
				else if (key == KeyEvent.VK_V && !tempObject.isColliding()) {
					
					if (handler.hasTextBox() == 0) {
						handler.addObject(new TextBox(playerX, playerY, "DialogBox", ObjectId.TextBox));
						System.out.println(tempObject.getId().toString() + " " +tempObject.isColliding());
					}
				}
				
//				System.out.println(tempObject.getX() + " " + tempObject.getY() + " ObjectId.Player");
			}
			
			if(tempObject.getId() == ObjectId.TextBox) {
				
				if(key == KeyEvent.VK_SPACE) {
					
					if (tempObject.isVisible) {
						handler.removeObject(tempObject);
						System.out.println("removeObject(" +tempObject.getId().toString()+ "");
					}
					tempObject.isVisible = !tempObject.isVisible;
//					System.out.print("tempObject: " +tempObject.getId().toString()+ ".isVisible = " +tempObject.isVisible);
				}
				
				if(key == KeyEvent.VK_V) {
					
					if (tempObject.isVisible) {
						handler.removeObject(tempObject);
						System.out.println("removeObject(" +tempObject.getId().toString()+ "");
					}
					tempObject.isVisible = !tempObject.isVisible;
					System.out.print("tempObject: " +tempObject.getId().toString()+ ".isVisible = " +tempObject.isVisible);
				}
				
//				System.out.println(tempObject.getX() + " " + tempObject.getY() + " ObjectId.TextBox");
//				System.out.println(tempObject.isVisible + " ObjectId.TextBox");
				
				//startMenuKeyCommands(tempObject, key);
				
				//If the textBox is a StartMenu, navigate through the options
				if (tempObject.textBoxType == "StartMenu" && handler.hasTextBox() == 1) {
					
					if (key == KeyEvent.VK_W) {
						handler.getTextBox("StartMenu").selected--;
						if (handler.getTextBox("StartMenu").selected < 0)
							handler.getTextBox("StartMenu").selected = handler.getTextBox("StartMenu").getStartMenuChoices().size() - 1;
					}
					if (key == KeyEvent.VK_S) {
						handler.getTextBox("StartMenu").selected++;
						if (handler.getTextBox("StartMenu").selected >= handler.getTextBox("StartMenu").getStartMenuChoices().size())	
							handler.getTextBox("StartMenu").selected = 0;
					}
					
					//navigate backward
					if (key == KeyEvent.VK_A) {
						System.out.println("StartMenu Back Selected");
						handler.removeObject(tempObject);
					}
					
					//select the StartMenu Option
					if (key == KeyEvent.VK_E || key == KeyEvent.VK_D) {
						int selection = handler.getTextBox("StartMenu").selected;
						
						if (selection == 0) { //Party
							System.out.println(handler.getTextBox("StartMenu").getStartMenuChoices().get(selection) + " Party Selected");
							handler.removeObject(tempObject);
							handler.addObject(new TextBox(playerX, playerY, "PartyBox", ObjectId.TextBox));
							break;
						}
						if (selection == 1) { //Inventory
							System.out.println(handler.getTextBox("StartMenu").getStartMenuChoices().get(selection) + " Inventory Selected");
							handler.removeObject(tempObject);
							handler.addObject(new TextBox(playerX, playerY, "InventoryBox", ObjectId.TextBox));
							break;
						}
						if (selection == 2) { //Urn
							System.out.println(handler.getTextBox("StartMenu").getStartMenuChoices().get(selection) + " Urn Selected");
							handler.removeObject(tempObject);
							handler.addObject(new TextBox(playerX, playerY, "UrnBox", ObjectId.TextBox));
							break;
						}
						if (selection == 3) { //Save
							System.out.println(handler.getTextBox("StartMenu").getStartMenuChoices().get(selection) + " Save Selected");
							//handler.removeObject(tempObject);
							handler.addObject(new TextBox(playerX, playerY, "SaveConfirmationBox", ObjectId.TextBox));
							break;
						}
						if (selection == 4) { //Options
							System.out.println(handler.getTextBox("StartMenu").getStartMenuChoices().get(selection) + " Options Selected");
							handler.removeObject(tempObject);
							handler.addObject(new TextBox(playerX, playerY, "OptionsBox", ObjectId.TextBox));
							break;
						}
						if (selection == 5) { //Quit
							System.out.println(handler.getTextBox("StartMenu").getStartMenuChoices().get(selection) + " Quit 1 Selected");
							handler.addObject(new TextBox(playerX, playerY, "QuitConfirmationBox", ObjectId.TextBox));
							break;
						}
					}
				}
				
				//If the textBox is a InventoryBox, navigate through the options
				if (tempObject.textBoxType == "InventoryBox" && handler.hasTextBox() == 1) {
					
					if (key == KeyEvent.VK_W) {
						handler.getTextBox("InventoryBox").selected--;
						if (handler.getTextBox("InventoryBox").selected < 0)
							handler.getTextBox("InventoryBox").selected = handler.getTextBox("InventoryBox").getInventoryItems().size() - 1;
					}
					
					if (key == KeyEvent.VK_S) {
						handler.getTextBox("InventoryBox").selected++;
						if (handler.getTextBox("InventoryBox").selected >= handler.getTextBox("InventoryBox").getInventoryItems().size())	
							handler.getTextBox("InventoryBox").selected = 0;
					}
					
					//navigate backward
					if (key == KeyEvent.VK_A) {
						System.out.println("Inventory Back Selected");
						handler.removeObject(tempObject);
						handler.addObject(new TextBox(playerX, playerY, "StartMenu", ObjectId.TextBox));
						break;
					}
					
					//select the Inventory Item
					if (key == KeyEvent.VK_E || key == KeyEvent.VK_D) {
						int selection = handler.getTextBox("InventoryBox").selected;
						System.out.println(selection);
						
						for (int ii = 0; ii < handler.getTextBox("InventoryBox").getInventoryItems().size(); ii++) {
							
							if (selection == ii) {
								System.out.println(handler.getTextBox("InventoryBox").getInventoryItems().get(ii) + " Selected");
								handler.addObject(new TextBox(playerX, playerY, "InventoryOptionsBox", ObjectId.TextBox));
								break;
							}
						}
						break;
					}
				}
				
				//If the textBox is a InventoryOptionsBox, navigate through the options
				if (tempObject.textBoxType == "InventoryOptionsBox" && handler.hasTextBox() > 1) {
					if (key == KeyEvent.VK_W) {
						handler.getTextBox("InventoryOptionsBox").selected--;
						if (handler.getTextBox("InventoryOptionsBox").selected < 0)
							handler.getTextBox("InventoryOptionsBox").selected = handler.getTextBox("InventoryOptionsBox").getInventoryOptions().size() - 1;
					}
					if (key == KeyEvent.VK_S) {
						handler.getTextBox("InventoryOptionsBox").selected++;
						if (handler.getTextBox("InventoryOptionsBox").selected >= handler.getTextBox("InventoryOptionsBox").getInventoryOptions().size())	
							handler.getTextBox("InventoryOptionsBox").selected = 0;
					}
					
					//navigate backward
					if (key == KeyEvent.VK_A) {
						System.out.println("InventoryOptions Back Selected");
						handler.removeObject(tempObject);
					}
					
					//select the InventoryOptionsBox Option
					if (key == KeyEvent.VK_E || key == KeyEvent.VK_D) {
						int selection = handler.getTextBox("InventoryOptionsBox").selected;
						
						if (selection == 0) { //Use
							System.out.println(handler.getTextBox("InventoryOptionsBox").getInventoryOptions().get(selection) + " Use Selected");
						}
						
						if (selection == 1) { //Equip
							System.out.println(handler.getTextBox("InventoryOptionsBox").getInventoryOptions().get(selection) + " Equip Selected");
							//handler.removeObject(tempObject);
						}
						
						if (selection == 2) { //Toss
							System.out.println(handler.getTextBox("InventoryOptionsBox").getInventoryOptions().get(selection) + " Toss Selected");
							//handler.removeObject(tempObject);
						}
					}
				}
				
				//If the textBox is a QuitConfirmationBox, navigate through the options
				if (tempObject.textBoxType == "QuitConfirmationBox" && handler.hasTextBox() > 1) {
					if (key == KeyEvent.VK_W) {
						handler.getTextBox("QuitConfirmationBox").selected--;
						if (handler.getTextBox("QuitConfirmationBox").selected < 0)
							handler.getTextBox("QuitConfirmationBox").selected = handler.getTextBox("QuitConfirmationBox").getQuitConfirmationOptions().size() - 1;

					}
					
					if (key == KeyEvent.VK_S) {
						handler.getTextBox("QuitConfirmationBox").selected++;
						if (handler.getTextBox("QuitConfirmationBox").selected >= handler.getTextBox("QuitConfirmationBox").getQuitConfirmationOptions().size())	
							handler.getTextBox("QuitConfirmationBox").selected = 0;

					}
					
					//navigate backward
					if (key == KeyEvent.VK_A) {
						System.out.println("Quit Back Selected");
						handler.removeObject(tempObject);
					}
					
					//select the QuitConfirmationBox Option
					if (key == KeyEvent.VK_E || key == KeyEvent.VK_D) {
						int selection = handler.getTextBox("QuitConfirmationBox").selected;
						
						if (selection == 0) { //Quit
							System.out.println(handler.getTextBox("QuitConfirmationBox").getQuitConfirmationOptions().get(selection) + " Quit 2 Selected");
							System.exit(1);
						}
						
						if (selection == 1) { //Cancel
							System.out.println(handler.getTextBox("QuitConfirmationBox").getQuitConfirmationOptions().get(selection) + " Cancel Selected");
							handler.removeObject(tempObject);
						}
					}
				}
				
				//If the textBox is a SaveConfirmationBox, navigate through the options
				if (tempObject.textBoxType == "SaveConfirmationBox" && handler.hasTextBox() > 1) {
					if (key == KeyEvent.VK_W) {
						handler.getTextBox("SaveConfirmationBox").selected--;
						if (handler.getTextBox("SaveConfirmationBox").selected < 0)
							handler.getTextBox("SaveConfirmationBox").selected = handler.getTextBox("SaveConfirmationBox").getSaveConfirmationOptions().size() - 1;
					}
					if (key == KeyEvent.VK_S) {
						handler.getTextBox("SaveConfirmationBox").selected++;
						if (handler.getTextBox("SaveConfirmationBox").selected >= handler.getTextBox("SaveConfirmationBox").getSaveConfirmationOptions().size())	
							handler.getTextBox("SaveConfirmationBox").selected = 0;
					}
					
					//navigate backward
					if (key == KeyEvent.VK_A) {
						System.out.println("SaveOptions Back Selected");
						handler.removeObject(tempObject);
					}
					
					//select the SaveConfirmationBox Option
					if (key == KeyEvent.VK_E || key == KeyEvent.VK_D) {
						int selection = handler.getTextBox("SaveConfirmationBox").selected;
						
						if (selection == 0) { //Save
							System.out.println(handler.getTextBox("SaveConfirmationBox").getSaveConfirmationOptions().get(selection) + " Save 2 Selected");
							handler.removeObject(handler.getTextBox("StartMenu"));
							handler.removeObject(tempObject);
							handler.addObject(new TextBox(playerX, playerY, "DialogBox", ObjectId.TextBox));
							break;
						}
						
						if (selection == 1) { //Cancel
							System.out.println(handler.getTextBox("SaveConfirmationBox").getSaveConfirmationOptions().get(selection) + " Cancel Selected");
							handler.removeObject(tempObject);
						}
					}
				}	
			}
		}
		
		if (key == KeyEvent.VK_ESCAPE){
			System.exit(1);
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.gObject.size(); i++){
			GameObject tempObject = handler.gObject.get(i);
			
			if(tempObject.getId() == ObjectId.Player) {
				
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
				if(key == KeyEvent.VK_W) tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) tempObject.setVelY(0);
			}
		}
	}
	
//	private void startMenuKeyCommands(GameObject tempObject, int key) {
//		
//		//If the textBox is a StartMenu, navigate through the options
//		if (tempObject.textBoxType == "StartMenu" && handler.hasTextBox() == 1) {
//			
//			if (key == KeyEvent.VK_W) {
//				handler.getTextBox("StartMenu").selected--;
//				if (handler.getTextBox("StartMenu").selected < 0)
//					handler.getTextBox("StartMenu").selected = handler.getTextBox("StartMenu").getStartMenuChoices().size() - 1;
//			}
//			if (key == KeyEvent.VK_S) {
//				handler.getTextBox("StartMenu").selected++;
//				if (handler.getTextBox("StartMenu").selected >= handler.getTextBox("StartMenu").getStartMenuChoices().size())	
//					handler.getTextBox("StartMenu").selected = 0;
//			}
//			
//			//navigate backward
//			if (key == KeyEvent.VK_A) {
//				System.out.println("StartMenu Back Selected");
//				handler.removeObject(tempObject);
//			}
//			
//			//select the StartMenu Option, this is the shorthand version of the selection logic. (Not fully tested, but pretty sound)
//			if (key == KeyEvent.VK_E || key == KeyEvent.VK_D) {
//				
//				int selection = handler.getTextBox("StartMenu").selected;
//				String boxType = handler.getTextBox("StartMenu").getStartMenuChoices().get(selection);
//				String boxName = null;
//				
//				if (boxType == "Save" || boxType == "Quit") boxName = boxType + "ConfirmationBox";
//				else boxName = boxType + "Box";
//					
//				System.out.println(boxName + " Selected");
//				handler.addObject(new TextBox(playerX, playerY, boxName, handler, ObjectId.TextBox));
//			}
//			
//		}
//	}
}
