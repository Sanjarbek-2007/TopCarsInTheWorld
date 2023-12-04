package bot.handlers;

import bot.handlers.database.DataBase;
import bot.handlers.enums.CarInfo;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class CallBackManage {
       public static void addToLikes(Update update, TelegramLongPollingBot bot ) {
        String data = update.getCallbackQuery().getData();
        DataBase base = new DataBase();
        for (CarInfo car : base.getCars()) {
            if (car.getCarName().endsWith(data.substring(7))) {
                DataBase.setCars(car);
                break;
            }
        }

           InlineKeyboardMarkup markup = InlineKeyboardMarkup.builder().
                   keyboardRow(List.of(InlineKeyboardButton.builder().text( "Ok "+"❤\uFE0F").build(),
                           InlineKeyboardButton.builder().text("Price in SUM").callbackData("/tosum" ).build())).build();

           EditMessageReplyMarkup reply = EditMessageReplyMarkup.builder().messageId(update.getMessage().getMessageId())
                   .chatId(update.getMessage().getChatId())
                   .replyMarkup(markup).build();
           try {
               bot.execute(reply);
           } catch (TelegramApiException e) {
               throw new RuntimeException(e);
           }


       }
    public static void toSum(Update update, TelegramLongPollingBot bot ) {
        String data = update.getCallbackQuery().getData();
        String model = data.substring(7,data.length()-1);
        long price = 1;
        DataBase base = new DataBase();
        for (CarInfo car : base.getCars()) {
            if (car.getCarName().equals(model)) {
                price= Long.parseLong(car.getPrice().substring(1));
            }
        }
        AnswerCallbackQuery answer = new  AnswerCallbackQuery();
        answer.setText(model+" price: "+ price + " : " + price*12330);
        answer.setCallbackQueryId(update.getCallbackQuery().getId());


        try {
            bot.execute(answer);
        } catch (TelegramApiException e) {
             e.printStackTrace();
        }
    }
}
