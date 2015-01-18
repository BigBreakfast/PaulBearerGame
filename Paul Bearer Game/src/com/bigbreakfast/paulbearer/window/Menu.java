package com.bigbreakfast.paulbearer.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Menu {
	
	private Rectangle startButton = new Rectangle(355, 318, 90, 32);
	private Rectangle quitButton = new Rectangle(355, 350, 90, 32);
	
	private String fontFileLoc = "/CopperplateLight.ttf";
	private InputStream is = Menu.class.getResourceAsStream(fontFileLoc);
	private Font font;
	
	private BufferedImage menuImg;
	
	private int selected;
	private List<String> menuOptions = Arrays.asList("Start", "Load", "Quit");
	
	public Menu(BufferedImage menuImg) {
		
		this.menuImg = menuImg;
		this.selected = 0;
		
		try {
			this.font = Font.createFont(Font.TRUETYPE_FONT, is);
			this.font = font.deriveFont(29.0f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
		
		g.drawImage(menuImg, 0, 0, null);
		
		//Graphics2D g2d = (Graphics2D) g;
		
		g.setFont(this.font);
		
		for (int i = 0; i < menuOptions.size(); i++) {
			
			if (i == selected) g.setColor(Color.YELLOW);
			else {
				g.setColor(Color.RED);
			}
			
			String currentOption = menuOptions.get(i);
			
			if (currentOption == "Start") g.drawString(currentOption, 362, 320);
			if (currentOption == "Load") g.drawString(currentOption, 369, 352);
			if (currentOption == "Quit") g.drawString(currentOption, 371, 384);
			
		}
		
		
//		g2d.setColor(Color.RED);
//		g2d.draw(startButton);
//		g2d.draw(quitButton);
	}

}
