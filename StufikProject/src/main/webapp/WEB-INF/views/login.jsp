<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- <div class="container"> -->
<!-- 	<div class="row"> -->
<%-- 	<c:if test="${param.fail}"> --%>
<!-- 		<div class="col-md-12 col-xs-12"> -->
<!-- 			<p style="color: red;">Fail</p> -->
<!-- 		</div> -->
<%-- 	</c:if> --%>
<%-- 		<form:form action="/login" class="form-inline" method="post"> --%>
<!-- 				<div class="form-group"> -->
<!-- 					<input name="login" placeholder="Login" class="form-control" /> -->
<!-- 					<input name="password" type="password" placeholder="********" class="form-control" /> -->
<!-- <!-- 					<input name="remember-me" type="checkbox" class="form-control"><label>Remember me</label> -->
<!-- 					<button type="submit" class="btn btn-primary">Ok</button> -->
<!-- 				</div> -->
<%-- 		</form:form> --%>
<!-- 	</div> -->
<!-- </div> -->

<c:if test="${authUser.confirmed} == false">
	<p>powke3powke</p>
</c:if>

<div class="container" style="margin-bottom: 110px">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <div class="text-center" style="padding:50px 0">
                <div class="logo">
                <p><a name="login"></a></p>
                login</div>
                <!-- Main Form -->
                <div class="login-form-1">
                    <div id="login-form" class="text-left">
                        <div class="login-form-main-message"></div>
                        <div class="main-login-form">
                            <div class="login-group">
                            	<div class="row">
	                            	<c:if test="${param.fail}">
										<div class="col-md-12 col-xs-12">
											<h4 style="color: red;">Неправильний логін або пароль</h4>
										</div>
									</c:if>
	                            	<form:form action="/login" class="form-inline" method="post">
	                            		<div class="form-group">
			                                 <input name="login" placeholder="Login" class="form-control" />
			                            </div>
			                            <div class="form-group">
			                                 <input name="password" type="password" placeholder="********" class="form-control" />
			                            </div>
<!-- 			                            <div class="form-group login-group-checkbox"> -->
<!-- 			                                    <input name="remember-me" type="checkbox" class="form-control"><label>Remember me</label> -->
<!-- 			                            </div> -->
			                             <button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
		                             </form:form>
                            	</div>
                            </div>
                        </div>
                        <div class="etc-login-form">                 
                            <p>new user? <a href="/registration">create new account</a></p>
                        </div>
                    </div>
                </div>
                <!-- end:Main Form -->
            </div>
        </div>
	</div>
</div>
</body>
</html>