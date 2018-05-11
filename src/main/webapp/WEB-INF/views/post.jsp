<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <style type="text/css">
  		<%@include file="css/bootstrap.min.css" %>
  		<%@include file="css/prod.css" %>
	</style>
      <script type="text/javascript">
      	<%@include file="js/jquery-3.2.1.slim.min.js" %>
      	<%@include file="js/popper.min.js" %>
      	<%@include file="js/bootstrap.min.js" %>
      </script> 
    <title>Book-a-book.com</title>
  </head>
  <body>
  	<script type="text/javascript">
	function setvalues(id,isbn,title,author,desc,price) {
		document.getElementById("pid").value = id;
		document.getElementById("isbn").value = isbn;
		document.getElementById("title").value = title;
		document.getElementById("author").value = author;
		document.getElementById("description").value = desc;
		document.getElementById("price").value =  price;
	};
	</script>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Book-a-Book</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span></button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav navbar">
		<li class="nav-item active">
			<a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
		</li>
		<li class="nav-item">
			<a class="nav-link" data-toggle="modal" href="#createpost">Post Advertisement</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" target="_blank" href="/viewpost">View Posts</a>
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
		</div>
	</nav>
	
	<!-- Modal for modify post -->
	<div class="modal fade" id="modifypost" tabindex="-1" role="dialog" aria-hidden="true">
	  <div class="modal-dialog" role="document">
		<div class="modal-content">
		  <div class="modal-header">
			<h5 class="modal-title" >Modify Advertisement Details</h5>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			  <span aria-hidden="true">&times;</span>
			</button>
		  </div>
		  <div class="modal-body">
			<form id="modifyPost" method="post" action="/post">
	 		<div class="form-group">
              <label for="pid"><span class="glyphicon glyphicon-user"></span> Post ID</label>
              <input type="text" class="form-control" name="pid" readonly="readonly" id="pid" value="${requestScope.pid}" required>
            </div>
	 		<div class="form-group">
              <label for="author"><span class="glyphicon glyphicon-user"></span> AUTHOR</label>
              <input type="text" class="form-control" name="author" readonly="readonly" id="author" placeholder="Enter Author Name" value="${requestScope.author}" required>
            </div>
    	   <div class="form-group">
              <label for="isbn"><span class="glyphicon glyphicon-eye-open"></span> ISBN</label>
              <input type="text" class="form-control" name="isbn" readonly="readonly" id="isbn" placeholder="Enter ISBN" value="${requestScope.isbn}" required>
            </div>
            
			<div class="form-group">
              <label for="title"><span class="glyphicon glyphicon-eye-open"></span> TITLE</label>
              <input type="text" class="form-control" name="title" readonly="readonly" id="title" placeholder="Enter TITLE" value="${requestScope.title}" required>
            </div>
			<div class="form-group">
              <label for="description"><span class="glyphicon glyphicon-eye-open"></span> DESCRIPTION</label>
              <textarea class="form-control" id="description" name="description" placeholder="Enter Description" form="modifyPost" required>${requestScope.description}</textarea>  
            </div>
             <div class="form-group">
              <label for="price"><span class="glyphicon glyphicon-eye-open"></span> Price</label>
              <input type="text" class="form-control" id="price" name="price" placeholder="Enter Price" value="${requestScope.price}" required>
            </div>
			</form>
		  </div>
		  
		  <div class="modal-footer">
			<button  class="btn btn-secondary" data-dismiss="modal">Close</button>
			<button form="modifyPost" name="modify" type="submit" class="btn btn-default btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Modify</button>
		  </div>
		</div>
	  </div>
	</div>
	
	
	<br><br>
	<!-- All Posts -->
	<div class="container">
	  <h2>Your Posts</h2>
	  <span style="color:green">(Click on post to edit)</span>	             
	  <table class="table table-hover">
	    <thead>	    
	      <tr>
	      	<th>Post Id</th>
	        <th>Title</th>
	        <th>Author</th>
	        <th>ISBN</th>
	        <th>Price</th>
	      </tr>
	    </thead>
	    <tbody>	   	     
	     <c:forEach var="pd" items="${postData}"  >     	
	       <tr data-toggle = "modal" href="#modifypost" onclick = "setvalues('${pd.getId() }','${pd.getB().getIsbn()}','${pd.getB().getTitle()}','${pd.getB().getAuthor()}','${pd.getDescription() }','${pd.getPrice() }');">
	       	 <td><c:out value = "${pd.getId() }" /> </td>
	         <td> <c:out value = "${pd.getB().getTitle()}" /> </td>
	         <td><c:out value = "${pd.getB().getAuthor()}" /></td>
	         <td><c:out value = "${pd.getB().getIsbn()}" /></td>
	         <td>&#8377; <c:out value = "${pd.getPrice()}" /></td>
	         <td href="#"><form id= "delete" method="post" action="/post"><input name="delete" value="${pd.getId()}" type="hidden"><button type="submit" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button></form></td>
	       </tr>	    
	      </c:forEach>   	
	    </tbody>
	  </table>
	</div>
	
	
	<!-- Modal for post-->
	<div class="modal fade" id="createpost" tabindex="-1" role="dialog" aria-hidden="true">
	  <div class="modal-dialog" role="document">
		<div class="modal-content">
		  <div class="modal-header">
			<h5 class="modal-title" >Enter Advertisement Details</h5>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			  <span aria-hidden="true">&times;</span>
			</button>
		  </div>
		  <div class="modal-body">
			<form id="createform" method="post" action="/post">
			<div class="form-group">
              <label for="author"><span class="glyphicon glyphicon-user"></span> AUTHOR</label>
              <input type="text" class="form-control" name="author" placeholder="Enter Author Name" value="${requestScope.author}" required>
            </div>
            <div class="form-group">
              <label for="isbn"><span class="glyphicon glyphicon-eye-open"></span> ISBN</label>
              <input type="text" class="form-control" name="isbn" placeholder="Enter ISBN" value="${requestScope.isbn}" required>
            </div>
            
			<div class="form-group">
              <label for="title"><span class="glyphicon glyphicon-eye-open"></span> TITLE</label>
              <input type="text" class="form-control" name="title" placeholder="Enter TITLE" value="${requestScope.title}" required>
            </div>
			<div class="form-group">
              <label for="description"><span class="glyphicon glyphicon-eye-open"></span> DESCRIPTION</label>
              <textarea class="form-control" name="description" placeholder="Enter Description" form="createform" required>${requestScope.description}</textarea>
			  
            </div>
            <div class="form-group">
              <label for="price"><span class="glyphicon glyphicon-eye-open"></span> Price</label>
              <input type="text" class="form-control" name="price" placeholder="Enter Price" value="${requestScope.price}" required>
            </div>
			</form>
		  </div>
		  
		  <div class="modal-footer">
			<button  class="btn btn-secondary" data-dismiss="modal">Close</button>
			<button form="createform" type="submit" name="create" class="btn btn-default btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Create</button>
		  </div>
		</div>
	  </div>
	</div>
  </body>
</html>