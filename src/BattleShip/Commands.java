package BattleShip;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter{

	private Game game;
	private Player players[] = new Player[2];
	
	//id for message of the invite 
	private Long inviteId;
	
	//array of emotes that are used as controls
	private final String emotes[] = {"1️⃣","2️⃣","3️⃣","4️⃣","5️⃣","6️⃣","7️⃣","8️⃣","9️⃣","🔟","🇦","🇧","🇨","🇩","🇪","🇫","🇬","🇭","🇮","🇯"};
	//change later
	private static int invEmoteCounter[] = {0,0, 0};
	
	//game states
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
			
			//creating invite
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
			event.getChannel().sendMessage(new EmbedBuilder().setDescription("Type ~play @user to invite them to a game of BattleShip").build()).queue();
		}
		//invalid command, prompt user to use help command
		else {
			event.getChannel().sendMessage(new EmbedBuilder().setDescription("Invalid Command, Please type ~help to get a list of all commands").build()).queue();
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
										
					//sending player zero the game, with board emote commands
					players[0].getUserObj().openPrivateChannel().flatMap(channel -> channel.sendMessage(game.generateBoards(players[0], players[1]))).queue(message -> {
						for(int i = 0; i < emotes.length; i++) {
							message.addReaction(emotes[i]).queue();
						}
						players[0].setGameMsgID(message.getIdLong(), 0);
					});
					
					//sending player zero the accept move msg
					players[0].getUserObj().openPrivateChannel().flatMap(channel -> channel.sendMessage(new EmbedBuilder().setDescription("Select the coordinates you would like to attack and click ✅").build())).queue(message -> {
						message.addReaction("✅").queue();
						players[0].setTextChannel(message.getPrivateChannel().getIdLong());
						players[0].setGameMsgID(message.getIdLong(), 1);
					});
					
					
					//srepeating for other player
					players[1].getUserObj().openPrivateChannel().flatMap(channel -> channel.sendMessage(game.generateBoards(players[1], players[0]))).queue(message -> {
						for(int i = 0; i < emotes.length; i++) {
							message.addReaction(emotes[i]).queue();
						}
						players[1].setGameMsgID(message.getIdLong(), 0);
					});
					
					players[1].getUserObj().openPrivateChannel().flatMap(channel -> channel.sendMessage(new EmbedBuilder().setDescription("Select the coordinates you would like to attack and click ✅").build())).queue(message -> {
						message.addReaction("✅").queue();
						players[1].setTextChannel(message.getPrivateChannel().getIdLong());
						players[1].setGameMsgID(message.getIdLong(), 1);
						curr = States.GAME;
					});										
				}
			}
			//if the invite is declined then go back to  idle
			else if(event.getMessageIdLong() == inviteId && event.getReactionEmote().getEmoji().equals("❎")) {
				invEmoteCounter[1]++;
				if(invEmoteCounter[1] > 1) {
				}			
				this.curr = States.IDLE;
			}					
			break;
			
		//if there is a game ongoing
		case GAME:
			//if the user clicks the checkmark button
			if(event.getMessageIdLong() == players[game.getTurn()].getMsgID(1) && event.getReactionEmote().getEmoji().equals("✅")) {
				//if user clicked checkmark, this if is required bc bot reaction ✅ will also trigger this if not
				if(event.getChannel().retrieveMessageById(event.getMessageId()).complete().getReactions().get(0).getCount() > 1){
					
					//list of emote reactions on the game message
					List<MessageReaction> playerChoice = event.getChannel().retrieveMessageById(players[game.getTurn()].getMsgID(0)).complete().getReactions();
					
					//input x and y position
					int posX = 0;
					int posY = 0;
					
					//tmp varibales used to make sure only one x and one y coordinateare selected
					int xCount = 0;
					int yCount= 0;
					
					//checking which reaction were used by the player and assigning to posx and posy accordingly
					for(int i = 0; i < 20; i++) {
						if(playerChoice.get(i).getCount() > 1) {
							if (mapEmoji(playerChoice.get(i).getReactionEmote().getEmoji()) < 10) {
								posX = mapEmoji(playerChoice.get(i).getReactionEmote().getEmoji());
								xCount++;
							}
							else if(mapEmoji(playerChoice.get(i).getReactionEmote().getEmoji()) < 20) {
								posY = mapEmoji(playerChoice.get(i).getReactionEmote().getEmoji()) - 10;
								yCount++;
							}
						}
					}
					
					//if the user successfully entered a valid move
					if(xCount == 1 && yCount == 1) {
						//do turns for both players
						players[(game.getTurn() - 1) * -1].doTurn(posX, posY);
						
						//flip the turn for the game
						game.flipTurn();
						
						//editing the game messages to reflect the move made
						event.getChannel().retrieveMessageById(players[(game.getTurn() - 1) * -1].getMsgID(0)).complete().editMessage(game.generateBoards(players[(game.getTurn() - 1) * -1], players[game.getTurn()])).complete();
						Main.jda.getPrivateChannelById(players[game.getTurn()].getTextChannel()).retrieveMessageById(players[game.getTurn()].getMsgID(0)).complete().editMessage(game.generateBoards(players[game.getTurn()], players[(game.getTurn() - 1) * -1])).complete();
					}
				}
			}
			break;
		}

	}	
	
	
	//used to map certain emoji reactions to an integer from 1-20
	public int mapEmoji(String emoji) {
		for(int i = 0; i < 20; i++) {
			if(emotes[i].equals(emoji)) {
				return i;
			}
		}
		return 0;
	}
	

}    
