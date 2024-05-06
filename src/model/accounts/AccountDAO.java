package model.accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.employee.Employee;
import model.employee.EmployeeDAO;
import util.DBHelper;
import util.FetchDataStatus;

public class AccountDAO {
	public static ArrayList<Account> getData() {
		
		ArrayList<Account> data = new ArrayList<Account>();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		String SQLQueryStatement = "SELECT Username, Password, EmployeeID "
			+ "FROM Account";
		
		try {
			con = DBHelper.getConnection();
			preparedStatement = con.prepareStatement(SQLQueryStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			ArrayList<Employee> employeeList = FetchDataStatus.getEmployeeData();
			
			while (rs.next()) {
				String username = rs.getString(1);
				String password = rs.getString(2);
				
				String employeeID = rs.getString(3); 
				
				Employee employee = employeeList.stream()
						.filter(x -> x.getEmployeeID().equalsIgnoreCase(employeeID))
						.findFirst()
						.orElse(null);

				Account account = new Account(username, password, employee);
				
				data.add(account);
			}	
		} catch (Exception e) {
		    // TODO: handle exception
		    e.printStackTrace();
		} finally {
		    try {
		        if(preparedStatement != null) preparedStatement.close();
		        if(con != null) con.close();
		    } catch (Exception e2) {
		        // TODO: handle exception
		    }
		}
		
		return data;
	}
	
	public static Account getLogin(String username, String password) {
		
		Connection con = null;
		PreparedStatement preparedStatement = null;

		String SQLQueryStatement =  "SELECT EmployeeID "
				+ "FROM Account WHERE Username = ? AND Password = ?";

		try {
		    con = DBHelper.getConnection();
		    preparedStatement = con.prepareStatement(SQLQueryStatement);

		    preparedStatement.setString(1, username);
		    preparedStatement.setString(2, password);

		    ResultSet rs = preparedStatement.executeQuery();

		    if (rs.next()) {
				String employeeID = rs.getString(1); 
		        Employee employee = EmployeeDAO.getEmployeeByID(employeeID);

				return new Account(username, password, employee);

		    }

		} catch (Exception e) {
		    // TODO: handle exception
		    e.printStackTrace();
		} finally {
		    try {
		        if(preparedStatement != null) preparedStatement.close();
		        if(con != null) con.close();
		    } catch (Exception e2) {
		        // TODO: handle exception
		    }
		}
		return null;

	}
	
	public static boolean updateAccount(Account a) {
		int n = 0;
		Connection con =  null;
		PreparedStatement preparedStatement = null;
		String SQLQueryStatement = "update Account "
				+ "set Username = ?, Password = ? "
				+ "where EmployeeID = ?";
		try {
			con = DBHelper.getConnection();
			preparedStatement = con.prepareStatement(SQLQueryStatement);
			
			preparedStatement.setString(1, a.getUsername());
			preparedStatement.setString(2, a.getPassword());
			preparedStatement.setString(3, a.getEmployee().getEmployeeID());
			
			n = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		FetchDataStatus.accountStatus = false;
		return n > 0;
	}
	
	public static boolean insertAccount(Account a) {
		int n = 0;
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		String SQLQueryStatement = "insert into Account"
				+ " values(?, ?, ?)";
		try {
			con = DBHelper.getConnection();
			preparedStatement = con.prepareStatement(SQLQueryStatement);
			
			preparedStatement.setString(1, a.getUsername());
			preparedStatement.setString(2, a.getPassword());
			preparedStatement.setString(3, a.getEmployee().getEmployeeID());
			
			n = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		FetchDataStatus.accountStatus = false;
		return n > 0;
	}
	
	public static boolean removeAccount(String id) {
		int n = 0;
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		String SQLQueryStatement = "delete Account "
								+ "where EmployeeID = ?";
		try {
			con = DBHelper.getConnection();
			preparedStatement = con.prepareStatement(SQLQueryStatement);
			
			preparedStatement.setString(1, id);
			
			n = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		FetchDataStatus.accountStatus = false;
		return n > 0;
	}
	
	public static Account getAccountByEmployeeID(String EmployeeID) {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM Account WHERE EmployeeID = ?");
				){
			statement.setString(1, EmployeeID);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String username = rs.getString(1);
				String password = rs.getString(2);
				
				String employeeID = rs.getString(3); 
		        Employee employee = EmployeeDAO.getEmployeeByID(employeeID);

				return new Account(username, password, employee);
			}
			
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
}
