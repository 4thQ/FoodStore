<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="FoodProductStuff.*"%>
<%@page import="Database.DBConnection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Controllers.*"%>


<!DOCTYPE html>
<html>

<head>
  	<title>Show All Food Products</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

	<link rel="stylesheet" href="table-01/css/style.css">
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

	</head>
	<body>
	<%
	  User user = (User) session.getAttribute("user");
    if(user == null){
      response.sendRedirect("index.jsp");
      return;
    }
	  ArrayList<FoodProduct> foodProducts = (ArrayList<FoodProduct>) request.getAttribute("ListOfFoodProduct");
	  FoodProductDAO foodProductDAO = new FoodProductDAO(DBConnection.getConnection());
	  if (foodProducts == null){
	    foodProducts = foodProductDAO.findAllProducts();
	  }
    ArrayList<String> categories = foodProductDAO.getCateforyOfFoodProduct();
	%>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
					<h2 class="heading-section">All Food Products</h2>
				</div>
			</div>
			<div class="row justify-content-center">
        <div class="col-md-6 text-center mb-5 d-flex">
          <div class="btn-group" role="group" aria-label="Basic mixed styles example">
            <a type="button" href="ShowAllFoodProduct.jsp" class="btn btn-primary">Food Product</a>
            <a type="button" href="ShowAllCustomer.jsp" class="btn btn-success">Customer</a>
           
          </div>
          <div class="btn-group mx-auto" role="group" aria-label="Basic mixed styles example">
            <a type="button" href="AddFoodProduct.jsp" class="btn btn-outline-success">Add Food Product</a>
          </div>
        </div>
        <div class="col-md-6 text-center mb-5 d-flex">
          <form class="d-flex mx-auto" method="post" action="search-foodproduct">
            <input class="form-control me-2" name="search" type="search" placeholder="Search">
            <button type="submit" class="btn btn-outline-success">Search</button>
          </form>
          <div class="dropdown">
            <button class="btn btn-success dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
              Category
            </button>
             <a href="index.jsp" class="btn btn-danger">Logout</a>
            <ul class="dropdown-menu">
            
          
            
              <%
                for(String category : categories){%>
                <li><a class="dropdown-item" href="category-food-product?category=<%= category %>"><%= category %></a></li>
              <%}%>
            </ul>
          </div>
        </div>
      </div>
			<div class="row">
				<div class="col-md-12">
					<div class="table-wrap">
						<table class="table">
						  <thead class="thead-primary">
						    <tr>
						      <th>ID</th>
						      <th>SKU</th>
						      <th>Description</th>
						      <th>Category</th>
						      <th>Price</th>
						      <th>Update</th>
						      <th>Delete</th>
						    </tr>
						  </thead>
						  <tbody>
						    <%
						      if(!foodProducts.isEmpty()){
						        for(FoodProduct foodProduct : foodProducts){%>
						          <tr>
                      	<td><%= foodProduct.getId() %></td>
                      	<td><%= foodProduct.getSKU() %></td>
                        <td><%= foodProduct.getDescription() %></td>
                        <td><%= foodProduct.getCategory() %></td>
                        <td><%= foodProduct.getPrice() %></td>
                        <td><a type="button" href="go-to-update-foodproduct?id=<%= foodProduct.getId() %>" class="btn btn-success">Update</a></td>
                        <td><a type="button" href="delete-foodproduct?id=<%= foodProduct.getId() %>" class="btn btn-danger">Delete</a></td>
                      </tr>
						        <%}
						      }
						    %>
						  </tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>

	<script src="table-01/js/jquery.min.js"></script>
  <script src="table-01/js/popper.js"></script>
  <script src="table-01/js/bootstrap.min.js"></script>
  <script src="table-01/js/main.js"></script>

	</body>
</html>

