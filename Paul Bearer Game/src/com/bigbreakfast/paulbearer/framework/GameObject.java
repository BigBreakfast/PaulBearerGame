package com.bigbreakfast.paulbearer.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public abstract class GameObject {

	protected float x, y;
	protected float velX = 0, velY = 0;
	protected ObjectId id;
	//protected int facing = 1;
	protected Facing facing;
	
	protected BufferedImage objectImage;

	protected boolean falling = true;
	protected boolean jumping = false;
	protected boolean colliding = false;
	
	protected boolean isVisible = false;
	protected boolean isInteracting = false;
	
	protected String textBoxType = null;

	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick(LinkedList<GameObject> gObject);

	public abstract void render(Graphics g);
	
	public abstract Rectangle getBounds();
	
	public ObjectId getId() {
		return id;
	}
	
	public Facing getFacing() {
		return facing;
	}
	
	public void setObjectImage(BufferedImage objectImage) {
		this.objectImage = objectImage;
	}
	
	public abstract BufferedImage getObjectImage();
	
//	public int getFacing() {
//		return facing;
//	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	public void setColliding(boolean colliding) {
		this.colliding = colliding;
	}
	
	public boolean isColliding() {
		return colliding;
	}
	
	public void setVisible() {
		this.isVisible = !this.isVisible;
	}
	
	public void setTextBoxType(String boxType) {
		this.textBoxType = boxType;
	}
	
	public String getTextBoxType() {
		return textBoxType;
	}
	
	public void setInteracting(boolean interacting) {
		this.isInteracting = interacting;
	}

	public boolean isInteracting() {
		return isInteracting;
	}
}
