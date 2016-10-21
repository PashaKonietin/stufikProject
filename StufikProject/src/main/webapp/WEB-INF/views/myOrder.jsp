<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="row" style="margin-top: 100px">
			<div class="col-md-12">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<div class="collapse navbar-collapse" id="">
							<ul class="nav navbar-nav">
								<li><a href="/admin">Admin Panel</a></li>
								<li><a href="/admin/brand">Brand</a></li>
								<li><a href="/admin/city">City</a></li>
								<li><a href="/admin/color">Color</a></li>
								<li><a href="/admin/delivery">Delivery</a></li>
								<li><a href="/admin/material">Material</a></li>
								<li><a href="/admin/modelName">Model Name</a></li>
								<li><a href="/admin/subCategory">SubCategory</a></li>
<!-- 								<li><a href="/admin/manager">Manager</a></li> -->
								<li><a href="/admin/commodity">Commodity</a></li>
								<li class="active"><a href="/admin/order">MyOrder</a><span class="sr-only">(current)</span></li>
							</ul>
						</div>
					</div>
				</nav>
			</div>
	<form:form class="form-inline" action="/admin/order" method="post" modelAttribute="order">
		<form:errors path="*"/>
		<form:hidden path="id" />
		<custom:hiddenInputs excludeParams="city, delivery, price, quantity, clients, id"/>
 					<div class="row">
 						<div class="col-md-12">
 							<div class="form-group">
 								<label>Chose City:</label>
 								<form:select path="cityOrder" items="${cities}" itemLabel="name" itemValue="id"  data-style="btn-primary"> 
 									<option value="0">Brand</option>
 								</form:select> 
 							</div> 
 						</div> 
 					</div> 
 					<div class="row">
 						<div class="col-md-12">
 							<div class="form-group">
 								<label>Chose delivery:</label>
 								<form:select path="delivery" items="${deliveries}" itemLabel="deliveryCompany" itemValue="id" class="selectpicker" data-style="btn-primary"> 
 								<option value="0">Category</option>
 								</form:select> 
 							</div> 
 						</div> 
 					</div> 
 					<div class="row"> 
 						<div class="col-md-12"> 
 							<div class="form-group"> 
 								<label>Chose client:</label> 
 								<form:select path="client" items="${clients}" itemLabel="name" itemValue="id" cssClass="selectpicker" data-style="btn-primary"> 
 								<option value="0">Color</option> 
 								</form:select> 
 							</div> 
 						</div>
 					</div> 
					
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<form:input path="price" placeholder="Order price" class="form-control" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<form:input path="quantity" placeholder="Order quantity" class="form-control" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<button type="submit" class="btn btn-success">Create</button>
							</div>
						</div>
					</div>
	</form:form>
	
	<div class="container">
	<div class="col-md-12 col-xs-12">
	<div class="row">
		<div class="col-md-3"><h4>Quantity</h4></div>
		<div class="col-md-3"><h4>Commodity</h4></div>
		<div class="col-md-2"><h4>City</h4></div>
		<div class="col-md-2"><h4>Client</h4></div>
		<div class="col-md-1"><h4>Delete</h4></div>
		<div class="col-md-1"><h4>Update</h4></div>
	</div>
		<c:forEach items="${myOrders.content}" var="order">
			<div class="row">
				<div class="col-md-3">${order.quantity}</div>
<%-- 				<div class="col-md-3">${order.commodities.modelName.name}</div> --%>
				<div class="col-md-2">${order.cityOrder.name}</div>
				<div class="col-md-2">${order.client.login}</div>
				
<%-- 				<div class="col-md-1"><a href="/admin/commodity/delete/${commodity.id}<custom:allParams/>">delete</a></div> --%>
<%-- 				<div class="col-md-1"><a href="/admin/commodity/update/${commodity.id}<custom:allParams/>">update</a></div> --%>
			</div>
		</c:forEach>
		
	</div>
	
</div>
</div>
</body>
</html>