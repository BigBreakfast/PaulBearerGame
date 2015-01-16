package com.bigbreakfast.paulbearer.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.bigbreakfast.paulbearer.framework.GameObject;
import com.bigbreakfast.paulbearer.framework.ObjectId;
import com.bigbreakfast.paulbearer.framework.Texture;
import com.bigbreakfast.paulbearer.framework.Facing;
import com.bigbreakfast.paulbearer.window.Animation;
import com.bigbreakfast.paulbearer.window.Game;
import com.bigbreakfast.paulbearer.window.Handler;

public class Player extends GameObject {

	private float width = 32, height = 32;

	private Handler handler;

	Texture tex = Game.getInstance();

	private Animation playerWalkRight;
	private Animation playerWalkLeft;
	private Animation playerWalkUp;
	private Animation playerWalkDown;
	private Animation playerIdleRight;
	private Animation playerIdleLeft;
	private Animation playerIdleDown;

	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;

		playerWalkRight = new Animation(15, tex.player[0], tex.player[1]);
		playerWalkLeft = new Animation(15, tex.player[2], tex.player[3]);
		playerIdleRight = new Animation(15, tex.player[0], tex.player[4]);
		playerIdleLeft = new Animation(15, tex.player[2], tex.player[5]);
		playerWalkDown = new Animation(15, tex.player[7], tex.player[8]);
		playerIdleDown = new Animation(15, tex.player[6], tex.player[9]);

	}

	public void tick(LinkedList<GameObject> gObject) {
		x += velX;
		y += velY;

		if (velX < 0)
			facing = Facing.Left;
			//facing = -1;
		else if (velX > 0)
			//facing = 1;
			facing = Facing.Right;
		else if (velY < 0)
			//facing = 2;
			facing = Facing.Up;
		else if (velY > 0)
			//facing = 3;
			facing = Facing.Down;

		Collision(gObject);

		playerWalkRight.runAnimation();
		playerWalkLeft.runAnimation();
		playerIdleRight.runAnimation();
		playerIdleLeft.runAnimation();
		playerWalkDown.runAnimation();
		playerIdleDown.runAnimation();

	}
	
	public void render(Graphics g) {

		if (velX != 0) {
			if (facing == Facing.Right)
				playerWalkRight.drawAnimation(g, (int) x, (int) y);
			else if (facing == Facing.Left)
				playerWalkLeft.drawAnimation(g, (int) x, (int) y);
		}
		
		if (velY != 0) {
			if (facing == Facing.Up) {}
				//playerWalkUp.drawAnimation(g, (int)x, (int) y);
			else if (facing == Facing.Down)
				playerWalkDown.drawAnimation(g, (int) x, (int) y);
		}

		else {
			if (facing == Facing.Right) {
				g.drawImage(tex.player[0], (int) x, (int) y, null);
				playerIdleRight.drawAnimation(g, (int) x, (int) y);
			}
			else if (facing == Facing.Left) {
				g.drawImage(tex.player[2], (int) x, (int) y, null);
				playerIdleLeft.drawAnimation(g, (int) x, (int) y);
			}
			else if (facing == Facing.Up) {
				g.drawImage(tex.player[4], (int) x, (int) y, null);
			}
			else if (facing == Facing.Down) {
				g.drawImage(tex.player[6], (int) x, (int) y, null);
				playerIdleDown.drawAnimation(g, (int) x, (int) y);
			}
		}
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.draw(getBounds());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsTop());
	}

	private void Collision(LinkedList<GameObject> gObject) {
		for (int i = 0; i < gObject.size(); i++) {
			GameObject tempObject = handler.gObject.get(i);

			if (tempObject.getId() == ObjectId.Block) {

				// Top
				if (getBoundsTop().intersects(tempObject.getBounds())) {

					y = tempObject.getY() + 32;

					velY = 0;
					
					//tempObject.setColliding(true);
					this.setColliding(true);
					
					//System.out.println("Colliding = " + tempObject.isColliding() + " " + tempObject.getId());
					System.out.println("Colliding = " + this.isColliding() + " " + this.id);
				}
				
				//trying to interacting
//				if ((y == (tempObject.getY()) + 32) && (x == (tempObject.getX())) && facing == 2) {
//					System.out.println("Player interactable with block: " +tempObject.getId());
//					this.setInteracting(true);
//				}
				
				else {
					//tempObject.setColliding(false);
					this.setColliding(false);
					//System.out.println("this.isColliding = " +this.isColliding());
					
				}

				// Bottom
				if (getBounds().intersects(tempObject.getBounds())) {

					y = tempObject.getY() - height;

					velY = 0;
					falling = false;
					jumping = false;
					
					//tempObject.setColliding(true);
					this.setColliding(true);
					
					//System.out.println("Colliding = " + tempObject.isColliding() + " " + tempObject.getId());
					System.out.println("Colliding = " + this.isColliding() + " " + this.id);

				} else {
					falling = true;
					//tempObject.setColliding(false);
					this.setColliding(false);
					
				}
				
				// Right
				if (getBoundsRight().intersects(tempObject.getBounds())) {

					x = tempObject.getX() - width;

					velY = 0;
					
					//tempObject.setColliding(true);
					this.setColliding(true);
					
					//System.out.println("Colliding = " + tempObject.isColliding() + " " + tempObject.getId());
					System.out.println("Colliding = " + this.isColliding() + " " + this.id);
				}
				
				else {
					//tempObject.setColliding(false);
					this.setColliding(false);
					
				}
				
				// Left
				if (getBoundsLeft().intersects(tempObject.getBounds())) {

					x = tempObject.getX() + width;

					velY = 0;
					
					//tempObject.setColliding(true);
					this.setColliding(true);
					
					//System.out.println("Colliding = " + tempObject.isColliding() + " " + tempObject.getId());
					System.out.println("Colliding = " + this.isColliding() + " " + this.id);
				}
				
				else {
					//tempObject.setColliding(false);
					this.setColliding(false);
				}
			}
			
			//LootableItem
			if (tempObject.getId() == ObjectId.LootableItem) {
				
				//top
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;

					//tempObject.setColliding(true);
					this.setColliding(true);
				}
				else {
					//tempObject.setColliding(false);
					this.setColliding(false);
					
				}

				// Top
				if (collidingWithLootableItem(tempObject, "Top")) {
					
					//System.out.println("Colliding = " + tempObject.isColliding() + " " + tempObject.getId());
					System.out.println("HITTING TOP");
//					System.out.println("getBoundsTop().getMinY() - 5)" + (getBoundsTop().getMinY() - 5) + "tempObject.getBounds().getMaxY()" + tempObject.getBounds().getMaxY());
//					System.out.println("getBoundsLeft().getMinX() - 5)" + (getBoundsLeft().getMinX() - 5) + "tempObject.getBounds().getMinX()" + tempObject.getBounds().getMinX());
//					System.out.println("getBoundsRight().getMinX() - 5)" + (getBoundsRight().getMinX() - 5) + "tempObject.getBounds().getMaxX()" + tempObject.getBounds().getMaxX());
//					System.out.println(this.id + " Top Colliding = " + this.isColliding() + " " + tempObject.getId());
				}
				
				//left
				if (getBoundsLeft().intersects(tempObject.getBounds())) {

					x = tempObject.getX() + width;

					//velY = 0;
					velX = 0;
					
					//tempObject.setColliding(true);
					this.setColliding(true);
				}
				else {
					//tempObject.setColliding(false);
					this.setColliding(false);
				}
				
				//Left
				if (collidingWithLootableItem(tempObject, "Left")) {
					
					//System.out.println("Colliding = " + tempObject.isColliding() + " " + tempObject.getId());
					System.out.println("HITTING LEFT");
//					System.out.println("getBoundsTop().getMinY() - 5)" + (getBoundsTop().getMinY() - 5) + "tempObject.getBounds().getMaxY()" + tempObject.getBounds().getMaxY());
//					System.out.println("getBoundsLeft().getMinX() - 5)" + (getBoundsLeft().getMinX() - 5) + "tempObject.getBounds().getMinX()" + tempObject.getBounds().getMinX());
//					System.out.println("getBoundsRight().getMinX() - 5)" + (getBoundsRight().getMinX() - 5) + "tempObject.getBounds().getMaxX()" + tempObject.getBounds().getMaxX());
//					System.out.println(this.id + " Top Colliding = " + this.isColliding() + " " + tempObject.getId());
				}

				// Bottom
				if (getBounds().intersects(tempObject.getBounds())) {

					y = tempObject.getY() - height;
					velY = 0;
					
					//tempObject.setColliding(true);
					this.setColliding(true);
					
					//System.out.println("Colliding = " + tempObject.isColliding() + " " + tempObject.getId());
					System.out.println("Colliding = " + this.isColliding() + " " + this.id);

				} else {
					falling = true;
					//tempObject.setColliding(false);
					this.setColliding(false);
					
				}
				
				//Bottom
				if (collidingWithLootableItem(tempObject, "Bottom")) {
					
					//System.out.println("Colliding = " + tempObject.isColliding() + " " + tempObject.getId());
					System.out.println("HITTING BOTTOM");
//					System.out.println("getBoundsTop().getMinY() - 5)" + (getBoundsTop().getMinY() - 5) + "tempObject.getBounds().getMaxY()" + tempObject.getBounds().getMaxY());
//					System.out.println("getBoundsLeft().getMinX() - 5)" + (getBoundsLeft().getMinX() - 5) + "tempObject.getBounds().getMinX()" + tempObject.getBounds().getMinX());
//					System.out.println("getBoundsRight().getMinX() - 5)" + (getBoundsRight().getMinX() - 5) + "tempObject.getBounds().getMaxX()" + tempObject.getBounds().getMaxX());
//					System.out.println(this.id + " Top Colliding = " + this.isColliding() + " " + tempObject.getId());
				}
				
				// Right
				if (getBoundsRight().intersects(tempObject.getBounds())) {

					x = tempObject.getX() - width;
					velX = 0;
					
					//tempObject.setColliding(true);
					this.setColliding(true);
					
					//System.out.println("Colliding = " + tempObject.isColliding() + " " + tempObject.getId());
					System.out.println("Colliding = " + this.isColliding() + " " + this.id);
				}
				
				else {
					//tempObject.setColliding(false);
					this.setColliding(false);
					
				}
				
				//Right
				if (collidingWithLootableItem(tempObject, "Right")) {
					
					//System.out.println("Colliding = " + tempObject.isColliding() + " " + tempObject.getId());
					System.out.println("HITTING RIGHT");
//					System.out.println("getBoundsTop().getMinY() - 5)" + (getBoundsTop().getMinY() - 5) + "tempObject.getBounds().getMaxY()" + tempObject.getBounds().getMaxY());
//					System.out.println("getBoundsLeft().getMinX() - 5)" + (getBoundsLeft().getMinX() - 5) + "tempObject.getBounds().getMinX()" + tempObject.getBounds().getMinX());
//					System.out.println("getBoundsRight().getMinX() - 5)" + (getBoundsRight().getMinX() - 5) + "tempObject.getBounds().getMaxX()" + tempObject.getBounds().getMaxX());
//					System.out.println(this.id + " Top Colliding = " + this.isColliding() + " " + tempObject.getId());
				}
			}
		}
	}	

	public Rectangle getBounds() {
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)),
				(int) (y + (height / 2)), (int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)),
				(int) y, (int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + (width - 5)), (int) y + 5,
				(int) 5, (int) height - 10);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
	}
	
	private boolean collidingWithLootableItem(GameObject tempObject, String side) {
		
		boolean topCollision = false;
		boolean bottomCollision = false;
		boolean leftCollision = false;
		boolean rightCollision = false;
		
		if (side == "Top" && facing == Facing.Up) {
			
			topCollision = Math.abs(getBoundsTop().getMinY() - tempObject.getBounds().getMaxY()) <= 5;
			leftCollision = Math.abs(getBounds().getCenterX() - tempObject.getBounds().getMaxX()) <= 28;
			rightCollision = Math.abs(getBounds().getCenterX() - tempObject.getBounds().getMinX()) <= 28;
			
			return topCollision && rightCollision && leftCollision;
		}
		
		else if (side == "Bottom" && facing == Facing.Down) {
			
			bottomCollision = Math.abs(getBounds().getMaxY() - tempObject.getBounds().getMinY()) <= 5;
			leftCollision = Math.abs(getBoundsTop().getCenterX() - tempObject.getBounds().getMaxX()) <= 28;
			rightCollision = Math.abs(getBoundsTop().getCenterX() - tempObject.getBounds().getMinX()) <= 28;
			
			return bottomCollision && rightCollision && leftCollision;
		}
		
		else if (side == "Left" && facing == Facing.Left) {
			
			rightCollision = Math.abs(getBoundsLeft().getMinX() - tempObject.getBounds().getMaxX()) <= 5;
			topCollision = Math.abs(getBoundsLeft().getCenterY() - tempObject.getBounds().getMinY()) <= 32;
			bottomCollision = Math.abs(getBoundsLeft().getCenterY() - tempObject.getBounds().getMaxY()) <= 32;
			
			return topCollision && rightCollision && bottomCollision;
		}
		
		else if (side == "Right" && facing == Facing.Right) {
			
			leftCollision = Math.abs(getBoundsRight().getMaxX() - tempObject.getBounds().getMinX()) <= 5;
			topCollision = Math.abs(getBoundsRight().getCenterY() - tempObject.getBounds().getMinY()) <= 32;
			bottomCollision = Math.abs(getBoundsRight().getCenterY() - tempObject.getBounds().getMaxY()) <= 32;
			
			return topCollision && leftCollision && bottomCollision;
		}
		
		return false;
	}
}
