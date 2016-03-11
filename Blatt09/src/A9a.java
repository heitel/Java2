import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class A9a {
	public static void main(String[] args) {
		GregorianCalendar jetzt = new GregorianCalendar();
		int days = 100;
		jetzt.add(Calendar.DAY_OF_YEAR, days);
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd.MM.yyyy");
		System.out.print("Heute in " + days + " Tagen ist: ");
		System.out.println(sdf.format(jetzt.getTime()));
	}
}
