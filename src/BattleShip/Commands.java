package BattleShip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
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
	private static Long inviteId;
	
	//change later
	private static int invEmoteCounter[] = {0,0, 0};
	private static int coordCounter[] = new int [20];
	
	private static enum States{
		IDLE,
		INVITE,
		GAME
	};
	
	States curr = States.IDLE;
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		//checking for valid invite
		if (args[0].equalsIgnoreCase(Main.prefix + "play") && args.length == 2 && curr == States.IDLE) {
			
			//getting the sender user obj
			players[0] = new Player(event.getAuthor());
			
			
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
				players[1] = new Player(event.getGuild().getMemberById(recipientID).getUser());
				
				//indicate that invite was successful
				invite.setDescription("✅ User " 
						+ players[1].getUserID()
						+ " Successfully invited!");
				MessageEmbed msg = invite.build();
				event.getChannel().sendMessage(msg).queue();
				
				//sending invite to recipient
				invite.setDescription("Accept Invite from User " 
						+ players[1].getUserID()
						+ " to play BattleShip"
						+ "\n✅ To accept"
						+ "\n❎ To decline");
				MessageEmbed msg2 = invite.build();
				players[1].getUserObj().openPrivateChannel().flatMap(channel -> channel.sendMessage(msg2)).queue(message -> {
					  message.addReaction("✅").queue();
					  message.addReaction("❎").queue();	 
					  inviteId = message.getIdLong();
					  }
				);
				
				curr = States.INVITE;
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
		
		switch(curr) {
		
		//if abou to start game
		case INVITE:
			//if invite is accepted
			if(event.getMessageIdLong() == inviteId && event.getReactionEmote().getEmoji().equals("✅")) {			
				invEmoteCounter[0]++;
				if(invEmoteCounter[0] > 1) {
					game = new Game(players);
					
					//editing the invite message
					EmbedBuilder acceptInvite = new EmbedBuilder();
					acceptInvite.setTitle("🚢BATTLESHIP🚢");
					acceptInvite.setDescription("✅ Game against "
							+ players[0].getUserID()
							+ " will now start!");
					event.getChannel().editMessageById(inviteId, acceptInvite.build()).complete();
					
					//telling the other player that invite has been accepted and game will start
					acceptInvite.setDescription("✅ " + players[1].getUserID()
							+ " has accepted your invite. Game will now start!");
					players[0].getUserObj().openPrivateChannel().flatMap(channel -> channel.sendMessage(acceptInvite.build())).queue();
					
					players[0].getUserObj().openPrivateChannel().flatMap(channel -> channel.sendMessage(game.generateBoards(players[0], players[1]))).queue(message -> {
						message.addReaction("1️⃣").queue();
						message.addReaction("2️⃣").queue();
						message.addReaction("3️⃣").queue();
						message.addReaction("4️⃣").queue();
						message.addReaction("5️⃣").queue();
						message.addReaction("6️⃣").queue();
						message.addReaction("7️⃣").queue();
						message.addReaction("8️⃣").queue();
						message.addReaction("9️⃣").queue();
						message.addReaction("🔟").queue();
						message.addReaction("🇦").queue();
						message.addReaction("🇧").queue();
						message.addReaction("🇨").queue();
						message.addReaction("🇩").queue();
						message.addReaction("🇪").queue();
						message.addReaction("🇫").queue();
						message.addReaction("🇬").queue();
						message.addReaction("🇭").queue();
						message.addReaction("🇮").queue();
						message.addReaction("🇯").queue();
						players[0].setGameMsgID(message.getIdLong(), 0);
					});
					
					players[0].getUserObj().openPrivateChannel().flatMap(channel -> channel.sendMessage(new EmbedBuilder().setDescription("Select the coordinates you would like to attack and click ✅").build())).queue(message -> {
						message.addReaction("✅").queue();
						players[0].setGameMsgID(message.getIdLong(), 1);
					});
					
					players[1].getUserObj().openPrivateChannel().flatMap(channel -> channel.sendMessage(game.generateBoards(players[1], players[0]))).queue(message -> {
						message.addReaction("1️⃣").queue();
						message.addReaction("2️⃣").queue();
						message.addReaction("3️⃣").queue();
						message.addReaction("4️⃣").queue();
						message.addReaction("5️⃣").queue();
						message.addReaction("6️⃣").queue();
						message.addReaction("7️⃣").queue();
						message.addReaction("8️⃣").queue();
						message.addReaction("9️⃣").queue();
						message.addReaction("🔟").queue();
						message.addReaction("🇦").queue();
						message.addReaction("🇧").queue();
						message.addReaction("🇨").queue();
						message.addReaction("🇩").queue();
						message.addReaction("🇪").queue();
						message.addReaction("🇫").queue();
						message.addReaction("🇬").queue();
						message.addReaction("🇭").queue();
						message.addReaction("🇮").queue();
						message.addReaction("🇯").queue();
						players[1].setGameMsgID(message.getIdLong(), 0);
					});
					
					players[1].getUserObj().openPrivateChannel().flatMap(channel -> channel.sendMessage(new EmbedBuilder().setDescription("Select the coordinates you would like to attack and click ✅").build())).queue(message -> {
						message.addReaction("✅").queue();
						players[1].setGameMsgID(message.getIdLong(), 1);
						curr = States.GAME;
					});										
				}
			}
			else if(event.getMessageIdLong() == inviteId && event.getReactionEmote().getEmoji().equals("❎")) {
				invEmoteCounter[1]++;
				if(invEmoteCounter[1] > 1) {
				}					
			}					
			break;
			
		//if there is a game ongoing
		case GAME:
			//
			if(event.getMessageIdLong() == players[game.getTurn()].getMsgID(1) && event.getReactionEmote().getEmoji().equals("✅")) {
				if(event.getChannel().retrieveMessageById(event.getMessageId()).complete().getReactions().get(0).getCount() > 1){
					
				}
			}
			
			

			break;
		}

	}	
	

}    
