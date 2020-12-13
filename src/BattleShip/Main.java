package BattleShip;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;





public class Main {
	public static String prefix = "~";
	

	public static void main(String[] args) {	
		JDA jda = null;
		JDABuilder builder = null;
		
		builder = JDABuilder.createDefault("Nzg2ODEyNjczMzk2MDQ3ODcy.X9L2Zw.htuzU5cxFRDOIqvbJE-CGny81Z0");

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
