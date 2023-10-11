import java.util.*;
import java.util.stream.Collectors;

class Laptop {
    String brand;
    int ram;
    int hdd;
    String os;
    String color;

    public Laptop(String brand, int ram, int hdd, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", hdd=" + hdd +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class main{
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", 15, 512, "Windows", "Black"));
        laptops.add(new Laptop("Apple", 7, 256, "MacOS", "Silver"));
        laptops.add(new Laptop("Lenovo", 31, 1024, "Windows", "Gray"));

        Map<String, Object> filters = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите критерии для фильтрации:");
        System.out.println("0 - ОЗУ");
        System.out.println("1 - Объем ЖД");
        System.out.println("2 - Операционная система");
        System.out.println("3 - Цвет");
        System.out.println("-1 - Завершить выбор");

        int choice;
        while (true) {
            choice = scanner.nextInt();
            if (choice == -1) {
                break;
            }
            switch (choice) {
                case 0:
                    System.out.println("Минимальный объем ОЗУ?");
                    filters.put("ram", scanner.nextInt());
                    break;
                case 1:
                    System.out.println("Минимальный объем ЖД?");
                    filters.put("hdd", scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Операционная система?");
                    filters.put("os", scanner.next());
                    break;
                case 3:
                    System.out.println("Цвет?");
                    filters.put("color", scanner.next());
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        Set<Laptop> filteredLaptops = laptops.stream()
                .filter(laptop -> filters.getOrDefault("ram", -1) instanceof Integer && laptop.ram >= (int) filters.getOrDefault("ram", 0))
                .filter(laptop -> filters.getOrDefault("hdd", -1) instanceof Integer && laptop.hdd >= (int) filters.getOrDefault("hdd", 0))
                .filter(laptop -> filters.getOrDefault("os", "").equals("") || laptop.os.equalsIgnoreCase((String) filters.getOrDefault("os", "")))
                .filter(laptop -> filters.getOrDefault("color", "").equals("") || laptop.color.equalsIgnoreCase((String) filters.getOrDefault("color", "")))
                .collect(Collectors.toSet());

        System.out.println("Отфильтрованные ноутбуки:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }
}