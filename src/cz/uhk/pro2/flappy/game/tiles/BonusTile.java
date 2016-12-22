package cz.uhk.pro2.flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappy.game.Tile;

public class BonusTile extends AbstrackTile {

    Tile emptyTile;
    boolean active;

//	Bonus
    public BonusTile(Image img, Tile emptyTile) {
        super.img = img;
        this.emptyTile = emptyTile;
        active = true;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        if(!active)
            emptyTile.draw(g, x, y);
        else
            g.drawImage(img, x, y, null);
    }
    
    public void setActive(boolean active){
        this.active = active;
    }
    
    public boolean isActive(){
        return active;
    }
    
    public Image getImage (){
        return super.img;
    }

}
