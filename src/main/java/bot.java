import Commands.AFK.AFKController;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class bot {


    public static void main(String[] args) throws LoginException {
//        JDA jda = JDABuilder.createDefault(config.get("TOKEN")).build();
        JDA jda = JDABuilder.createDefault(System.getenv("TOKEN")).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.watching("the clock | e.g. !afk 10m"));

        jda.addEventListener(new AFKController());
    }
}
