package BattleShip;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;





public class Main {
	public static String prefix = "~";
	private static Player players[] = new Player[2];
	private final static String DISCORD_API_KEY = "Nzg2ODEyNjczMzk2MDQ3ODcy.X9L2Zw.H-bexPI6ROFnxz9zrhlXGOCSDcM";
	
	public static void main(String[] args) {	
		
		JDA jda = null;
		JDABuilder builder = null;
		builder = JDABuilder.createDefault(DISCORD_API_KEY);

		Commands listener = new Commands();
		try {
			builder.addEventListeners(listener);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	
		try {
			jda = builder.build();
		}catch(LoginException e) {
			e.printStackTrace();
		}
		
		jda.getPresence().setPresence(Activity.watching("Finding GPUs"), true);

	}	
}
