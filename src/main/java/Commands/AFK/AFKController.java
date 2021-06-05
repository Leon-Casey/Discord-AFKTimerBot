package Commands.AFK;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

public class AFKController extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().contains("!afk ") || e.getMessage().getContentRaw().contains("!AFK ")) {
            try {
                String msgFiltered = e.getMessage().getContentRaw().replaceAll("[^a-zA-Z0-9]", "");
                String msg = msgFiltered.substring(msgFiltered.lastIndexOf("k") + 1);
                String[] split = msg.split("(?<=[0-9])(?=[a-zA-Z])");

                if (split.length > 1) {
                    if(split[1].length() > 1) {
                        return;
                    }
                    e.getMessage().addReaction("U+2705").queue();
                }
                else {
                    return;
                }
                AtomicInteger afkTime = new AtomicInteger(Integer.parseInt(split[0]));
                String sMH = split[1];

                JDA jda = Objects.requireNonNull(e.getMember()).getJDA();

                jda.retrieveUserById(e.getAuthor().getIdLong()).queue(user -> {
                    int period = 1000;
                    String secsMinsHours = "";

                    if (sMH.equalsIgnoreCase("s")) {
                        secsMinsHours = "secs";
                    } else if (sMH.equalsIgnoreCase("m")) {
                        secsMinsHours = "mins";
                        period = 1000 * 60;
                    } else if (sMH.equalsIgnoreCase("h")) {
                        secsMinsHours = "hours";
                        period = (1000 * 60);
                    }

                    if (afkTime.intValue() == 1) {
                        secsMinsHours = secsMinsHours.substring(0, secsMinsHours.length() - 1);
                    }
                    String finalSecsMinsHours = secsMinsHours;
                    int finalPeriod = period;
                    e.getChannel().sendMessage(user.getName() + " AFK timer - " + afkTime.get() + " " + secsMinsHours).queue(message -> {
                        Timer timer = new Timer();
                        timer.scheduleAtFixedRate(new AFKEdits(timer, message, user, afkTime.get(), finalSecsMinsHours), 0, finalPeriod);
                    });
                });
            } catch (Exception ex) {
                return;
            }
        }
    }
}
