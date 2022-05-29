import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class ConwaysLife implements MouseListener, ActionListener, Runnable {
	//create the game board
	JFrame frame = new JFrame("Life simulation");
	Container south = new Container();
	JButton step = new JButton("Step");
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	boolean running = false;
	JButton submit = new JButton("submit");
	JDialog javaDialog = new JDialog();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JLabel label = new JLabel("Enter the board size");
	String BoardSize = "";
	int row = 0;
	int col = 0;
	boolean[][] cells;
	boolean flag = false;
	JTextField changesize = new JTextField("row, col              ");
	lifePannel panel;


	public ConwaysLife() {
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		panel2.add(label);
		panel3.add(changesize);
		panel3.add(submit);
		javaDialog.add(panel2, BorderLayout.NORTH);
		javaDialog.add(panel3, BorderLayout.SOUTH);
		javaDialog.setSize(300, 100);
		submit.addActionListener(this);
		javaDialog.setVisible(true);
		
		
	}
	public static void main(String[] args) {
		new ConwaysLife();
	}

	@Override
	public void mouseClicked(MouseEvent event) {	
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		double width = (double)(panel.getWidth() / cells[0].length);
		double height = (double)(panel.getHeight() / cells.length);
		int col = Math.min(cells[0].length-1, (int)(event.getX() / width));	
		int row = Math.min(cells.length-1, (int)(event.getY() / height));
		cells[row][col] = !cells[row][col]; 
		frame.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		
	}

	@Override
	public void mouseExited(MouseEvent event) {

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(step)) {
			step();
		}
		if(event.getSource().equals(start)) {
			if(running == false){
				running = true;
				Thread t = new Thread(this);
				t.start();
			}
		}
		if(event.getSource().equals(stop)) {
			running = false;
		}
		if (event.getSource().equals(submit)) {
			BoardSize = changesize.getText();
			String[] boardSizeArray = BoardSize.split(",\\s*");
			cells = new boolean[Integer.parseInt(boardSizeArray[0])][Integer.parseInt(boardSizeArray[1])];
			javaDialog.setVisible(false);
			panel = new lifePannel(cells);
			frame.setSize(600, 600);
			frame.setLayout(new BorderLayout());
			frame.add(panel, BorderLayout.CENTER);
			panel.addMouseListener(this);
			south.setLayout(new GridLayout(1,3));
			south.add(step);
			step.addActionListener(this);
			south.add(start);
			start.addActionListener(this);
			south.add(stop);
			stop.addActionListener(this);
			frame.add(south, BorderLayout.SOUTH);;
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);		}

	}
	@Override
	public void run() {
		while(running == true) {
			step();
			try {
				Thread.sleep(300);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	public void step() {
		boolean [][] nextCells = new boolean[cells.length][cells[0].length];
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[0].length; col++) {
				int neighbors = 0;
				//check how many neighbors there are
				if (row>0 && col>0 && cells[row-1][col-1] == true) {
					neighbors++;
				}
				if(row>0 && cells[row-1][col] == true) {
					neighbors++;

				}
				if(row > 0  && col<cells[0].length-1 && cells[row-1][col+1] == true) {
					neighbors++;

				}
				if(col< cells[0].length-1 && cells[row][col+1] == true) {
					neighbors++;

				}
				if(col< cells[0].length-1 && row< cells.length-1 && cells[row+1][col+1] == true) {
					neighbors++;

				}

				if(row<cells.length-1 && cells[row+1][col] == true) {
					neighbors++;

				}
				if(col>0 && row<cells.length-1 && cells[row+1][col-1] == true) {
					neighbors++;

				}
				if(col>0 && cells[row][col-1] == true) {
					neighbors++;
				}					
				if(cells[row][col] == true) {
					if(neighbors == 2 || neighbors == 3) {
						nextCells[row][col] = true;
					}
					else {
						nextCells[row][col] = false;
					}
				}
				else {
					if(neighbors == 3) {	
						nextCells[row][col] = true;
					}
					else {
						nextCells[row][col] = false;
					}
				}

			}
		}
		cells = nextCells;
		panel.setCells(nextCells);
		frame.repaint();
		
	}



}




