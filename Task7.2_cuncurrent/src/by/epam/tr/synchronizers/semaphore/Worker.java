package by.epam.tr.synchronizers.semaphore;

import java.util.concurrent.Semaphore;

public class Worker extends Thread {
	private Semaphore semaphore;
	private String workerName;
	private boolean isAdder; // является ли работкик тем, кто наполняет тележку

	public Worker(Semaphore semaphore, String workerName, boolean isAdder) {
		this.semaphore = semaphore;
		this.workerName = workerName;
		this.isAdder = isAdder;
	}

	@Override
	public void run() {
		System.out.println(workerName + " started working...");
		try {
			System.out.println(workerName + " waiting for cart...");
			/*
			 * acquire() запрашивает доступ к следующему за вызовом этого метода
			 * блоку кода, если доступ не разрешен, поток вызвавший этот метод
			 * блокируется до тех пор, пока семафор не разрешит доступ
			 */
			semaphore.acquire();
			System.out.println(workerName + " got access to cart...");
			for (int i = 0; i < 10; i++) {
				if (isAdder)
					Cart.addWeight();
				else {
					Cart.reduceWeight();
				}
				System.out.println(workerName + " changed weight to: " + Cart.getWaight());
				Thread.sleep(10L);
			}
			System.out.println(workerName + " finished working with cart...");
		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			// освобождаем ресурс
			semaphore.release();
		}
	}
}
