package xyz.gnarbot.gnar.commands.executors.polls;

import xyz.gnarbot.gnar.commands.handlers.Command;
import xyz.gnarbot.gnar.commands.handlers.CommandExecutor;
import xyz.gnarbot.gnar.utils.Note;

import java.util.List;

// TODO recode command.
@Command(aliases = "poll",
        usage = "(argument)",
        description = "Do poll-y stuff!",
        showInHelp = false)
public class PollCommand extends CommandExecutor {
    @Override
    public void execute(Note note, List<String> args) {
        if (args.isEmpty() || (!args.isEmpty() && args.get(0).equalsIgnoreCase("help"))) {
            String reply = "Poll System Help!~\n {} = Required Arguments  |  () = Optional Arguments```ini\n" +
                    "[_poll help] This list\n" + "[_poll startyesno {time} {question}] Start a Yes/No Poll for " +
                    "\"time\" minutes." + "\n```";
            note.info(reply);
        } else if (args.size() > 0) {
            if (args.get(0).equalsIgnoreCase("startyesno") && args.size() > 1) {
                int time = 15;

                try {
                    time = Integer.parseInt(args.get(1).trim());
                } catch (NumberFormatException ignore) {}

                args.set(0, "");
                args.set(1, "");
                String q = "";
                for (String s : args) {
                    if (!s.equalsIgnoreCase("")) {
                        q += s + " ";
                    }
                }
                q = q.trim();
                PollManager.registerPoll(new YesNoPoll(note, q, time));
            }
        }
    }
}
