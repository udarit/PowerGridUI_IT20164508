package com.CustomerManage;
import java.sql.*;

public class Customer {
	
	public Connection connect()
	{
			Connection con = null;
			try
			{
			//database connectivity
				Class.forName("com.mysql.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafelectricity", "root", "");
			
				//For testing
				System.out.print("Successfully connected");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return con;
	}
	
public String insertCustomer(String Customer_name, String Customer_address, String Customer_nic, String Customer_email, String Customer_mobileNo)
		{
			String output = "";
			try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into customer1 (`Customer_ID`,`Customer_name`,`Customer_address`,`Customer_nic`,`Customer_email`,`Customer_mobileNo`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Customer_name);
			preparedStmt.setString(3, Customer_address);
			preparedStmt.setString(4, Customer_nic);
			preparedStmt.setString(5, Customer_email);
			preparedStmt.setString(6, Customer_mobileNo);


			// execute the statement
			preparedStmt.execute();
			con.close();
			String newCustomer = readCustomer();
			output = "{\"status\":\"success\", \"data\": \"" +newCustomer + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the customer1.\"}";
			System.err.println(e.getMessage());
		}
			
		return output;
	}

public String readCustomer()
{
		String output = "";
		try
		{
				Connection con = connect();
					if (con == null)
					{
						return "Error while connecting to the database for reading.";
					}

					//Prepare the HTML table to be displayed
					output = "<table border='3'>"
							+ "<tr><th>Customer Name</th>"
							+"<th>Customer Address</th>"
							+ "<th>Customer Nic</th>"
							+ "<th>Customer Email</th>"
							+ "<th>Customer Mobile</th>"
							+ "<th>Update</th><th>Remove</th></tr>";
					
					String query = "select * from customer1";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					// iterate through the rows in the result set
					while (rs.next())
					{
						String Customer_ID  = Integer.toString(rs.getInt("Customer_ID"));
						String Customer_name  = rs.getString("Customer_name");
						String Customer_address  = rs.getString("Customer_address");
						String Customer_nic =rs.getString("Customer_nic");
						String Customer_email  = rs.getString("Customer_email");
						String Customer_mobileNo = rs.getString("Customer_mobileNo");



						// Add a row into the HTML table
						output += "<tr><td><input id='hidCustomerIDUpdate'name='hidCustomerIDUpdate'type='hidden' value='" + Customer_ID  + "'>"+ Customer_name  + "</td>";
						output += "<td>" + Customer_address + "</td>";
						output += "<td>" + Customer_nic + "</td>";
						output += "<td>" + Customer_email + "</td>";
						output += "<td>" + Customer_mobileNo + "</td>";


						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-customerid='" + Customer_ID + "'></td>"
								+"<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-customerid='" + Customer_ID + "'></td></tr>"; 
					}
					con.close();


					// Complete the HTML table
					output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the customer1.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	public String deleteCustomer(String Customer_ID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}


			// create a prepared statement
			String query = "delete from customer1 where Customer_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Customer_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			//output = "Deleted successfully";
			String newCustomer = readCustomer();
			output = "{\"status\":\"success\", \"data\": \"" +newCustomer + "\"}";
			}
		catch (Exception e)
		{
			//output = "Error while deleting the customer.";
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the customer1.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	//method to update bill details in DB
	public String updateCustomer(String Customer_ID, String Customer_name, String Customer_address,String Customer_nic,String Customer_email,String Customer_mobileNo)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
				
			// create a prepared statement
			String query = "UPDATE customer1 SET Customer_name=?,Customer_address=?,Customer_nic=?,Customer_email=?,Customer_mobileNo=? WHERE Customer_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, Customer_name);
			preparedStmt.setString(2, Customer_address);
			preparedStmt.setString(3, Customer_nic);
			preparedStmt.setString(4, Customer_email);
			preparedStmt.setString(5, Customer_mobileNo);
			preparedStmt.setInt(6, Integer.parseInt(Customer_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Updated Customer details successfully";
			String newCustomer = readCustomer();
			output = "{\"status\":\"success\", \"data\": \"" +newCustomer + "\"}"; }
		catch (Exception e)
		{
			//output = "Error while updating the Customer Details.";
			output = "{\"status\":\"error\", \"data\":\"Error while updating the customer1.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}


}

