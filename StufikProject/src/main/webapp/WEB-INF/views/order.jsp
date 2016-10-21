<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form class="form-inline" action="/buy/${commodity.id}" method="post" modelAttribute="order" enctype="multipart/form-data"> 
			<div class="container" style="margin-top: 100px; margin-bottom: 100px">	
										<div class="col-xs-12 col-sm-6 col-md-3">
		                                      <div class="thumbnail">
		                                            <img class="img-thumbnail" width="254" src="/images/commodity/${commodity.id}${commodity.path}?version=${commodity.version}" />
		                                            <div class="caption">
		                                                <h4>$ ${commodity.price}</h4>
		                                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
		                                                <div class="row">
		                                                    <div class="col-md-6">
		                                                        <a class="btn btn-primary"><span class="glyphicon glyphicon-thumbs-up"></span> Like</a> 
		                                                    </div>
		                                                </div>
		                                            </div>
		                                          
		                                      </div>
                                    	</div>
	<div class="row" style="margin-top: 20px">
		<div class="col-md-12">
			<div class="form-group">
				<label>Виберіть компанію доставки</label> 
				<form:select path="delivery" items="${deliveries}" itemLabel="deliveryCompany" itemValue="id"> 
				</form:select>
				
			</div>
		</div>
	</div>
	
	<div class="row" style="margin-top: 20px">
		<div class="col-md-12">
			<div class="form-group">
				<label>Кількість товару</label> 
				<form:input path="quantity" placeholder="quantity" class="form-control" /> 
			</div>
		</div>
	</div>
	
	<div class="row" style="margin-top: 20px">
		<div class="col-md-12">
			<div class="form-group">
				<label>Виберіть місто</label> 
				<form:select path="cityOrder" items="${cities}" itemLabel="name" itemValue="id"> 
				</form:select> 
			</div>
		</div>
	</div>
	
	<div class="row" style="margin-top: 20px">
		<div class="col-md-12">
			<div class="form-group">
				<label>Підтвердити замовлення </label> 
				<button type="submit" class="btn btn-success">Ok</button>
			</div>
		</div>
	</div>
</div>
	
	
	
	
	
	<tr> 
		<td>Спосіб доставки</td> 
		<td> 
			<form:select path="delivery" items="${deliveries}" itemLabel="deliveryCompany" itemValue="id"> 

			</form:select> 
		</td> 

	</tr> 
	<tr> 
		<td>Кількість</td> 
		<td> 
			<form:input path="quantity" placeholder="quantity" class="form-control" /> 
		</td> 

	</tr> 

	<tr> 
		<td>Місто</td> 
		<td> 
			<form:select path="cityOrder" items="${cities}" itemLabel="name" itemValue="id"> 

			</form:select> 
		</td> 

	</tr> 
	<tr> 
		<td><button type="submit" class="btn btn-success">Ok</button></td> 
<%-- 		<td><a href="/buy/${commodity.id}" class="btn btn-success"><input type="submit"/><span class="glyphicon glyphicon-shopping-cart"></span> Buy</a></td> --%>
	</tr> 

</form:form>
</body>
</html>