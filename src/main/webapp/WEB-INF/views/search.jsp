<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Search Books</title>
	<style type="text/css">
  		<%@include file="css/bootstrap.min.css" %>
	</style>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
		<%@include file="js/search_radio.js" %>
	</script> 
</head>
<body>
	<div class ="row">
	    <div class ="col-xs-10 col-sm-6 col-md-4 col-cs-offset-1 col-sm-offset-3 col-md-offset-4">
	        <form id="search" method=post action="/search">
	            <h2>Search Book</h2>
	            <hr/>
	            <div class="row">
	                <div class = "col-xs-12 col-sm-12 col md-12">
	                    <div>
	                        <input name= "searchtype" value="ISBN" type="radio"  tabindex="1" onclick="show1();" checked>ISBN<input id="isbn_text" type="text" class="form-control input-md" placeholder="ISBN" tabindex="2" required>
	                    </div>
	                </div>
	                <div class = "col-xs-12 col-sm-12 col md-12">
	                    <div>
	                        <input name= "searchtype" value="Title" type="radio"  tabindex="3" onclick="show2();" >TITLE<input id="title_text" type="text" class="form-control input-md" placeholder="Title" tabindex="4" disabled=true required>
	                    </div>
	                </div>
	                <div class = "col-xs-12 col-sm-12 col md-12">
	                    <div>
	                        <input name= "searchtype" value="Author" type="radio"  tabindex="5" onclick="show3();" >Author<input id="author_text" type="text" class="form-control input-md" placeholder="Author"  tabindex="6" disabled=true required>
	                    </div>
	                </div>
	                
	            </div>
	            <div class="row">
	                
	                <div class="col-xs-12 col-sm-12 col-md-12">
	                    <div class="form-group">
	                        <button type = "submit" class="btn btn-primary btn-block btn-md" tabindex="7">Search</button>
	                    </div>
	                </div>
	            </div> 
	        </form>
	    </div>
	</div>	
</body>
</html>