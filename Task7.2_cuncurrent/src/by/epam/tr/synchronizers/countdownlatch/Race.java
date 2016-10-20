package by.epam.tr.synchronizers.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Race {
	
	public void startCompetition(CountDownLatch cdl){
		
		try {
			while (cdl.getCount() > 3){ 	// Проверка, собрались ли все автомобили у старта.
				Thread.sleep(100); 			// Если нет, ждем 100ms
			}
			Thread.sleep(1000);
			System.out.println("На старт!");
			cdl.countDown();				//уменьшает значение внутреннего счетчика CountDownLatch на 1
			Thread.sleep(1000);
			System.out.println("Внимание!");
			cdl.countDown();				//уменьшает значение внутреннего счетчика CountDownLatch на 1

			Thread.sleep(1000);

			System.out.println("Марш!");
			cdl.countDown();				//уменьшает значение внутреннего счетчика CountDownLatch на 1
											// счетчик становится равным нулю, и все ожидающие потоки
											// одновременно разблокируются и стартует гонка
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
