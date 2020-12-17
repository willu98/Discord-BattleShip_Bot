package BattleShip;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class Game {
	private Player players[];
	
	//determines who's turn it is, 0 or 1
	private int turn = 0;
	
	public Game(Player[] players) {
		this.players = players;
	}
	
	private static String letterEmotes[] = {"🇦", "🇧", "🇨", "🇩", "🇪", "🇫", "🇬", "🇭", "🇮", "🇯"};
	
	
	//generate sthe game boards for both players and returns it as an embedded message
	public MessageEmbed generateBoards(Player player1, Player player2) {

		EmbedBuilder boardInfo = new EmbedBuilder(); 
		boardInfo.setTitle("🚢BATTLESHIP🚢");
		
		//showing who's turn it is
		if(players[turn] == player1) {
			boardInfo.setDescription("Currently your turn");
		}
		else {
			boardInfo.setDescription("Currently " + players[turn].getUserID() + "'s turn");
		}
		
		StringBuilder boardBuilder = new StringBuilder();

		boardBuilder.append("0️⃣1️⃣2️⃣3️⃣4️⃣5️⃣6️⃣7️⃣8️⃣9️⃣🔟");
		
		//appending the the player's enemy's board, that is, fog of war is applied
		for(int i = 0; i < 10; i++) {
			for(int j = -1; j < 10; j++) {
				if (j == -1) {
					boardBuilder.append("\n" + letterEmotes[i]);
				}
				else {
					if(player2.getPlayerBoard().getBoardSpaces()[j][i] == 2) {
						boardBuilder.append("⭕");
					}
					else if(player2.getPlayerBoard().getBoardSpaces()[j][i] == 3){
						boardBuilder.append("❌");
					}
					else {
						boardBuilder.append("🟦");						
					}
				}
			}
		}
		
		boardInfo.addField(player2.getUserID() + "'s Board", boardBuilder.toString(), false);

		boardBuilder = new StringBuilder();
		boardBuilder.append("0️⃣1️⃣2️⃣3️⃣4️⃣5️⃣6️⃣7️⃣8️⃣9️⃣🔟");
		
		
		//creating players own board
		for(int i = 0; i < 10; i++) {
			for(int j = -1; j < 10; j++) {
				if (j == -1) {
					boardBuilder.append("\n" + letterEmotes[i]);
				}
				else {
					if(player1.getPlayerBoard().getBoardSpaces()[j][i] == 0) {
						boardBuilder.append("🟦");
					}
					else if(player1.getPlayerBoard().getBoardSpaces()[j][i] == 1) {
						boardBuilder.append("🚢");
					}	
					else if(player1.getPlayerBoard().getBoardSpaces()[j][i] == 2) {
						boardBuilder.append("⭕");
					}
					else {
						boardBuilder.append("❌");
					}
				}
			}
		}
		boardInfo.addField("Your Board", boardBuilder.toString(), false);
				
		return boardInfo.build();
	}

	//getting the current tun
	public int getTurn() {
		return this.turn;
	}
	
	
	//flipping the turn
	public void flipTurn() {
		this.turn = (this.turn - 1) * -1;
	}
}
