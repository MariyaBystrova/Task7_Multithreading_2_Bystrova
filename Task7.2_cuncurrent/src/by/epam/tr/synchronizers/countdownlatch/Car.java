package by.epam.tr.synchronizers.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {

	private int carNumber;
	private int carSpeed;// считаем, что скорость автомобиля постоянная
	private CountDownLatch cdl;
	private final int TRACK_LENGTH = 500000;

	public Car(int carNumber, int carSpeed, CountDownLatch cdl) {
		this.carNumber = carNumber;
		this.carSpeed = carSpeed;
		this.cdl = cdl;
	}

	@Override
	public void run() {
		try {
			System.out.printf("Автомобиль №%d подъехал к стартовой прямой.\n", carNumber); 
			cdl.countDown();	//уменьшает значение внутреннего счетчика CountDownLatch на 1
			
			cdl.await();		//блокирует поток, вызвавший его, до тех пор, пока
								// счетчик CountDownLatch не станет равен 0 (условие начала гонки)
	
			Thread.sleep(TRACK_LENGTH / carSpeed); //спит то время, сколько нужно для проезда трассы с заданной скоростью
			
			System.out.printf("Автомобиль №%d финишировал!\n", carNumber);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
