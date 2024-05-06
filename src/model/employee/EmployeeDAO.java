package model.employee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.DBHelper;
import util.FetchDataStatus;

public class EmployeeDAO {
	
	public static ArrayList<Employee> getData() {
		ArrayList<Employee> data = new ArrayList<>();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		String SQLQueryStatement = "SELECT EmployeeID, LastName, FirstName, Address, PhoneNumber,  "
				+ "Email, Position, Birth, Gender, CoefficientsSalary  FROM Employee";
		try {
			con = DBHelper.getConnection();
			preparedStatement = con.prepareStatement(SQLQueryStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String employeeID = rs.getString(1);
				String lastName = rs.getString(2);
				String firstName = rs.getString(3);
				String address = rs.getString(4);
				String phoneNum = rs.getString(5);
				String email = rs.getString(6);
				
				EmployeePosition ePosition = rs.getString(7).equalsIgnoreCase(EmployeePosition.SALE_EMPLOYEE.toString()) 
						? EmployeePosition.SALE_EMPLOYEE : EmployeePosition.MANAGER;
				
				Date birthDay = rs.getDate(8);
				
				Gender gender = rs.getString(9).equalsIgnoreCase(Gender.MALE.toString())
						? Gender.MALE : Gender.FEMALE;
				
				double coefficientsSalary = rs.getDouble(10);
				
				Employee employee = new Employee(employeeID, lastName, firstName, address, 
						phoneNum, email, ePosition, birthDay.toLocalDate(), gender, coefficientsSalary);
				
				data.add(employee);
			}
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
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
	
	public static boolean insertData(Employee e) {
		int n = 0;
		Connection con = null;
		PreparedStatement insertStatement = null;
		String SQLQueryStament = "INSERT INTO Employee VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			con = DBHelper.getConnection();
			insertStatement = con.prepareStatement(SQLQueryStament);
			
			insertStatement.setString(1, e.getEmployeeID());
			insertStatement.setString(2, e.getLastName());
			insertStatement.setString(3, e.getFirstName());
			insertStatement.setString(4, e.getAddress());
			insertStatement.setString(5, e.getPhoneNumber());
			insertStatement.setString(6, e.getEmail());
			insertStatement.setString(7, e.getPosition().toString());
			insertStatement.setDate(8, Date.valueOf(e.getBirthDay()));
			insertStatement.setString(9, e.getGender().toString());
			insertStatement.setDouble(10, e.getCoefficientsSalary());
			
			n = insertStatement.executeUpdate();
			
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		} finally {
			try {
				if(insertStatement != null) insertStatement.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		FetchDataStatus.employeeStatus = false;
		return n > 0;
	}
	
	public static boolean deleteData(String eID) {
		int n = 0;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		String SQLQueryStatement = "delete from Employee "
				+ "where EmployeeID = ?";
		try {
			con = DBHelper.getConnection();
			preparedStatement = con.prepareStatement(SQLQueryStatement);
			
			preparedStatement.setString(1, eID);
			
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
		
		FetchDataStatus.employeeStatus = false;
		return n > 0;
	}
	
	public static boolean updateData(Employee e) {
		int n = 0;
		Connection con = null;
		PreparedStatement preparedStatement  = null;
		String SQLQueryStatement = "update Employee "
				+ "SET LastName = ?, FirstName = ?, Address = ?, PhoneNumber = ?, "
				+ "Email = ?, Position = ?, Birth = ?, Gender = ?, CoefficientsSalary = ? "
				+ "WHERE EmployeeID = ?";
		
		try {
			con = DBHelper.getConnection();
			preparedStatement = con.prepareStatement(SQLQueryStatement);
			
			preparedStatement.setString(1, e.getLastName());
			preparedStatement.setString(2, e.getFirstName());
			preparedStatement.setString(3, e.getAddress());
			preparedStatement.setString(4, e.getPhoneNumber());
			preparedStatement.setString(5, e.getEmail());
			preparedStatement.setString(6, e.getPosition().toString());
			preparedStatement.setDate(7, Date.valueOf(e.getBirthDay()));
			preparedStatement.setString(8, e.getGender().toString());
			preparedStatement.setDouble(9, e.getCoefficientsSalary());
			preparedStatement.setString(10, e.getEmployeeID());
			
			n = preparedStatement.executeUpdate();
		} catch (Exception e1) {
		    e1.printStackTrace();
		} finally {
		    try {
		        if (preparedStatement != null) preparedStatement.close();
		        if (con != null) con.close();
		    } catch (SQLException e2) {
		        e2.printStackTrace();
		    }
		}
		
		FetchDataStatus.employeeStatus = false;
		return n > 0;
	}
	
	public static Employee getEmployeeByID(String id) {
		Connection con = null;
		PreparedStatement preparedStatement = null;

		String sql = "select * "
		            + "from Employee "
		            + "where EmployeeID = ?";
		try {
		    con = DBHelper.getConnection();
		    preparedStatement = con.prepareStatement(sql);
		    
		    preparedStatement.setString(1, id);
		    
		    ResultSet rs = preparedStatement.executeQuery();
		    
		    if (rs.next()) { 
		        String employeeID = rs.getString(1);
		        String lastName = rs.getString(2);
		        String firstName = rs.getString(3);
		        String address = rs.getString(4);
		        String phoneNum = rs.getString(5);
		        String email = rs.getString(6);
		        
		        EmployeePosition ePosition = rs.getString(7).equalsIgnoreCase(EmployeePosition.SALE_EMPLOYEE.toString()) 
		                ? EmployeePosition.SALE_EMPLOYEE : EmployeePosition.MANAGER;
		        
		        Date birthDay = rs.getDate(8);
		        
		        Gender gender = rs.getString(9).equalsIgnoreCase(Gender.MALE.toString())
		                ? Gender.MALE : Gender.FEMALE;
		        
		        double coefficientsSalary = rs.getDouble(10);
		        
		        return new Employee(employeeID, lastName, firstName, address, 
		                phoneNum, email, ePosition, birthDay.toLocalDate(), gender, coefficientsSalary);
		    }
		    
		} catch (Exception exception) {
		    exception.printStackTrace();
		} finally {
		    try {
		        if (preparedStatement != null) preparedStatement.close();
		        if (con != null) con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		return null;
	}
	
	public static String getLastEmployeeID() {
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				){
			String sql = "SELECT TOP 1 EmployeeID FROM Employee ORDER BY EmployeeID DESC";
			ResultSet rs = statement.executeQuery(sql);
			
			if (rs.next()) return rs.getString(1);
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
}
