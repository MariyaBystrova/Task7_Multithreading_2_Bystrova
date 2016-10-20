package by.epam.tr.locks.condition;

public class Consumer extends Thread {
	Warehouse warehouse;

	public Consumer(Warehouse sharedObject) {
		super("CONSUMER");
		this.warehouse = sharedObject;
	}

	@Override
	public void run() {
		try {
			warehouse.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
