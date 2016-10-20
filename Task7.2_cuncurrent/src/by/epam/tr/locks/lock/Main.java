package by.epam.tr.locks.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author mariya
 *
 *	Реализация работы принтера. 
 *	Lock введен для того, чтобы принтер начинал новую печать сразу после того, как закончится предыдущая.
 *
 */
public class Main {

	public static void main(String[] args) {
	
		Lock queueLock = new ReentrantLock();
		Thread thread[] = new Thread[10];
		for (int i = 0; i < 5; i++) {
			thread[i] = new Thread(new DocumentPrinter(queueLock), "Thread_" + (i+1));
		}
		for (int i = 0; i < 5; i++) {
			thread[i].start();
		}
	}

}
