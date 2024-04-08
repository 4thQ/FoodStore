<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="login-page-attribute/fonts/icomoon/style.css">

    <link rel="stylesheet" href="login-page-attribute/css/owl.carousel.min.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="login-page-attribute/css/bootstrap.min.css">

    <!-- Style -->
    <link rel="stylesheet" href="login-page-attribute/css/style.css">





<title>Login Page</title>
</head>
<body>
<%
  String message = (String) request.getAttribute("message");
%>
<div class="d-lg-flex half">
    <div class="bg order-1 order-md-2" style="background-image: url('login-page-attribute/images/food-store.jpg');"></div>
    <div class="contents order-2 order-md-1">

    <div class="container">
        <div class="row align-items-center justify-content-center">
        <div class="col-md-7">
            <h3>Login to <strong>Food Store</strong></h3>
            <%
              if(message != null){%>
              <p class="mb-4 text-danger fw-bold"><strong>Incorrect username or password!</strong></p>
            <%}%>
            <form method="post" action="login">
                <div class="form-group first">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" name="username" placeholder="Enter Your Username!" id="inputUsername" required>
                </div>
                <div class="form-group last mb-3">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" name="password" placeholder="Enter Your Password" id="inputPassword" required>
                </div>
                <div class="d-flex mb-5 align-items-center">
                    <label class="control control--checkbox mb-0"><span class="caption">Show Password</span>
                        <input type="checkbox" name="checkbox" onclick="showPassword()"/>
                        <div class="control__indicator"></div>
                    </label>
                </div>
                <input type="submit" value="Log In" class="btn btn-block btn-primary">
            </form>
        </div>
        </div>
    </div>
    </div>
</div>
<script type="text/javascript">
		function showPassword() {
			var password = document.getElementById('inputPassword');
			if(password.type == 'password'){
				password.type = 'text';
			}else{
				password.type = 'password';
			}
		}
</script>
<script src="login-page-attribute/js/jquery-3.3.1.min.js"></script>
<script src="login-page-attribute/js/popper.min.js"></script>
<script src="login-page-attribute/js/bootstrap.min.js"></script>
<script src="login-page-attribute/js/main.js"></script>
</body>
</html>