package practise3.task1;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class FirstClass implements Runnable{
    CopyOnWriteArrayList<Integer> listOfNumbers;

    public FirstClass(CopyOnWriteArrayList<Integer> listOfNumbers) {
        this.listOfNumbers = listOfNumbers;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 20; i++) {
            try {
                int number = new Random().nextInt(10);
                listOfNumbers.add(number);
                System.out.println("Added num: " + number);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
