
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;


public class Applet extends java.applet.Applet implements Runnable {


	private static final long serialVersionUID = 1L;
	
	Fish fish;
	FoodManager foodmgmt;
	Graphics bufferGraphics; 
    Image offscreen; 
    Dimension dim; 
     
	@Override
	public void init() {
		int width = 500;
		int height = 500;
		int num_foods = 20;
		
		setSize(width, height);
		dim = getSize(); 
		offscreen = createImage(dim.width,dim.height); 
        bufferGraphics = offscreen.getGraphics(); 
		super.init();
		fish = new Fish();
		foodmgmt = new FoodManager(width, height,fish,num_foods);
		Thread trd = new Thread(this);
		trd.start();
	}
	
	
	
	@Override
	public void paint(Graphics g) {
		//clear the canvas
		bufferGraphics.clearRect(0,0,dim.width,dim.width); 
         //draw the fish
		bufferGraphics.setColor(Color.green);
		Graphics2D gg = (Graphics2D) bufferGraphics.create();
	    gg.rotate(-(fish.direction)*3.14/180, fish.x+fish.width, fish.y+fish.width);
	    fish.paint(gg);
	    gg.dispose();
	    gg = (Graphics2D) bufferGraphics.create();
	    //draw the circles
		gg.setColor(Color.black);
		foodmgmt.paint(gg);
		//draw the closest food
		gg.setColor(Color.RED);
		if ( fish.closest_food != null )
			fish.closest_food.paint(gg);
		super.paint(gg);
		//flush to screen
		g.drawImage(offscreen,0,0,this); 
	}

	@Override
	public void run() {
		while ( true ) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			nextTurn();
			repaint();
		}
	}

	private void nextTurn() {
		fish.closest_food = foodmgmt.findClosest();
		fish.nextTurn();
		foodmgmt.nextTurn();
	}

}
