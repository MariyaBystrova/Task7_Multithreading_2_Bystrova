package by.epam.tr.locks.condition;

/**
 * 
 * @author mariya
 *
 * 	Решение задачи потребитель-продавец, где склад - разделяемый ресурс.
 * 	Пока продавец не добавит товар, покупатель не может ничего купить.
 *
 */
public class Main {

	public static void main(String[] args) {
		// склад - разделяемый ресурс
		Warehouse sharedObject = new Warehouse();

		Consumer c = new Consumer(sharedObject);
		Producer p = new Producer(sharedObject);
		
		c.start();
		p.start();
		
	}

}
