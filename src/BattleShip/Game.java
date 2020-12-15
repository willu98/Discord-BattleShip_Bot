package BattleShip;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class Game {
	private static Player players[];
	private static Board gameBoard;
	private static int turn = 0;
	public Game(Player[] players) {
		this.players = players;
	}

	public MessageEmbed generateBoards(Player player1, Player player2) {

		EmbedBuilder boardInfo = new EmbedBuilder(); 
		boardInfo.setTitle("🚢BATTLESHIP🚢");
		boardInfo.setDescription("Currently " + players[turn].getUserID() + "'s turn");
		boardInfo.addField(player2.getUserID() + "'s Board", 
		  "0️⃣1️⃣2️⃣3️⃣4️⃣5️⃣6️⃣7️⃣8️⃣9️⃣🔟"
		+ "\n🇦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇧🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇨🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇩🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇪🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇫🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇬🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇭🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇮🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇯🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦", false);

		boardInfo.addField("Your Board", 
		  "0️⃣1️⃣2️⃣3️⃣4️⃣5️⃣6️⃣7️⃣8️⃣9️⃣🔟"
		+ "\n🇦🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇧🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇨🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇩🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇪🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇫🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇬🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇭🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇮🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
		+ "\n🇯🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦", false);

		return boardInfo.build();
	}
	
	public int getTurn() {
		return Game.turn;
	}
}
