package cz.uhk.pro2.flappy.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Bird implements TickAware {
	
//	"fyzika"
	static double speed = 0.0;
	static final double speedInc = 0.25*Tile.SIZE/20;
	static final double kickSpeed = -4.0*Tile.SIZE/20;
	
//	souøadnice
	double viewportX;
	double viewportY;
	
//	rychlost padani
	
//	kolik tickù zbývá, než zaène padat
	int ticksToFall = 0;
	
	
	Image image;
	
	public Bird(int initX, int initY, Image image){
		this.viewportX = initX;
		this.viewportY = initY;
		this.image = image;
	}
	
	
	public void kick(){
		if(speed > 0)speed = 0;
		speed += kickSpeed;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
//		g.fillOval((int)viewportX-Tile.SIZE/2, (int)viewportY - Tile.SIZE/2, Tile.SIZE, Tile.SIZE);
		g.drawImage(image, (int)viewportX-Tile.SIZE/2, (int)viewportY - Tile.SIZE/2, null);
		g.setColor(Color.BLACK);
//		g.drawString(viewportX+", "+viewportY, (int)viewportX, (int)viewportY);
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
