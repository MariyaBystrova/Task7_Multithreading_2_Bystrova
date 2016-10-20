package by.epam.tr.synchronizers.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * @author mariya
 *
 *	Проводится автомобильную гонку. В гонке принимают участие пять автомобилей. 
 *	Для начала гонки нужно, чтобы выполнились следующие условия:
 *	(пять автомобилей подъехали к стартовой прямой, даны команды «На старт!», «Внимание!», «Марш!».
 *	Важно, чтобы все автомобили стартовали одновременно.
 */
public class Main {

	public static void main(String[] args) {
		//В конструктор передается количество операций, которое должно быть выполнено, 
		//чтобы замок «отпустил» заблокированные потоки.
		CountDownLatch cdl = new CountDownLatch(8);
		
		for (int i = 1; i <= 5; i++) {
			int speed = (int) (Math.random() * 100 + 50);
			new Thread(new Car(i, speed, cdl)).start();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		Race race = new Race();
		race.startCompetition(cdl);
	}

}
