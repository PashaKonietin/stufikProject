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
<style>
h4{
    font-family: Acquest Script;
    font-size: 48px;
    text-shadow: 0 0 10px white,
 				 0 0 10px #00b4d3;
  }
  </style>
</head>
<body>
<div class="row mainPart">
                                 
     <div class="col-md-12 col-sm-12 col-xs-12 categories">
	     <c:forEach items="${categoryes.content}" var="category">
			                  
	          <div class="col-md-6 category">
	              <div class="categoryName">
	                   <h4>${category.name}</h4>
	              </div>
	             
	                  <div class="col-md-12 data tabbable-panel">
	                  	<div class="row">
	                  	     <div class="col-md-7">
				               <div class="numberlist">
				                  <ol>
					                  <c:forEach items="${category.subCaterogyes}" var="subCategory">
					                     	  
										  <li>
											  <a class="menu-item-heading" href="/commodity/${subCategory.id}">${subCategory.name}</a>
										  </li>
											  
								     </c:forEach>  
								  </ol>
							 	</div>
							 </div>
							 <div class="col-md-5" style="margin-top: 4%">
			              	  	 <img class="img-thumbnail" width="200" src="/images/category/${category.id}${category.path}?version=${category.version}" />
			              	 </div>
						</div>
	              	  </div>
	              
	          </div>
	      </c:forEach>     
      </div>
</div>
</body>
</html>