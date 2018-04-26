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

	<div class="row">
	    <div class = "col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
	    <form id = "reg" method  = "post" action="/signup">
	            <h2>Create an account</h2>
	            <hr/>
	            <div class="row">
	                <div class = "col-xs-12 col-sm-6 col-md-6">
	                    <div class="form-group">
	                        <input type="text" name="first_name" class="form-control input-md" placeholder="First Name" tabindex="1" required>
	                    </div>
	                </div>
	                <div class = "col-xs-12 col-sm-6 col-md-6">
	                    <div class="form-group">
	                        <input type="text" name="last_name" class="form-control input-md" placeholder="Last Name" tabindex="2" required>
	                    </div>
	                </div>
	            </div>
	            <div class="form-group">
	                <input type="email" name="email" class="form-control input-md" placeholder="Email Address" tabindex="3" required>
	            </div>
	            <div class="form-group">
	                <input type="text" name="address" class="form-control input-md" placeholder="Address" tabindex="4" required>
	            </div>
	            <div class = "row">
	            <div class = "col-xs-12 col-sm-6 col-md-6">
	                    <div class="form-group">
	                        <input type="tel" name="contact" class="form-control input-md" placeholder="Contact" tabindex="5" required>
	                    </div>
	                    </div>
	               </div>
	                <div class="row">
	                <div class = "col-xs-12 col-sm-6 col-md-6">
	                    <div class="form-group">
	                        <input type="password" name="password" class="form-control input-md" placeholder="Password" tabindex="6" required>
	                    </div>
	                </div>
	                <div class = "col-xs-12 col-sm-6 col-md-6">
	                    <div class="form-group">
	                        <input type="password" class="form-control input-md" placeholder="Confirm Password" tabindex="7" required>
	                    </div>
	                </div>
	            </div>
	          
	            <div class="form-group">
	            	<label class="radio-inline">
      						<input name= "usertype" value="Seller" type="radio"  tabindex="8" checked>  Seller
    				</label>
    				<label class="radio-inline">
      						<input name="usertype" value="Buyer" type="radio"   tabindex="9" >   Buyer
    				</label>
	            </div>
	      </form>
	            <div class="row">
	                <div class="col-xs-12 col-md-6">
	                    <a href="/login" class="btn btn-success btn-block btn-md">Sign In</a>
	                </div>
	                <div class="col-xs-12 col-md-6">
	                    <button form="reg" type="submit" class="btn btn-primary btn-block btn-md" tabindex="6">Register</button>
	                </div>
	            </div>	     
	    </div>    
	</div>
</body>
</html>