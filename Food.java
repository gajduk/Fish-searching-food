
import java.awt.Graphics2D;


public class Food {
	int x;
	int y;
	int radius = 5;
	
	public Food() {
	}
	
	public Food(int x, int y, int radius) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	public static Food newRandomFood(int width, int height, Fish square, int radius ) {
		while ( true ) {
			int x = ((int) (Math.random()*(width-80)));
			int y = ((int) (Math.random()*(height-80)));
			Food res = new Food(x+40,y+40,radius); 
			if ( ! square.hitTest(res) ) return res;
		}
	}

	public void paint(Graphics2D gg) {	
		gg.fillOval(x-radius, y-radius, radius*2, radius*2);
	}

}
