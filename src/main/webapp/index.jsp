<%@page import="model.Inquiry"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("userID") != null) 
{ 
 Inquiry inquiryObj = new Inquiry(); 
 String stsMsg = ""; 
//Insert--------------------------
if (request.getParameter("hidInquiryIDSave") == "") 
 { 
 stsMsg = inquiryObj.insertInquiry(request.getParameter("userID"),
 request.getParameter("inquirySubject"), 
 request.getParameter("inquiryDate"), 
 request.getParameter("inquiryDesc")); 
 } 
else//Update----------------------
 { 
 stsMsg = inquiryObj.updateInquiry(request.getParameter("hidInquiryIDSave"), 
 request.getParameter("userID"), 
 request.getParameter("inquirySubject"), 
 request.getParameter("inquiryDate"), 
 request.getParameter("inquiryDesc")); 
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidInquiryIDDelete") != null) 
{ 
 Inquiry inquiryObj = new Inquiry(); 
 String stsMsg = inquiryObj.deleteInquiry(request.getParameter("hidInquiryIDDelete")); 
 session.setAttribute("statusMsg", stsMsg); 
}
%>
<!DOCTYPE html>
<html>
	<head>
	
	<meta charset="ISO-8859-1">
	<title>Inquiry Management</title>
	
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/inquiry.js"></script>
	</head>
<body> 
	
	<div class="container"><div class="row"><div class="col-6"> 
	<h1>Inquiry Management</h1>
	
	<form id="formInquiry" name="formInquiry">
	 User ID: 
 	<input id="userID" name="userID" type="text" class="form-control form-control-sm">
 	<br> 
 	Inquiry Subject: 
 	<input id="inquirySubject" name="inquirySubject" type="text" class="form-control form-control-sm">
 	<br> 
 	Inquiry Date: 
 	<input id="inquiryDate" name="inquiryDate" type="text" class="form-control form-control-sm">
 	<br> 
 	Inquiry Description: 
 	<input id="inquiryDesc" name="inquiryDesc" type="text"  class="form-control form-control-sm">
 	<br>
 	<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 	<input type="hidden" id="hidInquiryIDSave"  name="hidInquiryIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<br>

<div id="divInquiryGrid">
 <%
 Inquiry inquiryObj = new Inquiry(); 
  out.print(inquiryObj.readInquiry());
 %>
</div>

</div> </div> </div> 
</body>
</html>
