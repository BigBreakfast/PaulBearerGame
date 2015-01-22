package com.bigbreakfast.paulbearer.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.bigbreakfast.paulbearer.framework.GameObject;
import com.bigbreakfast.paulbearer.framework.ObjectId;
import com.bigbreakfast.paulbearer.framework.Texture;
import com.bigbreakfast.paulbearer.window.Game;

public class Block extends GameObject {
	
	Texture tex = Game.getInstance();
	
	private int type;
	//private Animation blockAnimate;

	public Block(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
		
		//blockAnimate = new Animation(20, tex.block[8], tex.block[9], tex.block[10], tex.block[11]);
	}
	
	public void render(Graphics g) {
		
		if (type == 0) //dirt block
			g.drawImage(tex.block[0], (int) x, (int) y, null);
		if (type == 1)
			g.drawImage(tex.block[1], (int) x, (int) y, null);
		if (type == 2)
			g.drawImage(tex.block[2], (int) x, (int) y, null);
		if (type == 3)
			g.drawImage(tex.block[3], (int) x, (int) y, null);
		if (type == 4)
			g.drawImage(tex.block[4], (int) x, (int) y, null);
		if (type == 5)
			g.drawImage(tex.block[5], (int) x, (int) y, null);
		if (type == 6)
			g.drawImage(tex.block[6], (int) x, (int) y, null);
		if (type == 7)
			g.drawImage(tex.block[7], (int) x, (int) y, null);
//		if (type == 8)
//			blockAnimate.drawAnimation(g, (int) x, (int) y);
			//g.drawImage(tex.block[8], (int) x, (int) y, null);
	}

	
	public void tick(LinkedList<GameObject> gObject) {
		// TODO Auto-generated method stub
		//blockAnimate.runAnimation();

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	@Override
	public BufferedImage getObjectImage() {
		// TODO Auto-generated method stub
		return null;
	}


}
