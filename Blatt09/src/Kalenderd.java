import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Kalenderd {

	public static void main(String[] args) {
		GregorianCalendar jetzt = new GregorianCalendar();
		if (args.length == 1) {
			int month = (Integer.parseInt(args[0])+11)%12;
			jetzt.set(Calendar.MONTH, month);
		}
		if (args.length == 2) {
			int month =  (Integer.parseInt(args[0])+11)%12;
			jetzt.set(Calendar.MONTH, month);
			int year = Integer.parseInt(args[1]);
			jetzt.set(Calendar.YEAR, year);
		}
		
		// Anfang des Monats setzen
		jetzt.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
		System.out.println(sdf.format(jetzt.getTime()));
		System.out.println("Mo Di Mi Do Fr Sa So | Woche");

		// Leerzeichen je nach Wochentag am Monatsanfang
		int dayOfWeek = (jetzt.get(Calendar.DAY_OF_WEEK) + 5) % 7;
		for (int i = 0; i < dayOfWeek; i++) {
			System.out.print("   ");
		}

		int max = jetzt.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < max; i++) {
			int day = jetzt.get(Calendar.DAY_OF_MONTH);
			System.out.print(Console.padStringLeft(day + "", 2) + " ");
			dayOfWeek = (jetzt.get(Calendar.DAY_OF_WEEK) + 5) % 7;
			if (dayOfWeek == 6) {
				System.out.print("| ");
				int week = jetzt.get(Calendar.WEEK_OF_YEAR);
				System.out.println(week);
			}
			jetzt.add(Calendar.DAY_OF_MONTH, 1);
		}
		System.out.println();
	}

}
