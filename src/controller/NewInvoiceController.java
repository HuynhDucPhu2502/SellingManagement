package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.accounts.Account;
import model.customers.Customer;
import model.customers.CustomerDAO;
import model.customers.CustomerType;
import model.invoices.Invoice;
import model.invoices.InvoiceDAO;
import model.invoices.InvoiceDetails;
import model.invoices.InvoiceDetailsDAO;
import model.products.Product;
import model.products.ProductDAO;
import util.ExportFileHelper;
import util.FetchDataStatus;
import view.invoices.NewInvoiceView;

public class NewInvoiceController implements ActionListener {
	private NewInvoiceView newInvoiceView;
	
	private Customer customer;
	private Account account;
	
	private String nextInvoiceID;
	private ArrayList<Product> products;
	
	private double total;
	private double rate;

	public NewInvoiceController(NewInvoiceView newInvoiceView,  Account account) {
		this.newInvoiceView = newInvoiceView;
		this.customer = null;
		this.account = account;
		
		register();
		
	}
	
	private void register() {
		products = newInvoiceView.getProductsList().getProducts();
		
		handlePhoneNumberTxtField();
		handleNextInvoiceID();
		handleChangeTxtField();
		
		registerProductListTableEvent();
		registerCartListTableEvent();
		
		newInvoiceView.getRemoveAllItemBtn().addActionListener(this);
		newInvoiceView.getRemoveOneItemBtn().addActionListener(this);
		newInvoiceView.getNewInvoiceBtn().addActionListener(this);
		newInvoiceView.getSearchBtn().addActionListener(this);
		newInvoiceView.getSearchTxtField().addActionListener(this);
		newInvoiceView.getResetBtn().addActionListener(this);
		
		newInvoiceView.getEmployeeIDTxtField().setText(account.getEmployee().getEmployeeID());
		newInvoiceView.getEmployeeNameTxtField().setText(account.getEmployee().getFirstName() + " " + account.getEmployee().getLastName());
		
	}
	
	
	// Query the phone number in the txtField. 
	// If the txtField reaches 10 characters, 
	// start searching in the Customer Table.
	private void handlePhoneNumberTxtField() {
		newInvoiceView.getPhoneNumberTxtField().getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				checkLength();	
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkLength();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
			
			private void checkLength() {
                String text = newInvoiceView.getPhoneNumberTxtField().getText();
                if (text.length() == 10) handleSearchingCustomer(text);
                else resetCustomerField();
            }
		});
	}
	
	private void handleChangeTxtField() {
			newInvoiceView.getCustomerPaymentAmountTxtField().getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				calcChangeAmount();	
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				calcChangeAmount();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
			
			private void calcChangeAmount() {
				if (!newInvoiceView.getFinalTotal().getText().isEmpty()) {
					String text = newInvoiceView.getCustomerPaymentAmountTxtField().getText();
					
					double payAmount = 0;
					try {
						payAmount = Double.parseDouble(text);
						if (payAmount <= 0) throw new IllegalArgumentException("Số tiền khách trả phải lớn hơn 0");
					} catch (Exception exception) {
						JOptionPane.showOptionDialog(null, exception.getMessage(), "Hệ thống", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
								null, null, null);
						return;
					}
					
					if (payAmount >= total) {
						DecimalFormat df = new DecimalFormat("#.##");
						newInvoiceView.getChangeAmountTxtField().setText(df.format(payAmount - total));
					}
					else newInvoiceView.getChangeAmountTxtField().setText(null);
				}
            }
		});
	}
	
	private void handleSearchingCustomer(String phoneNumber) {
		customer = CustomerDAO.searchCustomerByPhoneNumber(phoneNumber);
		
		if (customer == null) {
			JOptionPane.showOptionDialog(null, "Số điện thoại không hợp lệ", "Hệ thống", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
					null, null, null);
			
			return;
		}
		
		rate = customer.getCustomerType().equals(CustomerType.MEMBER) ? 0.03 : 0.06;
		
		newInvoiceView.getCustomerIDTxtField().setText(customer.getCustomerID());
		newInvoiceView.getCustomerNameTxtField().setText(customer.getFirstName() + " " + customer.getLastName());
		newInvoiceView.getAddressTxtField().setText(customer.getAddress());
		newInvoiceView.getCustomerTypeTxtField().setText(customer.getCustomerType().toString() + " [Giảm giá " + rate * 100 + " %]");
		
		handleSumSubtotal();
		
	}
	
	private void resetCustomerField() {
		customer = null;
		rate = 0;
		
		newInvoiceView.getCustomerIDTxtField().setText(null);
		newInvoiceView.getCustomerNameTxtField().setText(null);
		newInvoiceView.getAddressTxtField().setText(null);
		newInvoiceView.getCustomerTypeTxtField().setText(null);
		
		handleSumSubtotal();
	}
	
	private void handleNextInvoiceID() {
		Invoice lastInvoice = FetchDataStatus.getInvoiceData().get(FetchDataStatus.getInvoiceData().size() - 1);
		
		if (lastInvoice == null) nextInvoiceID = "HD00001";
		else {
			String numberPart = lastInvoice.getInvoiceID().substring(2);
			int nextNumber = Integer.parseInt(numberPart) + 1;
			nextInvoiceID = "HD" + String.format("%05d", nextNumber);
		}
		
		newInvoiceView.getInvoiceIDTxtField().setText(nextInvoiceID);
	}
	
	
	private void registerProductListTableEvent() {
		newInvoiceView.getProductsListTable().addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) { 
	                try {
	                    String inputStr = JOptionPane.showInputDialog(null, "Nhập số lượng thêm vào giỏ hàng:");
	                    if (inputStr == null || inputStr.trim().isEmpty()) return;
	                    
	                    int total = Integer.parseInt(inputStr);
	                    if (total <= 0) throw new IllegalArgumentException("Số lượng thêm phải từ 0 trở lên");

	                    int selectedRow = newInvoiceView.getProductsListTable().getSelectedRow();
	                    Product productFromProductList = newInvoiceView.getProductsList().getProducts().get(selectedRow);
	                    Product productFromCartList = newInvoiceView.getCartList().getProductByID(productFromProductList.getProductID());
	                    if (productFromCartList != null) total += productFromCartList.getStock();

	                    if (total > productFromProductList.getStock()) throw new IllegalArgumentException("Số lượng trong giỏ hàng phải nhỏ hơn hoặc bằng tồn kho");

	                    if (productFromCartList != null) {
	                        productFromCartList.setStock(total);
	                    } else {
	                        productFromCartList = (Product) productFromProductList.clone();
	                        productFromCartList.setStock(total);
	                        newInvoiceView.getCartList().getProducts().add(productFromCartList);
	                    }
	                    newInvoiceView.getCartList().fireTableDataChanged();
	                    	             
	                    handleSumSubtotal();	   
	                } catch (Exception exception) {
	                    JOptionPane.showOptionDialog(null, exception.getMessage(), "Hệ thống", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
	                }
	            }
	        }
	    });
	}
	
	private void registerCartListTableEvent() {
		newInvoiceView.getCartListTable().addMouseListener(new MouseAdapter() {
			 @Override
		      public void mouseClicked(MouseEvent e) {
				 if (e.getClickCount() == 2) {
					 try {
						 String inputStr = JOptionPane.showInputDialog(null, "Đặt lại số lượng cho hàng:");
		                 if (inputStr == null || inputStr.trim().isEmpty()) return;
		                 
		                 int total = Integer.parseInt(inputStr);
		                 if (total <= 0) throw new IllegalArgumentException("Số lượng thêm phải từ 0 trở lên");
		                 
		                 int selectedRow = newInvoiceView.getCartListTable().getSelectedRow();
		                 Product productFromCartList = newInvoiceView.getCartList().getProducts().get(selectedRow);
		                 Product productFromProductList = products.stream()
		                		 .filter(x -> x.getProductID().equalsIgnoreCase(productFromCartList.getId()))
		                		 .findFirst()
		                		 .orElse(null);
		                 
		                 if (total > productFromProductList.getStock())
		                	 throw new IllegalArgumentException("Số lượng trong giỏ hàng phải nhỏ hơn hoặc bằng tồn kho");
		                 
		                 productFromCartList.setStock(total);
		                 newInvoiceView.getCartList().fireTableDataChanged();
		                 
		                 handleSumSubtotal();	           		          
					 } catch (Exception exception) {
						 JOptionPane.showOptionDialog(null, exception.getMessage(), "Hệ thống", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
					 }
				 }
			 }
		});
	}
	
	private void handleSumSubtotal() {
		DecimalFormat df = new DecimalFormat("#.##");
        
		total = 0;
        newInvoiceView.getCartList().getProducts().forEach(x -> {
        	total += x.getSellingPrice() * x.getStock();
        });
        
        if (total == 0) {
        	 newInvoiceView.getSubTotalTxtField().setText(null);
             newInvoiceView.getVatTxtField().setText(null);
             newInvoiceView.getFinalTotal().setText(null);
             return;
        }
		
        newInvoiceView.getSubTotalTxtField().setText(df.format(total * (1 - rate)));
        newInvoiceView.getVatTxtField().setText(df.format(total * (1 - rate) * 0.1));
        newInvoiceView.getFinalTotal().setText(df.format(total * (1 - rate) * 1.1));
        
        total = Math.round(total * (1 - rate) * 1.1 * 100.0) / 100.0;	
	}
	
	private void handleRemoveOneItem() {
		int selectedRow = newInvoiceView.getCartListTable().getSelectedRow();
		
		if (selectedRow == -1) throw new IllegalArgumentException("Dòng cần xóa không hợp lệ");
		
		newInvoiceView.getCartList().getProducts().remove(selectedRow);
		newInvoiceView.getCartList().fireTableRowsDeleted(selectedRow, selectedRow);
		handleSumSubtotal();
	}
	
	private void handleRemoveAllItem() {
		if (newInvoiceView.getCartList().getProducts().isEmpty())
			throw new IllegalArgumentException("Giỏ hàng đã rỗng");
			
		newInvoiceView.getCartList().getProducts().clear();
		newInvoiceView.getCartList().fireTableDataChanged();
		handleSumSubtotal();
	}
	
	public void resetAll() {
		this.customer = null;
		this.total = 0;
		
		products = FetchDataStatus.getProductData();
		
		handleNextInvoiceID();
		
		resetCustomerField();
		newInvoiceView.getVatTxtField().setText(null);
        newInvoiceView.getFinalTotal().setText(null);
        newInvoiceView.getCustomerPaymentAmountTxtField().setText(null);
        newInvoiceView.getChangeAmountTxtField().setText(null);
        newInvoiceView.getPhoneNumberTxtField().setText(null);
        newInvoiceView.getSearchTxtField().setText(null);
        
        newInvoiceView.getCartList().clearData();
        newInvoiceView.getProductsList().setProducts(products);
        
	}
	
	private void handleNewInvoiceBtn() {
		if ( newInvoiceView.getCartList().getProducts().isEmpty())
			throw new IllegalArgumentException("Giỏ hàng đang rỗng");
		
		Invoice invoice = new Invoice(nextInvoiceID, account, customer, newInvoiceView.getInvoiceDateTxtField().getDate(), total);
		InvoiceDAO.insertInvoice(invoice);
		
		newInvoiceView.getCartList().getProducts().forEach(x -> {
			InvoiceDetails invoiceDetails = new InvoiceDetails(invoice, x, x.getStock(), 
					x.getSellingPrice(),x.getStock() * x.getSellingPrice());
			
			InvoiceDetailsDAO.insertInvoiceDetail(invoiceDetails);
			
			Product product = products.stream()
					.filter(y -> y.getProductID().equalsIgnoreCase(x.getProductID()))
					.findFirst().orElse(null);
			int newStock = product.getStock() - x.getStock();
			ProductDAO.updateStock(x.getProductID(), newStock);
		});
		
		// xuất hóa đơn
		
		try {
			ExportFileHelper.printInvoice(invoice);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
			
		resetAll();
	}

	private void handleSearch() {
		String searchInput = newInvoiceView.getSearchTxtField().getText().trim();
		
		if (searchInput.isEmpty()) newInvoiceView.getProductsList().setProducts(products);
		else {
			ArrayList<Product> res = ProductDAO.searchContainsName(searchInput);
			newInvoiceView.getProductsList().setProducts(res);
		}
			
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		try {
			if (source == newInvoiceView.getRemoveOneItemBtn()) handleRemoveOneItem();
			else if (source == newInvoiceView.getRemoveAllItemBtn()) handleRemoveAllItem();
			else if (source == newInvoiceView.getNewInvoiceBtn()) handleNewInvoiceBtn();
			else if (source == newInvoiceView.getSearchTxtField() || source == newInvoiceView.getSearchBtn()) handleSearch();
			else if (source == newInvoiceView.getResetBtn()) resetAll();
			
		} catch (Exception exception) {
			JOptionPane.showOptionDialog(null, exception.getMessage(), "Hệ thống", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
					null, null, null);
		}
	}
	
	
	
	
}
