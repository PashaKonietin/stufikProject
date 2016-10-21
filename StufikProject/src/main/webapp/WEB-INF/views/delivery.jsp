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
								<li class="active"><a href="/admin/delivery">Delivery</a><span class="sr-only">(current)</span></li>
								<li><a href="/admin/material">Material</a></li>
								<li><a href="/admin/modelName">ModelName</a></li>
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
			<form:form action="/admin/delivery" class="form-inline" method="get" modelAttribute="filter">
					<custom:hiddenInputs excludeParams="deliveryMethodSearch"/>
					<div class="form-group">
						<form:input path="deliveryMethodSearch" placeholder="Delivery Method" class="form-control" />
						<button type="submit" class="btn btn-success">Ok</button>
					</div>
					<div class="form-group">
						<form:input path="deliveryCompanySearch" placeholder="Delivery Company" class="form-control" />
						<button type="submit" class="btn btn-success">Ok</button>
					</div>
			</form:form>
		</div>
		<div class="col-md-7">
			<form:form action="/admin/delivery" method="post" class="form-inline" modelAttribute="delivery" enctype="multipart/form-data">
				<form:hidden path="id" />
				<custom:hiddenInputs excludeParams="deliveryMethod, deliveryCompany, id"/>
				<div class="form-group">
					<label for="deliveryMethod"><form:errors path="deliveryMethod" /></label>
					<form:input id="deliveryMethod" path="deliveryMethod" placeholder="Delivery Method" class="form-control" />
				</div>
				<div class="form-group">
					<label for="deliveryCompany"><form:errors path="deliveryCompany" /></label>
					<form:input id="deliveryCompany" path="deliveryCompany" placeholder="Delivery Company" class="form-control" />
					<button type="submit" class="btn btn-success">Add Delivery</button>
				</div>
			</form:form>
		<div class="row">
			<div class="col-md-3"><h4>Delivery Method</h4></div>
			<div class="col-md-4"><h4>Delivery Company</h4></div>
			<div class="col-md-2"><h4>Delete</h4></div>
			<div class="col-md-2"><h4>Update</h4></div>
		</div>
			<c:forEach items="${page.content}" var="delivery">
				<div class="row">
 					<div class="col-md-3">${delivery.deliveryMethod}</div>
					<div class="col-md-4">${delivery.deliveryCompany}</div>
					<div class="col-md-2">
						<a href="/admin/delivery/delete/${delivery.id}<custom:allParams/>">delete</a>
					</div>
					<div class="col-md-2">
						<a href="/admin/delivery/update/${delivery.id}<custom:allParams/>">update</a>
					</div>
				</div>
			</c:forEach>
			<div class="col-md-12 text-center">
				<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
			</div>
		</div>
		<div class="col-md-2">
			<div class="col-md-6">
				<div class="dropdown">
					<button class="btn btn-danger dropdown-toggle" type="button" data-toggle="dropdown">
						Sort<span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Method asc" paramValue="deliveryMethod"/>
						<custom:sort innerHtml="Method desc" paramValue="deliveryMethod,desc"/>
						<custom:sort innerHtml="Company asc" paramValue="deliveryCompany"/>
						<custom:sort innerHtml="Company desc" paramValue="deliveryCompany,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" title="Page size"/>
			</div>
		</div>
	</div>
</body>
</html>