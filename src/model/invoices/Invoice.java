package model.invoices;

import java.util.Date;
import java.util.Objects;

import model.accounts.Account;
import model.customers.Customer;

public class Invoice implements Cloneable {
	private String invoiceID;
	private Account account;
	private Customer customer;
	private Date invoiceDate;
	private double totalAmount;
	
	public Invoice(String invoiceID, Account account, Customer customer, Date invoiceDate, double totalAmount) {
		super();
		this.invoiceID = invoiceID;
		this.account = account;
		this.customer = customer;
		this.invoiceDate = invoiceDate;
		this.totalAmount = totalAmount;
	}
	
	

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}



	@Override
	public int hashCode() {
		return Objects.hash(invoiceID);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		return Objects.equals(invoiceID, other.invoiceID.toLowerCase());
	}



	@Override
	public String toString() {
		return "Invoice [invoiceID=" + invoiceID + ", account=" + account + ", customer=" + customer + ", invoiceDate="
				+ invoiceDate + ", totalAmount=" + totalAmount + "]";
	}



	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	

	public Date getInvoiceDate() {
		return invoiceDate;
	}



	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}



	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
	
	
}
