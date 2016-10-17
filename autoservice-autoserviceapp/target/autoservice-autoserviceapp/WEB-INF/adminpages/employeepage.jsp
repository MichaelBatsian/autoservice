<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript">
		function submit(form){
			document.getElementById(form).submit();
		}
		function sendParameter(parameter) {
            document.getElementById("adminaction").value=parameter;
            }
	
	</script>
<title>Starter Template for Bootstrap</title>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/admincss.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/bootstrap-clockpicker.min.css">
<link rel="stylesheet" type="text/css" href="css/jquery-clockpicker.min.css">
<link rel="stylesheet" href="css/jquery-ui.css">
</head>
<body>
<div class="formhead">
    <div class="container">
        <div class="col-md-2">
            <H3>Manage<span class="em-text">System</span></H3>
        </div>
        <div class="col-md-1" ></div>
        <div class="col-md-7">
        	<p class="phead"><c:out value="${sessionScope.user}"></c:out></p>
        </div>
        <div class="col-md-2" >
          <p class="phead">  <a href="AdminController?exit=exit">Выйти</a></p>
        </div>		
    </div>
</div>
<div class="baseform">
     <div class="col-md-2">
         <div class="tableform">
        <div class="panel panel-default">
        <div class="panel-heading">Опции</div>
        <div class="panel-body baseform">
        <c:forEach items="${sessionScope.userTables}" var="parametr">
            <a class="btn btn-info btnstyle" href="AdminController?returnParametrName=${parametr}"><c:out value="${parametr}"/></a>
            <br>
        </c:forEach>
        </div>
       </div>
         </div>
    </div>
    <div class="col-md-8">
        <div class="panel panel-default">
            <div class="panel-heading">Запрашиваемые данные</div>
            <div class="panel-body baseform" >
			  <div style="overflow: scroll; height: 530px">	
				<table class="table">
					<c:choose> 
						<c:when test="${sessionScope.returnParametr=='Расписание за период'}" > 
							<tr >
								<td colspan="7"><p><b>Расписание работников сети</b></p><td>
							</tr>
								<tr>
									<td>Тaб.№</td><td>ФИО</td><td>Должность</td><td>Адрес</td><td>Дата</td><td>Время</td><td>Заказ</td>
								</tr>
							<c:if test="${empty requestScope.timeTablePeriod}">
							<tr>	
								<td colspan="7">
									<h2 style="color: blue;">Выберите парамтеры запроса</h2>
								</td>
							</tr>
							</c:if>		
							<c:forEach items="${requestScope.timeTablePeriod}" var="timeTableBean">
								<tr>
									<td><c:out value="${timeTableBean.employeeId}"/></td>
									<td><c:out value="${timeTableBean.fullname}"/></td>
									<td><c:out value="${timeTableBean.position}"/></td>
									<td><c:out value="${timeTableBean.location}"/></td>
									<td><c:out value="${timeTableBean.date}"/></td>
									<td><c:out value="${timeTableBean.time}"/></td>
									<td><c:out value="${timeTableBean.getStringOrder()}"/></td>
								</tr>
							</c:forEach>
						 </c:when>
						 <c:when test="${sessionScope.returnParametr=='Расписание работника'}" > 
							<tr>
								<td colspan="7"><p><b>Расписание работника сети</b></p><td>
							</tr>
								<tr>
									<td>Тaб.№</td><td>ФИО</td><td>Должность</td><td>Адрес</td><td>Дата</td><td>Время</td><td>Заказ</td>
								</tr>
							<c:if test="${empty requestScope.timeTableEmployeePeriod}">
							<tr>	
								<td colspan="7">
									<h2 style="color: blue;">Выберите парамтеры запроса</h2>
								</td>		
							</tr>
							</c:if>						
							<c:forEach items="${requestScope.timeTableEmployeePeriod}" var="timeTableBean">
								<tr>
									<td><c:out value="${timeTableBean.employeeId}"/></td>
									<td><c:out value="${timeTableBean.fullname}"/></td>
									<td><c:out value="${timeTableBean.position}"/></td>
									<td><c:out value="${timeTableBean.location}"/></td>
									<td><c:out value="${timeTableBean.date}"/></td>
									<td><c:out value="${timeTableBean.time}"/></td>
									<td><c:out value="${timeTableBean.getStringOrder()}"/></td>
								</tr>
							</c:forEach>
						 </c:when>
						 <c:when test="${sessionScope.returnParametr=='Заказы за период'}" > 
							<tr >
								<td colspan="6"><p><b>Заказы за период</b></p><td>
							</tr>
								<tr>
									<td>Заказ№</td><td>ФИО клиента</td><td>Адрес Станции</td>
									<td>Дата</td><td>Цена со скидкой</td><td>Статус заказа</td>
								</tr>
							<c:if test="${empty requestScope.ordersForPeriod}">
							<tr>	
								<td colspan="7">
									<h2 style="color: blue;">Выберите парамтеры запроса</h2>
								</td>
							</tr>
							</c:if>		
							<c:forEach items="${requestScope.ordersForPeriod}" var="orderBean">
								<tr>
									<td><c:out value="${orderBean.orderId}"/></td>
									<td><c:out value="${orderBean.fullname}"/></td>
									<td><c:out value="${orderBean.stationLocation}"/></td>
									<td><c:out value="${orderBean.date}"/></td>
									<td><c:out value="${orderBean.discountPrice}"/></td>
									<td><c:out value="${orderBean.status}"/></td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="7" align="left">Итого сумма всех заказов за период c ${param.dateFrom} по ${param.dateTo} 
										составила <b>${requestScope.totalSumOfOrders}</b></td>
							</tr>
						 </c:when>
					  </c:choose>
		   			</table>
				</div>
            </div>
        </div>
    </div>
    <div class="col-md-2">
        <div class="panel panel-default">
            <div class="panel-heading">Форма для ввода данных</div>
            <div class="panel-body baseform">
           	  <c:if test="${sessionScope.returnParametr=='Расписание за период' or sessionScope.returnParametr=='Заказы за период'}">	
           		<form id="correctionForm" action="AdminController">
           			<p>Адрес станции</p>
           			   <c:if test="${sessionScope.returnParametr=='Заказы за период'}">
           			    <select class="form-control select-sm" name="location">
           			     		<option value="" >Выберите адрес</option>
                      		 <c:forEach items="${sessionScope.stations}" var="serviceStationBean">
                        		<option value="${serviceStationBean.location}" >${serviceStationBean.location}</option>
                        	 </c:forEach>
                        </select>
                        </c:if>  
                   		<c:if test="${sessionScope.returnParametr=='Расписание за период'}">
           			    <select class="form-control select-sm" name="location" required="required">
           			     		<option value="" >Выберите адрес</option>
                      		 <c:forEach items="${sessionScope.stations}" var="serviceStationBean">
                        		<option value="${serviceStationBean.location}" >${serviceStationBean.location}</option>
                        	 </c:forEach>
                        </select>
                        </c:if>  
                    <p>Начало периода</p>
                    <input class="form-control" type="text" id="datepickerFrom" name="dateFrom" required="required" >
                    <p>Конец периода</p>
                    <input class="form-control" type="text" id="datepickerTo" name="dateTo" required="required">
                    <input type="submit" class="btn btn-info" value="Отправить запрос" style="margin-top:10px;" >
           		</form>
    		  </c:if>
    		  <c:if test="${sessionScope.returnParametr=='Расписание работника'}">	
           		<form id="correctionForm" action="AdminController">
           			<p>ФИО работника</p>
           			    <select class="form-control select-sm" name="employeeFullname" required="required">
                      		 <c:forEach items="${sessionScope.userdataBeans}" var="userdataBean">
                        		<option value="${userdataBean.fullname}" >${userdataBean.fullname}</option>
                        	 </c:forEach>
                        </select> 
                   
                    <p>Начало периода</p>
                    <input class="form-control" type="text" id="datepickerFrom" name="dateFrom" required="required" >
                    <p>Конец периода</p>
                    <input class="form-control" type="text" id="datepickerTo" name="dateTo" required="required" >
                    <input type="submit" class="btn btn-info" value="Отправить запрос" style="margin-top:10px;" >
           		</form>
    		  </c:if>
    		 
           				
			</div>
			</div>
		</div>
 </div>


					
<footer class="formfooter">

        <div class="container">
            <div class="col-md-2"></div>
            <div class="col-md-1" ></div>
            <div class="col-md-8"></div>
            <div class="col-md-2" ></div>
        </div>

</footer>
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript">
    $(function() {
        $( "#datepickerFrom" ).datepicker({
            placement:'top',
            align: 'left'
        });
    });
    $(function() {
        $( "#datepickerTo" ).datepicker({
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

</body>
</html>