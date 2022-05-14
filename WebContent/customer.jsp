<%@page import="com.CustomerManage.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Customer Management</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery.min.js"></script>
		<script src="Components/customer.js"></script>
	</head>
	
	<style>
	body {
		background-image: url("image/powersystem1.jpg");
		background-repeat: no-repeat;
		background-size: cover;
	}
</style>

	<body> 
		<div class="container"><div class="row"><div class="col-6"> 
		<div style = "background-color:white; width:500px"><br><h1>&nbsp;&nbsp;&nbsp; Customer Management </h1>
		
			<form id="formCustomer" name="formCustomer">
			
 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Customer Name : 
 				<input id="Customer_name" name="Customer_name" type="text" placeholder="Enter Customer Name..."
 				class="form-control form-control-sm" style="width:300px;margin-left:60px"> <br>
			    
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Customer Address : 
 				<input id="Customer_address" name="Customer_address" type="text" placeholder="Enter Customer Address..."
 				class="form-control form-control-sm" style="width:300px;margin-left:60px"><br> 
 				
 				&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Customer NIC : 
 				<input id="Customer_nic" name="Customer_nic" type="text" placeholder="Enter Customer NIC..."
				class="form-control form-control-sm" style="width:300px;margin-left:60px"><br>
 				
 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Customer Email : 
 				<input id="Customer_email" name="Customer_email" type="text" placeholder="Enter Customer Email..."
 				class="form-control form-control-sm" style="width:300px;margin-left:60px"><br> 
 				
 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Customer Mobile No : 
 				<input id="Customer_mobileNo" name="Customer_mobileNo" type="text" placeholder="Enter Customer Mobile No..."
 				class="form-control form-control-sm" style="width:300px;margin-left:60px"><br>
 				
 				<input id="btnSave" name="btnSave" type="button" value="Save" 
 				class="btn btn-primary" style="margin-left:200px">
 				<input type="hidden" id="hidCustomerIDSave" 
				name="hidCustomerIDSave" value="">
				
			</form><br></div>
			
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		
		<div id="divCusGrid">
 		<%
 		Customer cusObj = new Customer(); 
 		out.print(cusObj.readCustomer()); 
 		%>
	</div>
	
	</div> </div> </div> 
</body>
</html>
