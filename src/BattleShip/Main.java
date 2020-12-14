package BattleShip;

import java.util.List;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;





public class Main {
	public static String prefix = "~";
	private final static String DISCORD_API_KEY = "Nzg2ODEyNjczMzk2MDQ3ODcy.X9L2Zw.H-bexPI6ROFnxz9zrhlXGOCSDcM";
	private static JDA jda = null;
	private static JDABuilder builder = JDABuilder.createDefault(DISCORD_API_KEY);
	
	public static void main(String[] args) {	
		


		//adding a listener
		try {
			builder.addEventListeners(new Commands());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//building JDA
		try {
			jda = builder.setChunkingFilter(ChunkingFilter.ALL) // enable member chunking for all guilds
			          .setMemberCachePolicy(MemberCachePolicy.ALL) // ignored if chunking enabled
			          .enableIntents(GatewayIntent.GUILD_MEMBERS)
			          .build();
		}catch(LoginException e) {
			e.printStackTrace();
		}

		
		jda.getPresence().setPresence(Activity.playing("Battleship"), true);

	}	
}
