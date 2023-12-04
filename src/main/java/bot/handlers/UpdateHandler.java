package bot.handlers;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UpdateHandler {
    public static void handle(Update update, TelegramLongPollingBot bot ){
        if (update.hasMessage()) {
            MessageHandler.handleMessage(update,bot);
        }
        if (update.hasCallbackQuery()) {
            CallbackQueryHandler.handleCallBack(update,bot);
        }
    }
}
