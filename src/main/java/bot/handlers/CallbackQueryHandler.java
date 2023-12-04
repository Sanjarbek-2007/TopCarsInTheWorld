package bot.handlers;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CallbackQueryHandler {
    public static void handleCallBack(Update update, TelegramLongPollingBot bot) {
        if (update.getCallbackQuery().getData().startsWith("/like")) {
            CallBackManage.addToLikes(update,bot);
        }
        if (update.getCallbackQuery().getData().startsWith("/tosum")) {
            CallBackManage.toSum(update,bot);
        }
    }
}
