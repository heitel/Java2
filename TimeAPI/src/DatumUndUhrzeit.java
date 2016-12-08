import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DatumUndUhrzeit {

	public static void main(String[] args) {
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE', der 'd.M.Y' um 'HH:mm:ss.SSS");
		System.out.println("Aktuell: " + ldt.format(formatter));

		int dayOffset = 100;
		ldt = ldt.plusDays(dayOffset);
		System.out.println(dayOffset + " Tage plus: " + ldt.format(formatter));

		DateTimeFormatter df = DateTimeFormatter.ofPattern("EEEE',der 'd.M.Y");
		LocalDate geb = LocalDate.of(1965, 3, 23);
		System.out.println("Geburtsdatum: " + geb.format(df));

		LocalDate heute = LocalDate.now();
		long alterInTagen = heute.toEpochDay() - geb.toEpochDay();
		System.out.println("Alter in Tagen: " + alterInTagen);

		Period period = Period.between(geb, heute);
		System.out.println("Das sind " + period.getYears() + " Jahre " + period.getMonths() + " Monate " + period.getDays() + " Tage");
	}

}
