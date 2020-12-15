package BattleShip;

import net.dv8tion.jda.api.entities.User;

public class Player {
	private User player;
	private String playerID;
	private Long gameMsgID[] = new Long[2];
	private Board gameBoard;
	private Long textChannel;
	
	Player(User player){
		this.player = player;
		this.playerID = player.getName() + "#" + player.getDiscriminator();
		this.gameBoard = new Board();
	}
	
	public boolean doTurn(int posX, int posY) {		
		if (this.gameBoard.getBoardSpaces()[posX][posY] != 2 || this.gameBoard.getBoardSpaces()[posX][posY] != 3) {
			this.gameBoard.setSpace(posX, posY);
			return true;
		}
		return false;
	}
	
	public void setGameMsgID(Long gameMsgID, int i) {
		this.gameMsgID[i] = gameMsgID;
	}
	
	public void setTextChannel(Long textChannel) {
		this.textChannel = textChannel;
	} 
	
	public Long getTextChannel() {
		return this.textChannel;
	}
	
	public Board getPlayerBoard() {
		return this.gameBoard;
	}
	
	public Long getMsgID(int i) {
		if (i == 0)
			return gameMsgID[0];
		else
			return gameMsgID[1];
	}
	
	public User getUserObj() {
		return player;
	}
	
	public String getUserID() {
		return playerID;
	}
}
