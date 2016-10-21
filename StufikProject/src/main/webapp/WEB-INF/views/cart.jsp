<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<security:authorize access="!isAuthenticated()">
	<div class="row" style="margin-top: 100px; margin-bottom: 100px">
		<div class="col-md-4">
		</div>
		<div class="col-md-4">
			<h3>Для того щоб перейти у свій кабінет необхідно зареєструватися <a href="/registration"><i class="fa fa-user"></i>ТУТ</a></h3>
		</div>
		<div class="col-md-4">
		</div>
	</div>
</security:authorize>
<!--myCabinet-->
<security:authorize access="isAuthenticated()">
<div class="container" style="margin-top: 110px; margin-bottom: 100px">
    <div class="row">
		<div class="col-xs-12 col-sm-3 col-md-3">
			<div class="profile-sidebar">
				<!-- SIDEBAR USERPIC -->
				<div class="profile-userpic">
					<img src="/resources/images/img02.jpg" class="img-responsive" alt="">
				</div>
				<!-- END SIDEBAR USERPIC -->
				<!-- SIDEBAR USER TITLE -->
				<div class="profile-usertitle">
					<div class="profile-usertitle-name">
						Прізвище Ім'я
					</div>
				</div>
				<!-- END SIDEBAR USER TITLE -->
				<!-- SIDEBAR BUTTONS -->
				<div class="profile-userbuttons">
					<button type="button" class="btn btn-success btn-sm">Змінити фото</button>
					<button type="button" class="btn btn-danger btn-sm">Видалити</button>
				</div>
				<!-- END SIDEBAR BUTTONS -->
				<!-- SIDEBAR MENU -->
				<div class="profile-usermenu">
					<ul class="nav profile-nav">
						<li>
							<a href="#">
							<i class="glyphicon glyphicon-home"></i>
							Адреси доставки </a>
						</li>
						<li  class="active">
							<a href="#">
							<i class="glyphicon glyphicon-shopping-cart"></i>
							Моя корзина </a>
						</li>
						<li>
							<a href="#" target="_blank">
							<i class="glyphicon glyphicon-user"></i>
							Налаштування </a>
						</li>
						<li>
							<a href="#">
							<i class="glyphicon glyphicon-ok"></i>
							Мої бажання </a>
						</li>
						<li>
							<a href="#">
							<i class="glyphicon glyphicon-flag"></i>
							Допомога </a>
						</li>
					</ul>
				</div>
				<!-- END MENU -->
			</div>
		</div>
		<div class="col-xs-12 col-sm-7 col-md-7" style="background: whitesmoke">
            <div class="profile-content">
			   <c:forEach items="${commodities}" var="commodity">
								    <div class="col-xs-12 col-sm-6 col-md-6">
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
								                               <a href="/cart/delete/${commodity.id}" class="btn btn-danger"><i class="fa fa-times" aria-hidden="true"></i>Delete</a>
								                           </div>
								                           <div class="col-md-6">
								                               <a href="/buy/${commodity.id}" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span> Buy</a>
								                           </div>
								                        </div>
								                     </div>
								            </div>
						             </div>
						      </c:forEach>
            </div>
		</div>
		<div class="col-xs-12 col-sm-2 col-md-2" style="background: #b1ffb7;">
			<h3>Total price:</h3>
			<h4>$ ${totalPrice}</h4>
			 <a href="#" class="btn btn-primary"><span class="glyphicon glyphicon-shopping-cart"></span> Buy All</a>
		</div>
	</div>
</div>
</security:authorize>
</body>
</html>