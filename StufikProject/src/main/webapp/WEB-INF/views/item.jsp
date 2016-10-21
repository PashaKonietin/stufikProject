<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
    <div class="row">
        <div class="col-md-7">
            
                <img class="img-thumbnail" width="600" src="/images/commodity/${commodity.id}${commodity.path}?version=${commodity.version}" />
            
        </div>

        <div class="col-md-5">
            <h3>${commodity.brand.name}</h3>
            <p>${commodity.modelName.name}</p>
            <div class="product-rating"><i class="fa fa-star gold"></i> <i class="fa fa-star gold"></i> <i class="fa fa-star gold"></i> <i class="fa fa-star gold"></i> <i class="fa fa-star-o"></i> </div>
            <hr>
            <h3>$ ${commodity.price}</h3>
            <div class="product-stock">In Stock</div>
            <hr>
            <div class="btn-group cart">
            <button type="button" class="btn btn-success">
                В корзину
            </button>
            </div>
                        <div class="btn-group wishlist">
                            <button type="button" class="btn btn-danger">
                                Додати в бажання
                            </button>
                        </div>
                    </div>
                </div>
			</div> 
		
<!--BEGGING OF THE CATALOG-->
<div class="container clear" style="margin-top: 50px;">
     <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="tabbable-panel">
                    <div class="tabbable-line">
                        <ul class="nav nav-tabs ">
                            <li class="active">
                                <a href="#tab_default_1" data-toggle="tab">
                                Опис товару </a>
                            </li>
                            <li>
                                <a href="#tab_default_2" data-toggle="tab">
                                Характеристики </a>
                            </li>
                            <li>
                                <a href="#tab_default_3" data-toggle="tab">
                                Коментарі </a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab_default_1">
                                <div class="row">
		                            <h3>${commodity.description}</h3>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab_default_2">
                                <div class="row">
		                           
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
<!--END OF CATALOG-->
	
</body>
</html>