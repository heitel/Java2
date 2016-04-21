

import java.util.ArrayList;

public class PermutationGenerator {
	private String word;
	
	public PermutationGenerator(String word) {
		this.word = word;
	}
	
	public ArrayList<String> getPerm() {
		ArrayList<String> list = new ArrayList<>();
		if (word.length()==1) {
			list.add(word);
			return list;
		}
		for (int i = 0; i < word.length(); i++) {
			String sub = word.substring(0, i) + word.substring(i+1);
			PermutationGenerator p = new PermutationGenerator(sub);
			ArrayList<String> tmp = p.getPerm();
			for (String s : tmp) {
				list.add(word.charAt(i) + s);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		PermutationGenerator p = new PermutationGenerator("daa");
		ArrayList<String> list = p.getPerm();
		for (String s : list) {
			System.out.println(s);
		}
	}
}
