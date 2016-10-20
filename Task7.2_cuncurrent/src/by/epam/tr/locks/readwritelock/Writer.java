package by.epam.tr.locks.readwritelock;

/**
 * 
 * @author mariya
 *
 *	Класс, представляющий собой поток, который изменяет данные.
 *	Когда работает этот класс, работающий с writeLock, другим потокам запрещено чтение или запись.
 *	Доступ на запись для одного потока. 
 *
 */
public class Writer extends Thread {
	
	private Dictionary d = null;
	private int count=0;

	public Writer(Dictionary d, String threadName) {
		this.d = d;
		this.setName(threadName);
	}

	@Override
	public void run() {

		
		String[] keys = d.getKeys();
		for (String key : keys) {
			String newValue = getNewValueFromDatastore(key);
			// updating dictionary with WRITE LOCK
			d.set(key, newValue);
			count++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public String getNewValueFromDatastore(String key) {
		// This part is not implemented. Out of scope of this artile
		if(count==0){
			return "Object oriented programming language";
		}else{
			return "Operating system";
		}
	}
}