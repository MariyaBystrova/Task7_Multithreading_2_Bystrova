package by.epam.tr.philosophers.entity;

import java.util.concurrent.Semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Philosopher extends Thread {
	
	private final static Logger rootLogger = LogManager.getRootLogger();
	
	private Chopstick leftChopstick;		// левая "вилка"
	private Chopstick rightChopstick;		// правая "вилка"
	private int count;						// сколько раз философ поел
	private Semaphore servant;				// семафор, разрешающий только 4 философам прикасаться к вилкам
		
	public Philosopher(Chopstick left, Chopstick right, Semaphore servant) {
		leftChopstick = left;
		rightChopstick = right;
		this.servant = servant;
		count = 0;
	}

	public void eat() {
		try {
			servant.acquire();				// запрос достума к вилкам
											// если 4 философа уже получили доступ, филосов вызвавший этот метод
											// блокируется до тех пор, пока семафор не разрешит доступ
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (!leftChopstick.isUsed()) {		//проверка занята ли сейчас левая вилка 
			leftChopstick.take();			//взять левую вилку
			if (!rightChopstick.isUsed()) {		//проверка занята ли сейчас правая вилка
				rightChopstick.take();			//взять правую вилку	

				rootLogger.info(Thread.currentThread().getName() + " : Eat");
				count++;											
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				rightChopstick.release();		//положить правую вилку
			}
			leftChopstick.release();			//положить левую вилку
		}
		servant.release();						//освободить ресурс
		think();
	}

	public void think() {
		rootLogger.info(Thread.currentThread().getName() + " : Think");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		for (int i = 0; i <= 10; i++) {
			eat();
		}
	}

	public int getCount() {
		return count;
	}
}
