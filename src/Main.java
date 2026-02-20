import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //№1 Массивы (Работа с парком машин)
        //
        //Создайте массив, представляющий годы выпуска
        //50 случайных машин (от 2000 до 2025).
        //
        //Выведите только машины, выпущенные после 2015 года.
        //
        //Посчитайте средний возраст авто.
        int[] CarYear = new int[50];
        for (int i =0; i<CarYear.length; i++){
            CarYear[i] = (int) (Math.random() * (26)) + 2000;
        }
        for (int j : CarYear) {
            if (j > 2015)
                System.out.println(j);
        }
        OptionalDouble avg = Arrays.stream(CarYear).average();
        System.out.println("средний возраст авто: " + avg.getAsDouble());
        //№2 Коллекции (Управление моделями)
        //
        //Создайте список с названиями моделей машин
        //(например: Toyota Camry, BMW X5).
        //! Могут быть дубликаты
        //
        //Удалите дубликаты, затем отсортируйте модели в обратном алфавитном порядке, выведите на экран, затем сохраните в Set.
        //
        //Реализуйте проверку: если модель содержит слово "Tesla", замените её на "ELECTRO_CAR".
        ArrayList<String> cars = new ArrayList<>(List.of("Tesla", "Toyota Camry", "BMW X5", "Porsche Cayenne", "Porsche Panamera", "Tesla", "BMW X5"));

        List<String> sortedList = cars.stream().distinct().sorted((s1, s2) -> s2.compareTo(s1)).peek(System.out::println).collect(Collectors.toList());
        Set<String> resultSet = new LinkedHashSet<>(sortedList);

        resultSet = resultSet.stream()
                .map(s -> s.replace("Tesla", "ELECTRO_CAR"))
                .collect(Collectors.toSet());


        System.out.println(resultSet);



        List<Car> cars2 = Arrays.asList(
                new Car("VIN1", "Model S", "Tesla", (short)2022, 10000, 80000),
                new Car("VIN2", "Camry", "Toyota", (short)2023, 5000, 35000),
                new Car("VIN3", "X5", "BMW", (short)2021, 25000, 60000),
                new Car("VIN4", "A4", "Audi", (short)2024, 1000, 45000),
                new Car("VIN4", "A1", "Audi", (short)2024, 52000, 45000)
        );

        System.out.println("До сортировки: " + cars2);

        Collections.sort(cars2);

        System.out.println("После сортировки (от новых к старым): " + cars2);


        //№4 Stream API (Анализ автопарка)
        //
        //Дан список машин (List<Car>):
        //
        //Отфильтруйте только машины с пробегом меньше 50.000 км (добавьте поле mileage).
        //
        //Отсортируйте по цене (по убыванию).
        //
        //Выведите топ-3 самые дорогие машины.
        //
        //Посчитайте средний пробег всех машин.
        //
        //Сгруппируйте машины по производителю в Map<String, List<Car>>.
        System.out.printf("////////////////////////\n");
        List<Car> lowMileageCars = cars2.stream()
                .filter( car -> car.getMileage() < 50000)
                .collect(Collectors.toList());
        System.out.println(lowMileageCars);

        System.out.printf("////////////////////////\n");
        List<Car> sortedPrice = cars2.stream()
                .sorted(Comparator.comparingInt(Car::getPrice).reversed())
                .collect(Collectors.toList());
        sortedPrice.forEach(System.out::println);

        System.out.printf("////////////////////////\n");

        List<Car> top3Expensive = cars2.stream()
                .sorted(Comparator.comparingInt(Car::getPrice).reversed())
                .limit(3)
                .collect(Collectors.toList());
        top3Expensive.forEach(System.out::println);

        System.out.printf("////////////////////////\n");

        OptionalDouble averageMileage = cars2.stream().mapToInt(Car::getMileage).average();
        System.out.println(averageMileage.getAsDouble());

        System.out.printf("////////////////////////\n");

        Map<String, List<Car>> carsByManufacturer = cars2.stream()
                .collect(Collectors.groupingBy(Car::getManufacturer));

        carsByManufacturer.forEach((manufacturer, carList) -> {
            System.out.println(manufacturer.toUpperCase() + ":");
            System.out.println("   Всего машин: " + carList.size());
        });




        CarDealership dealership = new CarDealership();
        dealership.startMenu();

    }
}