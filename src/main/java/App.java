import bot.handlers.UpdateHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App {
    public static void main(String[] args) throws TelegramApiException {

        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new TelegramLongPollingBot("6346990564:AAGzqTgE7W-tGfvIoLQUQXaa1QOVY5-RCJs") {
//        api.registerBot(new TelegramLongPollingBot("6845768790:AAHomwlq6K1vwc4aL1gvDO3VtaviV2Y8Asg") {


            @Override
            public void onUpdateReceived(Update update) {
                UpdateHandler.handle(update, this);
            }


            @Override
            public String getBotUsername() {
//                return "com_it_hub_bot";
                return "the_best_cars_bot";
            }
        });
    }
}