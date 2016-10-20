package by.epam.tr.locks.condition;

public class Producer extends Thread {
	Warehouse warehouse;

	public Producer(Warehouse sharedObject) {
		super("PRODUCER");
		this.warehouse = sharedObject;
	}

	@Override
	public void run() {
		try {
			warehouse.put();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
