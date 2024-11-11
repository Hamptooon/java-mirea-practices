package practise3.task2;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Класс, представляющий окно обслуживания
class Window {
    private int id;
    private PersonType[] allowedTypes;
    private boolean isBusy;
    private Lock lock;

    public Window(int id, PersonType... allowedTypes) {
        this.id = id;
        this.allowedTypes = allowedTypes;
        this.isBusy = false;
        this.lock = new ReentrantLock();
    }

    public boolean canServe(PersonType type) {
        if (allowedTypes.length == 0) return true; // Первое окно принимает всех
        for (PersonType allowedType : allowedTypes) {
            if (allowedType == type) return true;
        }
        return false;
    }

    public boolean tryService(Person person) {
        if (!canServe(person.getType())) {
            return false;
        }

        if (!lock.tryLock()) {
            return false;
        }

        try {
            if (isBusy) {
                return false;
            }
            isBusy = true;
            service(person);
            return true;
        } finally {
            isBusy = false;
            lock.unlock();
        }
    }

    private void service(Person person) {
        try {
            System.out.println(person.getName() + " (" + person.getType() +
                    ") обслуживается в окне " + id);
            // Симулируем время обслуживания
            Thread.sleep(new Random().nextInt(1000) + 500);
            System.out.println(person.getName() + " (" + person.getType() +
                    ") завершил обслуживание в окне " + id);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
