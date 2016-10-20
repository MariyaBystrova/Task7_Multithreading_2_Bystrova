package by.epam.tr.synchronizers.exchanger;

import java.util.concurrent.Exchanger;
/**
 * 
 * @author mariya
 *
 *	Есть два грузовика: один едет из пункта A в пункт D, другой из пункта B в пункт С. 
 *	Дороги AD и BC пересекаются в пункте E. 
 *	Из пунктов A и B нужно доставить посылки в пункты C и D. 
 *	Для этого грузовики в пункте E должны встретиться и обменяться соответствующими посылками.
 *
 */
public class Main {

	public static void main(String[] args) {
		Exchanger<String> exchanger = new Exchanger<>(); 				// создание обменника с обменным типом String
		String[] p1 = new String[]{"{посылка A->D}", "{посылка A->C}"}; //Формирование груза для 1-го грузовика
        String[] p2 = new String[]{"{посылка B->C}", "{посылка B->D}"}; //Формирование груза для 2-го грузовика
        new Thread(new Car(1, "A", "D", p1, exchanger)).start();		//Первый грузовик по маршруту из А в D
        try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        new Thread(new Car(2, "B", "C", p2, exchanger)).start();		//Второй грузовик из В в С

	}

}
