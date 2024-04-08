<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="FoodProductStuff.*"%>
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
    <title>Update Food Product</title>
  </head>
  <body>
    <%
      User user = (User) session.getAttribute("user");
      if(user == null){
        response.sendRedirect("LoginPage.jsp");
        return;
      }
      FoodProduct foodproduct = (FoodProduct) request.getAttribute("FoodProduct");
    %>
    <div class="container-fluid bg-body-tertiary text-light py-3">
        <header class="text-center">
          <h1 class="display-6">Update Food Product</h1>
        </header>
    </div>
    <section class="container my-2 bg-dark w-50 text-light p-2">
      <form class="row g-3 p-3" method="post" action="update-foodproduct">
        <div class="col-md-6">
          <input type="hidden" id="id" name="id" value="<%= foodproduct.getId() %>">
          <label for="inputSKU" class="form-label">SKU : <%= foodproduct.getSKU() %></label>
          <input type="text" name="SKU" value="<%= foodproduct.getSKU() %>" class="form-control" id="inputSKU" placeholder="Enter SKU" required>
        </div>
        <div class="col-md-6">
          <label for="inputDescription" class="form-label">Description : <%= foodproduct.getDescription() %></label>
          <input type="text" name="description" value="<%= foodproduct.getDescription() %>" class="form-control" id="inputDescription" placeholder="Enter Description" required>
        </div>
        <div class="col-md-6">
          <label for="inputCategory" class="form-label">Category : <%= foodproduct.getCategory() %></label>
          <input type="text" name="category" value="<%= foodproduct.getCategory() %>" class="form-control" id="inputCategory" placeholder="Enter Category" required>
        </div>
        <div class="col-md-6">
          <label for="inputPrice" class="form-label">Price : <%= foodproduct.getPrice() %></label>
          <input type="number" name="price" value="<%= foodproduct.getPrice() %>" class="form-control" id="inputPrice" placeholder="Enter Price ($)" required>
        </div>
        <div class="col-12 d-flex justify-content-between">
          <button type="submit" class="btn btn-primary">Update Food Product</button>
          <a type="button" href="ShowAllFoodProduct.jsp" class="btn btn-success">Back</a>
        </div>
      </form>
    </section>
  </body>
</html>