import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class A9b {
	public static void main(String[] args) {
		// Hier das Geburtsdatum eintragen
		int year = 2011;
		int month = 0;
		int day = 1;
		GregorianCalendar cal = new GregorianCalendar(year, month, day);
		GregorianCalendar jetzt = new GregorianCalendar();
		
		long diff = jetzt.getTimeInMillis() - cal.getTimeInMillis();
		GregorianCalendar erg = new GregorianCalendar();
		erg.setTimeInMillis(diff);
		
		int tage = (int) (diff / (1000 * 3600 * 24));
		System.out.println("Alter in Tagen: " + tage);
		
		year = erg.get(Calendar.YEAR) - 1970;
		month = erg.get(Calendar.MONTH);
		day = erg.get(Calendar.DAY_OF_MONTH)-1;
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd.MM.yyyy");
		System.out.println(sdf.format(erg.getTime()));
		
		System.out.println("Jahre: " + year);
		System.out.println("Monate: " + month);
		System.out.println("Tage: " + day);
	}
}
