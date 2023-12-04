package bot.handlers.database;

import bot.handlers.enums.CarInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileManager {

    /*
    private static List<CarInfo> carList= new ArrayList<>();


    public static void write(){
        carList.add(new CarInfo("src/main/resources/bmw.jpg",  "BMW M5\n" , """
                Engine: 4.4-liter TwinPower Turbo V8
                Power: Around 825 horsepower
                Acceleration 0-100 km/h (0-60 mph): Approximately 2.7-3.0 seconds
                Top Speed: Typically limited to 370 km/h """, "$ 94 800"));
        carList.add(new CarInfo("src/main/resources/bugatti.jpg",  "Bugatti Chiron\n" , """
                Engine: 8.0-liter quad-turbocharged W16
                Power: Approximately 1,500 horsepower
                Acceleration 0-60 mph (0-100 km/h): Around 2.5 seconds
                Top Speed: Limited to 490 km/h
                Transmission: 7-speed dual-clutch automatic transmission
                Braking: Equipped with high-performance brakes for rapid deceleration
                Weight: 2,000 kilograms)""", "$ 1 806 800"));
        carList.add(new CarInfo("src/main/resources/gclass.jpg",  "Mercedes G63\n" , """
                Engine: The G-Class comes with V8 gasoline and diesel engines.
                Power:  577 horsepower.
                Acceleration 0-60 mph (0-100 km/h): Roughly 4.5 to 5.3 seconds 
                Top Speed: 210-240 km/h
                Transmission: Most G-Class models come with a 9-speed automatic transmission.""", "$ 300 200"));
        carList.add(new CarInfo("src/main/resources/bmw.jpg",  "BMW M5\n" , """
                Engine: 4.4-liter TwinPower Turbo V8
                Power: Around 825 horsepower
                Acceleration 0-100 km/h (0-60 mph): Approximately 2.7-3.0 seconds
                Top Speed: Typically limited to 370 km/h """, "$ 94800"));
        carList.add(new CarInfo("src/main/resources/jiguli.jpg",  "JIGULI 07\n" , """
                Engine: 6.8-liter TwinPower Turbo V8
                Power: Around 1200 horsepower
                Acceleration 0-100 km/h (0-60 mph): Approximately 1.9-2.2 seconds
                Top Speed: Typically limited to 370 km/h """, "$ 15 050 620"));
        carList.add(new CarInfo("src/main/resources/kyoniseg.jpg",  "BMW M5\n" , """
                Engine: 5.0-liter twin-turbocharged V8
                Power Output: Around 1.360 hourse power
                Acceleration 0-60 mph (0-100 km/h): Approximately 2.3-2.4 seconds
                Top Speed: 510 km/h 
                Transmission: 7-speed dual-clutch automatic
                Weight: About 1,400-1,500 kg (3,100-3,300 lbs) """, "$ 1 200 800"));
        carList.add(new CarInfo("src/main/resources/mercedesbanan.jpg",  "Mercedes banan\n" , """
                Engine: 3.8-liter TwinPower Turbo V8
                Power: Around 600 horsepower
                Acceleration 0-100 km/h (0-60 mph): Approximately 2.7-3.0 seconds
                Top Speed: Typically limited to 340 km/h """, "$ 80800"));
        carList.add(new CarInfo("src/main/resources/mustang.jpg",  "Ford Mustang \n" , """
                Engine: The Mustang can come with a 5.0-liter V8 engine.
                Power: The power output can range to over 450 horsepower for the V8 models.
                Acceleration 0-60 mph:  4-5 seconds.
                Top Speed: Typically, the top speed islimited to around 250-290 km/h""", "$ 60800"));
        carList.add(new CarInfo("src/main/resources/porshche.jpg",  "Porshche 911\n" , """
                Engine: 3.0-liter twin-turbocharged flat-six
                Power Output: Around 443 horsepower
                Acceleration (0-60 mph): Approximately 3.5 seconds (with Sport Chrono Package)
                Top Speed: Over 190 mph (305 km/h)
                Transmission: Typically equipped with an 8-speed PDK (Porsche Doppelkupplung) automatic transmission
                """, "$ 290 300"));

        String json = convertListToJson(carList);

        // Путь к файлу для сохранения JSON
        String filePath = "src/main/resources/cars.json";

        // Сохранение JSON в файл
        saveJsonToFile(filePath, json);
    }  private static String convertListToJson(List<CarInfo> carList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(carList);
    }

     */

    // Метод для сохранения JSON в файл
    private static void saveJsonToFile(String filePath, String json) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(json);
            System.out.println("Car information successfully saved to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для чтения JSON из файла
    private static String readJsonFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    // Метод для преобразования JSON в список объектов

    public static List<CarInfo>     convertJsonToList(String jsonFilePath) {
        String json = null;
        try {
            json = Files.readString(Path.of(jsonFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        Type carListType = new TypeToken<List<CarInfo>>(){}.getType();

        return gson.fromJson(json, carListType);
    }

}
