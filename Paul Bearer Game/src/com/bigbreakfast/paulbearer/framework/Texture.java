package com.bigbreakfast.paulbearer.framework;

import java.awt.image.BufferedImage;

import com.bigbreakfast.paulbearer.window.BufferedImageLoader;

public class Texture {
	
	SpriteSheet bs, ps, cs, fs, tb;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage char_sheet = null;
	
	private BufferedImage floor_sheet = null;
	private BufferedImage textbox_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[12];
	public BufferedImage[] player = new BufferedImage[10];
	public BufferedImage[] character = new BufferedImage[1];
	
	public BufferedImage[] floor = new BufferedImage[6];
	public BufferedImage[] textbox = new BufferedImage[1];
	
	public Texture() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/PB_Sprite_Sheet.png"); //player_sheet = loader.loadImage("/player_sheet edited.png");
			char_sheet = loader.loadImage("/chars.png");
			floor_sheet = loader.loadImage("/PB_floor_sheet.png");
			textbox_sheet = loader.loadImage("/TextBox.png");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		cs = new SpriteSheet(char_sheet);
		fs = new SpriteSheet(floor_sheet);
		tb = new SpriteSheet(textbox_sheet);
		
		getTextures();
	}
	
	private void getTextures() {
		block[0] = bs.grabImage(1, 1, 32, 32); // dirt block
		block[1] = bs.grabImage(2, 1, 32, 32); // grass block
		block[2] = bs.grabImage(3, 1, 32, 32); // lava block
		block[3] = bs.grabImage(4, 1, 32, 32);
		block[4] = bs.grabImage(3, 2, 32, 32);
		block[5] = bs.grabImage(4, 2, 32, 32);
		block[6] = bs.grabImage(1, 2, 32, 32); //ladder		
		block[7] = bs.grabImage(2, 2, 32, 32); //key
		
//		player[0] = ps.grabImage(1, 1, 32, 32); //player
//		player[1] = ps.grabImage(2, 1, 32, 32);
//		
//		player[2] = ps.grabImage(1, 2, 32, 32);
//		player[3] = ps.grabImage(2, 2, 32, 32);
//		
//		player[4] = ps.grabImage(1, 3, 32, 32); //mutant right
//		player[5] = ps.grabImage(5, 3, 32, 32); //mutant left
//		player[6] = ps.grabImage(2, 3, 32, 32); //mutant walk right
//		player[7] = ps.grabImage(4, 3, 32, 32); //mutant walk left
		
		player[0] = ps.grabImage(1, 1, 32, 32); //paul right
		player[1] = ps.grabImage(2, 1, 32, 32); //paul walk right
		player[2] = ps.grabImage(1, 2, 32, 32); //paul left
		player[3] = ps.grabImage(2, 2, 32, 32); //paul walk left
		player[4] = ps.grabImage(4, 1, 32, 32); //paul right idle
		player[5] = ps.grabImage(4, 2, 32, 32); //paul left idle
		player[6] = ps.grabImage(1, 3, 32, 32); //paul down
		player[7] = ps.grabImage(2, 3, 32, 32); //paul walk down 1
		player[8] = ps.grabImage(3, 3, 32, 32); //paul walk down 2
		player[9] = ps.grabImage(5, 3, 32, 32); //paul down idle
		
		block[8] = ps.grabImage(15, 1, 32, 32); //scientist animation
		block[9] = ps.grabImage(15, 2, 32, 32); //scientist animation
		block[10] = ps.grabImage(15, 3, 32, 32); //scientist animation
		block[11] = ps.grabImage(15, 4, 32, 32); //scientist animation
		
		character[0] = cs.grabImage(7, 1, 33, 64);
		
		floor[0] = fs.grabImage(1, 1, 32, 32); // street vertical
		floor[1] = fs.grabImage(2, 1, 32, 32); // street horizontal
		floor[2] = fs.grabImage(3, 1, 32, 32); // hotel carpet down
		floor[3] = fs.grabImage(4, 1, 32, 32); // hotel carpet up
		floor[4] = fs.grabImage(5, 1, 32, 32); // hotel carpet down
		floor[5] = fs.grabImage(6, 1, 32, 32); // hotel carpet up
		
		textbox[0] = tb.grabImage(1, 1, 32, 32); //textbox
	}

}
