import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


public class A9c {
	public static void main(String[] args) {
		int year = 2000;
		int month = 0;
		int day = 1;
		GregorianCalendar cal = new GregorianCalendar(year, month, day);

		SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd.MM.yyyy");
		System.out.println("Am "+day+"."+(1+month)+"."+year+" war: ");
		System.out.println(sdf.format(cal.getTime()));
	}
}
