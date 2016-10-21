<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!--MAIN-->
<!--CAROUSEL AND CATEGORY CHOSING-->
<div class="container" style="margin-top: 45px;">

<a href="/cart">CArt</a>
<a href="/demo">Demo</a>

    <div class="row">
       <div class="col-xs-12 col-sm-4 col-md-4">
            <!-- CATEGORY-MENU-LIST START -->
	          <div class="left-category-menu">
	              <div class="left-product-cat">
	                  <div class="category-heading">
	                       <h2>categories</h2>
	                  </div>
	                  <div class="category-menu-list">
	                  <ul>
	                  <!-- Single menu start -->
		              <c:forEach items="${categoryes.content}" var="category">
		                  <li class="arrow-plus">
		                     <a href="#">${category.name}</a>
	 	                     <!-- MEGA MENU START -->
					         <div class="cat-left-drop-menu cat-left-drop-menu-photo-contain hidden-xs">
					             <div class="cat-left-drop-menu-left">
					                  <c:forEach items="${category.subCaterogyes}" var="subCategory">
					                       <a class="menu-item-heading" href="/commodity/${subCategory.id}">${subCategory.name}</a>
					                  </c:forEach> 
					             </div>
					         	 <div class="cat-left-drop-menu-left cat-left-drop-menu-photo">
			 	                     <img class="img-thumbnail" width="400" src="/images/category/${category.id}${category.path}?version=${category.version}" />
				                 </div>
					         </div>
	 			             <!--MEGA MENU END -->
		                  </li>
		              </c:forEach>
		              <!-- Single menu end -->
	                           
	                  <li class=" rx-parent">
	                      <a href="/category" class="rx-default">More categories <span class="cat-thumb  fa fa-plus"></span></a>
	                      <a class="rx-show"> close menu <span class="cat-thumb  fa fa-minus"></span></a>
	                  </li>
	                  <!-- MENU ACCORDION END -->
	                  </ul>
	             	  </div>
	               </div>
	           </div>
	       <!-- END CATEGORY-MENU-LIST -->
      	</div>
       
        <div class="col-xs-12 col-sm-8 col-md-8">
            <div id="carousel" class="carousel slide" data-interval="5000" data-ride="carousel">
                <!--Індикатори слайдів-->
                <ol class="carousel-indicators">
                    <li class="active" data-target="#carousel" data-slide="0"></li>
                    <li data-target="#carousel" data-slide="1"></li>
                    <li data-target="#carousel" data-slide="2"></li>
                </ol>
                <!--Слайди-->
                <div class="carousel-inner">
                    
                <div class="item active">
                <img src="/resources/images/bg-image004.png" width="100%" height="100%">
                    <div class="carousel-caption">
                        <!--<p>
                        <a href="#" class="btn btn-success">Дивитися</a>
                        </p>-->
                    </div>
                </div>
                    
                <div class="item">
                    <img src="/resources/images/bg-image005.png" width="100%">
                        <div class="carousel-caption">    
                            <!--<p>
                            <a href="#" class="btn btn-success">Дивитися</a>
                            </p>-->
                        </div>
                </div>
                    
                <div class="item">
                    <img src="/resources/images/bg-image006.png" width="100%">
                        <div class="carousel-caption">    
                            <!--<p>
                            <a href="#" class="btn btn-success">Дивитися</a>
                            </p>-->
                        </div>
                </div>
<!--                 Стрілки переходу між слайдами  -->
<!--              <a href="#carousel" class="left carousel-control" data-slide="prev">  -->
<!--                  <span class="glyphicon glyphicon-chevron-left"></span>  -->
<!--              </a> -->
<!--              <a href="#carousel" class="right carousel-control" data-slide="next">  -->
<!--                  <span class="glyphicon glyphicon-chevron-right"></span>  -->
<!--              </a>  -->
             
                </div>
            </div>
        </div>
        
    </div>
</div>
<!--END OF CAROUSEL-->

<!--BEGINNING OF CATEGORY CHOOSING-->


<div class="container clear">
    <div class="row">
        <div class="col-md-12">
            <div class="line"></div>
        </div>
    </div>
</div>
  
<!--BEGGING OF THE CATALOG-->
<div class="container clear">
     <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="tabbable-panel">
                    <div class="tabbable-line">
                        <ul class="nav nav-tabs ">
                            <li class="active">
                                <a href="#tab_default_1" data-toggle="tab">
                                Пропозиція дня </a>
                            </li>
                            <li>
                                <a href="#tab_default_2" data-toggle="tab">
                                Товари для двох </a>
                            </li>
                            <li>
                                <a href="#tab_default_3" data-toggle="tab">
                                Молл </a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab_default_1">
                                <div class="row">
		                           <c:forEach items="${commodities.content}" var="commodity">
		                                <div class="col-xs-12 col-sm-6 col-md-3">
		                                      <div class="thumbnail">
		                                      		<a href="/item/${commodity.id}">
		                                            	<img class="img-thumbnail" width="254" src="/images/commodity/${commodity.id}${commodity.path}?version=${commodity.version}" />
		                                            </a>
		                                            <div class="caption">
		                                                <h4>${commodity.brand.name} </h4>
		                                                <p>${commodity.description}</p>
		                                                <div class="row">
		                                                    <div class="col-md-6">
		                                                        <a href="/cart/addCommodity/${commodity.id}"class="btn btn-primary"><span class="glyphicon glyphicon-thumbs-up"></span> Like</a> 
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
                            </div>
                            <div class="tab-pane" id="tab_default_2">
                                <div class="row">
		                           <c:forEach items="${commodities.content}" var="commodity">
		                                <div class="col-xs-12 col-sm-6 col-md-3">
		                                      <div class="thumbnail">
		                                        <a href="/item/${commodity.id}">
		                                            <img class="img-thumbnail" width="254" src="/images/commodity/${commodity.id}${commodity.path}?version=${commodity.version}" />
		                                        </a>
		                                            <div class="caption">
		                                                <h4>${commodity.brand.name} </h4>
		                                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
		                                                <div class="row">
		                                                    <div class="col-md-6">
		                                                        <a class="btn btn-primary"><span class="glyphicon glyphicon-thumbs-up"></span> Like</a> 
		                                                    </div>
		                                                    <div class="col-md-6">
		                                                        <a href="#" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span> Buy</a>
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
                            </div>
                            <div class="tab-pane" id="tab_default_3">
                                <p>
                                    Howdy, I'm in Tab 3.
                                </p>
                                <p>
                                    Duis autem vel eum iriure dolor in hendrerit in vulputate. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat
                                </p>
                                <p>
                                    <a class="btn btn-info" href="http://j.mp/metronictheme" target="_blank">
                                        Learn more...
                                    </a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
         </div>
    </div>
</div>
<!-- <!--END OF CATALOG--> -->
   
<!--        <div class="container clear"> -->
<!--        <h2>Популярні категорії</h2>   -->
<!--         <div class="row"> -->
<!--                <div id="steps-slider"> -->
<%--                		<c:forEach items="${commodities.content}" var="commodity"> --%>
<!-- 			         <div class="col-xs-6 col-sm-6 col-md-3"> -->
<!-- 			            <ul class="pagination"> -->
<!-- 				            <li class="actives"> -->
<!-- 					            <a href="#"> -->
<%--                                 <h3>${category.name}</h3>  --%>
<!-- 						        <span>Pellentesque fermentum neque vel nisl auctor eget tincidunt mi.</span> -->
<!-- 					            </a> -->
<!-- 				            </li> -->
<!--                         </ul> -->
                        
<!-- 				    </div> -->
<%-- 				    </c:forEach> --%>
<!-- 				    <div class="col-xs-6 col-sm-6 col-md-2"> -->
<!-- 				        <ul class="pagination">     -->
<!-- 				            <li> -->
<!-- 					            <a href="#"> -->
<!-- 						        <h3>Категорія 2</h3> -->
<!-- 						        <span>Fusce porttitor, diam id accumsan porta, enim sem varius.</span> -->
<!-- 					            </a> -->
<!-- 				            </li> -->
<!--                         </ul> -->
<!--                     </div> -->
<!-- 				    <div class="col-xs-6 col-sm-6 col-md-2"> -->
<!-- 				        <ul class="pagination">     -->
<!-- 				            <li> -->
<!-- 					            <a href="#"> -->
<!-- 						        <h3>Категорія 3</h3> -->
<!-- 						        <span>Fusce porttitor, diam id accumsan porta, enim sem varius.</span> -->
<!-- 					            </a> -->
<!-- 				            </li> -->
<!--                         </ul> -->
<!--                     </div> -->
<!--                     <div class="col-xs-6 col-sm-6 col-md-2"> -->
<!-- 				        <ul class="pagination">     -->
<!-- 				            <li> -->
<!-- 					            <a href="#"> -->
<!-- 						        <h3>Категорія 4</h3> -->
<!-- 						        <span>Pellentesque fermentum neque vel nisl auctor eget tincidunt.</span> -->
<!-- 					            </a> -->
<!-- 				            </li> -->
<!--                         </ul> -->
<!--                     </div> -->
<!--                     <div class="col-xs-6 col-sm-6 col-md-2"> -->
<!-- 				        <ul class="pagination">     -->
<!-- 				            <li> -->
<!-- 					            <a href="#"> -->
<!-- 						        <h3>Категорія 5</h3> -->
<!-- 						        <span>Pellentesque fermentum neque vel nisl auctor eget tincidunt.</span> -->
<!-- 					            </a> -->
<!-- 				            </li> -->
<!--                         </ul> -->
<!--                     </div> -->
                    
<!--               </div> -->
<!--         </div> -->
<!--     </div> -->
<!-- <!--END OF CATEGORY CHOOSING--> 


<!-- <!--ITEMS OF CATEGORY-->
<!-- <div class="container clear"> -->
<!--     <div class="row">            -->
<!--             <ul class="gallery"> -->
<!--                <div class="row"> -->
<!--                     <div class="col-xs-6 col-sm-6 col-md-2">				 -->
<!-- 				        <li> -->
<!-- 					        <h4><a href="#">Товар нумеро 1</a></h4> -->
<!-- 					        <span class="sub-title"><a href="#">Поділитися</a></span> -->
<!-- 					        <div class="product-holder"> -->
<!-- 						        <div class="image"> -->
<!-- 							        <a href="#"><img class="large" src="/resources/images/bg-image03.jpg" alt="image description" width="180" height="91"></a> -->
<!-- 						        </div> -->
<!-- 						        <div class="wrap"> -->
<!-- 							        <span class="price">Ціна: $20.20</span> -->
<!-- 							        <ul class="stars"> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star02.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!-- 							        </ul> -->
<!-- 						        </div> -->
<!-- 						        <div class="stick"> -->
<!-- 							        <a href="#"><img src="images/stick-green.png" alt="image description" width="82" height="82" /></a> -->
<!-- 						        </div> -->
<!-- 					        </div> -->
<!-- 				        </li> -->
<!--                     </div> -->
<!--                     <div class="col-xs-6 col-sm-6 col-md-2"> -->
<!-- 				        <li> -->
<!-- 					        <h4><a href="#">Товар нумеро 2</a></h4> -->
<!-- 					        <span class="sub-title"><a href="#">Поділитися</a></span> -->
<!-- 					        <div class="product-holder"> -->
<!-- 						        <div class="image"> -->
<!-- 							        <a href="#"><img class="large" src="/resources/images/bg-image03.jpg" alt="image description" width="180" height="91"></a> -->
<!-- 						        </div> -->
<!-- 						        <div class="wrap"> -->
<!-- 							        <span class="price">Ціна: $8.80</span> -->
<!-- 							        <ul class="stars"> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star02.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!-- 							        </ul> -->
<!-- 						        </div> -->
<!-- 						        <div class="stick"> -->
<!-- 							        <a href="#"><img src="images/stick-violet.png" alt="image description" width="82" height="82" /></a> -->
<!-- 						        </div> -->
<!-- 					        </div> -->
<!-- 				        </li> -->
<!--                     </div> -->
<!--                      <div class="col-xs-6 col-sm-6 col-md-2">				 -->
<!-- 				        <li> -->
<!-- 					        <h4><a href="#">Товар нумеро 1</a></h4> -->
<!-- 					        <span class="sub-title"><a href="#">Поділитися</a></span> -->
<!-- 					        <div class="product-holder"> -->
<!-- 						        <div class="image"> -->
<!-- 							        <a href="#"><img class="large" src="/resources/images/bg-image03.jpg" alt="image description" width="180" height="91"></a> -->
<!-- 						        </div> -->
<!-- 						        <div class="wrap"> -->
<!-- 							        <span class="price">Ціна: $20.20</span> -->
<!-- 							        <ul class="stars"> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star02.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!-- 							        </ul> -->
<!-- 						        </div> -->
<!-- 						        <div class="stick"> -->
<!-- 							        <a href="#"><img src="/resources/images/stick-green.png" alt="image description" width="82" height="82" /></a> -->
<!-- 						        </div> -->
<!-- 					        </div> -->
<!-- 				        </li> -->
<!--                     </div> -->
<!--                     <div class="col-xs-6 col-sm-6 col-md-2"> -->
<!-- 				        <li> -->
<!-- 					        <h4><a href="#">Товар нумеро 2</a></h4> -->
<!-- 					        <span class="sub-title"><a href="#">Поділитися</a></span> -->
<!-- 					        <div class="product-holder"> -->
<!-- 						        <div class="image"> -->
<!-- 							        <a href="#"><img class="large" src="/resources/images/bg-image03.jpg" alt="image description" width="180" height="91"></a> -->
<!-- 						        </div> -->
<!-- 						        <div class="wrap"> -->
<!-- 							        <span class="price">Ціна: $8.80</span> -->
<!-- 							        <ul class="stars"> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star02.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!-- 							        </ul> -->
<!-- 						        </div> -->
<!-- 						        <div class="stick"> -->
<!-- 							        <a href="#"><img src="/resources/images/stick-violet.png" alt="image description" width="82" height="82" /></a> -->
<!-- 						        </div> -->
<!-- 					        </div> -->
<!-- 				        </li> -->
<!--                     </div> -->
<!--                     <div class="col-xs-6 col-sm-6 col-md-2"> -->
<!-- 				        <li> -->
<!-- 					        <h4><a href="#">Товар нумеро 3</a></h4> -->
<!-- 					        <span class="sub-title"><a href="#">Поділитися</a></span> -->
<!-- 					        <div class="product-holder"> -->
<!-- 						        <div class="image"> -->
<!-- 							        <a href="#"><img class="large" src="/resources/images/bg-image03.jpg" alt="image description" width="180" height="91"></a> -->
<!-- 						        </div> -->
<!-- 						        <div class="wrap"> -->
<!-- 							        <span class="price">Ціна: $29.00</span> -->
<!-- 							        <ul class="stars"> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star02.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!-- 							        </ul> -->
<!-- 						        </div> -->
<!-- 					        </div> -->
<!-- 				        </li> -->
<!--                     </div> -->
<!--                     <div class="col-xs-6 col-sm-6 col-md-2"> -->
<!-- 				        <li> -->
<!-- 					        <h4><a href="#">Товар нумеро 4</a></h4> -->
<!-- 					        <span class="sub-title"><a href="#">Поділитися</a></span> -->
<!-- 					        <div class="product-holder"> -->
<!-- 						        <div class="image"> -->
<!-- 							        <a href="#"><img class="large" src="/resources/images/bg-image03.jpg" alt="image description" width="180" height="91"></a> -->
<!-- 						        </div> -->
<!-- 						        <div class="wrap"> -->
<!-- 							        <span class="price">Ціна: $10.00</span> -->
<!-- 							        <ul class="stars"> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star02.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!-- 							        </ul> -->
<!-- 						        </div> -->
<!-- 						        <div class="stick"> -->
<!-- 							        <a href="#"><img src="/resources/images/stick-blue.png" alt="image description" width="82" height="82" /></a> -->
<!-- 						        </div> -->
<!-- 					        </div> -->
<!-- 				        </li> -->
<!-- 				    </div> -->
<!-- 				</div> -->
<!-- 				<div class="row"> -->
<!-- 				    <div class="col-xs-6 col-sm-6 col-md-2"> -->
<!-- 				        <li> -->
<!-- 					        <h4><a href="#">Товар нумеро 5</a></h4> -->
<!-- 					        <span class="sub-title"><a href="#">Поділитися</a></span> -->
<!-- 					        <div class="product-holder"> -->
<!-- 						        <div class="image"> -->
<!-- 							        <a href="#"><img class="large" src="images/bg-image03.jpg" alt="image description" width="180" height="91"></a> -->
<!-- 						        </div> -->
<!-- 						        <div class="wrap"> -->
<!-- 							        <span class="price">Ціна: $20.20</span> -->
<!-- 							        <ul class="stars"> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star02.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!-- 							        </ul> -->
<!-- 						        </div> -->
<!-- 						        <div class="stick"> -->
<!-- 							        <a href="#"><img src="/resources/images/stick-green.png" alt="image description" width="82" height="82" /></a> -->
<!-- 						        </div> -->
<!-- 					        </div> -->
<!-- 				        </li> -->
<!--                     </div> -->
<!--                     <div class="col-xs-6 col-sm-6 col-md-2"> -->
<!-- 				        <li> -->
<!-- 					        <h4><a href="#">Товар нумеро 6</a></h4> -->
<!-- 					        <span class="sub-title"><a href="#">Поділитися</a></span> -->
<!-- 					        <div class="product-holder"> -->
<!-- 						        <div class="image"> -->
<!-- 							        <a href="#"><img class="large" src="/resources/images/bg-image03.jpg" alt="image description" width="180" height="91"></a> -->
<!-- 						        </div> -->
<!-- 						        <div class="wrap"> -->
<!-- 							        <span class="price">Ціна: $8.80</span> -->
<!-- 							        <ul class="stars"> -->
<!--                                        <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star02.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!-- 							        </ul> -->
<!-- 						        </div> -->
<!-- 					        </div> -->
<!-- 				        </li> -->
<!--                     </div> -->
<!--                     <div class="col-xs-6 col-sm-6 col-md-2"> -->
<!--                         <li> -->
<!--                             <h4><a href="#">Товар нумеро 7</a></h4> -->
<!--                             <span class="sub-title"><a href="#">Поділитися</a></span> -->
<!--                             <div class="product-holder"> -->
<!--                                 <div class="image"> -->
<!--                                     <a href="#"><img class="large" src="/resources/images/bg-image03.jpg" alt="image description" width="180" height="91"></a> -->
<!--                                 </div> -->
<!--                                 <div class="wrap"> -->
<!--                                     <span class="price">Ціна: $29.00</span> -->
<!--                                     <ul class="stars"> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star02.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                                 <div class="stick"> -->
<!--                                     <a href=""><img src="/resources/images/stick-orange.png" alt="image description" width="82" height="82" /></a> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </li> -->
<!--                     </div> -->
<!--                     <div class="col-xs-6 col-sm-6 col-md-2"> -->
<!--                         <li> -->
<!--                             <h4><a href="#">Товар нумеро 8</a></h4> -->
<!--                             <span class="sub-title"><a href="#">Поділитися</a></span> -->
<!--                             <div class="product-holder"> -->
<!--                                 <div class="image"> -->
<!--                                     <a href="#"><img class="large" src="/resources/images/bg-image03.jpg" alt="image description" width="180" height="91"></a> -->
<!--                                 </div> -->
<!--                                 <div class="wrap"> -->
<!--                                     <span class="price">Ціна: $10.00</span> -->
<!--                                     <ul class="stars"> -->
<!--                                        <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star02.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                                 <div class="stick"> -->
<!--                                     <a href="#"><img src="/resources/images/stick-blue.png" alt="image description" width="82" height="82" /></a> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </li> -->
<!--                     </div> -->
<!--                     <div class="col-xs-6 col-sm-6 col-md-2"> -->
<!--                         <li> -->
<!--                             <h4><a href="#">Товар нумеро 7</a></h4> -->
<!--                             <span class="sub-title"><a href="#">Поділитися</a></span> -->
<!--                             <div class="product-holder"> -->
<!--                                 <div class="image"> -->
<!--                                     <a href="#"><img class="large" src="/resources/images/bg-image03.jpg" alt="image description" width="180" height="91"></a> -->
<!--                                 </div> -->
<!--                                 <div class="wrap"> -->
<!--                                     <span class="price">Ціна: $29.00</span> -->
<!--                                     <ul class="stars"> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star02.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                                 <div class="stick"> -->
<!--                                     <a href=""><img src="/resources/images/stick-orange.png" alt="image description" width="82" height="82" /></a> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </li> -->
<!--                     </div> -->
<!--                     <div class="col-xs-6 col-sm-6 col-md-2"> -->
<!--                         <li> -->
<!--                             <h4><a href="#">Товар нумеро 8</a></h4> -->
<!--                             <span class="sub-title"><a href="#">Поділитися</a></span> -->
<!--                             <div class="product-holder"> -->
<!--                                 <div class="image"> -->
<!--                                     <a href="#"><img class="large" src="/resources/images/bg-image03.jpg" alt="image description" width="180" height="91"></a> -->
<!--                                 </div> -->
<!--                                 <div class="wrap"> -->
<!--                                     <span class="price">Ціна: $10.00</span> -->
<!--                                     <ul class="stars"> -->
<!--                                        <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star02.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                         <li><a href="#"><img src="/resources/images/star03.png" alt="image description" width="15" height="15" /></a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                                 <div class="stick"> -->
<!--                                     <a href="#"><img src="/resources/images/stick-blue.png" alt="image description" width="82" height="82" /></a> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </li> -->
<!--                     </div> -->
<!-- 				</div> -->
<!-- 			</ul> -->
<!--     </div>		     -->
<!-- </div>  -->

<!-- <div class="container clear"> -->
<!--     <div class="row"> -->
<!--         <div align="center"><a href="#" class="btn btn-success btn-lg">Ще товари</a></div> -->
<!--     </div> -->
<!-- </div> -->

<div class="container clear">
    <div class="row">
        <div class="col-md-12">
            <div class="line"></div>
        </div>
    </div>
</div>
</body>
</html>