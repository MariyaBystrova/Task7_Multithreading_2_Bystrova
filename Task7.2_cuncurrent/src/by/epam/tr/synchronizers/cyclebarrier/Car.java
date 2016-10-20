package by.epam.tr.synchronizers.cyclebarrier;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private int carNumber;
    private CyclicBarrier barrier;

    public Car(int carNumber, CyclicBarrier barrier) {
        this.carNumber = carNumber;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Автомобиль №%d подъехал к паромной переправе.\n", carNumber);
   
            barrier.await(); 	// задаем барьер потоку, после чего он блокируется 
            					// и ждет пока остальные стороны достигнут барьера 
            					// (должно скопиться у барьера 3 машины-потока, чтобы запустилось барьерное действие)
            System.out.printf("Автомобиль №%d продолжил движение.\n", carNumber);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}
