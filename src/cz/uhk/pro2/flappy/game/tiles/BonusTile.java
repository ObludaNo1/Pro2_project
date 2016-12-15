package cz.uhk.pro2.flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappy.game.Tile;

public class BonusTile extends AbstrackTile{
	
	Tile emptyTile;
	
//	Bonus
	
	public BonusTile(Image img, Tile emptyTile){
		super.img = img;
		this.emptyTile = emptyTile;
	}
	
	@Override
	public void draw(Graphics g, int x, int y){
		g.drawImage(img, x, y, null);
	}
	
}
