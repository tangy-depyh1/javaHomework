import java.util.*;
import java.util.stream.Collectors;

class CarDealership {
    private List<CarV2> cars;
    private Set<String> vinSet;

    public CarDealership() {
        cars = new ArrayList<>();
        vinSet = new HashSet<>();
    }

    public boolean addCar(CarV2 car) {
        if (vinSet.contains(car.getVIN())) {
            System.out.println("Ошибка: VIN " + car.getVIN() + " уже есть");
            return false;
        }
        cars.add(car);
        vinSet.add(car.getVIN());
        System.out.println("Машина добавлена");
        return true;
    }

    public List<CarV2> findCarsByManufacturer(String manufacturer) {
        return cars.stream()
                .filter(c -> c.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }

    public double averagePriceByType(CarType type) {
        return cars.stream()
                .filter(c -> c.getType() == type)
                .mapToInt(CarV2::getPrice)
                .average()
                .orElse(0);
    }

    public List<CarV2> getCarsSortedByYear() {
        return cars.stream()
                .sorted((c1, c2) -> c2.getYearReleased() - c1.getYearReleased())
                .collect(Collectors.toList());
    }

    public Map<CarType, Long> countCarsByType() {
        return cars.stream()
                .collect(Collectors.groupingBy(CarV2::getType, Collectors.counting()));
    }

    public Map<String, CarV2> getOldestAndNewestCar() {
        Map<String, CarV2> result = new HashMap<>();
        if (cars.isEmpty()) return result;

        CarV2 oldest = cars.get(0);
        CarV2 newest = cars.get(0);

        for (CarV2 c : cars) {
            if (c.getYearReleased() < oldest.getYearReleased()) oldest = c;
            if (c.getYearReleased() > newest.getYearReleased()) newest = c;
        }

        result.put("oldest", oldest);
        result.put("newest", newest);
        return result;
    }

    public void showAllCars() {
        if (cars.isEmpty()) {
            System.out.println("Нет машин");
            return;
        }
        for (CarV2 c : cars) {
            System.out.println(c);
        }
    }

    public void initDemoData() {
        addCar(new CarV2("VIN1", "Model S", "Tesla", (short)2022, 15000, 80000, CarType.ELECTRIC));
        addCar(new CarV2("VIN2", "Camry", "Toyota", (short)2023, 45000, 35000, CarType.SEDAN));
        addCar(new CarV2("VIN3", "X5", "BMW", (short)2021, 75000, 60000, CarType.SUV));
        addCar(new CarV2("VIN4", "A4", "Audi", (short)2024, 5000, 45000, CarType.SEDAN));
        addCar(new CarV2("VIN5", "Civic", "Honda", (short)2020, 120000, 20000, CarType.HATCHBACK));
        addCar(new CarV2("VIN6", "Mustang", "Ford", (short)2022, 30000, 55000, CarType.COUPE));
        addCar(new CarV2("VIN7", "RAV4", "Toyota", (short)2024, 1000, 38000, CarType.SUV));
    }

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nМЕНЮ:");
            System.out.println("1. Показать все машины");
            System.out.println("2. Добавить машину");
            System.out.println("3. Найти по производителю");
            System.out.println("4. Средняя цена по типу");
            System.out.println("5. Сортировка по году");
            System.out.println("6. Статистика по типам");
            System.out.println("7. Самая старая/новая");
            System.out.println("8. Загрузить демо");
            System.out.println("9. Выход");
            System.out.print("Выберите: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    showAllCars();
                }
                else if (choice == 2) {
                    System.out.print("VIN: ");
                    String vin = scanner.nextLine();
                    System.out.print("Модель: ");
                    String model = scanner.nextLine();
                    System.out.print("Производитель: ");
                    String man = scanner.nextLine();
                    System.out.print("Год: ");
                    short year = Short.parseShort(scanner.nextLine());
                    System.out.print("Пробег: ");
                    int mil = Integer.parseInt(scanner.nextLine());
                    System.out.print("Цена: ");
                    int price = Integer.parseInt(scanner.nextLine());

                    System.out.println("Тип: 1-SEDAN 2-SUV 3-ELECTRIC 4-HATCHBACK");
                    int t = Integer.parseInt(scanner.nextLine());
                    CarType type = CarType.SEDAN;
                    if (t == 2) type = CarType.SUV;
                    else if (t == 3) type = CarType.ELECTRIC;
                    else if (t == 4) type = CarType.HATCHBACK;

                    addCar(new CarV2(vin, model, man, year, mil, price, type));
                }
                else if (choice == 3) {
                    System.out.print("Производитель: ");
                    String man = scanner.nextLine();
                    List<CarV2> found = findCarsByManufacturer(man);
                    if (found.isEmpty()) {
                        System.out.println("Не найдено");
                    } else {
                        for (CarV2 c : found) System.out.println(c);
                    }
                }
                else if (choice == 4) {
                    System.out.println("Тип: 1-SEDAN 2-SUV 3-ELECTRIC");
                    int t = Integer.parseInt(scanner.nextLine());
                    CarType type = CarType.SEDAN;
                    if (t == 2) type = CarType.SUV;
                    else if (t == 3) type = CarType.ELECTRIC;

                    double avg = averagePriceByType(type);
                    System.out.println("Средняя цена: " + avg);
                }
                else if (choice == 5) {
                    List<CarV2> sorted = getCarsSortedByYear();
                    for (CarV2 c : sorted) System.out.println(c);
                }
                else if (choice == 6) {
                    Map<CarType, Long> stats = countCarsByType();
                    for (CarType ct : stats.keySet()) {
                        System.out.println(ct + ": " + stats.get(ct));
                    }
                }
                else if (choice == 7) {
                    Map<String, CarV2> res = getOldestAndNewestCar();
                    if (!res.isEmpty()) {
                        System.out.println("Старая: " + res.get("oldest"));
                        System.out.println("Новая: " + res.get("newest"));
                    }
                }
                else if (choice == 8) {
                    initDemoData();
                }
                else if (choice == 9) {
                    running = false;
                    System.out.println("Все собственно");
                }
                else {
                    System.out.println("Неверный выбор");
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода");
            }
        }
        scanner.close();
    }
}