package cz.uhk.pro2.flappy.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import cz.uhk.pro2.flappy.game.GameBoard;
import cz.uhk.pro2.flappy.game.Tile;
import cz.uhk.pro2.flappy.game.tiles.EmptyTile;
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
					
//					z�sk�me odpov�daj�c� typ dla�dice z hashmapy
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
		
		try {
			// st�hnout img z URL a ulo�it do prom�nn�
			BufferedImage originalImage = ImageIO.read(new URL(url));
			
			//vy�ez�n� odpov�daj�c�ho spritu z velk�ho obr�zku
			BufferedImage croppedImage = originalImage.getSubimage(x, y, w, h);
			
			//zmen��me/zv�t��me, aby sed�l na velikost dla�dice
			BufferedImage resizedImage = new BufferedImage(Tile.SIZE, Tile.SIZE, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(croppedImage, 0, 0, Tile.SIZE, Tile.SIZE, null);
			
			//vytvo��me odpov�daj�c� typ dla�dice
//			Tile tile;
			switch(type){
				case "wall": return new WallTile(resizedImage);
				default: return new EmptyTile(resizedImage);
			}
			
			
		} catch (MalformedURLException e) {
			throw new RuntimeException("Wrong url of image "+type+": "+url, e);
		} catch (IOException e) {
			throw new RuntimeException("Error while reading file "+type+": "+url, e);
		}
		
//		return null;
	}
	
}
