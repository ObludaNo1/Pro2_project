package cz.uhk.pro2.flappy.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cz.uhk.pro2.flappy.game.GameBoard;
import cz.uhk.pro2.flappy.game.Tile;
import cz.uhk.pro2.flappy.game.tiles.WallTile;

public class CsvGameBoardLoader implements GameBoardLoader{
	
	private InputStream is;
	
	public CsvGameBoardLoader(InputStream is) {
		this.is = is;
	}
	
	
	@Override
	public GameBoard loadLevel() {
		try (
			BufferedReader br = new BufferedReader(
					new InputStreamReader(is)
				)
			){
			String[] cells = br.readLine().split(";");
			int typeCount = Integer.parseInt(cells[0]);
			for(int i = 0; i<typeCount;i++){
				br.readLine();
				// TODO zatím pøeskoèíme øádky typu tileset 
			}
			cells = br.readLine().split(";");
			int columns = Integer.parseInt(cells[1]);
			int rows = Integer.parseInt(cells[0]);
			
			Tile[][] tiles = new Tile[rows][columns];
			
			System.out.println(rows + " "+columns);
			for (int i = 0; i<rows;i++){
				cells = br.readLine().split(";");
				for (int j = 0; j<columns;j++){
					String cell = (j<cells.length)?cells[j]:"";
					// když buòka v CSV chybí, považujeme ji za prázdnou
//					switch (cell){
//						case "": break;
//						default: tiles[j][i] = new WallTile();
//					}
//					if(cell != ""){
					if(!("".equals(cell))){
						tiles[i][j] = new WallTile();
					}else{
//						tiles[j][i] = 
					}
				}
			}
			GameBoard gb = new GameBoard(tiles);
			return gb;
		} catch (IOException e) {
			throw new RuntimeException("Occured error while reading file", e);
		}
	}
	
}
