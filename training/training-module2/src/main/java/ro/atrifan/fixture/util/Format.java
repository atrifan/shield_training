package ro.atrifan.fixture.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Format {
	
	public void wrong(CellWrapper cell, String actual) {
		String oldText = cell.getText();
		cell.setText("fail: Expected: " + Tools.variableReplacer(oldText) + "\n Actual: " + actual);
	}
	
	public void right(CellWrapper cell) {
		cell.setText("pass: " + Tools.fromHtml(cell.getText()));
	}
	
	public void fail(CellWrapper cell, String text) {
		cell.setText("fail: " + text);
	}
	
	public void pass(CellWrapper cell, String text) {
		cell.setText("pass: " + text);
	}
	
	public void exception(CellWrapper cell, Throwable exception) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(out);
	    exception.printStackTrace(ps);
	    cell.setText("error: " + out.toString());
	}
	
	public void error(CellWrapper cell, String err) {
	    cell.setText("error: " + err);
	}
	
	public void edit(CellWrapper cell, String newValue) {
	    cell.setText("ignore: "+newValue);
	}
	
	public void replace(CellWrapper cell) {
		String oldText = cell.getText();
	    cell.setText("ignore: \n"+Tools.fromHtml(Tools.variableReplacer(oldText)));
	}
}
