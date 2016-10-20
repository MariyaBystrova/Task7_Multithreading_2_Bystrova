package by.epam.tr.locks.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Dictionary {
	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private Map<String, String> bookshelf = new HashMap<String, String>();

	public void set(String key, String value) {
		readWriteLock.writeLock().lock();
		try {
			bookshelf.put(key, value);
			System.out.println(Thread.currentThread().getName() + " INSERTED/UPDATED " + key + " into \"" + bookshelf.get(key)+"\"");
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}

	public String get(String key) {
		readWriteLock.readLock().lock();
		try {
			String value = bookshelf.get(key);
			System.out.println(Thread.currentThread().getName() + " READS " + key + " - \"" + value +"\"");
			return value;
		} finally {
			readWriteLock.readLock().unlock();
		}
	}

	public String[] getKeys() {
		readWriteLock.readLock().lock();
		try {
			String keys[] = new String[bookshelf.size()];
			return bookshelf.keySet().toArray(keys);
		} finally {
			readWriteLock.readLock().unlock();
		}
	}
}
