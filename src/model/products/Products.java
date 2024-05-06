package model.products;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class Products extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3699418567872906136L;
	private ArrayList<Product> products;
	private String[] column = {"Tên sản phẩm", "Mã sản phẩm", "Giá Nhập", "Giá Bán", "Mã loại", "Số lượng", "Đơn vị", "Ngày Nhập", "Nhà cung cấp","ID kệ"};
	
	public Products() {
		products = ProductDAO.getData();
	}
	
	

	public Products(ArrayList<Product> products) {
		super();
		this.products = products;
	}


	@Override
	public String getColumnName(int column) {
		return this.column[column];
	}

	@Override
	public int getRowCount() {
		return products.size();
	}
	
	@Override
	public int getColumnCount() {
		return column.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Product product = products.get(rowIndex);
		switch (columnIndex) {
			case 0: return product.getName();
			case 1: return product.getId();
			case 2: return product.getPurchasePrice();
			case 3: return product.getSellingPrice();
			case 4: return product.getCategory().getCategoryName();
			case 5: return product.getStock();
			case 6: return product.getUnit();
			case 7: {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				return dateFormat.format(product.getRecevieDate());
			}
			case 8: return product.getSupplier().getSupplierName();
			case 9: {
				if (product.getShelf() == null) return "CHƯA LÊN KỆ";
				return product.getShelf().getShelfID();
			}
			default: return null;
		}
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
		fireTableDataChanged();
	}
	
	
	
	
}
