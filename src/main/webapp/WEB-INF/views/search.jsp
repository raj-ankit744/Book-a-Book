<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Search Books</title>
	<style type="text/css">
  		<%@include file="css/bootstrap.min.css" %>
	</style>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
		<%@include file="js/search_radio.js" %>
		<%@include file="js/jquery-3.2.1.slim.min.js" %>
      	<%@include file="js/popper.min.js" %>
      	<%@include file="js/bootstrap.min.js" %>
	</script> 
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Book-a-Book</a>
		
		<ul class="navbar-nav navbar">
			<li class="nav-item active">
				<a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item active">
				<a class="nav-link" data-toggle="modal" href="#searchpost">Search Book</a>
			</li>
		</ul>
		<ul class = "nav navbar-nav ml-auto pull-right">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				${sessionScope.username}
				</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="/post?logout=true">Logout</a></div>
			</li>
		</ul>
	</nav>
	<!-- Search Results -->
	<div class="container">
	  <h2>Search Results</h2>	             
	  <table class="table table-hover" style="visibility : visible">
	    <thead>	    
	      <tr>
	        <th>Title</th>
	        <th>Author</th>
	        <th>ISBN</th>
	        <th>Price</th>
	      </tr>
	    </thead>
	    <tbody>	   	     
	     <c:forEach var="result" items="${result}"  >     	
	       <tr>
	         <td><a href="#"><c:out value = "${result.getB().getTitle()}" /></a></td>
	         <td><c:out value = "${result.getB().getAuthor()}" /></td>
	         <td><c:out value = "${result.getB().getIsbn()}" /></td>
	         <td><c:out value = "${result.getPrice()}" /></td>
	       </tr>	       
	      </c:forEach>   	     
	    </tbody>
	  </table>
	</div>
	<!-- Modal -->	
	<div class="modal fade" id="searchpost" tabindex="-1" role="dialog" aria-hidden="true">
	  <div class="modal-dialog" role="document">
		<div class="modal-content">
		  <div class="modal-header">
			<h5 class="modal-title" >Enter Search Details</h5>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			  <span aria-hidden="true">&times;</span>
			</button>
		  </div>
		  <div class="modal-body">
			<form id="createform" method="post" action="/search">
			<div class="form-group">
				<input name= "searchtype" value="ISBN" type="radio"  onclick="show1();" checked>&nbsp;ISBN
			</div>
			<div class="form-group">
				<input id="isbn_text" name="isbn_text" type="text" class="form-control" placeholder="ISBN" required>
            </div>
            <div class="form-group">
              <input name= "searchtype" value="Title" type="radio"  onclick="show2();" >&nbsp;TITLE
            </div>
            <div class="form-group">
              <input id="title_text" name="title_text" type="text" class="form-control input-md" placeholder="Title" disabled=true required>
            </div>
            
			<div class="form-group">
              <input name= "searchtype" value="Author" type="radio"  onclick="show3();" >&nbsp;Author
            </div>
            <div class="form-group">
              <input id="author_text" name="author_text" type="text" class="form-control input-md" placeholder="Author" disabled=true required>
            </div>
			</form>
		  </div>
		  
		  <div class="modal-footer">
			<button  class="btn btn-secondary" data-dismiss="modal">Close</button>
			<button form="createform" type="submit" class="btn btn-default btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Search</button>
		  </div>
		</div>
	  </div>
	</div>
</body>
</html>