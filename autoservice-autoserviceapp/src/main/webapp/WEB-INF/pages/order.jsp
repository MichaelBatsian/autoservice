<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<link rel="stylesheet" type="text/css" href="css/bootstrap-clockpicker.min.css">
<link rel="stylesheet" type="text/css" href="css/jquery-clockpicker.min.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<script type="text/javascript">
function submitOrderForm(){
    document.orderForm.submit();
}
</script>
<div class="forumhead container ">
    <div class="col-md-2">
        <H3>Профиль  <span class="em-text">Клиента</span></H3>
    </div>
    <div class="col-md-6">
        <h3>Добро пожаловать, <c:out value="${sessionScope.profile}"></c:out>, в систему онлайн-заказа </h3>
    </div>
    <div class="col-md-offset-4"></div>
</div>

<section class="forum container">
    <div class="col-md-4">
    <form name="orderForm" action="UserController">
       <input type="hidden" name="order" id="order" value="order">
       <div id="list">
       	 <ul>
            <li>Выберите Услугу <select class="form-control select-sm" name="service" onchange="submitOrderForm()">
            	<c:choose>	
            		<c:when test="${empty requestScope.service}">
            				<option value="">Выберите услугу</option>
                   		<c:forEach items="${sessionScope.allServices}" var="servicesBean">
                   			<option value="${servicesBean.serviceType}"><c:out value="${servicesBean.serviceType}"></c:out></option>
                   		</c:forEach>
            		</c:when>
            		<c:when test="${not empty requestScope.service}">
            			<c:forEach items="${sessionScope.allServices}" var="servicesBean">
                   			<c:if test="${requestScope.service!=servicesBean.serviceType}">
                   			<option value="${servicesBean.serviceType}"><c:out value="${servicesBean.serviceType}"></c:out></option>
                   			</c:if>
                   			<c:if test="${requestScope.service==servicesBean.serviceType}">
                   			<option selected value="${servicesBean.serviceType}"><c:out value="${servicesBean.serviceType}" ></c:out></option>
                   			</c:if>
                   		</c:forEach>
            		</c:when>
            	</c:choose>	
            		
            		
                </select>
            </li>
           
            <li>
             <c:choose>
             	<c:when test="${empty sessionScope.stationsToService}">
            		Выберите расположение<select class="form-control select-sm" name="stationLocation" disabled>
                 	   <option value="">Выберите расположение</option>
            					    	 </select>
            	</c:when>
            	<c:when test="${not empty sessionScope.stationsToService}">
            		Выберите расположение<select class="form-control select-sm" name="stationLocation">
                 	   <option value="">Выберите расположение</option>
                 	   <c:forEach items="${sessionScope.stationsToService}" var="servicesToStationsBean">
                 	  		<c:if test="${servicesToStationsBean.location!=sessionScope.stationLocation}">
                 	  			<option value="${servicesToStationsBean.location}"><c:out value="${servicesToStationsBean.location}"></c:out></option>
                 	  		</c:if>	
                 	  		<c:if test="${servicesToStationsBean.location==sessionScope.stationLocation}">
                 	  			<option selected value="${servicesToStationsBean.location}"><c:out value="${servicesToStationsBean.location}"></c:out></option>
                 	  		</c:if>	
                 	   </c:forEach>
            					    	 </select>
            	</c:when>				    
            </c:choose>
            </li>
            
            
            <li>
              <p>Выберите дату: 
              <c:if test="${not empty requestScope.dateError}">
             		<span style="color:#C12C29;"><c:out value="${requestScope.dateError}"></c:out></span>
              </c:if>
              <c:if test="${empty sessionScope.orderDate}">
             	 <input class="form-control" type="text" id="datepicker" name="orderDate" placeholder="год-мес-день" onchange="submitOrderForm()">
              </c:if>
              <c:if test="${not empty sessionScope.orderDate}">
           		  <input class="form-control" type="text" id="datepicker" name="orderDate" value="${sessionScope.orderDate}" onchange="submitOrderForm()">
              </c:if>
              </p>
              
            </li>
            <li> Выберите время
            <c:choose>
            	<c:when test="${empty sessionScope.freeTimeToOrder}">
              	<div class="clockpicker">
              	  <input type="text" class="form-control" name="orderTime" placeholder="--:--" disabled>
             	 </div>
             	</c:when>
             	
             	<c:when test="${not empty sessionScope.freeTimeToOrder}">
             	<select class="form-control select-sm" name="orderTime">
                 	<!--   <option value="" disabled>"--:--"</option>-->
                 	   <c:forEach items="${sessionScope.freeTimeToOrder}" var="freeTime">
                 	  		<option value="${freeTime}"><c:out value="${freeTime}"></c:out></option>
                 	   </c:forEach>
            					    	 </select>
              	
             	</c:when>
             </c:choose>
            </li>
            <li>
              <input type="hidden" name="createOrder" id="createOrder">
              <input  type="submit" class="btn btn-info" onclick="sendCreateOrder('create')" value="Добавить услугу">
              <a href="UserController?bill=getBill" class="btn btn-info" style="width: 120px; margin-left: 7px">Получить чек</a>
            </li>
        </ul>
 	  </div>
    </form>
 </div>
    <div class="col-md-8">
        <div class="panel panel-default">
            <div class="panel-heading"><h4 align="center">Добавленные услуги</h4></div>
            <div class="panel-body baseform" >
                <div style="overflow: scroll; height: 530px">
                 <c:if test="${not empty sessionScope.ordersToLastInvoice}">
                  <table class="table">
                 	<tr>
                 		<td><h4>Номер электронного чека<c:out value="${sessionScope.invoiceId}"></c:out></h4></td>
                 	</tr>
                 </table>
                   <c:forEach items="${sessionScope.ordersToLastInvoice}" var="ordersServiceBean"> 
                 <table>
                  <tr>
                  	<td>ФИО</td>
                  	<td><c:out value="${ordersServiceBean.fullname}"></c:out></td>
                  </tr>
                  <tr>
                  	<td>Электронный заказ №</td>
                  	<td><c:out value="${ordersServiceBean.orderId}"></c:out></td>
                  </tr>            
                  <tr>
                 	<td>Дата заказа</td>
                  	<td><c:out value="${ordersServiceBean.date}"></c:out></td>
                  </tr>
                  <tr>
                 	<td>Время заказа</td>
                  	<td><c:out value="${ordersServiceBean.time}"></c:out></td>
                  </tr>
                  <tr>
                  	<td>Время выполнения</td>
                  	<td><c:out value="${ordersServiceBean.addIntHoursToTime()}"></c:out></td>
                  </tr>
              
                  </table>
                  <table class="table">
                    <tr>
                    	<td>Услуга</td><td>Адрес станции</td><td>Цена услуги</td><td>Скидка</td><td>Цена со скидкой</td>
                    </tr>
                     <tr>
                    	<td><c:out value="${ordersServiceBean.service}"></c:out></td>
                    	<td><c:out value="${ordersServiceBean.location}"></c:out></td>
                    	<td><c:out value="${ordersServiceBean.price}"></c:out></td>
                    	<td><c:out value="${ordersServiceBean.getPercentFormat()}"></c:out></td>
                    	<td><c:out value="${ordersServiceBean.getDiscountPrice()}"></c:out></td>
                     </tr>
                  
                   </table>
                   </c:forEach>
                   <p class="em-text">Итого сумма чека: <c:out value="${sessionScope.invoiceTotalSum}"></c:out> </p>
                  </c:if>
                </div>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript">
    $(function() {
        $( "#datepicker" ).datepicker({
            placement:'top',
            align: 'left'
        });
    });
</script>
<script type="text/javascript" src="js/bootstrap-clockpicker.min.js"></script>
<script type="text/javascript">
    $('.clockpicker').clockpicker({
        placement: 'bottom',
        align: 'left',
        donetext: 'Готово'
    });
</script>
<script type="text/javascript">
function sendCreateOrder(parameter) {
    document.getElementById("createOrder").value=parameter;
  }
</script>

<%@ include file="footer.jsp" %>