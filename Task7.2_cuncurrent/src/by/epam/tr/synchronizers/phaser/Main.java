package by.epam.tr.synchronizers.phaser;

import java.util.concurrent.Phaser;

/**
 * 
 * @author mariya
 *
 *	Работа автобуса.
 *	Есть пять остановок. На первых четырех из них могут стоять пассажиры и ждать автобуса. 
 *	Автобус выезжает из парка и останавливается на каждой остановке на некоторое время. 
 *	После конечной остановки автобус едет в парк. 
 *	Автобус забрает пассажиров и высаживает их на нужных остановках.
 *
 */
public class Main {

	public static void main(String[] args) {
		Phaser phaser = new Phaser(1);
		Bus bus = new Bus(phaser);
		bus.drive();
	}
}
