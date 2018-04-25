<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<style type="text/css">
  		<%@include file="css/bootstrap.min.css" %>
	</style>
	<script type="text/javascript" src="js/bootstrap.min.js"></script> 
</head>
<body>
	<div class ="row">
	    <div class ="col-xs-10 col-sm-6 col-md-4 col-cs-offset-1 col-sm-offset-3 col-md-offset-4">
	        <form id="login" method="post" action="/login">
	            <h2>Login</h2>
	            <hr/>
	            <div class="row">
	                <div class = "col-xs-12 col-sm-12 col md-12">
	                    <div class="form-group">
	                        <input type="email" class="form-control input-md" placeholder="E-mail" tabindex="1" required>
	                    </div>
	                </div>
	                <div class="col-xs-12 col sm-12 col-md-12">
	                    <div class="form-group">
	                        <input type="password" class="form-control input-md" placeholder="Password" tabindex="2" required>
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="col-xs-12 col-sm-12 col-md-12">
	                    <div class="form-group">
	                        <a href="/signup">
	                            Not Registered? Create An Account
	                        </a>
	                    </div>
	                </div>
	                <div class="col-xs-12 col-sm-12 col-md-12">
	                    <div class="form-group">
	                        <button type = "submit" class="btn btn-primary btn-block btn-md" tabindex="3">Login</button>
	                    </div>
	                </div>
	            </div> 
	            ${error }
	        </form>
	    </div>
	</div>	
</body>
</html>