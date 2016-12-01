package cz.uhk.pro2.flappy.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import cz.uhk.pro2.flappy.game.GameBoard;
import cz.uhk.pro2.flappy.service.CsvGameBoardLoader;
import cz.uhk.pro2.flappy.service.GameBoardLoader;

public class MainWindow extends JFrame{
	GameBoard gameBoard;
	BoardPanel pnl = new BoardPanel();
	long x = 0;
	
	class BoardPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g); //vykresli prazdny panel
			gameBoard.draw(g); //vykresli hraci plochu
		}
	}
	
	public MainWindow(){
		try(InputStream is = new FileInputStream("level.csv")){
			GameBoardLoader loader = new CsvGameBoardLoader(is);
			gameBoard = loader.loadLevel();
			
		} catch (FileNotFoundException e){
			System.out.println("File not found");
			e.printStackTrace();
			System.out.println("File not found");
		} catch (IOException e){
			e.printStackTrace();
		}
		
//		gameBoard = new GameBoard();
		add(pnl,BorderLayout.CENTER);
		pnl.setPreferredSize(new Dimension(400,gameBoard.getHeightPix())); //TODO
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
//				TODO zavolame metodu kickTheBird
				gameBoard.kickTheBird();
			}
		});
		
		Timer t = new Timer(20, e -> {
			gameBoard.tick(x++);
			pnl.repaint();
		});
		
		t.start();
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(()-> {
			MainWindow w = new MainWindow();
			w.setVisible(true);
		});
	}
}