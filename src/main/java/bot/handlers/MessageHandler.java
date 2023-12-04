package bot.handlers;

import bot.handlers.messages.AllMessages;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageHandler {
    public static void handleMessage(Update update, TelegramLongPollingBot bot) {
        if (update.getMessage().getText().equals("/start")) {
            AllMessages.start(update, bot);
        }
        if (update.getMessage().getText().equals("Show the Cars")) {
            AllMessages.showAll(update, bot);
        }
        if (update.getMessage().getText().equals("Liked cars")) {
            AllMessages.likedCars(update, bot);
        }
        if (update.getMessage().getText().equals("Show the autor of cars")) {
            AllMessages.autor(update.getMessage(), bot);
        }
    }
}
