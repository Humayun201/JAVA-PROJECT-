package hotel;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.sql.Statement;

public class Customer {

	int customerID;
	String custName;
	String addr;
	String phone;
	String Type;
	static int j=0;
	
	
	Customer()
	{
		
	}
	
	
	public Customer(int customerID, String custName, String addr, String phone, String Type) {
		super();
		this.customerID = customerID;
		this.custName = custName;
		this.addr = addr;
		this.phone = phone;
		this.Type = Type;
	}

	public void printCustomer()
	{
		System.out.println("Customer Name : "+this.custName);
		System.out.println("Customer Address : "+this.addr);
		System.out.println("Customer Phone : "+this.phone);
	}
	
	
//
public void addCustomer(String oper, Customer cust) {
//	try {
//		if (oper.equals("i")) { // Use equals() to compare strings
//
//			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "H@mayun123");
//			try (PreparedStatement ps = connection.prepareStatement(
//					"INSERT INTO customer (Name, Address, Phone, Type) VALUES (?, ?, ?, ?)")) {
//
//				ps.setString(1, cust.custName);
//				ps.setString(2, cust.addr);
//				ps.setString(3, cust.phone);
//				ps.setString(4, cust.Type);
//
//				if (ps.executeUpdate() > 0) {
//					JOptionPane.showMessageDialog(null, "New Customer Added");
//				}
//			}
//		}
//	} catch (Exception ex) {
//		ex.printStackTrace();
//	}

	try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "H@mayun123");
		   if(oper.equals("i")){
			   String Qurey ="INSERT INTO customer (Name, Address, Phone, Type) VALUES (?, ?, ?, ?)";
			   PreparedStatement preparedStatement=connection.prepareStatement(Qurey);

			   preparedStatement.setString(1, cust.custName);
			   preparedStatement.setString(2, cust.addr);
			   preparedStatement.setString(3, cust.phone);
			   preparedStatement.setString(4, cust.Type);

			   if (preparedStatement.executeUpdate() > 0) {
				   JOptionPane.showMessageDialog(null, "New Customer Added");
			   }
		   }


	}
	catch(Exception ex){
		ex.printStackTrace();
	}
}




	public void deleteCustomer(String oper,Customer cust)
	{
//		 PreparedStatement ps = null;
//		    ResultSet result = null;
				try {
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "H@mayun123");

			    	 if(oper=="d")
			         {
						 String DeleteQuery="DELETE FROM customer where Name=?";
						 PreparedStatement ps=connection.prepareStatement(DeleteQuery);

			        	 ps=connection.prepareStatement("DELETE FROM customer where Name=?");
			        	 ps.setString(1,cust.custName);

			             ps.executeUpdate();
			                JOptionPane.showMessageDialog(null,"Deleted Sucessfully");

			         }
			    	}
			    	
			    	catch(Exception ex)
			    	{
			    		ex.printStackTrace();
			    	}
	}
	
	
	
}
