package com.bigbreakfast.paulbearer.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.bigbreakfast.paulbearer.framework.GameObject;
import com.bigbreakfast.paulbearer.framework.ObjectId;
import com.bigbreakfast.paulbearer.framework.Texture;
import com.bigbreakfast.paulbearer.window.Game;

public class Floor extends GameObject {
	
	Texture tex = Game.getInstance();
	
	private int type;
	//private Animation floorAnimate;

	public Floor(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
		
	}
	
	public void render(Graphics g) {
		
		if (type == 0) //lava floor
			g.drawImage(tex.floor[0], (int) x, (int) y, null);
		
		if (type == 1) //lava floor
			g.drawImage(tex.floor[1], (int) x, (int) y, null);
		
		if (type == 2) //lava floor
			g.drawImage(tex.floor[2], (int) x, (int) y, null);
		
		if (type == 3) //lava floor
			g.drawImage(tex.floor[3], (int) x, (int) y, null);
		
		if (type == 4) //lava floor
			g.drawImage(tex.floor[4], (int) x, (int) y, null);
		
		if (type == 5) //lava floor
			g.drawImage(tex.floor[5], (int) x, (int) y, null);
		
	}

	
	public void tick(LinkedList<GameObject> gObject) {
		// TODO Auto-generated method stub
		//floorAnimate.runAnimation();

	}

	public Rectangle getBounds() {
		//return new Rectangle((int) x, (int) y, 32, 32);
		return new Rectangle((int) x, (int) y, 0, 0);
	}


}
