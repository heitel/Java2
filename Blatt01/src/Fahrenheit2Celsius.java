public class Fahrenheit2Celsius {
	public static void main(String[] args) {
		double fahrenheit = 0;
		double celsius = 5.0/9 * (fahrenheit-32);
		
		System.out.println("Fahrenheit: " + fahrenheit + " Celsius: " + celsius);
		
		fahrenheit = 40;
		celsius = 5.0/9 * (fahrenheit-32);
		
		System.out.println("Fahrenheit: " + fahrenheit + " Celsius: " + celsius);
		
		fahrenheit = 80;
		celsius = 5.0/9 * (fahrenheit-32);
		
		System.out.println("Fahrenheit: " + fahrenheit + " Celsius: " + celsius);
	}
}
