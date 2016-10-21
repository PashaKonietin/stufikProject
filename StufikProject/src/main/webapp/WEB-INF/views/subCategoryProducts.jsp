<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<div class="col-md-2 col-xs-12">
			<form:form action="/commodity" class="form-inline" method="get" modelAttribute="filter">
					<custom:hiddenInputs excludeParams="min, max, brandIds, colorIds, materialIds, subCategoryIds"/>
					<h4>Choose range of price</h4>
					<div class="form-group">
						<form:input path="min" placeholder="min price" class="form-control"/>
					</div>
					<div class="form-group">
						<form:input path="max" placeholder="max price" class="form-control"/>
					</div>
		
					<div class="accordion" style="margin-top: 7%;"><h4>SubCategory <i class="fa fa-chevron-down" aria-hidden="true"></i></h4></div>
					<div class="panel">
					  <form:checkboxes items="${subCategoryes}" path="subCategoryIds" itemLabel="name" itemValue="id"/>
					</div>
					
					<div class="accordion"><h4>Brands <i class="fa fa-chevron-down" aria-hidden="true"></i></h4></div>
					<div class="panel">
					  <form:checkboxes items="${brands}" path="brandIds" itemLabel="name" itemValue="id" />
					</div>
					
					<div class="accordion"><h4>Colors <i class="fa fa-chevron-down" aria-hidden="true"></i></h4></div>
					<div class="panel">
					  <form:checkboxes items="${colors}" path="colorIds" itemLabel="name" itemValue="id"/>
					</div>
					
					<div class="accordion"><h4>Materials <i class="fa fa-chevron-down" aria-hidden="true"></i></h4></div>
					<div class="panel">
					  <form:checkboxes items="${materials}" path="materialIds" itemLabel="name" itemValue="id"/>
					</div>
					
					<div class="form-group">
						<button type="submit" class="btn btn-success">Ok</button>
					</div>
			</form:form>
	</div>

		<div class="col-md-10 col-xs-12">
			<div class="row">
				<div class="col-md-9">
				</div>
			<div class="col-md-3">
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
<%-- 		<c:forEach items="${commodities.content}" var="commodity"> --%>
<!-- 			<div class="row"> -->
<%-- 				<div class="col-md-2"><img class="img-thumbnail" width="100" src="/images/commodity/${commodity.id}${commodity.path}?version=${commodity.version}" /></div> --%>
<%-- 				<div class="col-md-1">${commodity.brand.name}</div> --%>
<%-- 				<div class="col-md-1">${commodity.modelName.name}</div> --%>
<%-- 				<div class="col-md-2">${commodity.subCategory.name}</div> --%>
<%-- 				<div class="col-md-1">${commodity.color.name}</div> --%>
<%-- 				<div class="col-md-1">${commodity.material.name}</div> --%>
<%-- 				<div class="col-md-1">${commodity.price}</div> --%>
<%-- 				<div class="col-md-1">${commodity.guarantee}</div> --%>
<%-- 				<div class="col-md-1"><a href="/admin/commodity/delete/${commodity.id}<custom:allParams/>">delete</a></div> --%>
<%-- 				<div class="col-md-1"><a href="/admin/commodity/update/${commodity.id}<custom:allParams/>">update</a></div> --%>
<!-- 			</div> -->
<%-- 		</c:forEach> --%>
		
		<c:forEach items="${commodities.content}" var="commodity">
		    <div class="col-xs-12 col-sm-6 col-md-4">
		           <div class="thumbnail">
		                <a href="/item/${commodity.id}">
		                	<img class="img-thumbnail" width="300" src="/images/commodity/${commodity.id}${commodity.path}?version=${commodity.version}" />
		                </a>
		                    <div class="caption">
		                        <h2>${commodity.brand.name} </h2>
		                        <h3>$ ${commodity.price}</h3>
		                        <p>${commodity.description}</p>
		                        <div class="row">
		                           <div class="col-md-6">
		                               <a class="btn btn-primary"><span class="glyphicon glyphicon-thumbs-up"></span> Like</a> 
		                           </div>
		                           <div class="col-md-6">
		                               <a href="/buy/${commodity.id}" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span> Buy</a>
		                          </div>
		                        </div>
		                     </div>
		            </div>
             </div>
      </c:forEach>
		<div class="col-md-12 text-center">
				<custom:pageable page="${commodities}" cell="<li></li>" container="<ul class='pagination'></ul>" />
			</div>
		</div>
		
		
		
<%-- 		<c:forEach items="${page.content}" var="commodity"> --%>
<!-- 		 <div class="col-xs-12 col-sm-6 col-md-4"> -->
<!-- 		           <div class="thumbnail"> -->
<%-- 		                <img class="img-thumbnail" width="300" src="/images/commodity/${commodity.id}${commodity.path}?version=${commodity.version}" /> --%>
<!-- 		                    <div class="caption"> -->
<%--  		                    	${commodity.color.name} --%> 
<%-- 		                        <h3>${commodity.price}</h3> --%>
<!-- 		                        <div class="row"> -->
<!-- 		                           <div class="col-md-6"> -->
<!-- 		                               <a class="btn btn-primary"><span class="glyphicon glyphicon-thumbs-up"></span> Like</a>  -->
<!-- 		                           </div> -->
<!-- 		                           <div class="col-md-6"> -->
<!-- 		                               <a href="#" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span> Buy</a> -->
<!-- 		                          </div> -->
<!-- 		                        </div> -->
<!-- 		                     </div> -->
		                                          
<!-- 		            </div> -->
<!--              </div> -->
<%-- 		</c:forEach> --%>
</div>
<script>
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
    acc[i].onclick = function(){
        this.classList.toggle("active");
        this.nextElementSibling.classList.toggle("show");
  }
}
</script>
</body>
</html>