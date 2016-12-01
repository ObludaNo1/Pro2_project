package cz.uhk.pro2.flappy.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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
			Map<String, Tile> tileTypes = new HashMap<>();
			for(int i = 0; i<typeCount;i++){
				cells = br.readLine().split(";");
				String tileType = cells[0];
				String type = cells[1];
				int x = Integer.parseInt(cells[2]);
				int y = Integer.parseInt(cells[3]);
				int w = Integer.parseInt(cells[4]);
				int h = Integer.parseInt(cells[5]);
				String url = cells[6];
//				tileTypes[i] = new WallTile(new Image);
				Tile tile = createTile(type, x, y, w, h, url);
				tileTypes.put(tileType, tile);
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
					
//					získáme odpovídající typ dlaždice z hashmapy
					tiles[i][j] = tileTypes.get(cell);
				}
			}
			GameBoard gb = new GameBoard(tiles);
			return gb;
		} catch (IOException e) {
			throw new RuntimeException("Occured error while reading file", e);
		}
	}


	private Tile createTile(String type, int x, int y, int w, int h, String url) {
		return null;
	}
	
}
