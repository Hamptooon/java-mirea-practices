package practise3.task2;

import java.util.Random;

public class App {
    private static Window[] windows;
    private static final int TOTAL_CLIENTS = 30;

    public static Window[] getWindows() {
        return windows;
    }

    public static void main(String[] args) throws InterruptedException {
        // Инициализация окон
        windows = new Window[]{
                new Window(0), // Первое окно принимает всех
                new Window(1, PersonType.ELDERLY), // Второе окно только для пожилых
                new Window(2, PersonType.BUSINESS) // Третье окно только для бизнесменов
        };

        // Создаем и запускаем потоки клиентов
        Thread[] clients = new Thread[TOTAL_CLIENTS];
        Random random = new Random();

        for (int i = 0; i < TOTAL_CLIENTS; i++) {
            PersonType type = PersonType.values()[random.nextInt(PersonType.values().length)];
            clients[i] = new Person(type, i);
            clients[i].start();
        }

        // Ждем завершения всех потоков
        for (Thread client : clients) {
            client.join();
        }

        // Выводим статистику
        System.out.println("\nСтатистика по недовольным клиентам:");
        for (PersonType type : PersonType.values()) {
            System.out.printf("%s: %.1f%%\n", type, Person.getAngryPercentage(type));
        }
    }
}