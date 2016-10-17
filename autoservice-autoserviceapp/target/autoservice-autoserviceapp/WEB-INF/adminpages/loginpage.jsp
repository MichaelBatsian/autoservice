<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Starter Template for Bootstrap</title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/admincss.css" rel="stylesheet">
    <title></title>
</head>
<body>
<div class="formhead">
    <div class="container">
      <div class="col-md-2">
        <H3>Manage<span class="em-text">System</span></H3>
    </div>
        <div class="col-md-1" ></div>
    <div class="col-md-8">
        <p class="phead">Добро пожаловать в систему управления ресурсом AutoService</p>
    </div>
        <div class="col-md-2" ></div>
    </div>
</div>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
       	<c:if test="${requestScope.user=='wrong'}">
    		<h3 class="modal-title" align="center" style="color: red;">Вы ввели неверный пароль или логин, попробуйте еще раз.</h3>	
    	</c:if>
    	<c:if test="${requestScope.user=='blocked'}">
    		<h3 class="modal-title" align="center" style="color: red;">Ваш аккаунт заблокирован, обратитесь в службу поддержки.</h3>	
    	</c:if>
    	<c:if test="${empty sessionScope.user and empty requestScope.user}">
    		<h3 class="modal-title" align="center">Введите логин и пароль</h3>	 
        </c:if>
            
        </div>
        <form action="AuthController" id="contact-form" class="modal-body" role="form">
            <div class="form-group">
                <input class="form-control input-lg" type="text" placeholder="Логин" required name="login">
            </div>
            <div class="form-group">
                <input class="form-control input-lg" type="password" placeholder="Пароль" required name="password">
            </div>
        <div class="modal-footer" style="text-align: center;">
           <input class="btn btn-info btn-lg" type="submit" value="Войти">
        </div>
        </form>
    </div>
</div>

<footer style="margin-top: 250px; height: 100px;">
</footer>
</body>
</html>