package com.bigbreakfast.paulbearer.framework;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		
//		img = image.getSubimage((col * width) - width, (row* height) - height, width, height);
		
		BufferedImage img;
		
		if (width == 32 && height == 32) {
			img = image.getSubimage((col * width) - width, (row* height) - height, width, height);
		}
		else {
			img = image.getSubimage((col * width) - width, (row* height) - height, width, height);
		}
		return img;
	}

}
