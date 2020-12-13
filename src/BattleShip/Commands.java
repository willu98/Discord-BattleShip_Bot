package BattleShip;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter{
	
	/*@Override
    public void onMessageReceived(MessageReceivedEvent event) {
		//System.out.println("YO");
		//event.getChannel().sendTyping().queue();
		event.getChannel().sendMessage("YO").queue();
    }*/
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if (args[0].equalsIgnoreCase(Main.prefix + "play")) {
			EmbedBuilder info = new EmbedBuilder();
			info.setTitle("🚢BATTLESHIP🚢");
			info.addField("Your Board", "0️⃣1️⃣2️⃣3️⃣4️⃣5️⃣6️⃣7️⃣8️⃣9️⃣🔟"
					+ "\n1️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n2️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n3️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n4️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n5️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n6️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n7️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n8️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n9️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n🔟🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦", false);
			
			info.addField("Enemy Board", "0️⃣1️⃣2️⃣3️⃣4️⃣5️⃣6️⃣7️⃣8️⃣9️⃣🔟"
					+ "\n1️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n2️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n3️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n4️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n5️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n6️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n7️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n8️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n9️⃣🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦"
					+ "\n🔟🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦", false);
			
			info.setColor(0x00A8FF);
			//event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(info.build()).queue();
			
			info.clear();
		}
    }
}    
