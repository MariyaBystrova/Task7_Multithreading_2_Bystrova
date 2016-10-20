package by.epam.tr.locks.lock;

import java.util.concurrent.locks.Lock;

public class DocumentPrinter implements Runnable {
	private final Lock queueLock;
	
	public DocumentPrinter(Lock queueLock){
		this.queueLock = queueLock;
	}
	

	@Override
	public void run() {
		System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
		printJob(new Object());
	}

	
	// Печать документа, в которую не должен вклиниться другой поток.
	private void printJob(Object document) {	
		queueLock.lock(); // взятие блокировки
		try {
			int duration = (int) (Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during "
					+ (duration / 1000) + " seconds");
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
			queueLock.unlock(); // снятие блокировки
		}
	}

}
