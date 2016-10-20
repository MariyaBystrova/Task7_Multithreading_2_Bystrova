package by.epam.tr.synchronizers.cyclebarrier;

import java.util.concurrent.CyclicBarrier;
/**
 * 
 * @author mariya
 *
 *	Паромная переправа. Паром может переправлять одновременно по три автомобиля. 
 *	Паром переправляется, когда у переправы соберется минимум три автомобиля.
 *	
 */
public class Main {

	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(3, new FerryBoat());  //Инициализируем барьер на три потока и таском, который будет выполняться, когда
	    																//у барьера соберется три потока. После этого, они будут освобождены.
		for (int i = 0; i < 9; i++) {
            new Thread(new Car(i, barrier)).start();
            try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }

	}

}
