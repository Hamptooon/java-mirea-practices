package practise3.task3;

import java.util.Random;

class Therapist extends Thread {
    private Clinic clinic;
    private Patient currentPatient;
    private Random random;

    public Therapist(Clinic clinic) {
        this.clinic = clinic;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                // Получаем следующего пациента из очереди
                currentPatient = clinic.getNextPatient();
                if (currentPatient == null) continue;

                System.out.println(currentPatient + " начал прием у терапевта");
                // Симулируем время приема у терапевта
                Thread.sleep(random.nextInt(1000) + 500);
                System.out.println(currentPatient + " закончил прием у терапевта");

                // Отправляем пациента на МРТ
                clinic.sendToMRT(currentPatient);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}