package bot.handlers.database;

import bot.handlers.enums.CarInfo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class DataBase {
    static DataBase datas = new DataBase();
    private List<CarInfo> cars = FileManager.convertJsonToList("src/main/resources/cars.json");
    public static int likes;
    private  List<CarInfo> likedCars = new ArrayList<>();

    public static void setCars(CarInfo car) {


        datas.likedCars.add(car);
    }

    public List<CarInfo> getLikedCars() {
        return likedCars;
    }

    public List<CarInfo> getCars() {
        return cars;
    }
}
