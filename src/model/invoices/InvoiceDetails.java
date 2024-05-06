package model.invoices;

import java.util.Objects;

import model.products.Product;

public class InvoiceDetails {
	private int invoiceDetailID;
	private Invoice invoice;
	private Product product;
	private int quantity;
	private double totalPrice;
	private double sellingPrice;
	
	public InvoiceDetails(int invoiceDetailID, Invoice invoice, Product product, int quantity, double sellingPrice, double totalPrice) {
		super();
		this.invoiceDetailID = invoiceDetailID;
		this.invoice = invoice;
		this.product = product;
		this.quantity = quantity;
		this.sellingPrice = sellingPrice;
		this.totalPrice = totalPrice;
	}
	
	
	
	public InvoiceDetails(Invoice invoice, Product product, int quantity, double sellingPrice, double totalPrice) {
		super();
		this.invoice = invoice;
		this.product = product;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.sellingPrice = sellingPrice;
	}



	@Override
	public String toString() {
		return "InvoiceDetail [invoiceDetailID=" + invoiceDetailID + ", invoice=" + invoice + ", product=" + product
				+ ", quantity=" + quantity + ", totalPrice=" + totalPrice + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(invoiceDetailID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvoiceDetails other = (InvoiceDetails) obj;
		return Objects.equals(invoiceDetailID, other.invoiceDetailID);
	}

	

	public int getInvoiceDetailID() {
		return invoiceDetailID;
	}

	public void setInvoiceDetailID(int invoiceDetailID) {
		this.invoiceDetailID = invoiceDetailID;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	
	
	
	
}
