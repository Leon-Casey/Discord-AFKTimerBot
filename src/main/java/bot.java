import Commands.AFK.AFKController;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class bot {


    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault("ODQ5MzAwNzM1MjkxMTYyNjc0.YLZK8g.ux1zl2t2DJhha5ru8GgRPZ3qHdM").build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.watching("the clock | e.g. !afk 10m"));

        jda.addEventListener(new AFKController());
    }
}
