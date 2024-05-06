package controller;

import util.FetchDataStatus;
import view.invoices.InvoiceManagerView;

public class InvoiceManagerController {
	private InvoiceManagerView invoiceManagerView;

	public InvoiceManagerController(InvoiceManagerView invoiceManagerView) {
		this.invoiceManagerView = invoiceManagerView;
	}
	
	public void register() {
		
	}
	
	public void resetAll() {
		invoiceManagerView.getInvoices().setInvoices(FetchDataStatus.getInvoiceData());
		invoiceManagerView.getInvoiceDetailsList().setInvoiceDetailsList(FetchDataStatus.getInvoiceDetailsData());
	}
}
