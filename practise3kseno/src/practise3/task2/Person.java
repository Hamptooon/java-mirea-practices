package practise3.task2;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

enum PersonType {
    YOUNG,      // Молодые
    ELDERLY,    // Пожилые
    BUSINESS    // Бизнесмены
}

// Класс, представляющий гражданина
class Person extends Thread {
    private PersonType type;
    private static AtomicInteger totalByType[] = new AtomicInteger[]{
            new AtomicInteger(0),
            new AtomicInteger(0),
            new AtomicInteger(0)
    };
    private static AtomicInteger angryByType[] = new AtomicInteger[]{
            new AtomicInteger(0),
            new AtomicInteger(0),
            new AtomicInteger(0)
    };

    public Person(PersonType type, int id) {
        this.type = type;
        this.setName("Person-" + id);
        totalByType[type.ordinal()].incrementAndGet();
    }

    public PersonType getType() {
        return type;
    }

    @Override
    public void run() {
        Random random = new Random();
        Window[] windows = App.getWindows();

        // Выбираем случайное окно
        int windowIndex = random.nextInt(windows.length);
        Window selectedWindow = windows[windowIndex];

        // Пытаемся получить обслуживание
        if (!selectedWindow.tryService(this)) {
            // Если не получилось - уходим разгневанными
            angryByType[type.ordinal()].incrementAndGet();
            System.out.println(getName() + " (" + type + ") ушел разгневанным из окна " + windowIndex);
        }
    }

    // Метод для получения статистики
    public static double getAngryPercentage(PersonType type) {
        int total = totalByType[type.ordinal()].get();
        int angry = angryByType[type.ordinal()].get();
        return total == 0 ? 0 : (angry * 100.0) / total;
    }
}

