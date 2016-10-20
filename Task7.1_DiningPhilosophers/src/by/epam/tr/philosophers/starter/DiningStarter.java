package by.epam.tr.philosophers.starter;

import by.epam.tr.philosophers.entity.Chopstick;
import by.epam.tr.philosophers.entity.Philosopher;
import java.util.concurrent.Semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author mariya
 *	
 *	ФОРМУЛИРОВКА ЗАДАЧИ:
 *
 *	Проблема обедающих философов. 
 *	За круглым столом расставлены пять стульев, на каждом из которых сидит философ (Ф1-Ф5).
 *	В центре стола размещено блюдо с макаронами. На столе лежат пять вилок (B1-B5), 
 *	каждая из которых находится между двумя соседними тарелками. 
 *	Каждый философ может находиться в двух состояниях: размышлять или есть макароны. 
 *	Для того, чтобы начать есть, философу необходимы две вилки: одна в правой руке, а другая в левой. 
 *	Закончив еду, философ кладет вилки на место и начинает размышлять до тех пор, пока снова не проголодается.
 *	
 *	В этой задаче имеются две опасные ситуации: «заговор соседей» и «голодная смерть».
	«Заговор соседей» имеет место, когда соседи слева и справа от философа поочередно забирают вилки то слева, 
	то справа от него, что приводит к вынужденному голоданию, так как он никогда не может воспользоваться обеими вилками.
	«Голодная смерть» возникает, когда философы одновременно проголодаются и одновременно попытаются взять, 
	например, свою левую вилку. При этом возникает тупиковая ситуация, так как никто из них не может начать есть, 
	не имея второй вилки.
 *
 *
 *	1) Для устранения заговора соседей, вводится очередность действий: 
 *	взять левую вилку, правую, поесть, положить правую, положить левую, думать.
 *	
 *	2) Для устранения голодной смерти вводится "слуга" - семафор, который позволяет есть одновременно только 4 философам. 
 *	   5го он не пускает даже пробовать взять первую (левую) вилку. Слуга - справедливый и он пускает философов в порядке очереди.
 *
 * ----------------------------------------------------------------------------------------------------------------------------------------
 * ПРЕДМЕТНЫЕ ОБЛАСТИ:
 * 
 * С подобными проблемами можно столкнуться работая с обновляемым набором данных и при параллельных вычислениях. 
 * К примеру, составление схемы работы светофоров так, чтобы светофоры одновременно не разрешали движение 
 * всех автомобилей по всем направлениям, но и не блокировали все направления сразу. 
 * 
 * Или же грамотрная организация работы завода. Когда у каждого рабочего есть дневная норма, которую он должен выполнить, чтобы его не уволили.
 * Рабочий также может перевыполнить норму. Проблема заключается в том, что количество станков ограничено и меньше числа рабочих.
 * И в интересах каждого рабочего получить доступ к ограниченному ресурсу, т.е. станку.
 * 
 * 
 * 
 */
public class DiningStarter {
	private final static Logger rootLogger = LogManager.getRootLogger();
	
	private final static String CHOPSTIC = "C";
	private final static String PHILOSOPHER = "P";
	
	public static void main(String[] args) throws InterruptedException {
	
		Semaphore servant = new Semaphore(4, true); //справедливый семафор, который пускает философов в порядке очереди
		
		Chopstick[] chopistics = new Chopstick[5];

		// "вилки" - "палочки"
		for (int i = 0; i < chopistics.length; i++) {
			chopistics[i] = new Chopstick(CHOPSTIC + i);
		}
		
		Philosopher[] philosophers = new Philosopher[5];
		
		philosophers[0] = new Philosopher(chopistics[0], chopistics[1], servant);
		philosophers[1] = new Philosopher(chopistics[1], chopistics[2], servant);
		philosophers[2] = new Philosopher(chopistics[2], chopistics[3], servant);
		philosophers[3] = new Philosopher(chopistics[3], chopistics[4], servant);
		philosophers[4] = new Philosopher(chopistics[4], chopistics[0], servant);

		for (int i = 0; i < philosophers.length; i++) {
			philosophers[i].setName(PHILOSOPHER + i);
			philosophers[i].start();
		}
		for (int i = 0; i < philosophers.length; i++) {
			philosophers[i].join();
		}
		// вывод на консоль сколько раз поел каждый из философов
		for (int i = 0; i < philosophers.length; i++) {
			rootLogger.info("P"+ i +" ate "+ philosophers[i].getCount() +" times.");
		}
		
	}

}
