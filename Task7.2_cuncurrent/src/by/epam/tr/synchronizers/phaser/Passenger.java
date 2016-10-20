package by.epam.tr.synchronizers.phaser;

import java.util.concurrent.Phaser;

public class Passenger extends Thread {
	private int departure;
	private int destination;
	private Phaser phaser;

	public Passenger(int departure, int destination, Phaser phaser) {
		this.departure = departure;
		this.destination = destination;
		this.phaser = phaser;
		System.out.println(this + " ждёт на остановке № " + this.departure);
	}

	@Override
	public void run() {
		try {
			System.out.println(this + " сел в автобус.");

			while (phaser.getPhase() < destination) { 	// Пока автобус не приедет
														// на нужную
														// остановку(фазу)
				phaser.arriveAndAwaitAdvance(); // заявляем в каждой фазе о готовности и ждем
			} 									

			Thread.sleep(1);
			System.out.println(this + " покинул автобус.");
			phaser.arriveAndDeregister(); // Отменяем регистрацию на нужной фазе
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Пассажир{" + departure + " -> " + destination + '}';
	}

	public int getDeparture() {
		return departure;
	}

	public void setDeparture(int departure) {
		this.departure = departure;
	}

	public int getDestination() {
		return destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

	public Phaser getPhaser() {
		return phaser;
	}

	public void setPhaser(Phaser phaser) {
		this.phaser = phaser;
	}

}
