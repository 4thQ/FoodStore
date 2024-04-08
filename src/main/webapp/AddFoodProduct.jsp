<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
    <title>Add Food Product</title>
  </head>
  <body>
  <%
    User user = (User) session.getAttribute("user");
    if(user == null){
      response.sendRedirect("LoginPage.jsp");
      return;
    }
  %>
    <div class="container-fluid bg-body-tertiary text-light py-3">
        <header class="text-center">
          <h1 class="display-6">Add Food Product</h1>
        </header>
    </div>
    <section class="container my-2 bg-dark w-50 text-light p-2">
      <form class="row g-3 p-3" method="post" action="add-foodproduct">
        <div class="col-md-6">
          <label for="inputSKU" class="form-label">SKU</label>
          <input type="text" name="SKU" class="form-control" id="inputSKU" placeholder="Enter SKU" required>
        </div>
        <div class="col-md-6">
          <label for="inputDescription" class="form-label">Description</label>
          <input type="text" name="description" class="form-control" id="inputDescription" placeholder="Enter Description" required>
        </div>
        <div class="col-md-6">
          <label for="inputCategory" class="form-label">Category</label>
          <input type="text" name="category" class="form-control" id="inputCategory" placeholder="Enter Category" required>
        </div>
        <div class="col-md-6">
          <label for="inputPrice" class="form-label">Price</label>
          <input type="number" name="price" class="form-control" id="inputPrice" placeholder="Enter Price ($)" required>
        </div>
        <div class="col-12 d-flex justify-content-between">
          <button type="submit" class="btn btn-primary">Add Food Product</button>
          <a type="button" href="ShowAllFoodProduct.jsp" class="btn btn-success">Back</a>
        </div>
      </form>
    </section>
  </body>
</html>