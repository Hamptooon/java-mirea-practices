package practise3.task3;

import java.util.Random;

public class ClinicSimulation {
    private static final int TOTAL_PATIENTS = 20;

    public static void main(String[] args) throws InterruptedException {
        Clinic clinic = new Clinic();

        // Создаем и запускаем терапевта и кабинет МРТ
        Therapist therapist = new Therapist(clinic);
        MRTRoom mrtRoom = new MRTRoom(clinic);

        therapist.start();
        mrtRoom.start();

        // Создаем и запускаем пациентов
        Thread[] patientThreads = new Thread[TOTAL_PATIENTS];
        for (int i = 0; i < TOTAL_PATIENTS; i++) {
            patientThreads[i] = new Patient(i + 1, clinic);
            patientThreads[i].start();
            // Небольшая задержка между приходом пациентов
            Thread.sleep(new Random().nextInt(1000));
        }

        // Ждем завершения всех пациентов
        for (Thread patient : patientThreads) {
            patient.join();
        }

        // Даем время на завершение обслуживания последних пациентов
        Thread.sleep(5000);

        // Завершаем работу терапевта и МРТ
        therapist.interrupt();
        mrtRoom.interrupt();

        System.out.println("\nМаксимальная длина очереди: " + clinic.getMaxQueueSize());
    }
}