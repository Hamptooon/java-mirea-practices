package practise3.task1;

import java.util.concurrent.CopyOnWriteArrayList;

public class SecondClass implements Runnable {
    CopyOnWriteArrayList<Integer> listOfNumbers;

    public SecondClass(CopyOnWriteArrayList<Integer> listOfNumbers) {
        this.listOfNumbers = listOfNumbers;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Current list nums: "  + listOfNumbers);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
