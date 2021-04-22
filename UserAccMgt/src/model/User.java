package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	
	private Connection connect()
	{
	Connection con = null;
	try
	{
	Class.forName("com.mysql.jdbc.Driver");
	//Provide the correct details: DBServer/DBName, username, password
	con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
	}
	catch (Exception e)
	{e.printStackTrace();}
	return con;
	}

	
	
	public String insertItem(String code, String name, String password, String desc)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for inserting."; }
	// create a prepared statement
	String query = "insert into useraccmgt (`userId`,`userName`,`userEmail`,`userPassword`,`userPhoneNo`)"+ " values (?, ?, ?, ?, ?)";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, 0);
	preparedStmt.setString(2, code);
	preparedStmt.setString(3, name);
	preparedStmt.setString(4, password);
	preparedStmt.setString(5, desc);
	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Inserted successfully";
	}
	catch (Exception e)
	{
	output = "Error while inserting the item.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	public String readItems()
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for reading."; }
	// Prepare the html table to be displayed
	output = "<table border='1'><tr><th>User Name</th><th>User Email</th>" +"<th>User Password</th>" +"<th>User Phone Number </th>" +"<th>Update</th><th>Remove</th></tr>";
	String query = "select * from useraccmgt";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	// iterate through the rows in the result set
	while (rs.next())
	{
	 String userId = Integer.toString(rs.getInt("userId"));
	 String userName = rs.getString("userName");
	 String userEmail = rs.getString("userEmail");
	 String userPassword = rs.getString("userPassword");
	 String userPhoneNo = rs.getString("userPhoneNo");
	// Add into the html table
	 output += "<tr><td>" + userName + "</td>";
	 output += "<td>" + userEmail + "</td>";
	 output += "<td>" + userPassword + "</td>";
	 output += "<td>" + userPhoneNo + "</td>";
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
	+ "<td><form method='post' action='useraccmgt.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
	+ "<input name='userId' type='hidden' value='" + userId
	+ "'>" + "</form></td></tr>";
	}
	con.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
	output = "Error while reading the items.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	public String updateItem(String ID, String code, String name, String password, String desc)

	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for updating."; }
	// create a prepared statement
	String query = "UPDATE useraccmgt SET userName=?,userEmail=?,userPassword=?,userPhoneNo=?WHERE userId=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setString(1, code);
	preparedStmt.setString(2, name);
	preparedStmt.setString(3, password);
	preparedStmt.setString(4, desc);
	preparedStmt.setInt(5, Integer.parseInt(ID));
	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Updated successfully";
	}
	catch (Exception e)
	{
	output = "Error while updating the item.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	public String deleteItem(String userId)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for deleting."; }
	// create a prepared statement
	String query = "delete from useraccmgt where userId=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(userId));
	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Deleted successfully";
	}
	catch (Exception e)
	{
	output = "Error while deleting the item.";
	System.err.println(e.getMessage());
	}
	return output;
	}

}
