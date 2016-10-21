<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
</head>
<body>

    <p style="margin: 0"> <a name="arrow"></a></p>
   <div class="header-top hidden-xs">
				<div class="container" style="height: 50px">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="top-menu">
								<!-- Start Language -->
								<ul class="language">
									<li><a href="#"><img class="right-5" src="/resources/images/flags/ukr.png" alt="#">Ukrainian<i class="fa fa-caret-down left-5"></i></a>
										<ul>
											<li><a href="#"><img class="right-5" src="/resources/images/flags/fr.png" alt="#">French</a></li>
											<li><a href="#"><img class="right-5" src="/resources/images/flags/gb.png" alt="#">English</a></li>
											<li><a href="#"><img class="right-5" src="/resources/images/flags/ukr.png" alt="#">Ukrainian</a></li>
										</ul>
									</li>
								</ul>
								<!-- End Language -->
								<!-- Start Currency -->
								<ul class="currency">
									<li><a href="#"><strong>$</strong> UAH<i class="fa fa-caret-down left-5"></i></a>
										<ul>
											<li><a href="#">$ UAH</a></li>
											<li><a href="#">$ GBP</a></li>
											<li><a href="#">$ USD</a></li>
										</ul>
									</li>
								</ul>
								<!-- End Currency -->
								<p class="welcome-msg">Вітаємо на нашому сайті, <span class="userName">${authUser.login}</span></p>
							</div>
							<!-- Start Top-Link -->
							<div class="top-link">
								<ul class="link">
									<security:authorize access="!isAuthenticated()">
										<li>
											<a href="/registration"><i class="fa fa-user"></i>Зареєструватися</a>
										</li>
									</security:authorize>
									<security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
										<li>
											<a href="/admin"><i class="fa fa-unlock" aria-hidden="true"></i>Admin panel</a>
										</li>
									</security:authorize>
									<li><a href="checkout.html"><i class="fa fa-share"></i>Ознайомитись</a></li>
									<security:authorize access="isAuthenticated()">
										<li>
											<a href="/myCabinet"><i class="fa fa-user"></i>Мій кабінет</a>
										</li>
									</security:authorize>
									<security:authorize access="isAuthenticated()">
											<li>
												<form:form action="/logout" method="post">
													<button type="submit" class="btn btn-success">Logout</button>
												</form:form>
											</li>
									</security:authorize>
									<security:authorize access="!isAuthenticated()">
											<li>
												<form:form action="/login" method="get">
													<button type="submit" class="btn btn-success">Login</button>
												</form:form>
											</li>
									</security:authorize>
								</ul>
							</div>
							<!-- End Top-Link -->
						</div>
					</div>
				</div>
    </div>
    <!-- HEADER-MIDDLE START -->
			<div class="header-middle">
				<div class="container">
					<!-- Start Support-Client -->
					<div class="support-client hidden-xs">
						<div class="row">
							<!-- Start Single-Support -->
							<div class="col-md-3 hidden-sm">
								<div class="single-support">
									<div class="support-content">
										<i class="fa fa-clock-o"></i>
										<div class="support-text">
											<h1 class="zero gfont-1">working time</h1>
											<p>Mon- Sun: 8.00 - 18.00</p>
										</div>
									</div>
								</div>
							</div>
							<!-- End Single-Support -->
							<!-- Start Single-Support -->
							<div class="col-md-3 col-sm-4">
								<div class="single-support">
									<i class="fa fa-truck"></i>
									<div class="support-text">
										<h1 class="zero gfont-1">Free shipping</h1>
										<p>On order over $199</p>
									</div>
								</div>
							</div>
							<!-- End Single-Support -->
							<!-- Start Single-Support -->
							<div class="col-md-3 col-sm-4">
								<div class="single-support">
									<i class="fa fa-money"></i>
									<div class="support-text">
										<h1 class="zero gfont-1">Money back 100%</h1>
										<p>Within 30 Days after delivery</p>
									</div>
								</div>
							</div>
							<!-- End Single-Support -->
							<!-- Start Single-Support -->
							<div class="col-md-3 col-sm-4">
								<div class="single-support">
									<i class="fa fa-phone-square"></i>
									<div class="support-text">
										<h1 class="zero gfont-1">Phone: 0123456789</h1>
										<p>Order Online Now !</p>
									</div>
								</div>
							</div>
							<!-- End Single-Support -->
						</div>
					</div>
					<!-- End Support-Client -->
					<!-- Start logo & Search Box -->
					<div class="row">
						<div class="col-md-3 col-sm-12">
							<div class="logo">
								<a href="/" title="Malias"><img src="/resources/images/logo3.png" alt="Stufic"></a>
							</div>
						</div>
						<div class="col-md-9 col-sm-12">
		                    <div class="quick-access">
		                    	<div class="search-by-category">
		                    		<div class="search-container">
			                    		<select>
			                    			<option class="all-cate" style="font-weight:bold">All Categories</option>
 											<c:forEach items="${categoryes.content}" var="category">
 												<optgroup  class="cate-item-head" label=${category.name}> 
													<c:forEach items="${category.subCaterogyes}" var="subCategory">  
 					                       				<option class="cate-item-title">${subCategory.name}</option> 
 					                 				 </c:forEach>   
 												</optgroup> 
 											</c:forEach> 
			                    		</select> 
 		                    		</div> 
 		                    		<div class="header-search"> 
	                    			<form action="#">
 			                    			<input type="text" placeholder="Search">
 			                    			<button type="submit"><i class="fa fa-search"></i></button> 
		                    			</form> 
 		                    		</div> 
 		                    	</div> 
		                    	
		                    	<security:authorize access="isAuthenticated()">
			                    	<div class="top-cart">
			                    		<ul style="padding: 0px;">
			                    			<li>
				                    			<a href="cart.html">
				                    				<span><i class="fa fa-shopping-cart"></i></span>
				                    				<span class="cart-total">
	                                                    <span class="cart-title">Корзина</span>
				                    				</span>
				                    			</a>
												<div class="mini-cart-content">
													<div class="cart-img-details">
														<div class="cart-img-photo">
															<a href="#"><img src="images/Products/total-cart.jpg" alt="#"></a>
														</div>
														<div class="cart-img-content">
															<a href="#"><h4>Prod Aldults</h4></a>
															<span>
																<strong class="text-right">1 x</strong>
																<strong class="cart-price text-right">$180.00</strong>
															</span>
														</div>
														<div class="pro-del">
															<a href="#"><i class="fa fa-times"></i></a>
														</div>
													</div>
													<div class="clear"></div>
													<div class="cart-img-details">
														<div class="cart-img-photo">
															<a href="#"><img src="images/Products/total-cart2.jpg" alt="#"></a>
														</div>
														<div class="cart-img-content">
															<a href="#"><h4>Fact Prone</h4></a>
															<span>
																<strong class="text-right">1 x</strong>
																<strong class="cart-price text-right">$185.00</strong>
															</span>
														</div>
														<div class="pro-del">
															<a href="#"><i class="fa fa-times"></i></a>
														</div>
													</div>
													<div class="cart-inner-bottom">
														<span class="total">
															Total:
															<span class="amount">$550.00</span>
														</span>
														<span class="cart-button-top">
															<a href="cart.html">View Cart</a>
															<a href="checkout.html">Checkout</a>
														</span>
													</div>
												</div>
			                    			</li>
			                    		</ul>
			                    	</div>
		                    	</security:authorize>
		                    </div>
		                </div>
					</div>
					<!-- End logo & Search Box -->
				</div> 
			</div>
			<!-- HEADER-MIDDLE END -->
    
<!--NAVIGATION-->
 <nav class="navbar navbar-inverse" role="navigation">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse fixed stickyeah" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
<!-- 	        <li><a href="#">Про нас</a></li> -->
	        <li><a href="/category">Категоріїї</a></li>
	        <li><a href="/myCabinet">Кабінет</a></li>
	        <li class="hot"><a href="#">Бестселлери</a></li>
	        <li class="new"><a href="#">Нові продукти</a></li>
	        <li><a href="#">Контакти</a></li>
	      </ul>
    </div><!-- /.navbar-collapse -->
   
  </div><!-- /.container-fluid -->
</nav>
<!--END OF HEADER-->
</body>
</html>