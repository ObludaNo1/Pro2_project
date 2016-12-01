package cz.uhk.pro2.flappy.game;

import java.awt.Graphics;

import cz.uhk.pro2.flappy.game.tiles.WallTile;

public class GameBoard implements TickAware {
	Tile[][] tiles;
	int shiftX=30;
	int viewportWidth = 400;  // TODO
	Bird bird;
	
	public GameBoard(){
		this.tiles = new Tile[20][20]; //TODO
		tiles[2][1]=new WallTile();
		bird = new Bird(viewportWidth/4,tiles.length*Tile.SIZE/2);
	}
	
	/**
	 * kresli cely herni svet(zdi,bonusy,ptaka) na platno g.
	 * @param g
	 */
	
	public GameBoard(Tile[][] tiles){
		this.tiles = tiles;
		bird = new Bird(viewportWidth/4,tiles.length*Tile.SIZE/2);
	}
	
	public void draw(Graphics g){
		int minJ = shiftX/Tile.SIZE; //spocitame prvni j index bunky kterou ma smysl kreslit (je videt na obrazovce)
		int maxJ = minJ + viewportWidth/Tile.SIZE + 2; //+2 protoze celociselne delime jak shiftX tak viewportWidth ale chceme 'zaokrouhlit' nahoru
		for(int i = 0; i < tiles.length; i++){
			for(int j = minJ; j <= maxJ ; j++){
				// chceme, aby se svìt toèil dokola 0-19
				// j2 se pohybuje od 0 do poèet sloupcù - 1
				int j2 = j % tiles[0].length;
				Tile t = tiles[i][j2];
				if(t != null){ //je na souradnicich i j dlazdice?
					int screenX=j*Tile.SIZE-shiftX;
					int screenY=i*Tile.SIZE;
					t.draw(g, screenX, screenY);
				}
			}				
		}
		
		bird.draw(g);
		
	}

	@Override
	public void tick(long ticksSinceStart) {
//		s každým tickem ve høe posuneme hru o jeden px
//		tj. poèet tickù a pixelù posunu se rovnají
		
		shiftX = (int)ticksSinceStart;
		bird.tick(ticksSinceStart);
		
//		TODO dame vedet jeste ptakovi ze hodiny ticknuli
		
		
		
	}

	public int getHeightPix() {
		return Tile.SIZE*tiles.length;
	}
	
	public void kickTheBird(){
		bird.kick();
	}
	
	
}
