package by.epam.tr.synchronizers.phaser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

public class Bus {
	private Phaser phaser;		//Фазы 0 и 6 - это автобусный парк, 1 - 5 остановки

	public Bus(Phaser phaser) {
		this.phaser = phaser;
	}

	// Фазы 0 и 6 - это автобусный парк, 1 - 5 остановки
	public void drive() {
		List<Passenger> passengers = putPassengers();
		for (int i = 0; i < 7; i++) {
			switch (i) {
			case 0:
				System.out.println("Автобус выехал из парка.");
				phaser.arrive();	// сообщает, что сторона завершила фазу, и возвращает номер фазы. 
									// При вызове данного метода поток не приостанавливается, а продолжает выполнятся
				break;
			case 6:
				System.out.println("Автобус уехал в парк.");
				phaser.arriveAndDeregister();	//сообщает о завершении всех фаз стороной и снимает ее с регистрации
				break;
			default:
				int currentBusStop = phaser.getPhase();
				System.out.println("Остановка № " + currentBusStop);
				for (Passenger p : passengers) { // есть ли пассажиры на остановке
					if (p.getDeparture() == currentBusStop) {
						phaser.register(); 	//регистрирует поток, который будет участвовать в фазах
						p.start(); 			//запускаем
					}
				}
				phaser.arriveAndAwaitAdvance();		// указывает что поток завершил выполнение фазы. 
													// поток приостанавливается до момента, пока все остальные стороны 
													// не закончат выполнять данную фазу
			}
		}
	}

	private List<Passenger> putPassengers() {
		List<Passenger> passengers = new ArrayList<>();
		for (int i = 1; i < 5; i++) { 							// Сгенерируем пассажиров на остановках
			if ((int) (Math.random() * 2) > 0) {
				passengers.add(new Passenger(i, i + 1, phaser));// Этот пассажир выходит на следующей
			} 
			if ((int) (Math.random() * 2) > 0) {
				passengers.add(new Passenger(i, 5, phaser)); 	// Этот пассажир  выходит на конечной
			} 
		}
		return passengers;
	}
}
