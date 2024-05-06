package model.invoices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.products.Product;
import model.products.ProductDAO;
import util.DBHelper;
import util.FetchDataStatus;

public class InvoiceDetailsDAO {
	public static void insertInvoiceDetail(InvoiceDetails invoiceDetail) {
	    try (
	        Connection connection = DBHelper.getConnection();
	        PreparedStatement insertStatement = connection.prepareStatement(
	            "INSERT INTO InvoiceDetails(InvoiceID, ProductID, Quantity, SellingPrice, TotalPrice) VALUES (?, ?, ?, ?, ?)"
	        );
	    ) {
	        insertStatement.setString(1, invoiceDetail.getInvoice().getInvoiceID());
	        insertStatement.setString(2, invoiceDetail.getProduct().getProductID());
	        insertStatement.setInt(3, invoiceDetail.getQuantity());
	        insertStatement.setDouble(4, invoiceDetail.getSellingPrice());
	        insertStatement.setDouble(5, invoiceDetail.getTotalPrice());

	        insertStatement.execute();
	    } catch (Exception exception) {
	        exception.printStackTrace();
	        System.exit(1);
	    }
	    
	    FetchDataStatus.invoiceDetailsStatus = false;
	}
	
	public static ArrayList<InvoiceDetails> getData() {
		ArrayList<InvoiceDetails> data = new ArrayList<InvoiceDetails>();
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				){
			String sql = "SELECT InvoiceDetailID, InvoiceID, ProductID, Quantity, SellingPrice, TotalPrice FROM InvoiceDetails";
			ResultSet rs = statement.executeQuery(sql);
			
			ArrayList<Product> productList = FetchDataStatus.getProductData();
			ArrayList<Invoice> invoiceList = FetchDataStatus.getInvoiceData();
			
			while (rs.next()) {
				int invoiceDetailID = rs.getInt(1);
				
				String invoiceID = rs.getString(2);
				Invoice invoice = invoiceList.stream()
						.filter(x -> x.getInvoiceID().equalsIgnoreCase(invoiceID))
						.findFirst()
						.orElse(null);
				
				String productID = rs.getString(3);
				Product product = productList.stream()
						.filter(x -> x.getProductID().equalsIgnoreCase(productID))
						.findFirst()
						.orElse(null);
				
				int quantity = rs.getInt(4);
				double sellingPrice = rs.getDouble(5);
				double totalPrice = rs.getDouble(6);
				
				
				InvoiceDetails invoiceDetails = new InvoiceDetails(
		                invoiceDetailID, 
		                invoice == null ? null : (Invoice)invoice.clone(), 
		                product == null ? null : (Product)product.clone(), 
		                quantity, 
		                sellingPrice, 
		                totalPrice
		            );
				
	
				data.add(invoiceDetails);
			}
				
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data;
	}
	
	public static ArrayList<InvoiceDetails> getDataByInvoiceID(String InvoiceID) {
		ArrayList<InvoiceDetails> data = new ArrayList<InvoiceDetails>();
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT InvoiceDetailID, InvoiceID, ProductID, "
						+ "Quantity, SellingPrice, TotalPrice FROM InvoiceDetails WHERE InvoiceID = ?");
				)
		{
			statement.setString(1, InvoiceID); 
	        ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				int invoiceDetailID = rs.getInt(1);
				
				String invoiceID = rs.getString(2);
				Invoice invoice = InvoiceDAO.getInvoiceByID(invoiceID);
				
				String productID = rs.getString(3);
				Product product = ProductDAO.getProductByID(productID);
				
				int quantity = rs.getInt(4);
				double sellingPrice = rs.getDouble(5);
				double totalPrice = rs.getDouble(6);
				
				InvoiceDetails invoiceDetails = new InvoiceDetails(invoiceDetailID, invoice, product, quantity, sellingPrice, totalPrice);
				data.add(invoiceDetails);
			}
				
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data;
	}
}
