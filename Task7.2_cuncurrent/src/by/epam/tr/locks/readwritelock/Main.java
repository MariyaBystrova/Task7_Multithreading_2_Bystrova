package by.epam.tr.locks.readwritelock;
/**
 * 
 * @author mariya
 *
 *	Реализация потокобезопасного словаря.
 *	Несколько потоков читают несколько раз все записи в соловаре, а один поток в это время изменяет записи.
 *
 */
public class Main {

	public static void main(String[] args) {

		Dictionary d = new Dictionary();
		d.set("java", "Programmer language");
		d.set("linux", "rulez");
		Writer writer = new Writer(d, "Mr. Writer");
		Reader reader1 = new Reader(d, "Mrs Reader1");
		Reader reader2 = new Reader(d, "Mrs Reader2");
		Reader reader3 = new Reader(d, "Mrs Reader3");
		Reader reader4 = new Reader(d, "Mrs Reader4");
		Reader reader5 = new Reader(d, "Mrs Reader5");
		
		writer.start();
		reader1.start();
		reader2.start();
		reader3.start();
		reader4.start();
		reader5.start();
	}

}
