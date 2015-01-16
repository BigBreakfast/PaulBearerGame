package com.bigbreakfast.paulbearer.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.bigbreakfast.paulbearer.framework.KeyInput;
import com.bigbreakfast.paulbearer.framework.ObjectId;
import com.bigbreakfast.paulbearer.framework.Texture;
import com.bigbreakfast.paulbearer.objects.Block;
import com.bigbreakfast.paulbearer.objects.Floor;
import com.bigbreakfast.paulbearer.objects.Player;
import com.bigbreakfast.paulbearer.objects.TextBox;
import com.bigbreakfast.paulbearer.objects.LootableItem;
import com.bigbreakfast.paulbearer.sound.Sound;

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7789547412198681130L;

	private boolean running = false;
	private Thread thread;
	
	public static int WIDTH, HEIGHT;
	
	private BufferedImage level = null, backgroundImg = null, textBox = null;

	// Object
	Handler handler;
	Camera cam;
	static Texture tex;
	Sound sound;

	Random rand = new Random();

	private void init() {
		requestFocus();
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		tex = new Texture();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/Stage 1.png"); //loading the level
		backgroundImg = loader.loadImage("/Cinematic 1 - Paul.jpg"); //loading background
		
		//Here we need to instantiate a new textbox with visibility false
		//Then, upon pressing space, textbox becomes visible, aka renders
		//When space is pressed again, the textbox goes away, unless it spawns a smaller text box
		textBox = loader.loadImage("/TextBox.png");
		
		handler = new Handler();
		
		cam = new Camera(0, 0);
		
		LoadImageLevel(level);
		
		try {
			sound.playSound();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		this.addKeyListener(new KeyInput(handler));
	}

	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();

	}

	public void run() {

		System.out.println("Thread has begun");

		init();
		this.requestFocus();

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// fps = frames;
				// ticks = updates;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void tick() {
		handler.tick();
		
		//working camera
		for (int i = 0; i < handler.gObject.size(); i++) {
			if(handler.gObject.get(i).getId() == ObjectId.Player)
				cam.tick(handler.gObject.get(i));
				
		}
	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(5);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		Graphics2D g2d = (Graphics2D) g;

		// //////////////////////////////////
		
		// DRAW HERE
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.drawImage(backgroundImg, (int) (0 * cam.getX()), (int) (0 * cam.getY()), this);		
		
		g2d.translate(cam.getX(), cam.getY()); //begin of cam
			
		
			handler.render(g);
		
		g2d.translate(-cam.getX(), -cam.getY()); //end of cam
		
		// //////////////////////////////////

		g.dispose();
		bs.show();

	}
	
	private void LoadImageLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println("width, height: " + w + h);
		
		for(int xx = 0; xx < w; xx++) {
			
			for (int yy = 0; yy < h; yy++) {
				
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				
				if (red == 255 && green == 0 && blue == 0) { //red pixel //street vertical
					handler.addObject(new Floor(xx*32, yy*32, 0, ObjectId.Floor));
				}
				
				if (red == 254 && green == 0 && blue == 0) { //red pixel //street horizontal
					handler.addObject(new Floor(xx*32, yy*32, 1, ObjectId.Floor));
				}
				
				if (red == 253 && green == 0 && blue == 0) { //red pixel //hotel carpet down
					handler.addObject(new Floor(xx*32, yy*32, 2, ObjectId.Floor));
				}
				
				if (red == 252 && green == 0 && blue == 0) { //red pixel //hotel carpet up
					handler.addObject(new Floor(xx*32, yy*32, 3, ObjectId.Floor));
				}
				
				if (red == 251 && green == 0 && blue == 0) { //red pixel //hotel carpet right
					handler.addObject(new Floor(xx*32, yy*32, 4, ObjectId.Floor));
				}
				
				if (red == 250 && green == 0 && blue == 0) { //red pixel //hotel carpet left
					handler.addObject(new Floor(xx*32, yy*32, 5, ObjectId.Floor));
				}				
				
				if (red == 255 && green == 255 && blue == 255) { //white pixel //block
					handler.addObject(new Block(xx*32, yy*32, 1, ObjectId.Block));
				}		
				
				if (red == 128 && green == 128 && blue == 128) { //grey pixel //lava
					if(rand.nextInt()%2 == 0)
						handler.addObject(new Block(xx*32, yy*32, 2, ObjectId.Block));
					else
						handler.addObject(new Block(xx*32, yy*32, 3, ObjectId.Block));
				}
				
				if (red == 99 && green == 99 && blue == 99) { //light grey pixel //lava2
					if(rand.nextInt()%2 == 0)
						handler.addObject(new Block(xx*32, yy*32, 4, ObjectId.Block));
					else
						handler.addObject(new Block(xx*32, yy*32, 5, ObjectId.Block));
				}
				
				if (red == 50 && green == 50 && blue == 50) { //dark grey pixel //ladder
					handler.addObject(new Block(xx*32, yy*32, 6, ObjectId.Block));
				}
				
				if (red == 228 && green == 255 && blue == 0) { //yellow pixel //key
					handler.addObject(new Block(xx*32, yy*32, 7, ObjectId.Block));
				}
				
				if (red == 0 && green == 102 && blue == 50) { //green pixel //scientist
					handler.addObject(new Block(xx*32, yy*32, 8, ObjectId.Block));
				}
				
				if (red == 255 && green == 100 && blue == 255) { //pink pixel //item (key)
					handler.addObject(new LootableItem(xx*32, yy*32, "Key", 7, ObjectId.LootableItem));
				}
				
			}
		}
		//Loads character last in order to have render priority (rendered last)
		LoadCharacter(image);
		
		//LoadTextBox(0);
	}
	
	//Loads character separately in order to have render priority (rendered last)
	private void LoadCharacter(BufferedImage image) {
		
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println("width, height: " + w + h);
		
		for(int xx = 0; xx < w; xx++) {
			
			for (int yy = 0; yy < h; yy++) {
		
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;				
				
				if (red == 0 && green == 0 && blue == 255) { //blue pixel //player
					handler.addObject(new Player(xx*32, yy*32, handler, ObjectId.Player));
					System.out.println(xx*32 + " " + yy*32 + " ObjectId.Player");
				}
			}
		}
	}
	
	//set up to work at load time. do i need?
	public void LoadTextBox(int type) {
		
		for (int i = 0; i < handler.gObject.size(); i++) {
			
			if (handler.gObject.get(i).getId() == ObjectId.Player) {
				
				int playerX = (int) handler.gObject.get(i).getX();
				int playerY = (int) handler.gObject.get(i).getY();
				
				//works before cam adjustment
				//with cam adjustment
				//workshandler.addObject(new TextBox(width + 16, height - 16, type, ObjectId.TextBox));
				
				
				//handler.addObject(new TextBox(playerX, playerY, "StartMenu", handler, ObjectId.TextBox));
				//handler.addObject(new TextBox(Game.WIDTH, Game.HEIGHT/2, "StartMenu", handler, ObjectId.TextBox));
				System.out.println(playerX + " " + playerY + " ObjectId.TextBox");
				
			}
		}
		
		//handler.addObject(new TextBox(cam.getX(), cam.getY(), type, ObjectId.TextBox));
	}
	
	public static Texture getInstance() {
		return tex;
	}

	public static void main(String args[]) {
		new Window(800, 600, "Platform Game Proto", new Game()); //OG Platformer
		//new Window(640, 480, "Paul Bearer: Father of Destruction", new Game());
	}

}
