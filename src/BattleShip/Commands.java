package BattleShip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter{
	
	/*@Override
    public void onMessageReceived(MessageReceivedEvent event) {
		//System.out.println("YO");
		//event.getChannel().sendTyping().queue();
		event.getChannel().sendMessage("YO").queue();
    }*/
	private static Game game;
	private static Player players[] = new Player[2];
	private static String inviteId = "";
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		//checking for valid invite
		if (args[0].equalsIgnoreCase(Main.prefix + "play") && args.length == 2) {
			
			//getting the sender user obj
			User sender = event.getAuthor();
			
			
			EmbedBuilder invite = new EmbedBuilder();			
			invite.setColor(0x00A8FF);
			invite.setTitle("🚢BATTLESHIP🚢");
			
			
			//pulling the user id number from the invite using 18 digit regex
			Pattern pattern = Pattern.compile("[0-9]{18}");
			Matcher matcher = pattern.matcher(args[1]);
			String recipientID = "";
			if (matcher.find()) {
				recipientID = matcher.group(0);
			}
			
			//trying to get a valid user
			try {
				User recipient = event.getGuild().getMemberById(recipientID).getUser();
				
				//indicate that invite was successful
				invite.setDescription("✅ User " 
						+ recipient.getName() + "#" 
						+ recipient.getDiscriminator() 
						+ " Successfully invited!");
				MessageEmbed msg = invite.build();
				event.getChannel().sendMessage(msg).queue();
				
				//sending invite to recipient
				invite.setDescription("Accept Invite from User " 
						+ recipient.getName() + "#" 
						+ recipient.getDiscriminator() 
						+ " to play BattleShip"
						+ "\n✅ To accept"
						+ "\n❎ To decline");
				MessageEmbed msg2 = invite.build();
				recipient.openPrivateChannel().flatMap(channel -> channel.sendMessage(msg2)).queue(message -> {
					  message.addReaction("✅").queue();
					  message.addReaction("❎").queue();					  
					  }
				);
				
			}
			
			
			//invalide user id
			catch(java.lang.NumberFormatException e) {
				//indicate that user entered was not valid
				invite.setDescription("❎ Invite unsuccessful: please invite a valid user");
				MessageEmbed msg = invite.build();
				event.getChannel().sendMessage(msg).queue();
			}
			invite.clear();
		}	
		//if help command is called, show user all commands and how to play
		else if(args[0].equalsIgnoreCase(Main.prefix + "help")) {
			
		}
		//invalid command, prompt user to use help command
		else {
			
		}
    }
	
	public void onMessageReactionAdd(MessageReactionAddEvent event) {
		
		if(event.getMessageId().equals() && event.getReactionEmote().getEmoji().equals("✅"));
	}	
	

}    
