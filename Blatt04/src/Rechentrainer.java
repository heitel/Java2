public class Rechentrainer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Rechentrainer:");

		do {
		} while (uebung());

		System.out.println("Ende Rechentrainer.");
	}

	private static boolean uebung() {
		Console con = new Console();
		double op1 = con.readDouble("Operand 1 ");
		char operator = con.readChar("operator ");

		if (operator == ' ') {
			return false;
		}
		double op2 = con.readDouble("Operand 2 ");
		double erg = con.readDouble("Ergebnis ");
		double calc = 0;
		switch (operator) {
		case '+':
			calc = op1 + op2;
			break;
		case '-':
			calc = op1 - op2;
			break;
		case '*':
			calc = op1 * op2;
			break;
		case '/':
			calc = op1 / op2;
			break;

		default:
			System.out.println("Ungültiger Operator");
			break;
		}

		if (Math.abs(erg - calc) < 1e-6) {
			System.out.println("Richtig!");
		} else {
			System.out.println("Falsch. Das Ergebnis lautet: " + calc);
		}

		return true;
	}

}
