package model; 
import java.sql.*; 


public class Inquiry 
{ 
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection( "jdbc:mysql://127.0.0.1:3306/inqmng", "root", ""); 
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		} 
		return con; 
	} 


public String readInquiry() 
	{ 
	String output = ""; 
	try
	{ 
		Connection con = connect(); 
		if (con == null) 
		{ 
			return "Error while connecting to the database for reading."; 
		} 
 // Prepare the html table to be displayed
		output = "<table border='1'><tr><th>User ID</th>" 
		 +"<th>Inquiry Subject</th><th>Inquiry Date</th>"
		 +"<th>Inquiry Description</th>" 
		 + "<th>Update</th><th>Remove</th></tr>"; 
		String query = "select * from inquiry"; 
		Statement stmt = con.createStatement(); 
		ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
		while (rs.next()) 
		{ 
			String inquiryID = Integer.toString(rs.getInt("inquiryID")); 
			String userID = rs.getString("userID"); 
			String inquirySubject = rs.getString("inquirySubject"); 
			String inquiryDate = rs.getString("inquiryDate"); 
			String inquiryDesc = rs.getString("inquiryDesc"); 
 
 // Add into the html table
			output += "<tr><td>" + userID + "</td>"; 
			output += "<td>" + inquirySubject + "</td>"; 
			output += "<td>" + inquiryDate + "</td>"; 
			output += "<td>" + inquiryDesc + "</td>"; 
// buttons
			output += "<td><input name='btnUpdate' type='button' value='Update' "
					+ "class='btnUpdate btn btn-secondary' data-inquiryid='" + inquiryID + "'></td>"
					+ "<td><input name='btnRemove' type='button' value='Remove' "
					+ "class='btnRemove btn btn-danger' data-inquiryid='" + inquiryID + "'></td></tr>"; 
		} 
		con.close(); 
 // Complete the html table
		output += "</table>"; 
	} 
	catch (Exception e) 
	{ 
		output = "Error while reading the inquiry."; 
		System.err.println(e.getMessage()); 
	} 
	return output; 
	}






public String insertInquiry(String userID, String inquirySubject, 
		 String inquiryDate, String inquiryDesc) 
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
		 String query = " insert into inquiry (`inquiryID`,`userID`,`inquirySubject`,`inquiryDate`,`inquiryDesc`)"
		 + " values (?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, userID); 
		 preparedStmt.setString(3, inquirySubject); 
		 preparedStmt.setString(4, inquiryDate); 
		 preparedStmt.setString(5, inquiryDesc); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newInquiry = readInquiry(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newInquiry + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the inquiry.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		





public String updateInquiry(String inquiryID, String userID, String inquirySubject, 
		 String inquiryDate, String inquiryDesc) 
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
		 String query = "UPDATE inquiry SET userID=?,inquirySubject=?,inquiryDate=?,inquiryDesc=? WHERE inquiryID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, userID); 
		 preparedStmt.setString(2, inquirySubject); 
		 preparedStmt.setString(3, inquiryDate); 
		 preparedStmt.setString(4, inquiryDesc); 
		 preparedStmt.setInt(5, Integer.parseInt(inquiryID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newInquiry = readInquiry(); 
		 output = "{\"status\":\"success\", \"data\": \"" + newInquiry + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the inquiry.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
		
		
		
		public String deleteInquiry(String inquiryID) 
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
		 String query = "delete from inquiry where inquiryID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(inquiryID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newInquiry = readInquiry(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newInquiry + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the inquiry.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		}


