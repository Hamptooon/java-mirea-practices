package practise3.task1;

import java.util.concurrent.CopyOnWriteArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class App {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> listOfNumbers = new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3, 4});

        Runnable classOne = new FirstClass(listOfNumbers);
        Runnable classTwo = new SecondClass(listOfNumbers);

        Thread threadClassOne = new Thread(classOne);
        Thread threadClassTwo = new Thread(classTwo);

        threadClassOne.start();
        threadClassTwo.start();
    }
}