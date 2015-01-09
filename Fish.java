
import java.awt.Graphics2D;


public class Fish {
	//position of the square
	int x = 0;
	int y = 0;
	//direction is angle from x axis in degrees from 0 to 360 
	int direction = 0;
	Food closest_food;

	//per time unit
	double velocity = 4;
	double max_direction_change = 7;
	int width = 10;
	
	public Fish() {
	}
	
	public Fish(int x, int y, int direction, Food closest_circle,
			double velocity, double max_direction_change, int width) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.closest_food = closest_circle;
		this.velocity = velocity;
		this.max_direction_change = max_direction_change;
		this.width = width;
	}



	public boolean hitTest ( Food circle ) {
		return (circle.radius*circle.radius + width*width) > distance(circle);
	}

	public void nextTurn() {
		changeDirection();
		if ( direction >= 360 ) direction -= 360;
		if ( direction < 0 ) direction += 360;
		double dx = Math.cos(direction*3.14/180)*velocity;
		double dy = Math.sin(direction*3.14/180)*velocity;
		x += dx; y -= dy;
	}

	private void changeDirection() {
		if ( closest_food == null ) {
			double change = Math.random()*max_direction_change*5 - max_direction_change;
			direction += change;
			if ( direction < 0 ) direction += 360;
			if ( direction > 360 ) direction -= 360;
			return;
		}
		double dx = x-closest_food.x;
		double dy = y-closest_food.y;
		
		double desired_direction = Math.atan((1.0*dy)/dx)*180/3.14;
		if ( Math.abs(dx) < 2 ) {
			desired_direction = y > closest_food.y ? 90 : 270;
		}
		else {
			if ( x > closest_food.x ) {
				if ( 0 < desired_direction ) desired_direction = 180 - desired_direction;
				else desired_direction = 270 + desired_direction;
			}
			else {
				if ( desired_direction < 0 ) {
					desired_direction *= -1;
				}
				else {
					desired_direction *= -1;
				}
			}
		}
		if ( desired_direction < 0 ) desired_direction += 360;
		if ( Math.abs(direction - desired_direction) < 2 ) return;
		if ( Math.abs(direction - desired_direction) < max_direction_change ) {
			direction = (int) desired_direction;
			return;
		}
		if ( (desired_direction < 180 && direction > desired_direction && direction < desired_direction+180) || (desired_direction > 180 && (direction > desired_direction || direction < desired_direction-180))  ) {
			direction -= max_direction_change;
		}
		else {
			direction += max_direction_change;
		}
	}

	public double distance(Food circle) {
		return ((x+width-circle.x)*(x+width-circle.x)+(y+width-circle.y)*(y+width-circle.y));
	}

	public void paint(Graphics2D gg) {
		gg.fillArc(x, y, width*4, width*2, 130, 100);
	}
}
