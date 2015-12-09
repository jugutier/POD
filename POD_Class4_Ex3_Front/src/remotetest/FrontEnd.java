package remotetest;

import java.awt.BasicStroke;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import core.CallBack;
import core.Service;

public class FrontEnd extends JFrame implements CallBack
{
 	  // marker is used to indicate the token type
	  private char marker;

	  // myTurn indicates whether the player can move now
	  private boolean myTurn = false;

	  // Each cell can be empty or marked as 'O' or 'X'
	  private Cell[][] cell;
	  
	  // ticTacToe is the game server for coordinating with the players
	  private Service handle;

	  // Border for cells and panel
	  private Border lineBorder = BorderFactory.createLineBorder(Color.BLUE, 2);
	  private JLabel statusBar = new JLabel();
	  

/**********************************
 * Servicio Remoto Ofrecido!
 **********************************
 */

	public void takeTurn(boolean turn) throws RemoteException 
	{
		if (turn)
			notify("It is my turn");
		else
			notify("Wait for the other player to move");
		
	    this.myTurn = turn;
	}
	
	/** The server notifies a client of the other player's move */
	public void mark(int row, int column, char token) throws RemoteException 
	{
		cell[row][column].setToken(token);
	}

	public void setToken(char token)
	{
		  setTitle("TicTacToeClientRMI- You play with " + token);
		  marker= token;
	}

	public void notify(String message) throws RemoteException 
	{
	    statusBar.setText(message);
	}


	public void notifyGameOver()  throws RemoteException
	{
		notify("Game over. Nobody wins :|");
	}
	

	public void notifyYouWon(String cause) throws RemoteException 
	{
		 notify(String.format("%s. I won :-)", cause ) );
		this.myTurn = false;
	}

	public void notifyYouLost(String cause) throws RemoteException 
	{
		  notify(String.format("%s. I lost :-(", cause) );
	
	}
	
   
	  public FrontEnd(Service aHandle) throws RemoteException
	  {
		
		  UnicastRemoteObject.exportObject(this, 0);
		  handle= aHandle;

		  JPanel jPanelPpal = new JPanel();
		  jPanelPpal.setBorder(lineBorder);
		  jPanelPpal.setLayout(new GridLayout(3, 3, 1, 1));
	    
		  // Create cells and place cells in the panel
		  cell = new Cell[3][3];
		  for (int i = 0; i < 3; i++)
		  {
			  for (int j = 0; j < 3; j++)
			  {
	    		 jPanelPpal.add(cell[i][j] = new Cell(i, j));
			  }
		  }
	    
	    getContentPane().add(jPanelPpal, BorderLayout.CENTER);

	    getContentPane().add(statusBar, BorderLayout.SOUTH);


	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    addWindowListener(new WindowAdapter() 
	    {
	    	public void windowClosing(WindowEvent e) 
	    	{
	    		try 
	    		{
					handle.disconnect(FrontEnd.this);
				} 
	    		catch (RemoteException e1) 
	    		{
					e1.printStackTrace();
				}
	    		super.windowClosing(e);
	    	}
		});
	    
	    
	    pack();
	    setSize(400, 320);
	    setVisible(true);
	    }

	  
	  /** Inner class Cell for modeling a cell on the TicTacToe board */
	  private class Cell extends JPanel 
	  {
	    // marked indicates whether the cell has been used
	    private boolean marked = false;

	    // row and column indicate where the cell appears on the board
	    private int row, column;

	    // The token for the cell
	    private char token;

	    /** Construct a cell */
	    public Cell(final int row, final int column) 
	    {
	    	this.row = row;
	    	this.column = column;
	    	addMouseListener(new MouseAdapter() 
	    	{
	    		public void mouseClicked(MouseEvent e) 
	    		{
	    			if (myTurn && !marked) // validacion en el cliente de si es el turno y esta libre
	    			{
	    				// Mark the cell
		    			setToken(marker);

		    			// Notify the server of the move
		    			try 
		    			{
		    				handle.myMove(row, column, marker);
		    			}
		    			catch (RemoteException ex) 
		    			{
		    				System.out.println(ex);
		    			}
	    			}
	    		}
	    	});
	    	setBorder(lineBorder);
	    }

		/** Set token on a cell (mark a cell) */
		public void setToken(char c) 
		{
			token = c;
			marked = true;
			repaint();
		}

	    /** Paint the cell to draw a shape for the token */
		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
		
			// Draw the border
			g.drawRect(0, 0, getSize().width, getSize().height);
		
			if (token == 'X')
			{
				g.setColor(Color.MAGENTA); 
				((Graphics2D) g).setStroke(new BasicStroke(10F));  // set stroke width of 10
				 
				g.drawLine(10, 10, getSize().width - 10, getSize().height - 10);
				g.drawLine(getSize().width - 10, 10, 10, getSize().height - 10);
			}
			else if (token == 'O') 
				{
					g.setColor(Color.GREEN);
					((Graphics2D) g).setStroke(new BasicStroke(10F));  // set stroke width of 10
					
					g.drawOval(10, 10, getSize().width - 20, getSize().height - 20);
				}
		}
	}

}

