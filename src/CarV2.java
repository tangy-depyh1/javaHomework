import java.util.Objects;

enum CarType {
    SEDAN, SUV, ELECTRIC, HATCHBACK, COUPE, CONVERTIBLE, WAGON, MINIVAN
}

public class CarV2 implements Comparable<Car> {
    private String VIN;
    private String model;
    private String manufacturer;
    private short yearReleased;
    private int mileage;
    private int price;
    private CarType type;

    public CarV2(String VIN, String model, String manufacturer,
               short yearReleased, int mileage, int price, CarType type) {
        this.VIN = VIN;
        this.model = model;
        this.manufacturer = manufacturer;
        this.yearReleased = yearReleased;
        this.mileage = mileage;
        this.price = price;
        this.type = type;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public short getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(short yearReleased) {
        this.yearReleased = yearReleased;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Car car = (Car) obj;
        return Objects.equals(VIN, car.getVIN());
    }

    @Override
    public int hashCode() {
        return Objects.hash(VIN);
    }

    @Override
    public int compareTo(Car other) {
        return other.getYearReleased() - this.yearReleased;
    }

    @Override
    public String toString() {
        return String.format("Car{VIN='%s', model='%s', manufacturer='%s', year=%d, mileage=%d, price=%d, type=%s}",
                VIN, model, manufacturer, yearReleased, mileage, price, type);
    }
}