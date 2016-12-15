package cz.uhk.pro2.flappy.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Bird implements TickAware {
	
//	"fyzika"
	static double speed = 0.0;
	static final double speedInc = 0.375*Tile.SIZE/30;
	static final double kickSpeed = -10.0*Tile.SIZE/30;
	
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
//		g.drawString(viewportX+", "+viewportY, 20, 20);
	}
	
	public boolean collidesWithRectangle(int x, int y, int w, int h){
		Ellipse2D.Float birdsBoundary = new Ellipse2D.Float((float)viewportX-Tile.SIZE/2, (float)viewportY-Tile.SIZE/2, w, h);
		return birdsBoundary.intersects(x,y,w,h);
	}
	
	public double getX(){
		return viewportX;
	}
		
	public double getY(){
		return viewportY;
	}
	
	@Override
	public void tick(long ticksSinceStart) {
//		viewportY = Math.abs(viewportY + velocityY)%400;
		
		speed += speedInc;
		viewportY = viewportY + speed;
		
	}

}
