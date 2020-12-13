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
		
		if (args[0].equalsIgnoreCase(Main.prefix + "info")) {
			EmbedBuilder info = new EmbedBuilder();
			info.setTitle("Inventory Bot");//);
			info.setDescription("test description");
			info.setColor(0xf45642);
			info.setFooter("Created by willu", event.getMember().getUser().getAvatarUrl());
			//event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(info.build()).queue();
			
			info.clear();
		}
    }
}    
