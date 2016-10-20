package by.epam.tr.philosophers.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * 
 * @author mariya
 *
 *	Класс "палочка"/"вилка"
 */
public class Chopstick {
	private boolean used;	//флаг используется ли сейчас вилка
	private String name;

	private final static Logger rootLogger = LogManager.getRootLogger();
	
	public Chopstick(String name){
		this.name = name;
	}

	/**
	 * метод взять вилку
	 */
	public synchronized void take() {
		rootLogger.info(Thread.currentThread().getName() + " took " + name);
		this.used = true;
	}
	/**
	 * метод положить вилку
	 */
	public synchronized void release() {
		rootLogger.info(Thread.currentThread().getName() + " released " + name);
		this.used = false ;
	}

	public synchronized boolean isUsed() {
		return used;
	}
	
}
