package practise3.task3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class Clinic {
    private Queue<Patient> mainQueue;
    private Queue<Patient> mrtQueue;
    private AtomicInteger currentQueueSize;
    private AtomicInteger maxQueueSize;

    public Clinic() {
        this.mainQueue = new LinkedList<>();
        this.mrtQueue = new LinkedList<>();
        this.currentQueueSize = new AtomicInteger(0);
        this.maxQueueSize = new AtomicInteger(0);
    }

    public synchronized void addToQueue(Patient patient) throws InterruptedException {
        mainQueue.add(patient);
        int size = currentQueueSize.incrementAndGet();
        maxQueueSize.set(Math.max(maxQueueSize.get(), size));
        System.out.println(patient + " встал в очередь. Текущая длина очереди: " + size);
        notifyAll();
    }

    public synchronized Patient getNextPatient() throws InterruptedException {
        while (mainQueue.isEmpty()) {
            wait();
        }
        Patient patient = mainQueue.poll();
        currentQueueSize.decrementAndGet();
        return patient;
    }

    public synchronized void sendToMRT(Patient patient) throws InterruptedException {
        mrtQueue.add(patient);
        notifyAll();
    }

    public synchronized Patient getNextMRTPatient() throws InterruptedException {
        while (mrtQueue.isEmpty()) {
            wait();
        }
        return mrtQueue.poll();
    }

    public synchronized void finishMRT() {
        notifyAll();
    }

    public int getMaxQueueSize() {
        return maxQueueSize.get();
    }
}
