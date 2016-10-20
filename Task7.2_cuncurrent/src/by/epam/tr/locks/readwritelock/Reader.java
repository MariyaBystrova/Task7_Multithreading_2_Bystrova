package by.epam.tr.locks.readwritelock;

/**
 * 
 * @author mariya
 *
 *	Класс, представляющий собой поток, который читает данные.
 *	Одновременное чтение для нескольких потоков - позволено. 
 *
 */
public class Reader extends Thread {
	private Dictionary d = null;

	public Reader(Dictionary d, String threadName) {
		this.d = d;
		this.setName(threadName);
	}


	@Override
	public void run() {
		
			String[] keys = d.getKeys();
			for (String key : keys) {
				// reading from dictionary with READ LOCK
				d.get(key);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}

	
}
