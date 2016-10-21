<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
$(function() {
	$('select[name=brand]').chosen();
	$('select[name=modelName]').chosen();
	$('select[name=description]').chosen();
});
</script>
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
								<li><a href="/admin/description">Description</a></li>
								<li><a href="/admin/manager">Manager</a></li>
								<li class="active"><a href="/admin/manager">Commodity</a><span class="sr-only">(current)</span></li>
								<li><a href="/admin/order">Order</a></li>
							</ul>
						</div>
					</div>
				</nav>
			</div>
	</div>
	
	<div class="row-fluid">
	<div class="col-md-3 col-xs-12">
			<form:form action="/admin/commodity" class="form-inline" method="get" modelAttribute="filter">
					<custom:hiddenInputs excludeParams="min, max, brandIds, colorIds, materialIds, subCategoryIds"/>
					<div class="form-group">
						<form:input path="min" placeholder="min price" class="form-control"/>
					</div>
					<div class="form-group">
						<form:input path="max" placeholder="max price" class="form-control"/>
					</div>
					<div class="form-group">
							<h4>Brands</h4>
					</div>
					<div class="form-group">
							<form:checkboxes items="${brands}" path="brandIds" itemLabel="name" itemValue="id" />
					</div>
					<div class="form-group">
						<h4>Colors  </h4>
					</div>
					<div class="form-group">
						<form:checkboxes items="${colors}" path="colorIds" itemLabel="name" itemValue="id"/>
					</div>
					<div class="form-group">
						<h4>Materials</h4>
					</div>
					<div class="form-group">
						<form:checkboxes items="${materials}" path="materialIds" itemLabel="name" itemValue="id"/>
					</div>
					<div class="form-group">
						<h4>SubCategory</h4>
					</div>
					<div class="form-group">
						<form:checkboxes items="${subCategoryes}" path="subCategoryIds" itemLabel="name" itemValue="id"/>
						<hr>
					</div>
					
					<div class="form-group">
						<button type="submit" class="btn btn-success">Ok</button>
					</div>
			</form:form>
		</div>
	<div class="col-md-6 col-xs-12">
	<form:form class="form-inline" action="/admin/commodity" method="post" modelAttribute="commodity" enctype="multipart/form-data">
		<form:errors path="*"/>
		<form:hidden path="id" />
		<form:hidden path="path" /> 
 		<form:hidden path="version" /> 
		<custom:hiddenInputs excludeParams="manager, subCategory, color, material, brand, modelName, clients, id"/>
 					<div class="row">
 						<div class="col-md-12">
 							<div class="form-group">
 								<label>Chose Brand:</label>
 								<form:select path="brand" items="${brands}" itemLabel="name" itemValue="id"  data-style="btn-primary"> 
 									<option value="0">Brand</option>
 								</form:select> 
 							</div> 
 						</div> 
 					</div> 
 					<div class="row">
 						<div class="col-md-12">
 							<div class="form-group">
 								<label>Chose subCategory:</label>
 								<form:select path="subCategory" items="${subCategoryes}" itemLabel="name" itemValue="id" class="selectpicker" data-style="btn-primary"> 
 								<option value="0">Category</option>
 								</form:select> 
 							</div> 
 						</div> 
 					</div> 
 					<div class="row"> 
 						<div class="col-md-12"> 
 							<div class="form-group"> 
 								<label>Chose color:</label> 
 								<form:select path="color" items="${colors}" itemLabel="name" itemValue="id" cssClass="selectpicker" data-style="btn-primary"> 
 								<option value="0">Color</option> 
 								</form:select> 
 							</div> 
 						</div>
 					</div> 
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label>Chose material:</label>
								<form:select path="material" items="${materials}" itemLabel="name" itemValue="id" cssClass="selectpicker" data-style="btn-primary">
								<option value="0">Materials</option>
								</form:select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label>Chose modelNAme:</label>
								<form:select path="modelName" items="${modelNames}" itemLabel="name" itemValue="id" cssClass="selectpicker" data-style="btn-primary">
								<option value="0">ModelName</option>
								</form:select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<form:input path="description" placeholder="Commodity description" class="form-control" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<form:input path="price" placeholder="Commodity price" class="form-control" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<form:input path="guarantee" placeholder="Commodity guarantee" class="form-control" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label>Please chose picture:</label>
						<label class="btn btn-default btn-file">
	        			Browse <input type="file" name="multipartFile" style="display: none;">
	        			</label>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<button type="submit" class="btn btn-success">Create</button>
							</div>
						</div>
					</div>
	</form:form>
	
</div>
<div class="col-md-3 col-xs-12">
			<div class="col-md-6">
				<div class="dropdown">
					<button class="btn btn-danger dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Brand asc" paramValue="brand"/>
						<custom:sort innerHtml="Brand desc" paramValue="brand,desc"/>
<%-- 						<custom:sort innerHtml="SubCategory asc" paramValue="subCategory"/> --%>
<%-- 						<custom:sort innerHtml="SubCategory desc" paramValue="SubCategory,desc"/> --%>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<custom:size posibleSizes="1,2,5,10" size="${commodities.size}" title="size"/>
			</div>
	</div>
</div>
<div class="container">
	<div class="col-md-12 col-xs-12">
	<div class="row">
		<div class="col-md-2"><h4>Image</h4></div>
		<div class="col-md-1"><h4>Brand</h4></div>
		<div class="col-md-1"><h4>Model</h4></div>
		<div class="col-md-1"><h4>SubCategory</h4></div>
		<div class="col-md-1"><h4>Color</h4></div>
		<div class="col-md-1"><h4>Material</h4></div>
		<div class="col-md-1"><h4>Price</h4></div>
		<div class="col-md-1"><h4>Guarantee</h4></div>
		<div class="col-md-1"><h4>Description</h4></div>
		<div class="col-md-1"><h4>Delete</h4></div>
		<div class="col-md-1"><h4>Update</h4></div>
	</div>
		<c:forEach items="${commodities.content}" var="commodity">
			<div class="row">
				<div class="col-md-2"><img class="img-thumbnail" width="100" src="/images/commodity/${commodity.id}${commodity.path}?version=${commodity.version}" /></div>
				<div class="col-md-1">${commodity.brand.name}</div>
				<div class="col-md-1">${commodity.modelName.name}</div>
				<div class="col-md-1">${commodity.subCategory.name}</div>
				<div class="col-md-1">${commodity.color.name}</div>
				<div class="col-md-1">${commodity.material.name}</div>
				<div class="col-md-1">${commodity.price}</div>
				<div class="col-md-1">${commodity.guarantee}</div>
				<div class="col-md-1">${commodity.description}</div>
				<div class="col-md-1"><a href="/admin/commodity/delete/${commodity.id}<custom:allParams/>">delete</a></div>
				<div class="col-md-1"><a href="/admin/commodity/update/${commodity.id}<custom:allParams/>">update</a></div>
			</div>
		</c:forEach>
		<div class="col-md-12 text-center">
			<custom:pageable page="${commodities}" cell="<li></li>" container="<ul class='pagination'></ul>" />
		</div>
	</div>
	
</div>
</body>
</html>