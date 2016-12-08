package cz.uhk.pro2.flappy.game;

import java.awt.Graphics;

/**
 * Reprezentuje herni objekt umisteny do matice herni plochy
 * @author 
 *
 */
public interface Tile {
	/**
	 * sirka a vyska dlazdice v px
	 */
	static final int SIZE = 50;
	/**
	 * Kresli herni objekt na platno g
	 * @param x x-ova souradnice v px na obrazovce, kam se dlazdice vykresli
	 * @param y y-ova souradnice v px na obrazovce, kam se dlazdice vykresli
	 * @param g
	 */
	void draw(Graphics g, int x, int y);
	
}
