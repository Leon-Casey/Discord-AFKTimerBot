package Commands.AFK;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.util.Timer;
import java.util.TimerTask;

public class AFKEdits extends TimerTask {
    Message message;
    User user;
    int afkTime;
    String secsMinsHours;

    int oldValue;

    Timer timer;

    public AFKEdits(Timer timer, Message message, User user, int afkTime, String secsMinsHours) {
        this.timer = timer;
        this.message = message;
        this.user = user;
        this.afkTime = afkTime;
        this.secsMinsHours = secsMinsHours;

        this.oldValue = afkTime;
    }

    @Override
    public void run() {
        if(oldValue == 0) {
            timer.cancel();
            timer.purge();
            message.editMessage("AFK Timer Over <@" + user.getId() + ">").queue();
            return;
        }
        message.editMessage("<@" + user.getId() + "> AFK timer - " + (oldValue - 1) + " " + secsMinsHours).queue();
        oldValue--;
    }
}
