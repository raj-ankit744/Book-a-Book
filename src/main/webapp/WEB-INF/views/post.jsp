<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <style type="text/css">
  		<%@include file="css/bootstrap.min.css" %>
	</style>
      <script type="text/javascript">
      	<%@include file="js/jquery-3.2.1.slim.min.js" %>
      	<%@include file="js/popper.min.js" %>
      	<%@include file="js/bootstrap.min.js" %>
      </script> 
    <title>Book-a-book.com</title>
  </head>
  <body>
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
		</ul>
		<ul class = "nav navbar-nav ml-auto pull-right">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				Dropdown
				</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="#">Logout</a></div>
			</li>
		</ul>
		</div>
	</nav>
	<!-- Modal -->
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
			  <br>
              <textarea row="5" col="3" class="form-control" name="description" placeholder="Enter Description" form="createform" value="${requestScope.description}" required></textarea>
			  
            </div>
            <div class="form-group">
              <label for="price"><span class="glyphicon glyphicon-eye-open"></span> Price</label>
              <input type="text" class="form-control" name="price" placeholder="Enter Price" value="${requestScope.price}" required>
            </div>
			</form>
		  </div>
		  
		  <div class="modal-footer">
			<button  class="btn btn-secondary" data-dismiss="modal">Close</button>
			<button form="createform" type="submit" class="btn btn-default btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Create</button>
		  </div>
		</div>
	  </div>
	</div>

  </body>
</html>