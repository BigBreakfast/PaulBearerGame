package com.bigbreakfast.paulbearer.window;

import java.util.LinkedList;

import com.bigbreakfast.paulbearer.framework.GameObject;
import com.bigbreakfast.paulbearer.framework.ObjectId;

public class Camera {
	
private float x, y;
private int count = 0;
	
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	//working camera
	public void tick(GameObject gObject){
		count++;
		//works
//		x = -gObject.getX() + Game.WIDTH/2;
//		y = -gObject.getY() + Game.HEIGHT/2;
		
		x = -gObject.getX() - 16 + Game.WIDTH/2;
		y = -gObject.getY() + Game.HEIGHT/2;
		
		//System.out.println("cam: " + x + " " + y);
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	

}
