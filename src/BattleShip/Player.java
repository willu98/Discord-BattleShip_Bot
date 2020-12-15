package BattleShip;

import net.dv8tion.jda.api.entities.User;

public class Player {
	private User player;
	private String playerID;
	private Long gameMsgID[] = new Long[2];
	
	//player 0 or player 1
	private static int ID;

	Player(User player){
		this.player = player;
		this.playerID = player.getName() + "#" + player.getDiscriminator();
	}
	
	public void setGameMsgID(Long gameMsgID, int i) {
		this.gameMsgID[i] = gameMsgID;
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
