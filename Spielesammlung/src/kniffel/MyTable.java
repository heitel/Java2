package kniffel;
import javax.swing.JTable;


@SuppressWarnings("serial")
public class MyTable extends JTable {


	public MyTable(int numRows, int numColumns) {
		super(numRows, numColumns);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return true;
	}


}
