package morse;

public class Code {
	private String morse;
	private String zeichen;
	
	public Code(String morse, String zeichen) {
		this.morse = morse;
		this.zeichen = zeichen;
	}

	public String getMorse() {
		return morse;
	}

	public String getZeichen() {
		return zeichen;
	}

	@Override
	public String toString() {
		return morse + " | " + zeichen;
	}

}
