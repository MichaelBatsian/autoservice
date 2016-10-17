<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ include file="header.jsp" %>   
<div class="forumhead container ">
    <div class="col-md-2">
        <H3>Профиль  <span class="em-text">Клиента</span></H3>
    </div>
    <div class="col-md-6">
        <h3>Добро пожаловать, <c:out value="${sessionScope.profile}"/></h3>
    </div>
    <div class="col-md-4"><h3 align="right"><a  href="UserController?exit=exit">Выйти</a></h3></div>
</div>
<section class="forum container">
    <div class="col-md-4">
		<div id="list">
        <ul>
            <li> <a href="UserController?order=order&createInvoice=create" class="btn btn-info">Подать онлайн-заявку</a></li>
      		<li> <a href="UserController?userAccount=account" class="btn btn-info">Текущая скидка</a></li>
        </ul>
		</div>
    </div>
    <div class="col-md-8">
        <div class="panel panel-default">
            <div class="panel-heading"><h4 align="center">Запрашиваемые данные</h4></div>
            <div class="panel-body baseform" >
                <div style="overflow: scroll; height: 530px">
                    <table class="table">
                    	<c:choose>
                    		<c:when test="${not empty requestScope.userServices}">
                    			<tr>
                    				<td>№</td><td>Логин</td><td>Услуга</td><td>Время</td><td>Дата</td><td>Статус</td>
                    			</tr>
                    			<c:forEach items="${requestScope.userServices}" var="ordersBean">
                    				<tr>
                    					<td><c:out value="${ordersBean.orderId}"/></td>
										<td><c:out value="${ordersBean.login}"/></td>
										<td><c:out value="${ordersBean.service}"/></td>
										<td><c:out value="${ordersBean.time}"/></td>
										<td><c:out value="${ordersBean.date}"/></td>
										<td><c:out value="${ordersBean.status}"/></td>
                    				</tr>
                    			</c:forEach>
                    		</c:when>
                    		<c:when test="${empty requestScope.userServices and empty sessionScope.userDiscountData }">
                    			<p>Вы еще ни разу не воспользовались нашими услугами. Для этого перейдите по ссылке "Подать онлайн-заявку".</p>
                    		</c:when>
                    		<c:when test="${not empty sessionScope.userDiscountData}">
                    		
                    			<tr>
	                    			<td>Скидка</td>
	                    			<td>Накопленная сумма</td>
                    			</tr>
                    			<tr>
	                    			<td>${sessionScope.userDiscountData.getPercentFormat()}</td>
	                    			<td>${sessionScope.userDiscountData.clientTotalSum}</td>
                    			</tr>
                    		
                    		</c:when>
                    	</c:choose>
                    
                    
                    
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
<%@ include file="footer.jsp" %>