<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%--     pageEncoding="ISO-8859-1"%> --%>
<%--      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<%--     <%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%> --%>
<%--     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>
<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<!-- <title>Insert title here</title> -->
<!-- <script> -->
// $(function() {
// 	$('select[name=subCategory]').chosen();
// 	$('select[name=color]').chosen();
// 	$('select[name=material]').chosen();
// });
<!-- </script> -->
<!-- </head> -->
<!-- <body> -->
<!-- 	<div class="row"> -->
<!-- 			<div class="col-md-12"> -->
<!-- 				<nav class="navbar navbar-inverse"> -->
<!-- 					<div class="container-fluid"> -->
<!-- 						<div class="collapse navbar-collapse" id=""> -->
<!-- 							<ul class="nav navbar-nav"> -->
<!-- 								<li><a href="/admin">Admin Panel</a></li> -->
<!-- 								<li><a href="/admin/brand">Brand</a></li> -->
<!-- 								<li><a href="/admin/city">City</a></li> -->
<!-- 								<li><a href="/admin/color">Color</a></li> -->
<!-- 								<li><a href="/admin/delivery">Delivery</a></li> -->
<!-- 								<li><a href="/admin/material">Material</a></li> -->
<!-- 								<li><a href="/admin/modelName">Model Name</a></li> -->
<!-- 								<li><a href="/admin/subCategory">SubCategory</a></li> -->
<!-- 								<li class="active"><a href="/admin/description">Description</a><span class="sr-only">(current)</span></li> -->
<!-- 							</ul> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</nav> -->
<!-- 			</div> -->
<!-- 	</div> -->
	
<!-- 	<div class="row-fluid"> -->
<!-- 	<div class="col-md-3 col-xs-12"> -->
<%-- 			<form:form action="/admin/description" class="form-inline" method="get" modelAttribute="filter"> --%>
<%-- 					<custom:hiddenInputs excludeParams="weightSearch, colorIds, materialIds, subCategoryIds"/> --%>
<!-- 					<div class="form-group"> -->
<%-- 						<form:input path="weightSearch" placeholder="Weight search" class="form-control" /> --%>
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<!-- 						<h4>SubCategory</h4> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<%-- 						<form:checkboxes items="${subCategoryes}" path="subCategoryIds" itemLabel="name" itemValue="id"/> --%>
<!-- 						<hr> -->
<!-- 					</div> -->
					
<!-- 					<div class="form-group"> -->
<!-- 						<h4>Colors  </h4> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<%-- 						<form:checkboxes items="${colors}" path="colorIds" itemLabel="name" itemValue="id"/> --%>
<!-- 						<hr> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<!-- 						<h4>Materials</h4> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<%-- 						<form:checkboxes items="${materials}" path="materialIds" itemLabel="name" itemValue="id"/> --%>
<!-- 						<hr> -->
<!-- 					</div> -->
<!-- 					<div class="form-group"> -->
<!-- 						<button type="submit" class="btn btn-success">Ok</button> -->
<!-- 					</div> -->
<%-- 			</form:form> --%>
<!-- 		</div> -->
<!-- 	<div class="col-md-7 col-xs-12"> -->
<%-- 	<form:form class="form-inline" action="/admin/description" method="post" modelAttribute="description"> --%>
<%-- 		<form:errors path="*"/> --%>
<%-- 		<form:hidden path="id" /> --%>
<%-- 		<custom:hiddenInputs excludeParams="subCategory, color, material, weight, id"/> --%>
<!--  					<div class="row"> -->
<!--  						<div class="col-md-12"> -->
<!--  							<div class="form-group"> -->
<!--  								<label>Chose subCategory:</label> -->
<%--  								<form:select path="subCategory" items="${subCategoryes}" itemLabel="name" itemValue="id" cssClass="selectpicker" data-style="btn-primary">  --%>
<!--  								<option value="0">Category</option> -->
<%--  								</form:select>  --%>
<!--  							</div>  -->
<!--  						</div>  -->
<!--  					</div>  -->
<!--  					<div class="row">  -->
<!--  						<div class="col-md-12">  -->
<!--  							<div class="form-group">  -->
<!--  								<label>Chose color:</label>  -->
<%--  								<form:select path="color" items="${colors}" itemLabel="name" itemValue="id" cssClass="selectpicker" data-style="btn-primary">  --%>
<!--  								<option value="0">Color</option>  -->
<%--  								</form:select>  --%>
<!--  							</div>  -->
<!--  						</div> -->
<!--  					</div>  -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col-md-12"> -->
<!-- 							<div class="form-group"> -->
<!-- 								<label>Chose material:</label> -->
<%-- 								<form:select path="material" items="${materials}" itemLabel="name" itemValue="id" cssClass="selectpicker" data-style="btn-primary"> --%>
<!-- 								<option value="0">Materials</option> -->
<%-- 								</form:select> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col-md-12"> -->
<!-- 							<div class="form-group"> -->
<%-- 								<label for="weight"><form:errors path="weight" /></label> --%>
<%-- 								<form:input path="weight" placeholder="Commodity weight" class="form-control" /> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col-md-12"> -->
<!-- 							<div class="form-group"> -->
<%-- 								<form:input path="name" placeholder="Description name" class="form-control" /> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col-md-12"> -->
<!-- 							<div class="form-group"> -->
<!-- 								<button type="submit" class="btn btn-success">Create</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<%-- 	</form:form> --%>
<!-- 	<div class="row"> -->
<!-- 		<div class="col-md-2"><h4>Name</h4></div> -->
<!-- 		<div class="col-md-3"><h4>SubCategory</h4></div> -->
<!-- 		<div class="col-md-2"><h4>Color</h4></div> -->
<!-- 		<div class="col-md-2"><h4>Material</h4></div> -->
<!-- 		<div class="col-md-1"><h4>Weight</h4></div> -->
<!-- 		<div class="col-md-1"><h4>Delete</h4></div> -->
<!-- 		<div class="col-md-1"><h4>Update</h4></div> -->
<!-- 	</div> -->
<%-- 		<c:forEach items="${descriptions.content}" var="description"> --%>
<!-- 			<div class="row"> -->
<%-- 				<div class="col-md-2">${description.name}</div> --%>
<%-- 				<div class="col-md-3">${description.subCategory.name}</div> --%>
<%-- 				<div class="col-md-2">${description.color.name}</div> --%>
<%-- 				<div class="col-md-2">${description.material.name}</div> --%>
<%-- 				<div class="col-md-1">${description.weight}</div> --%>
<%-- 				<div class="col-md-1"><a href="/admin/description/delete/${description.id}<custom:allParams/>">delete</a></div> --%>
<%-- 				<div class="col-md-1"><a href="/admin/description/update/${description.id}<custom:allParams/>">update</a></div> --%>
<!-- 			</div> -->
<%-- 		</c:forEach> --%>
<!-- 		<div class="col-md-12 text-center"> -->
<%-- 			<custom:pageable page="${descriptions}" cell="<li></li>" container="<ul class='pagination'></ul>" /> --%>
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="col-md-2 col-xs-12"> -->
<!-- 			<div class="col-md-6"> -->
<!-- 				<div class="dropdown"> -->
<!-- 					<button class="btn btn-danger dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span> -->
<!-- 					</button> -->
<!-- 					<ul class="dropdown-menu"> -->
<%-- 						<custom:sort innerHtml="Weight asc" paramValue="weight"/> --%>
<%-- 						<custom:sort innerHtml="Weight desc" paramValue="weight,desc"/> --%>
<%-- 						<custom:sort innerHtml="SubCategory asc" paramValue="subCategory"/> --%>
<%-- 						<custom:sort innerHtml="SubCategory desc" paramValue="SubCategory,desc"/> --%>
<!-- 					</ul> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="col-md-6"> -->
<%-- 				<custom:size posibleSizes="1,2,5,10" size="${descriptions.size}" title="size"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- </div> -->
<!-- </body> -->
<!-- </html> -->