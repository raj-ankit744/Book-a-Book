<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
  		<%@include file="css/prod.css" %>
	</style>
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
					<a class="dropdown-item" href="/search?logout=true">Logout</a></div>
			</li>
		</ul>
	</nav>
	<script type="text/javascript">
	function setvalues(id,isbn,title,author,descr,price) {
		document.getElementById("pid").value = id;
		document.getElementById("isbn").innerHTML = isbn;
		document.getElementById("title").innerHTML = title;
		document.getElementById("author").innerHTML = author;
		document.getElementById("desc").innerHTML = descr;
		document.getElementById("price").innerHTML = "<span>" + price + "</span>";
	};
	</script>
	<!-- Search Results -->
	<div class="container">
	<c:if test="${fn:length(result) gt 0}">
	  <h2>Search Results</h2>	             
	  <table class="table table-hover">

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
	       <tr data-toggle = "modal" href="#product" onclick = "setvalues('${result.getId() }','${result.getB().getIsbn()}','${result.getB().getTitle()}','${result.getB().getAuthor()}','${result.getDescription()}','${result.getPrice() }');">
	         <td> <c:out value = "${result.getB().getTitle()}" /> </td>
	         <td><c:out value = "${result.getB().getAuthor()}" /></td>
	         <td><c:out value = "${result.getB().getIsbn()}" /></td>
	         <td><c:out value = "${result.getPrice()}" /></td>
	       </tr>	       
	      </c:forEach>   	     
	    </tbody>
	  </table>
	  </c:if>
	  <c:if test="${fn:length(result) eq 0}">
	  	<h2>Request Book</h2>
	  	<form id="requestform" method="post" action="/search">
	  	<div class="row">
	  		<div class = "col-xs-12 col-sm-12 col md-12">
	  		<div class="form-group">
				<input id="isbn_request_text" name="isbn_request_text" type="text"  placeholder="ISBN" required>
            </div></div></div>
            <button form="requestform" name="req" type="submit" class="btn btn-default btn-success" ><span class="glyphicon glyphicon-off"></span> Search</button>
            </form>
	  </c:if>
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
			<button form="createform" name="confirmSearch" type="submit" class="btn btn-default btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Search</button>
		  </div>
		</div>
	  </div>
	</div>
	<!-- modal for products -->
	  <div class="modal fade" id="product" tabindex="-1" role="dialog" aria-hidden="true">
	  <div class="modal-dialog" role="document">
		<div class="modal-content">
		  <div class="modal-header">
			<h5 id = "title" class="modal-title" ></h5>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			  <span aria-hidden="true">&times;</span>
			</button>
		  </div>
		  <div class="modal-body">
			<div class="modal-body">
				<div class="row">
                    <div class="col-md-6">
                        <img class="img-responsive" alt="Bootstrap template" src="http://www.prepbootstrap.com/Content/images/template/productslider/product_001.jpg" />
                    </div>
                    <div class="col-md-6 product_content">
                        <h7>ISBN: <span id="isbn"></span></h7>
                      
                      
                         <h4>Author:<span id="author"></span></h4>
                        
                        <h4>Description:</h4>
                        <p id = "desc" class="text-justify product-description"></p>
                        
                        <br />
                        <h2>$<span id="price" class="price"></span></h2>
                        <form id="fpid" method="post" action="/search">
                        	<input id="pid" name="pid" type="hidden" />
                        	<input name="buid" type=hidden value="${sessionScope.username}" />
                        </form>
                    </div>
                </div>
		    </div>
		  
		  <div class="modal-footer">
			<button  class="btn btn-secondary" data-dismiss="modal">Close</button>
			<button form="fpid" type="submit" class="btn btn-default btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Place Order</button>
		  </div>
		</div>
	  </div>
	</div>
</body>
</html>