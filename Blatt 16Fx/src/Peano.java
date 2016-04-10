public class Peano {

	public static int succ(int x) {
		assert x >= 0 : "succ mit x <= 0 geht nicht!";
		return x + 1;
	}

	public static int pred(int x) {
		assert x > 0 : "pred mit x < 0 geht nicht!";
		if (x == 0) {
			return 0;
		}
		return x - 1;
	}

	public static int add(int x, int y) {
		if (y == 0) {
			return x;
		}
		return add(succ(x), pred(y));
	}

	public static int sub(int x, int y) {
		assert x >= y : "sub mit x < y geht nicht!";
		if (y == 0) {
			return x;
		}
		return sub(pred(x), pred(y));
	}

	public static int mul(int x, int y) {
		if (y == 0) {
			return 0;
		}
		if (y == 1) {
			return x;
		}
		return add(x, mul(x, pred(y)));
	}

	public static int pot(int x, int y) {
		if (y == 0) {
			return 1;
		}
		if (y == 1) {
			return x;
		}
		return mul(x, pot(x, pred(y)));
	}

	public static int div(int x, int y) {
		assert y > 0 : "y <= 0 geht nicht!";
		if (x < y) {
			System.out.println("Rest: " + x);
			return 0;
		}
		return succ(div(sub(x, y), y));
	}
	
	private static void showMenu() {		
		System.out
				.println("Rechnen nach den Peano-Axiomen mit natürlichen Zahlen");
		System.out
				.println("=====================================================");

		System.out.println("0\tEnde");
		System.out.println("1\tAddition");
		System.out.println("2\tSubtraktion");
		System.out.println("3\tMultiplikation");
		System.out.println("4\tDivision");
		System.out.println("5\tPotenzierung");
	}

	public static void main(String[] args) {
		Console con = new Console();
		int auswahl;

		do {
			showMenu();
			auswahl = con.readInt("Was wünschen Sie zu tun? ");
			if (auswahl != 0) {
				String txt = con
						.readString("Geben Sie zwei positive ganze Zahlen mit Komma getrennt ein:");
				String tmp[] = txt.split(",");
				int x = Integer.parseInt(tmp[0].trim());
				int y = Integer.parseInt(tmp[1].trim());
				int z;
				
				switch (auswahl) {
				case 1:
					z = add(x,y);
					System.out.println("\t--> " + x + " + " + y + " = " + z);
					break;
				case 2:
					z = sub(x,y);
					System.out.println("\t--> " + x + " - " + y + " = " + z);
					break;
				case 3:
					z = mul(x,y);
					System.out.println("\t--> " + x + " * " + y + " = " + z);
					break;
				case 4:
					z = div(x,y);
					System.out.println("\t--> " + x + " / " + y + " = " + z);
					break;
				case 5:
					z = pot(x,y);
					System.out.println("\t--> " + x + " ^ " + y + " = " + z);
					break;
				default:
					break;
				}
				System.out.println("\n\n");
			}
		} while (auswahl != 0);

	}

}
