import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class KalenderD {
	public static void main(String[] args) {
		LocalDate heute = LocalDate.now();
		int year = heute.getYear();
		int month = heute.getMonthValue();
		switch (args.length) {
		case 0:
			break;
		case 1:
			month = Integer.parseInt(args[0]);
			break;
		case 2:
			month = Integer.parseInt(args[0]);
			year = Integer.parseInt(args[1]);
			break;
		default:
			System.out.println("Falsche Anzahl von Parametern (Monat, Jahr)");
			return;
		}

		printMonthYear(month, year);
		System.out.println("\n");
	}

	private static void printMonthYear(int month, int year) {
		LocalDate d = LocalDate.of(year, month, 1);
		DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM y");
		System.out.println(d.format(df));

		System.out.println(" Mo Di Mi Do Fr Sa So | Woche");
		System.out.println("----------------------+------");
		int dayOfWeek = d.getDayOfWeek().getValue();
		printBlank(dayOfWeek - 1);

		while (d.getMonthValue() == month) {
			printDay(d.getDayOfMonth());
			d = d.plusDays(1);
			if (d.getDayOfWeek() == DayOfWeek.MONDAY) {
				WeekFields weekFields = WeekFields.of(Locale.getDefault());
				System.out.println(" | " + d.get(weekFields.weekOfWeekBasedYear()));
			}
		}
	}

	private static void printDay(int day) {
		System.out.print(Console.padStringLeft("" + day, 3));
	}

	private static void printBlank(int anz) {
		for (int i = 0; i < anz; i++) {
			System.out.print("   ");
		}
	}
}
