package model.shelf;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import util.FetchDataStatus;



public class Shelfs extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4725368850676798823L;
	private ArrayList<Shelf> listShelfs;
	private String[] columns = {"Mã kệ","Tên kệ","Vị trí","Kích thước","Trạng thái","Ghi chú"};
		
	public Shelfs() {
		listShelfs = FetchDataStatus.getShelfData();
	}
	
	

	public Shelfs(ArrayList<Shelf> listShelfs) {
		this.listShelfs = listShelfs;
	}



	@Override
	public String getColumnName(int column) {
		return this.columns[column];
	}

	@Override
	public int getRowCount() {
		return listShelfs.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Shelf shelf = listShelfs.get(rowIndex);
		switch (columnIndex) {
			case 0: return shelf.getShelfID();
			case 1: return shelf.getShelfName();
			case 2: return shelf.getShelfLocation();
			case 3: return shelf.getSizeTye();
			case 4:	return shelf.getStatus();
			case 5: return shelf.getNote();
			default: return null;
		}
	}

	public Shelf getShelfByID(String cID) {
		return listShelfs.stream()
				.filter(x -> x.getShelfID().equalsIgnoreCase(cID))
				.findFirst().orElse(null);
	}
		
	public Shelf getShelfByIndex(int index) {
		if(index < 0 || index >= listShelfs.size())
			return null;
		return listShelfs.get(index);
	}



	public ArrayList<Shelf> getListShelfs() {
		return listShelfs;
	}



	public void setListShelfs(ArrayList<Shelf> listShelfs) {
		this.listShelfs = listShelfs;
		fireTableDataChanged();
	}
	
	
}
