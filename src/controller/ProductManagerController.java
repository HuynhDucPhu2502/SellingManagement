package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import model.categories.Categories;
import model.categories.Category;
import model.categories.CategoryDAO;
import model.products.Product;
import model.products.ProductDAO;
import model.products.Products;
import model.shelf.Shelf;
import model.shelf.ShelfDAO;
import model.shelf.Shelfs;
import model.suppliers.Supplier;
import model.suppliers.Suppliers;
import util.FetchDataStatus;
import view.products.ProductManagerView;
import view.products.ProductSearchView;

public class ProductManagerController implements ActionListener {
	private ProductManagerView productManagerView;
	private ProductSearchView productSearchView;
	
	private Products products;
	private Categories categories;
	private Suppliers suppliers;
	private Shelfs shelfs;
	

	public ProductManagerController(ProductManagerView productManagerView, ProductSearchView productSearchView) {
		this.productManagerView = productManagerView;
		this.productSearchView = productSearchView;
		
		this.products = productManagerView.getProducts();
		this.categories = productManagerView.getCategories();
		this.suppliers = productManagerView.getSuppliers();
		this.shelfs= productManagerView.getShelfs();
		
		register();
	}
	
	private void register() {
		productManagerView.getAddCategoryBtn().addActionListener(this);
		productManagerView.getRemoveCategoryBtn().addActionListener(this);
		productManagerView.getUpdateCategoryBtn().addActionListener(this);
		
		productManagerView.getAddProductBtn().addActionListener(this);
		productManagerView.getRemoveProductBtn().addActionListener(this);
		productManagerView.getUpdateProductBtn().addActionListener(this);
		
		
		reloadCBox();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		try {
			if (source == productManagerView.getAddProductBtn()) handleAddProductBtn();
			else if (source == productManagerView.getRemoveProductBtn()) handleRemoveProductBtn();
			else if (source == productManagerView.getUpdateProductBtn()) handleUpdateProductBtn();
			else if (source == productManagerView.getAddCategoryBtn()) handleAddCategoryBtn();
			else if (source == productManagerView.getRemoveCategoryBtn()) handleRemoveCategoryBtn();
			else if (source == productManagerView.getUpdateCategoryBtn()) handleUpdateCategoryBtn();
		} catch (Exception exception) {
			JOptionPane.showOptionDialog(null, exception.getMessage(), "Hệ thống", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
					null, null, null);
		}
	}
	
	private void handleAddProductBtn() {
		String productName = productManagerView.getProductNameTxtField().getText().trim();
		String productID = productManagerView.getProductIDTxtField().getText().trim();
		String purchasePriceStr = productManagerView.getPurchasePriceTxtField().getText().trim();
		String sellingPriceStr = productManagerView.getSellingPriceTxtField().getText().trim();
		String categoryName = productManagerView.getCategoryNameCBox().getSelectedItem() == null ?  "" 
				: (String)productManagerView.getCategoryNameCBox().getSelectedItem();
		String stockStr = productManagerView.getStockTxtField().getText().trim();
		String unit = productManagerView.getUnitCBox().getSelectedItem() == null ? ""
				: (String)productManagerView.getUnitCBox().getSelectedItem();
		Date receiveDate = productManagerView.getReceiveDateDChooser().getDate();
		String supplierName = productManagerView.getSupplierCBox().getSelectedItem() == null ? ""
				:  (String)productManagerView.getSupplierCBox().getSelectedItem();
		String shelfID = productManagerView.getShelfIDCBox().getSelectedItem() == null ? ""
				: (String) productManagerView.getShelfIDCBox().getSelectedItem();
		
		validProductData(productName, productID, purchasePriceStr, sellingPriceStr, stockStr, categoryName, receiveDate, supplierName,shelfID);
		
		Supplier supplier = suppliers.getSuppliers().stream()
				.filter(x -> x.getSupplierName().equalsIgnoreCase(supplierName))
				.findFirst()
				.orElse(null);
		
		Category category = categories.getCategories().stream()
				.filter(x -> x.getCategoryName().equalsIgnoreCase(categoryName))
				.findFirst()
				.orElse(null);
		
		Shelf shelf = ShelfDAO.getShelfByID(shelfID); // Đảm bảo ShelfDAO đã được triển khai phương thức getShelfByID() để lấy thông tin Shelf từ ID

		Product product = new Product(productID, productName, Double.parseDouble(purchasePriceStr),  Double.parseDouble(sellingPriceStr), category, Integer.parseInt(stockStr), unit, receiveDate, supplier, shelf);

		
		ProductDAO.insertData(product);
		
		products.setProducts(FetchDataStatus.getProductData());
		categories.setCategories(FetchDataStatus.getCategoryData());
		
		
	}
	
	private void handleAddCategoryBtn() {
		String id = productManagerView.getCategoryIDTxtField().getText().trim();
		String name = productManagerView.getCategoryNameTxtField().getText().trim();
		
		validCategoryData(id, name);
		
		Category category = new Category(id, name, 0);
		CategoryDAO.insertData(category);
		
		categories.setCategories(FetchDataStatus.getCategoryData());
		products.setProducts(FetchDataStatus.getProductData());
				
		reloadCBox();	
	}
	
	private void handleRemoveProductBtn() {
		int index = productManagerView.getProductTable().getSelectedRow();
		
		if (index == -1)
			throw new IllegalArgumentException("Vui lòng chọn dòng cần xóa");
		
		int option = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn muốn xóa?", "Hệ thống", 
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, 
				null, null, null);
		
		if (option == JOptionPane.YES_OPTION) {
			Product product = products.getProducts().get(index);
			ProductDAO.deleteData(product.getId(), product.getCategory().getCategoryName());
			
			products.setProducts(FetchDataStatus.getProductData());
			categories.setCategories(FetchDataStatus.getCategoryData());
			
			reloadCBox();
		}
		
	}
	
	private void handleRemoveCategoryBtn() {
		int index = productManagerView.getCategoryTable().getSelectedRow();
		
		if (index == -1)
			throw new IllegalArgumentException("Vui lòng chọn dòng cần xóa");
		
		int option = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn muốn xóa?", "Hệ thống", 
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, 
				null, null, null);
		
		if (option == JOptionPane.YES_OPTION) {
			CategoryDAO.deleteData(categories.getCategories().get(index).getCategoryID());
			
			categories.setCategories(FetchDataStatus.getCategoryData());
			products.setProducts(FetchDataStatus.getProductData());
			
			
			reloadCBox();
		}
	}
	
	private void handleUpdateProductBtn() {
		int index = productManagerView.getProductTable().getSelectedRow();

		if (index == -1)
		    throw new IllegalArgumentException("Vui lòng chọn dòng cần cập nhật");

		String productName = productManagerView.getProductNameTxtField().getText().trim();
		String productID = productManagerView.getProductIDTxtField().getText().trim();
		String purchasePriceStr = productManagerView.getPurchasePriceTxtField().getText().trim();
		String sellingPriceStr = productManagerView.getSellingPriceTxtField().getText().trim();
		String categoryName = productManagerView.getCategoryNameCBox().getSelectedItem() == null ? "" 
		        : (String) productManagerView.getCategoryNameCBox().getSelectedItem();
		String stockStr = productManagerView.getStockTxtField().getText().trim();
		String unit = productManagerView.getUnitCBox().getSelectedItem() == null ? ""
		        : (String) productManagerView.getUnitCBox().getSelectedItem();
		Date receiveDate = productManagerView.getReceiveDateDChooser().getDate();
		String supplierName = productManagerView.getSupplierCBox().getSelectedItem() == null ? ""
		        : (String) productManagerView.getSupplierCBox().getSelectedItem();
		String shelfID = productManagerView.getShelfIDCBox().getSelectedItem() == null ? ""
				: (String) productManagerView.getShelfIDCBox().getSelectedItem();

		validProductData(productName, productID, purchasePriceStr, sellingPriceStr, stockStr, categoryName, receiveDate, supplierName,shelfID);

		Supplier supplier = suppliers.getSuppliers().stream()
		        .filter(x -> x.getSupplierName().equalsIgnoreCase(supplierName))
		        .findFirst()
		        .orElse(null);

		Category category = categories.getCategories().stream()
		        .filter(x -> x.getCategoryName().equalsIgnoreCase(categoryName))
		        .findFirst()
		        .orElse(null);

		Shelf shelf = shelfs.getListShelfs().stream()
				.filter(x -> x.getShelfID().equalsIgnoreCase(shelfID))
				.findFirst()
				.orElse(null);

		Product product = new Product(productID,
		        productName, Double.parseDouble(purchasePriceStr),  Double.parseDouble(sellingPriceStr), category, 
		        Integer.parseInt(stockStr), unit, receiveDate, supplier, shelf);

		ProductDAO.updateData(product);

		products.setProducts(FetchDataStatus.getProductData());
		categories.setCategories(FetchDataStatus.getCategoryData());

		
	}
	
	private void handleUpdateCategoryBtn() {	
		int index = productManagerView.getCategoryTable().getSelectedRow();
		
		if (index == -1)
			throw new IllegalArgumentException("Vui lòng chọn dòng cần cập nhật");
		
		String id = productManagerView.getCategoryIDTxtField().getText().trim();
		String name = productManagerView.getCategoryNameTxtField().getText().trim();
		
		validCategoryData(id, name);
	
		Category category = new Category(id, name, 
				Integer.parseInt(productManagerView.getCategoryItemCountTxtField().getText()));
		
		CategoryDAO.updateData(name, category.getCategoryID());
		
		categories.setCategories(FetchDataStatus.getCategoryData());
		products.setProducts(FetchDataStatus.getProductData());
		
		
		reloadCBox();
	}
	
	public void resetAll() {
		suppliers.setSuppliers(FetchDataStatus.getSupplierData());
		categories.setCategories(FetchDataStatus.getCategoryData());
		products.setProducts(FetchDataStatus.getProductData());
		shelfs.setListShelfs(FetchDataStatus.getShelfData());
		
		reloadCBox();
	}
	
	private void reloadCBox() {
		ArrayList<String> categoriesName = categories.getCategories()
				.stream()
				.map(x -> x.getCategoryName()).collect(Collectors.toCollection(ArrayList::new));
		
		ArrayList<String> suppliersName = suppliers.getSuppliers()
				.stream()
				.map(x -> x.getSupplierName()).collect(Collectors.toCollection(ArrayList::new));
		
		ArrayList<String> shelfsID = shelfs.getListShelfs()
				.stream()
				.map(x -> x.getShelfID()).collect(Collectors.toCollection(ArrayList::new));
		
		productManagerView.getCategoryNameComboBoxModel().removeAllElements();
		productManagerView.getCategoryNameComboBoxModel().addAll(categoriesName);
		
		productManagerView.getSupplierComBoxModel().removeAllElements();
		productManagerView.getSupplierComBoxModel().addAll(suppliersName);
		
		productManagerView.getShelfDefaultComboxModel().removeAllElements();
		productManagerView.getShelfDefaultComboxModel().addAll(shelfsID);
		
		
		productSearchView.getCategoryDefaultComboxModel().removeAllElements();
		productSearchView.getCategoryDefaultComboxModel().addAll(categoriesName);
		
		if (productManagerView.getCategoryNameComboBoxModel().getSize() != 0)
			productManagerView.getCategoryNameCBox().setSelectedIndex(0);
		if (productManagerView.getSupplierComBoxModel().getSize() != 0)
			productManagerView.getSupplierCBox().setSelectedIndex(0);
		if (productManagerView.getShelfDefaultComboxModel().getSize() != 0)
			productManagerView.getShelfIDCBox().setSelectedIndex(0);
		
		
		if (productSearchView.getCategoryDefaultComboxModel().getSize() != 0)
			productSearchView.getCategoryNameCBox().setSelectedIndex(0);
		if (productSearchView.getSupplierDefaultComboBoxModel().getSize() != 0)
			productSearchView.getSupplierNameCBox().setSelectedIndex(0);
	}
	
	private void validProductData(String productName, String productID, String purchasePriceStr, String sellingPriceStr, 
			String stockStr, String categoryName, Date receiveDate, String supplierName, String shelfID) {
		if (productName.isEmpty() || productID.isEmpty() || purchasePriceStr.isEmpty() || 
				sellingPriceStr.isEmpty() || categoryName.isEmpty() || supplierName.isEmpty() || shelfID.isEmpty())
			throw new IllegalArgumentException("Trường dữ liệu không được để trống");
			
		if (receiveDate == null)
			throw new IllegalArgumentException("Ngày nhập không được bỏ trống");
			
		try {
			double purchasePrice = Double.parseDouble(purchasePriceStr);
			if (purchasePrice <= 0)
				throw new IllegalArgumentException("Giá nhập phải từ 1 trở lên");
		} catch (Exception exception) {
			throw new IllegalArgumentException("Giá phải là một con số");
		}
		
		try {
			double sellingPrice = Double.parseDouble(sellingPriceStr);
			if (sellingPrice <= 0)
				throw new IllegalArgumentException("Giá nhập phải từ 1 trở lên");
		} catch (Exception exception) {
			throw new IllegalArgumentException("Giá phải là một con số");
		}
		
		try {
			int stock = Integer.parseInt(stockStr);
			if (stock <= 0)
				throw new IllegalArgumentException("Số lượng phải từ 1 trở lên");
		} catch (Exception exception) {
			throw new IllegalArgumentException("Số lượng phải là một con số");
		}
		
	}
	
	private void validCategoryData(String idStr, String name) {
		if (idStr.isEmpty() || name.isEmpty())
			throw new IllegalArgumentException("Trường dữ liệu không được để trống");
		
	}
	
	
	
	
}
