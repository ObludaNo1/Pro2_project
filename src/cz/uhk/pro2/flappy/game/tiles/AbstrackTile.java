package cz.uhk.pro2.flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappy.game.Tile;

public abstract class AbstrackTile implements Tile{
	
	protected Image img; 
	
	public AbstrackTile(Image img){
		this.img = img;
	}
	
	public AbstrackTile(){}

	@Override
	public void draw(Graphics g, int x, int y) {
		//g.drawRect(x, y, SIZE, SIZE);
		g.drawImage(img, x, y, null);
	}

}
