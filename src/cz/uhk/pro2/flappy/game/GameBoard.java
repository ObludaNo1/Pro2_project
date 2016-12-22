package cz.uhk.pro2.flappy.game;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappy.game.tiles.BonusTile;
import cz.uhk.pro2.flappy.game.tiles.WallTile;

public class GameBoard implements TickAware {

    Tile[][] tiles;
    int shiftX = 30;
    int viewportWidth = 400;  // TODO
    Bird bird;
    boolean gameOver = false;
    Tile tile;

    /**
     * kresli cely herni svet(zdi,bonusy,ptaka) na platno g.
     *
     * @param g
     */
    public GameBoard(Tile[][] tiles, Image image) {
        this.tiles = tiles;
        bird = new Bird(viewportWidth / 4, tiles.length * Tile.SIZE / 2, image);
    }

    public void drawAndTestCollisions(Graphics g) {
        int minJ = shiftX / Tile.SIZE; //spocitame prvni j index bunky kterou ma smysl kreslit (je videt na obrazovce)
        int maxJ = minJ + viewportWidth / Tile.SIZE + 2; //+2 protoze celociselne delime jak shiftX tak viewportWidth ale chceme 'zaokrouhlit' nahoru
        int lastIndex = (((shiftX/Tile.SIZE) % tiles[0].length) == 0)?(tiles[0].length-1):((shiftX/Tile.SIZE) % tiles[0].length)-1;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = minJ; j <= maxJ; j++) {
				// chceme, aby se sv�t to�il dokola 0-19
                // j2 se pohybuje od 0 do po�et sloupc� - 1
                int j2 = j % tiles[0].length;
                Tile t = tiles[i][j2];
                if (t != null) { //je na souradnicich i j dlazdice?
                    int screenX = j * Tile.SIZE - shiftX;
                    int screenY = i * Tile.SIZE;

                    // nakresl�me dla�dici
                    t.draw(g, screenX, screenY);
					//otestujeme mo�nou kolizi dla�dice s pt�kem
                    
                    if(screenX >= Tile.SIZE*(tiles[0].length-3)){
                        if(t.getClass() == BonusTile.class){
                            System.out.println("Bonus reactivate");
                            ((BonusTile)t).setActive(true);
                        }
                    }

                    if (t.getClass() == WallTile.class)
                        if (bird.getY() < 0 || bird.getY() > tiles.length * Tile.SIZE || bird.collidesWithRectangle(screenX, screenY, Tile.SIZE, Tile.SIZE))
                            gameOver = false;
                    
                    if (t.getClass() == BonusTile.class)
                        if (bird.collidesWithRectangle(screenX, screenY, Tile.SIZE, Tile.SIZE))
                            if( ((BonusTile)t).isActive() )
                                ((BonusTile)t).setActive(false);
                }
            }
        }
        for (int i = 0; i < tiles.length; i++) {
            if(tiles[i][lastIndex].getClass() == BonusTile.class){
                ((BonusTile)tiles[i][lastIndex]).setActive(true);
            }
        }            

        bird.draw(g);
    }

    @Override
    public void tick(long ticksSinceStart) {
//		s ka�d�m tickem ve h�e posuneme hru o jeden px
//		tj. po�et tick� a pixel� posunu se rovnaj�

        if (!gameOver) {
            shiftX = (int) ticksSinceStart;
            bird.tick(ticksSinceStart);

        }

    }

    public int getHeightPix() {
        return Tile.SIZE * tiles.length;
    }

    public void kickTheBird() {
        bird.kick();
    }

    public void setTile(int x, int y, Tile tile) {
        if (x > 0 && y > 0)
            if (x <= tiles.length && y <= tiles[0].length)
                tiles[x][y] = tile;
    }

    public void setTile(Tile t) {
        this.tile = t;
    }

}
