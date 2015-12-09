package publish;
import java.rmi.*;   
import java.rmi.server.*;
import java.util.ArrayList;

import core.CallBack;
import core.Service;

import java.rmi.registry.*;


public class ServiceImpl implements Service  
{
	  // Declare two players, used to call players back
	  private CallBack player1 ;
	  private CallBack player2 ;
  
	  private ArrayList<CallBack> pendings= new ArrayList<CallBack>();
 
	  // board records players' moves
	  private char[][] board;

	  public ServiceImpl() throws RemoteException 
	  {
		  clearContents();
		  UnicastRemoteObject.exportObject(this, 0);
	  }

	  synchronized public void clearContents() throws RemoteException
	  {
		  // board records players' moves
		  board = new char[3][3];
		  player1= null;
		  player2= null;
		  if (pendings.size() > 0)
		  {
			  player1= pendings.remove(0);
		      player1.notify("Wait for a second player to join");
		      player1.setToken('X');
		  }
		  if (pendings.size() > 0)
		  {
			  player2= pendings.remove(0);
		      player2.takeTurn(false);
		      player2.setToken('O');
		      player1.takeTurn(true);
		  }
	  }

  
	  synchronized public void disconnect(CallBack client) throws RemoteException
	  {
		  //case 1: someone on the waitlist disconnects
		  if(pendings.remove(client)){
			  return;
		  }
		  //case 2: someone already playing disconnects
		  if(player1.equals(client)){
			  player1 = null;
			  player2.notifyYouWon("Your oponent walked!");
			  for (int i = 0; i < 3; i++)
			      for (int j = 0; j < 3; j++)
			        player2.mark(i, j, '\u0000');
			  pendings.add(0, player2);
			  clearContents();//this gets a second player if any..
		  }
		  //case 2: someone already playing disconnects
		  if(player2.equals(client)){
			  player2 = null;
			  player1.notifyYouWon("Your oponent walked!");
			  for (int i = 0; i < 3; i++)
			      for (int j = 0; j < 3; j++)
			    	  player1.mark(i, j, '\u0000');
			  pendings.add(0, player1);
			  clearContents();//this gets a second player if any..
		  }
	  }
	  
  
	  /**
	   * Connect to the TicTacToe server 
	   */
	  synchronized public void connect(CallBack client) throws RemoteException 
	  {
	    if (player1 == null) 
	    {
	       player1= client;
	       player1.notify("Wait for a second player to join");
	       player1.setToken('X');
	    }
	    else if (player2 == null) 
	    {
	       player2= client;
	       player2.takeTurn(false);
	       player2.setToken('O');
	       player1.takeTurn(true);
	     }
	    else 
	    {
	      // Already two players
	    	pendings.add(client);
	       client.notify("Two players are already in the game. Next Game is coming...");
	    }
	  }

  /** A client invokes this method to notify the server of its move*/
  synchronized public void myMove(int row, int column, char token) throws RemoteException 
  {
	  System.out.println("Row "+row+" Column "+column);
	  // a participant has left
	  if (player1 == null || player2 == null)
		  return;
	  
    // Set token to the specified cell
    board[row][column] = token;

    // Notify the other player of the move
    if (token == 'X')
      player2.mark(row, column, 'X');
    else
      player1.mark(row, column, 'O');

    // Check if the player with this token wins
    if (isWon(token)) 
    {
	      if (token == 'X') 
	      {
	    	  player1.notifyYouWon("Well done!");
	    	  player2.notifyYouLost("Oh!");
	          clearContents();
	      }
	      else 
	      {
	     	  player2.notifyYouWon("Well done!");
	    	  player1.notifyYouLost("Oh!");
	          clearContents();
	      }
    }
    else if (isFull()) // no chance
    {
    	player1.notifyGameOver();
    	player2.notifyGameOver();
    	clearContents();
    }
    else 
    {
    	player1.takeTurn(token == 'O');
    	player2.takeTurn(token == 'X');
    }
  }

  /** Check if a player with the specified token wins */
  public boolean isWon(char token) 
  {
    for (int i = 0; i < 3; i++)
      if ((board[i][0] == token) && (board[i][1] == token) && (board[i][2] == token))
        return true;

    for (int j = 0; j < 3; j++)
      if ((board[0][j] == token) && (board[1][j] == token) && (board[2][j] == token))
        return true;

    if ((board[0][0] == token) && (board[1][1] == token) && (board[2][2] == token))
      return true;

    if ((board[0][2] == token) && (board[1][1] == token)
      && (board[2][0] == token))
      return true;

    return false;
  }

  /** Check if the board is full */
  public boolean isFull() 
  {
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        if (board[i][j] == '\u0000')
          return false;

    return true;
  }


}
