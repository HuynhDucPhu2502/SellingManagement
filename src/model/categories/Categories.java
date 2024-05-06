package model.categories;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class Categories extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8344022944530773920L;
	private ArrayList<Category> categories;
	private final String[] column = {"Mã loại", "Tên loại", "Số mặt hàng"};
	
	public Categories() {
		this.categories = CategoryDAO.getData();
	}
	
	public Categories(ArrayList<Category> categories) {
		super();
		this.categories = categories;
	}

	@Override
	public String getColumnName(int column) {
		return this.column[column];
	}
	
	@Override
	public int getRowCount() {
		return categories.size();
	}
	
	@Override
	public int getColumnCount() {
		return column.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Category category = categories.get(rowIndex);
		switch (columnIndex) {
			case 0: return category.getCategoryID();
			case 1: return category.getCategoryName();
			case 2: return category.getItemCount();
			default: return null;
		}
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
		fireTableDataChanged();
	}
	
	
	
	
	
	
}

 