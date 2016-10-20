package by.epam.tr.synchronizers.semaphore;
/**
 * 
 * @author mariya
 *
 *Класс тележка - разделяемый ресурс
 *в нее можно что-то ложить и доставать
 *
 */
public class Cart {
	private static int weight = 30;
	
    public static void addWeight(){
        weight++;
    }

    public static void reduceWeight(){
        weight--;
    }

    public static int getWaight(){
        return weight;
    }
}
