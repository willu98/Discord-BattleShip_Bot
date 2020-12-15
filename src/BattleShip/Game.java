package BattleShip;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class Game {
	private Player players[];
	
	//determines who's turn it is, 0 or 1
	private static int turn = 0;
	
	public Game(Player[] players) {
		this.players = players;
	}
	
	private static String letterEmotes[] = {"🇦", "🇧", "🇨", "🇩", "🇪", "🇫", "🇬", "🇭", "🇮", "🇯"};
	public MessageEmbed generateBoards(Player player1, Player player2) {

		EmbedBuilder boardInfo = new EmbedBuilder(); 
		boardInfo.setTitle("🚢BATTLESHIP🚢");
		if(players[turn] == player1) {
			boardInfo.setDescription("Currently your turn");
		}
		else {
			boardInfo.setDescription("Currently " + players[turn].getUserID() + "'s turn");
		}
		
		StringBuilder boardBuilder = new StringBuilder();

		boardBuilder.append("0️⃣1️⃣2️⃣3️⃣4️⃣5️⃣6️⃣7️⃣8️⃣9️⃣🔟");
		
		for(int i = 0; i < 10; i++) {
			for(int j = -1; j < 10; j++) {
				if (j == -1) {
					boardBuilder.append("\n" + letterEmotes[i]);
				}
				else {

					if(player2.getPlayerBoard().getBoardSpaces()[i][j] == 2) {
						boardBuilder.append("⭕");
					}
					else if(player2.getPlayerBoard().getBoardSpaces()[i][j] == 3){
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
		
		for(int i = 0; i < 10; i++) {
			for(int j = -1; j < 10; j++) {
				if (j == -1) {
					boardBuilder.append("\n" + letterEmotes[i]);
				}
				else {
					if(player1.getPlayerBoard().getBoardSpaces()[i][j] == 0) {
						boardBuilder.append("🟦");
					}
					else if(player1.getPlayerBoard().getBoardSpaces()[i][j] == 1) {
						boardBuilder.append("🚢");
					}	
					else if(player1.getPlayerBoard().getBoardSpaces()[i][j] == 2) {
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

	public int getTurn() {
		return Game.turn;
	}
	
	public void flipTurn() {
		this.turn = (this.turn - 1) * -1;
	}
}
