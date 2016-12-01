package cz.uhk.pro2.flappy.service;

import cz.uhk.pro2.flappy.game.GameBoard;

/**
 * Spoledne rozhrani pro tridy umoznujici nacitat level
 * 
 * @author cahaon1
 *
 */

public interface GameBoardLoader {
	/**
	 * na�te level (hern� plochu)
	 * @return
	 */
	GameBoard loadLevel();
	
}
