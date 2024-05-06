package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import model.categories.Categories;
import model.products.ProductDAO;
import model.suppliers.Suppliers;
import util.FetchDataStatus;
import view.products.ProductSearchView;

public class ProductSearchController implements ActionListener {
	private ProductSearchView productSearchView;
	
	private Categories categories;
	private Suppliers suppleirs;
	
	public ProductSearchController(ProductSearchView productSearchView) {
		this.productSearchView = productSearchView;
		this.categories = new Categories(FetchDataStatus.getCategoryData());
		this.suppleirs = new Suppliers(FetchDataStatus.getSupplierData());
		register();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		try {
			if (source == productSearchView.getResetSearchBtn()) handleResetBtn();
			else if (source == productSearchView.getSearchContainsNameBtn() 
					|| source == productSearchView.getSearchContainsNameTxTField()) handleSearchByContainsProductName();
			else if (source == productSearchView.getSearchByIDBtn()
					|| source == productSearchView.getSearchByIDTxtField()) handleSearchByContainsProductID();
			else if (source == productSearchView.getSearchByCategoryNameBtn()) handleSearchByCategoryName();
			else if (source == productSearchView.getSearchBySupplierIDBtn()) handleSearchBySupplierName();

		} catch (Exception exception) {
			JOptionPane.showOptionDialog(null, exception.getMessage(), "Hệ thống", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
					null, null, null);
		}
		
	}
	
	private void handleResetBtn() {
		productSearchView.getProducts().setProducts(FetchDataStatus.getProductData());
	}
	
	private void handleSearchByContainsProductName() {
		String name = productSearchView.getSearchContainsNameTxTField().getText().trim();
		if (name.isEmpty())
			throw new IllegalArgumentException("Dữ liệu không được để trống");
		productSearchView.getProducts().setProducts(ProductDAO.searchContainsName(name));
	}
	
	private void handleSearchByContainsProductID() {
		String id = productSearchView.getSearchByIDTxtField().getText().trim();
		if (id.isEmpty())
			throw new IllegalArgumentException("Dữ liệu không được để trống");
		productSearchView.getProducts().setProducts(ProductDAO.searchContainsProductID(id));
	}
	
	private void handleSearchByCategoryName() {
		String categoryName = productSearchView.getCategoryNameCBox().getSelectedItem() == null ? "" 
				: (String)productSearchView.getCategoryNameCBox().getSelectedItem();
		if (categoryName.isEmpty())
			throw new IllegalArgumentException("Dữ liệu không được để trống");
		productSearchView.getProducts().setProducts(ProductDAO.searchCategoryName(categoryName));
	}
	
	private void handleSearchBySupplierName() {
		String supplierName = productSearchView.getSupplierNameCBox().getSelectedItem() == null ? "" 
				: (String)productSearchView.getSupplierNameCBox().getSelectedItem();
		if (supplierName.isEmpty())
			throw new IllegalArgumentException("Dữ liệu không được để trống");
		productSearchView.getProducts().setProducts(ProductDAO.searchContainsSupplierName(supplierName));
	}
	
	public void resetAll() {
		productSearchView.getProducts().setProducts(FetchDataStatus.getProductData());
	}

	private void register() {
		
		productSearchView.getResetSearchBtn().addActionListener(this);
		productSearchView.getSearchContainsNameBtn().addActionListener(this);
		productSearchView.getSearchByIDBtn().addActionListener(this);
		productSearchView.getSearchByCategoryNameBtn().addActionListener(this);
		productSearchView.getSearchBySupplierIDBtn().addActionListener(this);
		productSearchView.getSearchContainsNameTxTField().addActionListener(this);
		productSearchView.getSearchByIDTxtField().addActionListener(this);
		
		ArrayList<String> categoriesName = categories.getCategories()
				.stream()
				.map(x -> x.getCategoryName()).collect(Collectors.toCollection(ArrayList::new));
		productSearchView.getCategoryDefaultComboxModel().addAll(categoriesName);
		
		ArrayList<String> suppliersName = suppleirs.getSuppliers()
				.stream()
				.map(x -> x.getSupplierName()).collect(Collectors.toCollection(ArrayList::new));
		productSearchView.getSupplierDefaultComboBoxModel().addAll(suppliersName);
		
		if (!categoriesName.isEmpty())
			productSearchView.getCategoryNameCBox().setSelectedIndex(0);
		
		if (!suppliersName.isEmpty()) {
			productSearchView.getSupplierNameCBox().setSelectedIndex(0);
		}
	}
	
	
}
