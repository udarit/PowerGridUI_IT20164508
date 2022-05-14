$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 	$("#alertSuccess").hide();
	 }
	 	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();

// Form validation-------------------
var status = validateCusForm();
	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
     }
 
// If valid------------------------
var type = ($("#hidCustomerIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "CustomerAPI",
 type : type,
 data : $("#formCustomer").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onCustomerSaveComplete(response.responseText, status);
 }
 });
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidCustomerIDSave").val($(this).data("customerid"));
 $("#Customer_name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#Customer_address").val($(this).closest("tr").find('td:eq(1)').text());
 $("#Customer_nic").val($(this).closest("tr").find('td:eq(2)').text());
 $("#Customer_email").val($(this).closest("tr").find('td:eq(3)').text());
 $("#Customer_mobileNo").val($(this).closest("tr").find('td:eq(4)').text());
});

//DELETE==========================================================
$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "CustomerAPI",
 type : "DELETE",
 data : "Customer_ID=" + $(this).data("customerid"),
 dataType : "text",
 complete : function(response, status)
 {
 onCustomerDeleteComplete(response.responseText, status);
 }
 });
});

// CLIENT-MODEL================================================================
function validateCusForm()
{
	
//customer name-----------------------------
if ($("#Customer_name").val().trim() == "")
 {
 return "Insert Customer_name.";
 }

// customer address----------------------------------
if ($("#Customer_address").val().trim() == "")
 {
 return "Insert Customer_address.";
 } 

// customer NIC----------------------------------
if ($("#Customer_nic").val().trim() == "")
 {
 return "Insert Customer_nic.";
 }

// customer email-------------------------------
if ($("#Customer_email").val().trim() == "")
 {
 return "Insert Customer_email.";
 }
  
 // customer Phone-------------------------------
if ($("#Customer_mobileNo").val().trim() == "")
 {
 return "Insert Customer_mobileNo.";
 }
 
return true;
}

function onCustomerSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divCusGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
$("#hidCustomerIDSave").val("");
 $("#formCustomer")[0].reset();
}

function onCustomerDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divCusGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}
