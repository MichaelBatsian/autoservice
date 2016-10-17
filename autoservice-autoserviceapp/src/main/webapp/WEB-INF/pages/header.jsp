<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Autoservice</title>
    <link href="css/bootstrap.css" rel="stylesheet">
     <link href="css/style.css" rel="stylesheet">
     
     <script src="js/jquery.min.js" type="text/javascript" ></script>
     <script src="js/bootstrap.js"></script>
 
</head>

<body style="background: rgba(144, 144, 144, 0.23);">
    <nav class="navbar navbar-inverse navbar-fixed-top container">
     <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand em-text " href="#"><img class="carlabel" src="img/green.gif"></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="UserController?home=home">Главная</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Наши станции <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                      <c:forEach items="${sessionScope.stations}" var="serviceStationBean">
                        <li><a href="#"><c:out value="${serviceStationBean.location}"></c:out></a></li>
                    </c:forEach>
                    </ul>
                </li>
                <li><a href="#about">О Нас</a></li>
                <li><a href="#contact">Контакты</a></li>
                <li><a href="UserController?forum=forum">Форум</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                        Выберите язык <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Русский</a></li>
                    </ul>
                </li>
            </ul>
            
            <c:if  test="${not empty sessionScope.user}">
            <ul class="nav navbar-nav pull-right">
           		 <li class="search">
                        <a href="UserController?exit=exit">Выйти</a>
             	</li>
            </ul>
            </c:if>
        </div>
    </div>
</nav>