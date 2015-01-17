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
	private LootableItem lootableItem;

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
		else if (velX > 0)
			facing = Facing.Right;
		else if (velY < 0)
			facing = Facing.Up;
		else if (velY > 0)
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
			if (facing == Facing.Up)
				g.drawImage(tex.player[4], (int) x, (int) y, null);
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
				if (getBoundsTop().intersects(tempObject.getBounds())) { y = tempObject.getY() + 32; velY = 0; }

				// Bottom
				if (getBounds().intersects(tempObject.getBounds())) { y = tempObject.getY() - height; velY = 0; }
				
				// Right
				if (getBoundsRight().intersects(tempObject.getBounds())) { x = tempObject.getX() - width; velY = 0; }
				
				// Left
				if (getBoundsLeft().intersects(tempObject.getBounds())) { x = tempObject.getX() + width; velY = 0; }
				
			}
			
			//LootableItem
			if (tempObject.getId() == ObjectId.LootableItem) {
				
				//top
				if (getBoundsTop().intersects(tempObject.getBounds())) { y = tempObject.getY() + 32; velY = 0; }
				
				//left
				if (getBoundsLeft().intersects(tempObject.getBounds())) { x = tempObject.getX() + width; velX = 0; }
				
				// Bottom
				if (getBounds().intersects(tempObject.getBounds())) { y = tempObject.getY() - height; velY = 0; }
				
				// Right
				if (getBoundsRight().intersects(tempObject.getBounds())) { x = tempObject.getX() - width; velX = 0; }
				
				else { //collidingWithLootableItem

					// Top
					if (collidingWithLootableItem(tempObject, "Top")) {
						
						this.lootableItem = (LootableItem) tempObject;
						this.setColliding(true);

						//System.out.println("HITTING TOP");						
						//System.out.println("this.lootableItem = " + this.lootableItem);
					}
					
					//Left
					else if (collidingWithLootableItem(tempObject, "Left")) {
						
						this.lootableItem = (LootableItem) tempObject;
						this.setColliding(true);

						//System.out.println("HITTING LEFT");						
						//System.out.println("this.lootableItem = " + this.lootableItem);
					}
					
					//Bottom
					else if (collidingWithLootableItem(tempObject, "Bottom")) {
						
						this.lootableItem = (LootableItem) tempObject;
						this.setColliding(true);

						//System.out.println("HITTING BOTTOM");						
						//System.out.println("this.lootableItem = " + this.lootableItem);
					}					
					
					//Right
					else if (collidingWithLootableItem(tempObject, "Right")) {
						
						this.lootableItem = (LootableItem) tempObject;
						this.setColliding(true);

						//System.out.println("HITTING RIGHT");
						//System.out.println("this.lootableItem = " + this.lootableItem);
					}
					
					//Set player's lootableItem = null, he is no longer colliding with it
					else {
	
						this.lootableItem = null;
						this.setColliding(false);
						
						//System.out.println("this.lootableItem = " + this.lootableItem);
					}
				}
			}
		}
	}
	
	public LootableItem getLootableItem() {
		return this.lootableItem;
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
