<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="CustomerStuff.*"%>
<%@page import="Controllers.*"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="InputFormStuff/style.css">
    <title>Update Customer</title>
  </head>
  <body>
    <%
      User user = (User) session.getAttribute("user");
      if(user == null){
        response.sendRedirect("LoginPage.jsp");
        return;
      }
      Customer customer = (Customer) request.getAttribute("customer");
      Address address = customer.getAddress();
    %>
    <div class="container-fluid bg-body-tertiary text-light py-3">
        <header class="text-center">
          <h1 class="display-6">Update Food Product</h1>
        </header>
    </div>
    <section class="container my-2 bg-dark w-50 text-light p-2">
      <form class="row g-3 p-3" method="post" action="update-customer">
        <div class="col-md-6">
          <input type="hidden" id="id" name="idCustomer" value="<%= customer.getCustomerID() %>">
          <input type="hidden" id="id" name="idAddress" value="<%= address.getAddressID() %>">
          <label for="inputBusinessName" class="form-label">Business Name : <%= customer.getBusinessName() %></label>
          <input type="text" name="BusinessName" value="<%= customer.getBusinessName() %>" class="form-control" id="inputBusinessName" placeholder="Enter Business" required>
        </div>
        <div class="col-md-6">
          <label for="inputAddressLine1" class="form-label">Address Line 1 : <%= address.getAddressLine1() %></label>
          <input type="text" name="AddressLine1" value="<%= address.getAddressLine1() %>" class="form-control" id="inputAddressLine1" placeholder="Enter Address Line 1" required>
        </div>
        <div class="col-md-6">
          <label for="inputAddressLine2" class="form-label">Address Line 2 : <%= address.getAddressLine2() %></label>
          <input type="text" name="AddressLine2" value="<%= address.getAddressLine2() %>" class="form-control" id="inputAddressLine2" placeholder="Enter Address Line 2" required>
        </div>
        <div class="col-md-6">
          <label for="inputAddressLine3" class="form-label">Address Line 3 : <%= address.getAddressLine3() %></label>
          <input type="text" name="AddressLine3" value="<%= address.getAddressLine3() %>" class="form-control" id="inputAddressLine3" placeholder="Enter Address Line 3" required>
        </div>
        <div class="col-md-6">
          <label for="inputCountry" class="form-label">Country : <%= address.getCountry() %></label>
          <input type="text" name="Country" value="<%= address.getCountry() %>" class="form-control" id="inputCountry" placeholder="Enter Country" required>
        </div>
        <div class="col-md-6">
          <label for="inputPostCode" class="form-label">Post Code : <%= address.getPostCode() %></label>
          <input type="text" name="PostCode" value="<%= address.getPostCode() %>" class="form-control" id="inputPostCode" placeholder="Enter Post Code" required>
        </div>
        <div class="col-md-6">
          <label for="inputTelephoneNumber" class="form-label">Telephone Number : <%= customer.getTelephoneNumber() %></label>
          <input type="text" value="<%= customer.getTelephoneNumber() %>" name="TelephoneNumber" class="form-control" id="inputTelephoneNumber" placeholder="Enter Telephone Number" required>
        </div>
        <div class="col-12 d-flex justify-content-between">
          <button type="submit" class="btn btn-primary">Update Customer</button>
          <a type="button" href="ShowAllCustomer.jsp" class="btn btn-success">Back</a>
        </div>
      </form>
    </section>
  </body>
</html>