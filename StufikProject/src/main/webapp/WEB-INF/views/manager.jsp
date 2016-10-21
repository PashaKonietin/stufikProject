<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="row">
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
								<li><a href="/admin/description">Description</a></li>
<!-- 								<li class="active"><a href="/admin/description">Manager</a><span class="sr-only">(current)</span></li> -->
								<li><a href="/admin/commodity">Commodity</a></li>
								<li><a href="/admin/order">Order</a></li>
							</ul>
						</div>
					</div>
				</nav>
			</div>
	</div>
	
	<div class="row-fluid">
	<div class="col-md-3 col-xs-12">
			<form:form action="/admin/manager" class="form-inline" method="get" modelAttribute="filter">
					<custom:hiddenInputs excludeParams="companySearch"/>
					<div class="form-group">
						<form:input path="companySearch" placeholder="Company search" class="form-control" />
					</div>
<!-- 					<div class="form-group"> -->
<!-- 						<h4>Delivery Company</h4> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<%-- 						<form:checkboxes items="${deliveryes}" path="deliveryIds" itemLabel="deliveryCompany" itemValue="id"/> --%>
<!-- 						<hr> -->
<!-- 					</div> -->
					<div class="form-group">
						<button type="submit" class="btn btn-success">Ok</button>
					</div>
			</form:form>
		</div>
	<div class="col-md-7 col-xs-12">
	<form:form class="form-inline" action="/admin/manager" method="post" modelAttribute="manager">
		<form:hidden path="id" />
		<custom:hiddenInputs excludeParams="name, surname, middleName, email, company, id"/>
 					
<!--  					<div class="row">  -->
<!--  						<div class="col-md-12">  -->
<!--  							<div class="form-group">  -->
<!--  								<label>Chose deliveryCompany:</label>  -->
<%--  								<form:select path="delivery" items="${deliveryes}" itemLabel="deliveryCompany" itemValue="id" cssClass="selectpicker" data-style="btn-primary">  --%>
<!--  								<option value="0">Company</option>  -->
<%--  								</form:select>  --%>
<!--  							</div>  -->
<!--  						</div> -->
<!--  					</div>  -->
					
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<form:input path="name" placeholder="Name" class="form-control" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<form:input path="surname" placeholder="Surname" class="form-control" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<form:input path="middleName" placeholder="MiddleName" class="form-control" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<form:input path="email" placeholder="Email" class="form-control" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<form:input path="company" placeholder="Company name" class="form-control" />
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
	<div class="row">
		<div class="col-md-2"><h4>Name</h4></div>
		<div class="col-md-2"><h4>Surname</h4></div>
		<div class="col-md-2"><h4>MiddleName</h4></div>
		<div class="col-md-2"><h4>Company</h4></div>
		<div class="col-md-2"><h4>Email</h4></div>
		<div class="col-md-1"><h4>Delete</h4></div>
		<div class="col-md-1"><h4>Update</h4></div>
	</div>
		<c:forEach items="${managers.content}" var="manager">
			<div class="row">
				<div class="col-md-2">${manager.name}</div>
				<div class="col-md-2">${manager.surname}</div>
				<div class="col-md-2">${manager.middleName}</div>
				<div class="col-md-2">${manager.company}</div>
				<div class="col-md-2">${manager.email}</div>
				<div class="col-md-1"><a href="/admin/manager/delete/${manager.id}<custom:allParams/>">delete</a></div>
				<div class="col-md-1"><a href="/admin/manager/update/${manager.id}<custom:allParams/>">update</a></div>
			</div>
		</c:forEach>
		<div class="col-md-12 text-center">
			<custom:pageable page="${managers}" cell="<li></li>" container="<ul class='pagination'></ul>" />
		</div>
	</div>
	<div class="col-md-2 col-xs-12">
			<div class="col-md-6">
				<div class="dropdown">
					<button class="btn btn-danger dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Name asc" paramValue="name"/>
						<custom:sort innerHtml="Name desc" paramValue="name,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<custom:size posibleSizes="1,2,5,10" size="${managers.size}" title="size"/>
			</div>
		</div>
</div>
</body>
</html>