package multibounce;

import java.awt.Color;

public class Ball {

	int xx;
	int yy;
	int diameter;
	int speedX;
	int speedY;
	Color ballColour;
	
	Ball(int xx, int yy, int diameter, int speedX, int speedY, Color ballColour) {
		this.xx = xx;
		this.yy = yy;
		this.diameter = diameter;
		this.speedX = speedX;
		this.speedY = speedY;
		this.ballColour = ballColour;
	}
}
