package cz.uhk.pro2.flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappy.game.Tile;

public class WallTile implements Tile{
	
	private Image img; 
	
	public WallTile(Image img){
		this.img = img;
	}
	
	public WallTile(){}

	@Override
	public void draw(Graphics g, int x, int y) {
		//g.drawRect(x, y, SIZE, SIZE);
		g.drawImage(img, x, y, null);
	}
	
}
