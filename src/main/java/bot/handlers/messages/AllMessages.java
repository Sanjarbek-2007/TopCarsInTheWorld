package bot.handlers.messages;

import bot.handlers.database.DataBase;
import bot.handlers.enums.CarInfo;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AllMessages {
    public static void start(Update message, TelegramLongPollingBot bot){

        ReplyKeyboardMarkup mar = ReplyKeyboardMarkup.builder()
                .keyboardRow(new KeyboardRow(List.of(KeyboardButton.builder().text("Show the Cars").build()
                        , KeyboardButton.builder().text("Show the autor of cars").build())))
                .keyboardRow(new KeyboardRow(List.of(KeyboardButton.builder().text("Liked cars").build())))
                .resizeKeyboard(true).build();
        SendMessage mess = SendMessage.builder().chatId(message.getMessage().getChatId()).text("Choose the option : ").replyMarkup(mar).build();
        try {
            bot.execute(mess);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
    public static void showAll(Update update, TelegramLongPollingBot bot ){
        DataBase dataBase = new DataBase();
        List<CarInfo> cars = dataBase.getCars();

        for (CarInfo car : cars) {
            selectCar(car,update,bot);
        }

    }

    public static void selectCar(CarInfo car, Update update, TelegramLongPollingBot bot){

        InlineKeyboardMarkup markup = InlineKeyboardMarkup.builder().
                keyboardRow(List.of(InlineKeyboardButton.builder().callbackData("/like "+car.getCarName() ).text( "❤\uFE0F").build(),
                        InlineKeyboardButton.builder().text("Price in SUM").callbackData("/tosum" /*+ car.getCarName()*/).build())).build();
        SendPhoto message = SendPhoto.builder()
                .chatId(update.getMessage().getChatId())
                .photo(new InputFile(new File(car.getPhotoPath())))
                .caption(car.getCarName() + car.getCarDescription() + "\n\n" + car.getPrice())
                .replyMarkup(markup)
                .build();

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
//             Обработка исключения - можно вывести сообщение об ошибке или залогировать
            e.printStackTrace();
        }


    }

    public static void likedCars(Update update, TelegramLongPollingBot bot ){

        DataBase base = new DataBase();
        for (CarInfo likedCar : base.getLikedCars()) {
            selectCar(likedCar,update,bot);
        }

        try {
            bot.execute(new SendMessage(update.getMessage().getChatId().toString(),"Liked cars"));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        for (CarInfo car : base.getLikedCars()) {
            selectCar(car,update,bot);
        }
    }
    public static void autor(Message message, TelegramLongPollingBot bot){
        InlineKeyboardMarkup ma = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(InlineKeyboardButton.builder().text("SANJARBEK").url("https://www.instagram.com/godamn_out/").build())).build();
        SendMessage send = SendMessage.builder().replyMarkup(ma).text("INSTAGRAM").chatId(message.getChatId()).build();
        try {
            bot.execute(send);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    public static InlineKeyboardMarkup likeKeyboard(CarInfo car) {
        InlineKeyboardMarkup markup = InlineKeyboardMarkup.builder().
                keyboardRow(List.of(InlineKeyboardButton.builder().callbackData("/liked").text(DataBase.likes + "❤\uFE0F").build())).build();

        return markup;
    }
    public static InlineKeyboardMarkup likeKeyboard() {
        InlineKeyboardMarkup markup = InlineKeyboardMarkup.builder().
                keyboardRow(List.of(InlineKeyboardButton.builder().text(DataBase.likes + "❤\uFE0F").build())).build();

        return markup;

    }
}
