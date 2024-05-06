package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import model.suppliers.Supplier;
import model.suppliers.SupplierDAO;
import model.suppliers.Suppliers;
import util.FetchDataStatus;
import view.products.ProductManagerView;
import view.suppliers.SupplierManagerView;

public class SupplierController implements ActionListener {
	private SupplierManagerView supplierManagerView;
	private ProductManagerView productManagerView;
	
	private Suppliers suppliers;
	
	private String nextSupplierID;

	public SupplierController(SupplierManagerView supplierManagerView, ProductManagerView productManagerView) {
		this.supplierManagerView = supplierManagerView;
		this.productManagerView = productManagerView;
		
		this.suppliers = supplierManagerView.getSuppliers();
		
		register();
	}
	
	private void register() {
		supplierManagerView.getAddBtn().addActionListener(this);
		supplierManagerView.getRemoveBtn().addActionListener(this);
		supplierManagerView.getUpdateBtn().addActionListener(this);
		handleTableSelection();
		handleNextSupplierID();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		try {
			if (source == supplierManagerView.getAddBtn()) handleAddBtn();
			else if (source == supplierManagerView.getRemoveBtn()) handleRemoveBtn();
			else if (source == supplierManagerView.getUpdateBtn()) handleUpdateBtn();
		} catch (Exception exception) {
			JOptionPane.showOptionDialog(null, exception.getMessage(), "Hệ thống", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
					null, null, null);
		}
	}
	
	public void resetAll() {
		suppliers.setSuppliers(FetchDataStatus.getSupplierData());
		reloadCBox();
		handleNextSupplierID();
	}
	
	private void handleAddBtn() {
		String supplierID = supplierManagerView.getSupplierIDTxtField().getText().trim();
		String supplierName = supplierManagerView.getSupplierNameTxtField().getText().trim();
		String address = supplierManagerView.getAddressTxtField().getText().trim();
		String phoneNumber = supplierManagerView.getPhoneNumberTxtField().getText().trim();
		String email = supplierManagerView.getEmailTxtField().getText().trim();
		
		validData(supplierID, supplierName, address, phoneNumber, email);
		
		Supplier supplier = new Supplier(supplierID, supplierName, address, phoneNumber, email);
	
		SupplierDAO.insertData(supplier);
		
		suppliers.setSuppliers(FetchDataStatus.getSupplierData());
		
		reloadCBox();
		handleNextSupplierID();
		
	}
	
	private void handleRemoveBtn() {
		int selectedIndex = supplierManagerView.getSupplierTable().getSelectedRow();
		
		if (selectedIndex == -1)
			throw new IllegalArgumentException("Vui lòng chọn dòng cần xóa");
		
		int option = JOptionPane.showOptionDialog(null, "Bạn có chắc chắn muốn xóa?", "Hệ thống", 
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, 
				null, null, null);
		
		if (option == JOptionPane.YES_OPTION) {
			Supplier supplier = suppliers.getSuppliers().get(selectedIndex);
			SupplierDAO.deleteData(supplier.getSupplierID());
		
			suppliers.setSuppliers(FetchDataStatus.getSupplierData());
			
			reloadCBox();
			handleNextSupplierID();
		}
	}
	
	private void handleUpdateBtn() {
		int selectedIndex = supplierManagerView.getSupplierTable().getSelectedRow();
		
		if (selectedIndex == -1)
			throw new IllegalArgumentException("Vui lòng chọn dòng cần cập nhật");
		
		String supplierID = supplierManagerView.getSupplierIDTxtField().getText().trim();
		String supplierName = supplierManagerView.getSupplierNameTxtField().getText().trim();
		String address = supplierManagerView.getAddressTxtField().getText().trim();
		String phoneNumber = supplierManagerView.getPhoneNumberTxtField().getText().trim();
		String email = supplierManagerView.getEmailTxtField().getText().trim();
		
		validData(supplierID, supplierName, address, phoneNumber, email);
		
		Supplier supplier = new Supplier(supplierID, supplierName, address, phoneNumber, email);
		SupplierDAO.updateData(supplier);
		
		suppliers.setSuppliers(FetchDataStatus.getSupplierData());
		
		reloadCBox();
		handleNextSupplierID();
	}
	
	private void reloadCBox() {
		ArrayList<String> suppliersName = suppliers.getSuppliers()
				.stream()
				.map(x -> x.getSupplierName()).collect(Collectors.toCollection(ArrayList::new));
		
		productManagerView.getSupplierComBoxModel().removeAllElements();
		productManagerView.getSupplierComBoxModel().addAll(suppliersName);
	}
	
	private void handleNextSupplierID() {
		
		if (suppliers.getSuppliers().isEmpty()) nextSupplierID = "SPL001";
		else {
			String lastSupplierID = suppliers.getSuppliers().get(suppliers.getSuppliers().size() - 1).getSupplierID();
			String numberPart = lastSupplierID.substring(3);
			int nextNumber = Integer.parseInt(numberPart) + 1;
			nextSupplierID = "SPL" + String.format("%03d", nextNumber);
		}
		
		supplierManagerView.getSupplierIDTxtField().setText(nextSupplierID);
	}
	
	private void validData(String supplierID, String supplierName, String address, String phoneNumber, String email) {
		if (supplierID.isEmpty() || supplierName.isEmpty() || address.isEmpty() 
				||	phoneNumber.isEmpty() || email.isEmpty())
			throw new IllegalArgumentException("Trường dữ liệu không được để trống");
		
		Supplier supplier = suppliers.getSuppliers()
				.stream()
				.filter(x -> x.getSupplierName().equalsIgnoreCase(supplierName))
				.findFirst().orElse(null);
		
		if (supplier != null)
			throw new IllegalArgumentException("Mã nhà cung cấp không được trùng");
		
	}
	
	private void handleTableSelection() {
		supplierManagerView.getSupplierTable().getSelectionModel().addListSelectionListener(event -> {
			if (!event.getValueIsAdjusting()) {
				int selectedIndex = supplierManagerView.getSupplierTable().getSelectedRow();
				if (selectedIndex != -1) showTxtField(selectedIndex);
				else resetTxtField();
			}
		});
	}
	
	private void resetTxtField() {
		supplierManagerView.getSupplierIDTxtField().setText(null);
		supplierManagerView.getSupplierNameTxtField().setText(null);
		supplierManagerView.getAddressTxtField().setText(null);
		supplierManagerView.getPhoneNumberTxtField().setText(null);
		supplierManagerView.getEmailTxtField().setText(null);
		handleNextSupplierID();
	}
	
	private void showTxtField(int selectedIndex) {
		Supplier supplier = suppliers.getSuppliers().get(selectedIndex);
		supplierManagerView.getSupplierIDTxtField().setText(supplier.getSupplierID());
		supplierManagerView.getSupplierNameTxtField().setText(supplier.getSupplierName());
		supplierManagerView.getAddressTxtField().setText(supplier.getAddress());
		supplierManagerView.getPhoneNumberTxtField().setText(supplier.getPhoneNumber());
		supplierManagerView.getEmailTxtField().setText(supplier.getEmail());
	}
	
	
	
	
}
