package practise3.task3;

import java.util.Random;

public class MRTRoom extends Thread {
    private Clinic clinic;
    private Random random;

    public MRTRoom(Clinic clinic) {
        this.clinic = clinic;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                // Получаем пациента от терапевта
                Patient patient = clinic.getNextMRTPatient();
                if (patient == null) continue;

                System.out.println(patient + " начал МРТ обследование");
                // Симулируем время МРТ
                Thread.sleep(random.nextInt(2000) + 1000);
                System.out.println(patient + " закончил МРТ обследование");

                // Сигнализируем об окончании МРТ
                clinic.finishMRT();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
