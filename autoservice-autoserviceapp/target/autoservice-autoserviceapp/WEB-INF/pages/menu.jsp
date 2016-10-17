<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
  
<div class="jumbotron container">

      <div class="row">
        <div class="col-md-4">
            <h1>Auto <span class="em-text">Service</span></h1>

         <div id="menu-text">
            <nav class="navmenu navmenu-default" role="navigation" >
                <a class="navmenu-brand " >Меню</a>
                <ul class="nav navmenu-nav">
                    <li class="dropdown menu-text">
                        <a href="UserController" class="dropdown-toggle" data-toggle="dropdown"> Наши услуги </a>
                        <ul class="dropdown-menu navmenu-nav" role="menu">
                       	 	<c:forEach items="${sessionScope.ourServices}" var="servicesBean">
                         		<li><a><c:out value="${servicesBean.serviceType}"></c:out></a></li>
                        	</c:forEach>    
                        </ul>
                    </li>
                     <li class="dropdown menu-text">
                        <a href="UserController" class="dropdown-toggle" data-toggle="dropdown"> Скидки </a>
                        <ul class="dropdown-menu navmenu-nav" role="menu">
                       	 	<c:forEach items="${sessionScope.discountsBeansMenu}" var="discountsBean">
                         		<li><a>${discountsBean.getPercentFormat()}" при накопленной сумме ${discountsBean.totalSum} </a></li>
                        	</c:forEach>    
                        </ul>
                    </li>
                    
                    <li><a href="UserController?feedback=feedback">Обратная связь</a></li>
                    <li><a href="UserController?priceList=priceList">Скачать прайс</a></li>
                </ul>
            </nav>
         </div>
        </div>