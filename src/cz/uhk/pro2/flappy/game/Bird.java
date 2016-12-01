package cz.uhk.pro2.flappy.game;

import java.awt.Color;
import java.awt.Graphics;

public class Bird implements TickAware {
	
//	"fyzika"
	static double speed = 0.0;
	static final double speedInc = 0.5;
	static final double kickSpeed = -12.0;
	
//	souøadnice
	double viewportX;
	double viewportY;
	
//	rychlost padani
	
//	kolik tickù zbývá, než zaène padat
	int ticksToFall = 0;
	
	
	public Bird(int initX, int initY){
		this.viewportX = initX;
		this.viewportY = initY;
	}
	
	
	public void kick(){
		speed += kickSpeed;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		g.fillOval((int)viewportX-Tile.SIZE/2, (int)viewportY - Tile.SIZE/2, Tile.SIZE, Tile.SIZE);
		g.setColor(Color.BLACK);
		g.drawString(viewportX+", "+viewportY, (int)viewportX, (int)viewportY);
	}
	
	
	@Override
	public void tick(long ticksSinceStart) {
//		viewportY = Math.abs(viewportY + velocityY)%400;
		
		speed += speedInc;
		viewportY = viewportY + speed;
		
	}

}
