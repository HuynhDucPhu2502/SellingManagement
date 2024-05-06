package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.suppliers.SupplierDAO;
import model.suppliers.Suppliers;
import util.FetchDataStatus;
import view.suppliers.SupplierSearchView;

public class SupplierSearchController implements ActionListener {
	private SupplierSearchView supplierSearchView;
	
	private Suppliers suppliers;
	
	public SupplierSearchController( SupplierSearchView supplierSearchView) {
		this.supplierSearchView = supplierSearchView;
		this.suppliers = supplierSearchView.getSuppliers();
		register();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		try {
			if (source == supplierSearchView.getSearchContainsSupplierIDBtn()) handleSearchByContainsSupplierID();
			else if (source == supplierSearchView.getSearchContainsSupplierNameBtn()) handleSearchByContainsSupplierName();
			else if (source == supplierSearchView.getSearchContainsPhoneNumberBtn()) handleSearchByContainsPhoneNumber();
			else if (source == supplierSearchView.getResetSearchBtn()) resetAll();

		} catch (Exception exception) {
			JOptionPane.showOptionDialog(null, exception.getMessage(), "Hệ thống", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
					null, null, null);
		}
		
	}
	
	public void handleResetBtn() {
		suppliers.setSuppliers(FetchDataStatus.getSupplierData());
	}
	
	private void handleSearchByContainsSupplierName() {
		String name = supplierSearchView.getSearchContainsSupplierNameTxtField().getText().trim();
		if (name.isEmpty())
			throw new IllegalArgumentException("Dữ liệu không được để trống");
		suppliers.setSuppliers(SupplierDAO.searchContainsSupplierName(name));
	}
	
	private void handleSearchByContainsSupplierID() {
		String id = supplierSearchView.getSearchContainsSupplierIDTxtField().getText().trim();
		if (id.isEmpty())
			throw new IllegalArgumentException("Dữ liệu không được để trống");
		suppliers.setSuppliers(SupplierDAO.searchContainsSupplierID(id));
	}
	
	private void handleSearchByContainsPhoneNumber() {
		String phoneNumber = supplierSearchView.getSearchContainsPhoneNumberTxtField().getText().trim();
		if (phoneNumber.isEmpty())
			throw new IllegalArgumentException("Dữ liệu không được để trống");
		suppliers.setSuppliers(SupplierDAO.searchContainsPhoneNumber(phoneNumber));
	}
	
	
	public void resetAll() {
		suppliers.setSuppliers(FetchDataStatus.getSupplierData());
	}

	private void register() {
		
		supplierSearchView.getSearchContainsPhoneNumberBtn().addActionListener(this);
		supplierSearchView.getSearchContainsSupplierIDBtn().addActionListener(this);
		supplierSearchView.getSearchContainsSupplierNameBtn().addActionListener(this);
		
		supplierSearchView.getSearchContainsPhoneNumberTxtField().addActionListener(this);
		supplierSearchView.getSearchContainsSupplierIDTxtField().addActionListener(this);
		supplierSearchView.getSearchContainsSupplierNameTxtField().addActionListener(this);
		
		supplierSearchView.getResetSearchBtn().addActionListener(this);
		
		
	}
}
