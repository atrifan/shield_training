package ro.atrifan.fixture.util;

import java.util.*;

public class RowWrapper {
	
	protected List<CellWrapper> cells = new Vector<CellWrapper>();
	protected int noCells = 0;
	protected int rowNumber;
	
	public RowWrapper(List<String> row) throws Exception {
		for(String c : row) {
			CellWrapper cell = new CellWrapper(c);
			cells.add(cell);
			this.noCells++;
		}
	}
	
	public void setRowNumber(int number) {
		this.rowNumber = number;
	}
	
	public int getNoCells() {
		return this.noCells;
	}
	
	public List<CellWrapper> getCells() {
		return this.cells;
	}
	
	public List<String> getAsList() {
		List<String> rowString = new Vector<String>();
		List<CellWrapper> cells = this.getCells();
		for(CellWrapper cell : cells) {
			rowString.add(cell.getText());
		}
		
		return rowString;
	}
	
	public CellWrapper getCell(int index) {
		return this.cells.get(index);
	}

}
