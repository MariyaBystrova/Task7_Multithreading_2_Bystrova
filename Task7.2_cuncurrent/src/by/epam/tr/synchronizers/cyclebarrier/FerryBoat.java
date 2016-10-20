package by.epam.tr.synchronizers.cyclebarrier;

/**
 * 
 * @author mariya
 *			
 *		   Барьерное действие.
 *         Имитация работы переправы. Время сна - время переправы.
 * 
 */
public class FerryBoat implements Runnable {

	@Override
	public void run() {

		try {
			Thread.sleep(500);
			System.out.println("Паром переправил автомобили!");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
