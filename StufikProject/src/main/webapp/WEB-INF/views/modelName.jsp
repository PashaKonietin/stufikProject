<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
								<li class="active"><a href="/admin/modelName">ModelName</a><span class="sr-only">(current)</span></li>
								<li><a href="/admin/subCategory">SubCategory</a></li>
								<li><a href="/admin/category">Category</a></li>
<!-- 								<li><a href="/admin/manager">Manager</a></li> -->
								<li><a href="/admin/commodity">Commodity</a></li>
								<li><a href="/admin/order">Order</a></li>
							</ul>
						</div>
					</div>
				</nav>
			</div>
	</div>
	
	<div class="row-fluid">
		<div class="col-md-3">
			<form:form action="/admin/modelName" class="form-inline" method="get" modelAttribute="filter">
					<custom:hiddenInputs excludeParams="search"/>
					<div class="form-group">
						<form:input path="search" placeholder="search" class="form-control" />
						<button type="submit" class="btn btn-success">Ok</button>
					</div>
			</form:form>
		</div>
		<div class="col-md-7">
			<form:form action="/admin/modelName" method="post" class="form-inline" modelAttribute="modelName">
				<form:hidden path="id" />
				<custom:hiddenInputs excludeParams="name, id"/>
				<div class="form-group">
					<label for="name"><form:errors path="name" /></label>
					<form:input id="name" path="name" placeholder="Model name" class="form-control" />
					<button type="submit" class="btn btn-success">Create Model</button>
				</div>
			</form:form>
			<div class="col-md-3"><h4>Id</h4></div>
			<div class="col-md-3"><h4>Model name</h4></div>
			<div class="col-md-3"><h4>Delete</h4></div>
			<div class="col-md-3"><h4>Update</h4></div>
			<c:forEach items="${modelNames.content}" var="modelName">
				<div class="col-md-3">${modelName.id}</div>
				<div class="col-md-3">${modelName.name}</div>
				<div class="col-md-3">
					<a href="/admin/modelName/delete/${modelName.id}<custom:allParams/>">delete</a>
				</div>
				<div class="col-md-3">
					<a href="/admin/modelName/update/${modelName.id}<custom:allParams/>">update</a>
				</div>
			</c:forEach>
			<div class="col-md-12 text-center">
				<custom:pageable page="${modelNames}" cell="<li></li>" container="<ul class='pagination'></ul>" />
			</div>
		</div>
		<div class="col-md-2">
			<div class="col-md-6">
				<div class="dropdown">
					<button class="btn btn-danger dropdown-toggle" type="button" data-toggle="dropdown">
						Sort<span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Name asc" paramValue="name"/>
						<custom:sort innerHtml="Name desc" paramValue="name,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<custom:size posibleSizes="1,2,5,10" size="${modelNames.size}" title="Page size"/>
			</div>
		</div>
	</div>
</body>
</html>