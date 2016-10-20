package by.epam.tr.synchronizers.exchanger;

import java.util.concurrent.Exchanger;

public class Car implements Runnable {
	private int number;
	private String dep;
	private String dest;
	private String[] parcels;
	private Exchanger<String> exchanger;

	public Car(int number, String departure, String destination, String[] parcels, Exchanger<String> exchanger) {
        this.number = number;
        this.dep = departure;
        this.dest = destination;
        this.parcels = parcels;
        this.exchanger = exchanger;
    }

	@Override
	public void run() {
		try {
			System.out.printf("В грузовик №%d погрузили: %s и %s.\n", number, parcels[0], parcels[1]);
			System.out.printf("Грузовик №%d выехал из пункта %s в пункт %s.\n", number, dep, dest);
			Thread.sleep(1000 + (long) Math.random() * 5000);
			System.out.printf("Грузовик №%d приехал в пункт Е.\n", number);
			parcels[1] = exchanger.exchange(parcels[1]);// При вызове exchange()
														// поток блокируется и
														// ждет
			// пока другой поток вызовет exchange(), после этого произойдет
			// обмен посылками и обы потока продолжат выполнение
			System.out.printf("В грузовик №%d переместили посылку для пункта %s.\n", number, dest);
			Thread.sleep(1000 + (long) Math.random() * 5000);
			System.out.printf("Грузовик №%d приехал в %s и доставил: %s и %s.\n", number, dest, parcels[0], parcels[1]);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}