package bot.handlers.enums;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public  class CarInfo {
    private String photoPath;
    private String carName;
    private String carDescription;
    private String price;

    public CarInfo(String photoPath, String carName, String carDescription, String price) {
        this.photoPath = photoPath;
        this.carName = carName;
        this.carDescription = carDescription;
        this.price = price;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "photoPath='" + photoPath + '\'' +
                ", carName='" + carName + '\'' +
                ", carDescription='" + carDescription + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
