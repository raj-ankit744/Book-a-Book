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
	      <c:forEach var="od" items="${orderdata}" varStatus="i"  >
	      <c:choose>
		      <c:when test= "${type == 'buyer'}" >
		        <tr data-toggle="modal" href="#orders" onclick="setvalues('${postdata[i.index].id}','${od.oid}','${bookdata[i.index].isbn}','${bookdata[i.index].title}','${bookdata[i.index].author}','${postdata[i.index].price}','${od.buid}','0','${od.status}')">
		      </c:when>
		      <c:otherwise>     		       
		      	<tr data-toggle="modal" href="#posts" onclick="setvalues('${postdata[i.index].id}','${od.oid}','${bookdata[i.index].isbn}','${bookdata[i.index].title}','${bookdata[i.index].author}','${postdata[i.index].price}','${od.buid}','1','${od.status}')">
		      </c:otherwise>
	      </c:choose>
	         <td> <c:out value = "${od.oid}" /> </td>
	          <td><c:out value = "${postdata[i.index].id}" /></td> 
	         <td><c:out value = "${od.date}" /></td>	
	         <c:if test="${od.status eq 2 }"><td>Cancelled</td></c:if>    
	         <c:if test="${od.status eq 1 }"><td>Confirmed</td></c:if>     
	       </tr>	
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
                        <h7>ISBN: <span id="isbn0"></span></h7>
                        <h4 style="color:#ff0033"> <span id="title0"></span></h4>                      
                         <h7>by <span id="author0" style="color:#698e98"></span></h7>                                               
                        <h2>&#8377; <span id="price0" class="price"></span></h2>                        
                    </div>
                </div>
		  </div>
		  <div class="modal-footer">
		  	<form id="can" method="post" action="/viewpost" >
		  		<input id="oid0" name="oid" type="hidden" />
		  		<input  id = "pid0" name="pid" type="hidden" />	  		
		  	</form>
			<button  class="btn btn-secondary" data-dismiss="modal">Close</button>
			<button form="can" type="submit" id="cancel" name="cancel" class="btn btn-default btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Cancel Order</button>
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
                        <h7>ISBN: <span id="isbn1"></span></h7>
                        <h4 style="color:#ff0033"> <span id="title1"></span></h4>                      
                        <h7>by <span id="author1" style="color:#698e98"></span></h7>                                               
                        <h2>&#8377; <span id="price1" class="price"></span></h2> 
                        <h6>Ordered by <span id="buyer1" ></span></h6>                       
                    </div>
                </div>
		  </div>
		  <div class="modal-footer">
		  	<form id="cfrm" method="post" action="/viewpost" >		  		
		  		<input id="oid1" name="oid" type="hidden" />
		  		<input  id="pid1" name="pid" type="hidden" />	  		
		  	</form>
			<button  class="btn btn-secondary" data-dismiss="modal">Close</button>
			<button form="cfrm" type="submit" id= "confirm" name="confirm" class="btn btn-default btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Confirm Order</button>
		  </div>		 
		</div>
		</div>
	</div>
	<script type="text/javascript">
	function setvalues(pid,oid,isbn,title,author,price,buyer,tail,status) {
		if(status==2 || status == 1){
			document.getElementById("cancel").hidden = true;
			document.getElementById("confirm").hidden = true;
		}
		else{
			document.getElementById("cancel").hidden = false;
			document.getElementById("confirm").hidden = false;
		}
		document.getElementById("oid"+tail).value = oid;
		document.getElementById("pid"+tail).value = pid;
		document.getElementById("isbn"+tail).innerHTML = isbn;
		document.getElementById("title"+tail).innerHTML = title;
		document.getElementById("author"+tail).innerHTML = author;		
		document.getElementById("price"+tail).innerHTML = "<span>" + price + "</span>";
		document.getElementById("buyer"+tail).innerHTML = buyer;
	};
	</script>
</body>
</html>