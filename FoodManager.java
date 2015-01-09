
import java.awt.Graphics2D;


public class FoodManager {
	Food[] foods;
	int num_fish = 10;
	int width;
	int height;
	Fish fish;
	
	public FoodManager( int width , int height , Fish square , int num_circles) {
		this.width = width; this.height = height;
		this.num_fish = num_circles;
		this.fish = square;
		foods = new Food[num_circles];
		for ( int i = 0 ; i < num_circles ; ++i ) {
			foods[i] = Food.newRandomFood(width,height,square,5);
		}
	}

	public void nextTurn( ) {
		for ( int i = 0 ; i < num_fish ; ++i ) {
			if ( foods[i] == null ) continue;
			if ( fish.hitTest(foods[i]) ) {
				foods[i] = null;
			}
		}
	}

	public Food findClosest( ) {
		double min_dist = Double.MAX_VALUE;
		int min_indx = 0;
		for ( int i = 0 ; i < num_fish ; ++i ) {
			if ( foods[i] == null ) continue;
			double dist = fish.distance(foods[i]);
			if ( dist < min_dist ) {
				min_dist = dist;
				min_indx = i;
			}
		}
		return foods[min_indx];
	}

	public void paint(Graphics2D gg) {
		for ( int i = 0 ; i < foods.length ; ++i ) {
			if ( foods[i] == null ) continue;
			Food c = foods[i];
			c.paint(gg);
		}
	}
}
