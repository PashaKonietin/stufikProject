<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function myFunction(){
		alert("Вам на email було надіслано повідомлення, підтвердіть для того щоб зайти")
	}
</script>
</head>
<body>
<!-- <div class="container"> -->
<!-- 	<div class="row"> -->
<%-- 	<c:if test="${param.fail}"> --%>
<!-- 		<div class="col-md-12 col-xs-12"> -->
<!-- 			<p style="color: red;">Fail</p> -->
<!-- 		</div> -->
<%-- 	</c:if> --%>
<%-- 		<form:form action="/registration" class="form-group" method="post" modelAttribute="client"> --%>
<!-- 				<div class="form-group"> -->
<!-- 					<input name="login" placeholder="Login" class="form-control" /> -->
<!-- 					<input name="email" placeholder="E-mail" class="form-control" /> -->
<!-- 					<input name="password" type="password" placeholder="Some like ***" class="form-control" /> -->
<!-- 					<button type="submit" class="btn btn-primary">Ok</button> -->
<!-- 				</div> -->
<%-- 		</form:form> --%>
<!-- 	</div> -->
<!-- </div> -->

<div class="container" style="margin-bottom: 80px">

        <!-- REGISTRATION FORM -->
        <div class="col-xs-12 col-sm-12 col-md-12">
            <div class="text-center" style="padding:50px 0">
                <div class="logo">
                <p><a name="register"></a></p>
                register</div>
                <!-- Main Form -->
                <div class="login-form-1">
                   
                        <div class="login-form-main-message"></div>
                        <div class="main-login-form">
                            <div class="login-group">
	                             <div class="row">
									<c:if test="${param.fail}">
										<div class="col-md-12 col-xs-12">
											<p style="color: red;">Fail</p>
										</div>
									</c:if>
	                            	<form:form action="/registration" class="form-group" method="post" modelAttribute="client">
	                            		<div class="form-group">
		                                    <input name="name" placeholder="Name" class="form-control" />
		                                </div>
		                                <div class="form-group">
		                                    <input name="login" placeholder="Login" class="form-control" />
		                                </div>
		                                <div class="form-group">
		                                    <input name="password" type="password" placeholder="Password" class="form-control" />
		                                </div>
		                                <div class="form-group">
		                                    <input name="email" placeholder="E-mail" class="form-control" />
		                                </div>
		                                <button onclick="myFunction()" type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
		                            </form:form>
	                            </div>
                            	
                            	
                        	</div>
	                        <div class="etc-login-form">
	                            <p>already have an account? <a href="/login">login here</a></p>
	                        </div>
                        </div>
                    
                </div>
                <!-- end:Main Form -->
            </div>
        </div>
</div>

<!-- FORGOT PASSWORD FORM -->

<!-- <div class="container"> -->
<!--     <div class="row"> -->
<!--         <div class="col-xs-12 col-sm-12 col-md-12"> -->
<!--             <div class="text-center" style="padding:50px 0"> -->
<!--                 <div class="logo">             -->
<!--                 forgot password</div> -->
<!--                 Main Form -->
<!--                 <div class="login-form-1"> -->
<%--                     <form id="forgot-password-form" class="text-left"> --%>
<!--                         <div class="etc-login-form"> -->
<!--                             <p>При заповненні форми на вашу електроннону почту будуть відправлені інструкції як змінити пароль.</p> -->
<!--                         </div> -->
<!--                         <div class="login-form-main-message"></div> -->
<!--                         <div class="main-login-form"> -->
<!--                             <div class="login-group"> -->
<!--                                 <div class="form-group"> -->
<!--                                     <label for="fp_email" class="sr-only">Email address</label> -->
<!--                                     <input type="text" class="form-control" id="fp_email" name="fp_email" placeholder="email address"> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                             <a href="index-mycabinet.html" style="text-indent: 0.6em;" class="login-button"><i class="fa fa-chevron-right"></i></a> -->
<!--                         </div> -->
<!--                         <div class="etc-login-form"> -->
<!--                             <p>Вернутися до login <a href="/login">login here</a></p> -->
<!--                             <p>Зареєстрватися<a href="#register">Повернутися до реєстрації</a></p> -->
<!--                         </div> -->
<%--                     </form> --%>
<!--                 </div> -->
<!--                 end:Main Form -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
<!-- </div> -->
</body>
</html>