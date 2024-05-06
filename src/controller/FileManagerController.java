package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import model.categories.Category;
import model.customers.Customer;
import model.employee.Employee;
import model.invoices.Invoice;
import model.products.Product;
import model.suppliers.Supplier;
import util.ExportFileHelper;
import util.FetchDataStatus;
import view.file.FileManagerView;

public class FileManagerController implements ActionListener, MouseListener{
	
	private static FileManagerView fileManagerView;
	
	public FileManagerController(FileManagerView fileManagerView) {
		FileManagerController.fileManagerView = fileManagerView;
		
		// add action
		this.addAction();
		
		// reading data to table
		readingDataFromDBToTable(FetchDataStatus.getEmployeeData(), FetchDataStatus.getProductData(), 
				FetchDataStatus.getCategoryData(), FetchDataStatus.getSupplierData(), 
				FetchDataStatus.getCustomerData(), FetchDataStatus.getInvoiceData());
	}
	
	private void addAction() {
		fileManagerView.getListOption().addMouseListener(this);
		fileManagerView.getBtnLuu().addActionListener(this);
	} 
	
	public static void resetAll() {
		readingDataFromDBToTable(FetchDataStatus.getEmployeeData(), FetchDataStatus.getProductData(), 
				FetchDataStatus.getCategoryData(), FetchDataStatus.getSupplierData(), 
				FetchDataStatus.getCustomerData(), FetchDataStatus.getInvoiceData());
	}
	
	private static void readingDataFromDBToTable(List<Employee> listEmployee, List<Product> listProduct,
		List<Category> listCategory, List<Supplier> listSupplier, List<Customer> listCustomer,
		List<Invoice> listInvoice) {
		
		fileManagerView.getDfTblEmp().setRowCount(0);
		for(Employee e : listEmployee) {
			fileManagerView.getDfTblEmp().addRow(new Object[] {e.getEmployeeID(), 
					e.getLastName(), e.getFirstName(), e.getAddress(), e.getPhoneNumber(),
					e.getEmail(), e.getPosition().toString(), e.getBirthDay(), e.getGender().toString(),
					e.getCoefficientsSalary()});
		}
		
		fileManagerView.getDfTblPrd().setRowCount(0);
		for(Product p : listProduct) {
			fileManagerView.getDfTblPrd().addRow(new Object[] {p.getName(), p.getId(),
					p.getPurchasePrice(), p.getSellingPrice(), p.getCategory().getCategoryID(),
					p.getStock(), p.getUnit(), p.getRecevieDate(), p.getSupplier().getSupplierName()});
		}
		
		fileManagerView.getDfTblCategory().setRowCount(0);
		for(Category c : listCategory) {
			fileManagerView.getDfTblCategory().addRow(new Object[] {c.getCategoryID(), 
					c.getCategoryName(), c.getItemCount()});
		}
		
		fileManagerView.getDfTblSupplier().setRowCount(0);
		for(Supplier s : listSupplier) {
			fileManagerView.getDfTblSupplier().addRow(new Object[] {s.getSupplierID(), s.getSupplierName(),
					s.getAddress(), s.getPhoneNumber(), s.getEmail()});
		}
		
		fileManagerView.getDfTblCus().setRowCount(0);
		for(Customer c : listCustomer) {
			fileManagerView.getDfTblCus().addRow(new Object[] {c.getCustomerID(), c.getLastName(),
					c.getFirstName(), c.getAddress(), c.getPhoneNumber(), c.getCustomerType()});
		}
		
		fileManagerView.getDfTbInvoice().setRowCount(0);
		for(Invoice i : listInvoice) {
			//if(i.getCustomer() == null) throw new IllegalStateException("rong");
			if(i.getCustomer() == null) {
				fileManagerView.getDfTbInvoice().addRow(
					new Object[] {
						i.getInvoiceID(), i.getAccount().getEmployee().getEmployeeID(),
						i.getAccount().getEmployee().getFirstName() + " " + i.getAccount().getEmployee().getLastName(),
						"Vãng lai", "Vãng lai", "Vãng lai", i.getInvoiceDate(), i.getTotalAmount()
					});
			}else {
				fileManagerView.getDfTbInvoice().addRow(
					new Object[] {
						i.getInvoiceID(), i.getAccount().getEmployee().getEmployeeID(),
						i.getAccount().getEmployee().getFirstName() + " " + i.getAccount().getEmployee().getLastName(),
						i.getCustomer().getCustomerID(), i.getCustomer().getLastName() + " " + i.getCustomer().getFirstName(),
						i.getCustomer().getCustomerType().toString(),
						i.getInvoiceDate(), i.getTotalAmount()
					});
			}
			
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(fileManagerView.getBtnLuu())) {
			String object = fileManagerView.getListOption().getSelectedValue();
				
			switch (object) {
				case "Hàng hóa":{
					ExportFileHelper.exportToExcel(fileManagerView, object, fileManagerView.getTblPrd());
					break;
				}
				case "Loại hàng hóa":{
					ExportFileHelper.exportToExcel(fileManagerView, object, fileManagerView.getTblCategory());
					break;
				}
				case "Nhà cung cấp":{
					ExportFileHelper.exportToExcel(fileManagerView, object, fileManagerView.getTblSupplier());
					break;
				}
				case "Khách hàng":{
					ExportFileHelper.exportToExcel(fileManagerView, object, fileManagerView.getTblCus());
					break;
				}
				case "Nhân viên":{
					ExportFileHelper.exportToExcel(fileManagerView, object, fileManagerView.getTblEmployee());
					break;
				}
				case "Hóa đơn":{
					ExportFileHelper.exportToExcel(fileManagerView, object, fileManagerView.getTbInvoice());
					break;
				}
				default:{
					break;
				}	
			}
			
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String value = fileManagerView.getListOption().getSelectedValue();
		switch (value) {
			case "Nhân viên":
				fileManagerView.getCardLayout().show(fileManagerView.getPnlCenter(), "Nhân viên");
				break;
			case "Hàng hóa":
				fileManagerView.getCardLayout().show(fileManagerView.getPnlCenter(), "Hàng hóa");
				break;
			case "Loại hàng hóa":
				fileManagerView.getCardLayout().show(fileManagerView.getPnlCenter(), "Loại hàng hóa");
				break;
			case "Nhà cung cấp":
				fileManagerView.getCardLayout().show(fileManagerView.getPnlCenter(), "Nhà cung cấp");
				break;
			case "Khách hàng":
				fileManagerView.getCardLayout().show(fileManagerView.getPnlCenter(), "Khách hàng");
				break;
			case "Hóa đơn":
				fileManagerView.getCardLayout().show(fileManagerView.getPnlCenter(), "Hóa đơn");
				break;
			default:
				break;
		}
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public FileManagerView getFileManagerView() {
		return fileManagerView;
	}

}
