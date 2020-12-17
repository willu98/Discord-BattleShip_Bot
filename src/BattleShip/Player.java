package BattleShip;

import net.dv8tion.jda.api.entities.User;

public class Player {
	
	//User object for the player
	private User player;
	
	//viewable discord id
	private String playerID;
	
	//id's for both of the game messages used for each player, i.e the game boards and the accept messages
	private Long gameMsgID[] = new Long[2];
	
	//each player gets a game board
	private Board gameBoard;
	
	//the private text channel id that is used btwn the bot and the player
	private Long textChannel;
	
	//each player starts with 17 ship spaces
	private int numShips = 17;
	
	Player(User player){
		this.player = player;
		this.playerID = player.getName() + "#" + player.getDiscriminator();
		this.gameBoard = new Board();
	}
	
	
	//this function does the turn for the player and updates the board, returns true
	//if move is successful, otherwise false
	public boolean doTurn(int posX, int posY) {		
		if (this.gameBoard.getBoardSpaces()[posX][posY] != 2 || this.gameBoard.getBoardSpaces()[posX][posY] != 3) {
			this.gameBoard.setSpace(posX, posY);
			return true;
		}
		return false;
	}
	
	
	//sets the gamemsg id's, 2nd parameter i indicates which game message is being set
	public void setGameMsgID(Long gameMsgID, int i) {
		this.gameMsgID[i] = gameMsgID;
	}
	
	
	//setting the private text channel id
	public void setTextChannel(Long textChannel) {
		this.textChannel = textChannel;
	} 
	
	
	//gettting the text channel id
	public Long getTextChannel() {
		return this.textChannel;
	}
	
	
	//gettig tyhe board thatthe player uses
	public Board getPlayerBoard() {
		return this.gameBoard;
	}
	
	
	//getting the message id's, i indicates which message
	public Long getMsgID(int i) {
		if (i == 0)
			return gameMsgID[0];
		else
			return gameMsgID[1];
	}
	
	
	//gets the players User object
	public User getUserObj() {
		return player;
	}
	
	
	//gets the player discord id
	public String getUserID() {
		return playerID;
	}
}
