<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
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
	<title>View Post</title>
</head>
<body>
	
	<div class="container">
	  <h2>Orders</h2>
	  <p>All of your placed orders:</p>            
	  <table class="table table-hover">
	    <thead>
	      <tr>
	        <th>Order ID</th>
	        <th>Post ID</th>
	        <th>Date</th>	        
	      </tr>
	    </thead>
	    <tbody>
	      <c:forEach var="od" items="${orderdata}"  >
	      <c:forEach var="pd" items="${postdata}" >
	      <c:forEach var="bd" items="${bookdata}" >
	      <c:choose>
		      <c:when test= "${type == 'buyer'}" >
		        <tr data-toggle="modal" href="#orders" onclick="setvalues('${pd.id}','${od.oid}','${bd.isbn}','${bd.title}','${bd.author}','${pd.price}','${od.buid}')">
		      </c:when>
		      <c:otherwise>     		       
		      	<tr data-toggle="modal" href="#posts" onclick="setvalues('${pd.id}','${od.oid}','${bd.isbn}','${bd.title}','${bd.author}','${pd.price}','${od.buid}')">
		      </c:otherwise>
	      </c:choose>
	         <td> <c:out value = "${od.oid}" /> </td>
	         <td><c:out value = "${pd.id}" /></td>
	         <td><c:out value = "${od.date}" /></td>	        
	       </tr>	
	      </c:forEach>
	      </c:forEach>       
	      </c:forEach> 
	    </tbody>
	  </table>
	</div>
	<!-- For buyers -->
	<div class="modal fade" id="orders" role="dialog" aria-hidden="true">
		<div class="modal-dialog" role="document">
		<div class="modal-content">
		  <div class="modal-header">
			<h5 class="modal-title" >Order Details</h5>
		  </div>
		  <div class="modal-body">
		  	<div class="row">
                    <div class="col-md-6">
                        <img class="img-responsive" alt="Bootstrap template" src="http://www.prepbootstrap.com/Content/images/template/productslider/product_001.jpg" />
                    </div>
                    <div class="col-md-6 product_content">
                        <h7>ISBN: <span id="isbn"></span></h7>
                        <h4 style="color:#ff0033"> <span id="title"></span></h4>                      
                         <h7>by <span id="author" style="color:#698e98"></span></h7>                                               
                        <h2>&#8377; <span id="price" class="price"></span></h2>                        
                    </div>
                </div>
		  </div>
		  <div class="modal-footer">
		  	<form id="can" method="post" action="/viewpost" >
		  		<input id="oid" name="oid" type="hidden" />
		  		<input id="pid" name="pid" type="hidden" />	  		
		  	</form>
			<button  class="btn btn-secondary" data-dismiss="modal">Close</button>
			<button form="can" type="submit" name="cancel" class="btn btn-default btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Cancel Order</button>
		  </div>		 
		</div>
		</div>
	</div>	
	
	<!-- For sellers -->
	<div class="modal fade" id="posts" role="dialog" aria-hidden="true" tabindex="-1">
		<div class="modal-dialog" role="document">
		<div class="modal-content">
		  <div class="modal-header">
			<h5 class="modal-title" >Post Details</h5>
		  </div>
		  <div class="modal-body">
		  	<div class="row">
                    <div class="col-md-6">
                        <img class="img-responsive" alt="Bootstrap template" src="http://www.prepbootstrap.com/Content/images/template/productslider/product_001.jpg" />
                    </div>
                    <div class="col-md-6 product_content">	
                    	<!-- pid,oid,price IDs not working, but working from javascript alert method -->
                        <h7>ISBN: <span id="pid"></span></h7>
                        <h4 style="color:#ff0033"> <span id="title"></span></h4>                      
                        <h7>by <span id="oid" style="color:#698e98"></span></h7>                                               
                        <h2>&#8377; <span id="price" class="price"></span></h2> 
                        <h6>Ordered by <span id="buyer" ></span></h6>                       
                    </div>
                </div>
		  </div>
		  <div class="modal-footer">
		  	<form id="cfrm" method="post" action="/viewpost" >		  		
		  		<input id="oid" name="oid" type="hidden" />
		  		<input id="pid" name="pid" type="hidden" />	  		
		  	</form>
			<button  class="btn btn-secondary" data-dismiss="modal">Close</button>
			<button form="cfrm" type="submit" name="confirm" class="btn btn-default btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Confirm Order</button>
		  </div>		 
		</div>
		</div>
	</div>
	<script type="text/javascript">
	function setvalues(pid,oid,isbn,title,author,price,buyer) {
		document.getElementById("pid").value = pid;
		document.getElementById("oid").value = oid;
		document.getElementById("isbn").innerHTML = isbn;
		document.getElementById("title").innerHTML = title;
		document.getElementById("author").innerHTML = author;		
		document.getElementById("price").innerHTML = "<span>" + price + "</span>";
		document.getElementById("buyer").innerHTML = buyer;
		window.alert(title);
	};
	</script>
</body>
</html>