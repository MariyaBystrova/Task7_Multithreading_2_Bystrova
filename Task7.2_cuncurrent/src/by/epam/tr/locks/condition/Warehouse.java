package by.epam.tr.locks.condition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Warehouse {
	
	private static final int CAPACITY = 10;
	private final Queue<Integer> queue = new LinkedList<>();

	private final Lock aLock = new ReentrantLock();
	private final Condition bufferNotFull = aLock.newCondition();
	private final Condition bufferNotEmpty = aLock.newCondition();

	public void put() throws InterruptedException {
		aLock.lock();
		try {
			while (queue.size() == CAPACITY) {
				System.out.println(Thread.currentThread().getName() + " : warehouse is full, waiting");
				bufferNotEmpty.await();
			}

			int number = new Random().nextInt(1000000);
			boolean isAdded = queue.offer(number);
			if (isAdded) {
				System.out.printf("%s : added %d into queue %n", Thread.currentThread().getName(), number);
				
				System.out.println(Thread.currentThread().getName() + " : signalling that warehouse is no more empty now");
				bufferNotFull.signalAll();		// сообщаем покупателю, что хранилиже заполнилось товаром
			}
		} finally {
			aLock.unlock();
		}
	}

	public void get() throws InterruptedException {
		aLock.lock();
		try {
			while (queue.isEmpty()){
				System.out.println(Thread.currentThread().getName() + " : warehouse is empty, waiting");
				bufferNotFull.await();
			}

			Integer value = queue.poll();
			if (value != null) {
				System.out.printf("%s : consumed %d from queue %n", Thread.currentThread().getName(), value);

				
				System.out.println(Thread.currentThread().getName() + " : signalling that warehouse may be empty now");
				bufferNotEmpty.signalAll();		// сообщаем продавцу, что хранилище может быть пустым
			}

		} finally {
			aLock.unlock();
		}
	}
}
