package filter;



public class RedFilter extends ColorFilter {
	public RedFilter() {
		mask = 0xFFFF0000;
	}
}
