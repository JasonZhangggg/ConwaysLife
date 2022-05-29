/*
 * Panel for Conway's life simulation
 * 
 * 
 * 
 * */
import java.awt.*;

import javax.swing.JPanel;

public class lifePannel extends JPanel {
	boolean[][] cells;
	double width;
	double height;
	public lifePannel(boolean[][] in) {
		cells = in;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		width = (double)this.getWidth() / cells[0].length;
		height = (double)this.getHeight() / cells.length;
		g.setColor(Color.BLUE);
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[0].length; col++) {
				if(cells[row][col] == true) {
					g.fillRect((int)Math.round(col*width), 
							(int)Math.round(row*height),
							(int)width+1, (int)height+1);
				}
			}
		}
		
		g.setColor(Color.BLACK);
		for (int x = 0; x < cells[0].length+1; x++) {
			g.drawLine((int)(Math.round(x * width)), 0, (int)Math.round(x*width), this.getHeight());
		}
		for (int y = 0; y < cells.length; y++) {
			g.drawLine(0, (int)Math.round(y*height) , this.getWidth(), (int)Math.round(y*height));
		} 
	}
	public void setCells(boolean[][] cellsNew) {
		cells = cellsNew;
	}
}
