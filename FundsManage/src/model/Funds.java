package model;
import java.sql.*;
public class Funds
{ //A common method to connect to the DB
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
public String insertItem(String code,String price, String desc)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = " insert into funds(`FundID`,`FundingProjectName`,`Amount`,`FundEstimatedBy`)"+ " values (?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, code);
 preparedStmt.setDouble(3, Double.parseDouble(price));
 preparedStmt.setString(4, desc);
// execute the statement
 preparedStmt.execute();
 con.close();
 output = "Inserted successfully";
 }
 catch (Exception e)
 {
 output = "Error while inserting the funds.";
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
 output = "<table border='1'><tr><th>FundID</th><th>FundingProjectName</th>" +
 "<th>Amount</th>" +
 "<th>FundEstimatedBy</th>" +
 "<th>Update</th><th>Remove</th></tr>";

 String query = "select * from funds";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String itemID = Integer.toString(rs.getInt("FundID"));
 String itemCode = rs.getString("FundingProjectName");
 String itemPrice = Double.toString(rs.getDouble("Amount"));
 String itemDesc = rs.getString("FundEstimatedBy");
 // Add into the html table
 output += "<tr><td>" + itemID + "</td>";
 output += "<td>" + itemCode + "</td>";
 output += "<td>" + itemPrice + "</td>";
 output += "<td>" + itemDesc + "</td>";
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
 + "<td><form method='post' action='Funds.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='FundID' type='hidden' value='" + itemID
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
public String updateItem(String ID, String code, String price, String desc)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE items SET itemCode=?,itemName=?,itemPrice=?,itemDesc=?WHERE itemID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setString(1, code);
preparedStmt.setDouble(2, Double.parseDouble(price));
preparedStmt.setString(3, desc);
preparedStmt.setInt(4, Integer.parseInt(ID));
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
public String deleteItem(String itemID)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for deleting."; }
// create a prepared statement
String query = "delete from funds where FundID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, Integer.parseInt(itemID));
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
