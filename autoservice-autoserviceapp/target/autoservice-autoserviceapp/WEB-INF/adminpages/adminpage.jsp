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
<script src="js/jquery.min.js" type="text/javascript" ></script>
 <script src="js/bootstrap.js"></script>
<script type="text/javascript">
<script src="js/user.js"></script>
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
    <div class="col-md-6">
        <div class="panel panel-default">
            <div class="panel-heading">Запрашиваемые данные</div>
            <div class="panel-body baseform" >
			  <div style="overflow: scroll; height: 530px">	
				<table class="table">
					<c:choose> 
						<c:when test="${sessionScope.returnParametr=='users'}" > 
							<tr>
								<td colspan="5"><p><b>users</b></p><td>
							</tr>
								<tr>
									<td>№</td><td>Логин</td><td>Пароль</td><td>Роль</td><td>Статус</td>
								</tr>
							<c:forEach items="${sessionScope.userTable}" var="userBean">
								
								<tr>
									<td><c:out value="${userBean.userId}"/></td>
									<td><c:out value="${userBean.login}"/></td>
									<td><c:out value="${userBean.password}"/></td>
									<td><c:out value="${userBean.role}"/></td>
									<td><c:out value="${userBean.blockedStatus}"/></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='roles'}" > 
							<tr>
								<td colspan="2"><p><b>roles</b></p><td>
							</tr>
							<tr>
									<td>№</td><td>Роль</td>
								</tr>
							<c:forEach items="${sessionScope.userTable}" var="roleBean">
								<tr>
									<td><c:out value="${roleBean.roleId}"/></td>
									<td><c:out value="${roleBean.role}"/></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='employee_position'}" > 
							<tr>
								<td colspan="2"><p><b>Employee position</b></p><td>
							</tr>
							<tr>
									<td>№</td><td>Должность</td>
								</tr>
							<c:forEach items="${sessionScope.userTable}" var="employeePositionBean">
								<tr>
									<td><c:out value="${employeePositionBean.positionId}"/></td>
									<td><c:out value="${employeePositionBean.position}"/></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='usersdata'}" > 
							<tr>
								<td colspan="7"><p><b>usersdata</b></p><td>
							</tr>
							<tr>
									<td>№</td><td>Логин</td><td>ФИО</td><td>Дата рождения</td><td>Адрес</td><td>Телефон</td><td>Пол</td>
								</tr>
							<c:forEach items="${sessionScope.userTable}" var="userdataBean">
								<tr>
									<td><c:out value="${userdataBean.userdataId}"/></td>
									<td><c:out value="${userdataBean.login}"/></td>
									<td><c:out value="${userdataBean.fullname}"/></td>
									<td><c:out value="${userdataBean.birthday}"/></td>
									<td><c:out value="${userdataBean.adress}"/></td>
									<td><c:out value="${userdataBean.phone}"/></td>
									<td><c:out value="${userdataBean.gender}"/></td>
								</tr>
							</c:forEach>
						</c:when>
						
						
						<c:when test="${sessionScope.returnParametr=='discounts'}">
								<tr>
									<td colspan="3"><p><b>Discounts</b></p><td>
								</tr>
								<tr>
									<td>№</td><td>Скидка,%</td><td>Сумма руб. для скидки</td>
								</tr>
							
							<c:forEach items="${sessionScope.userTable}" var="discountsBean">
								<tr>
									<td><c:out value="${discountsBean.discountId}"/></td>
									<td><c:out value="${discountsBean.getPercentFormat()}"/></td>
									<td><c:out value="${discountsBean.totalSum}"/></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='client_discount'}">
								<tr>
									<td colspan="5"><b>client_discount</b><td>
								</tr>
								<tr>
									<td>№</td><td>Логин</td><td>Скидка,%</td><td>Общая сумма клиента</td><td>Бонус клиента</td>
								</tr>
							<c:forEach items="${sessionScope.userTable}" var="clientDiscountBean">
								<tr>
									<td><c:out value="${clientDiscountBean.clientDiscountId}"/></td>
									<td><c:out value="${clientDiscountBean.login}"/></td>
									<td><c:out value="${clientDiscountBean.getPercentFormat()}"/></td>
									<td><c:out value="${clientDiscountBean.clientTotalSum}"/></td>
									<td><c:out value="${clientDiscountBean.bonus}"/></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='forum'}">
								<tr>
									<td colspan="2"><p><b>forum</b></p><td>
								</tr>
								<tr>
									<td>№</td><td>Тема форума</td>
								</tr>
							<c:forEach items="${sessionScope.userTable}" var="forumBean">
								<tr>
									<td><c:out value="${forumBean.topicId}"/></td>
									<td><c:out value="${forumBean.topic}"/></td>
							    </tr>
							</c:forEach>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='employee_list'}">
								<tr>
									<td colspan="6"><p><b>employee_list</b></p><td>
								</tr>
								<tr>
									<td>№</td><td>Логин</td><td>ФИО</td><td>Должность</td><td>станция</td><td>Адрес</td>
								</tr>
							<c:forEach items="${sessionScope.userTable}" var="employeeListBean">
								<tr>
									<td><c:out value="${employeeListBean.employeeId}"/></td>
									<td><c:out value="${employeeListBean.login}"/></td>
									<td><c:out value="${employeeListBean.fullname}"/></td>
									<td><c:out value="${employeeListBean.position}"/></td>
									<td><c:out value="${employeeListBean.stationName}"/></td>
									<td><c:out value="${employeeListBean.location}"/></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='invoice'}">
							<c:forEach items="${sessionScope.userTable}" var="invoiceBean">
								<tr>
									<td><c:out value="${invoiceBean.invoiceId}"/></td>
									<td><c:out value="${invoiceBean.status}"/></td>
							    </tr>
							</c:forEach>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='messages'}">
								<tr>
									<td colspan="4"><b>messages</b><td>
								</tr>
								<tr>
									<td>№</td><td>Пользователь</td><td>Сообщение</td><td>Тема</td><td></td>
								</tr>
							<c:forEach items="${sessionScope.userTable}" var="messageBean">
								<tr>
									<td><c:out value="${messageBean.messageId}"/></td>
									<td><c:out value="${messageBean.login}"/></td>
									<td><c:out value="${messageBean.message}"/></td>
									<td><c:out value="${messageBean.topic}"/></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='services'}">
						<tr>
									<td colspan="4"><b>services</b><td>
								</tr>
								<tr>
									<td>№</td><td>Услуга</td><td>Затраты человекочасов</td><td>Цены</td>
								</tr>
							<c:forEach items="${sessionScope.userTable}" var="sevicesBean">
								<tr>
									<td><c:out value="${sevicesBean.serviceId}"/></td>
									<td><c:out value="${sevicesBean.serviceType}"/></td>
									<td><c:out value="${sevicesBean.manHours}"/></td>
									<td><c:out value="${sevicesBean.price}"/></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='service_station'}">
						<tr>
									<td colspan="3"><b>service_station</b><td>
								</tr>
								<tr>
									<td>№</td><td>Станция</td><td>Адрес</td>
								</tr>
							<c:forEach items="${sessionScope.userTable}" var="serviceStationBean">
								<tr>
									<td><c:out value="${serviceStationBean.stationId}"/></td>
									<td><c:out value="${serviceStationBean.stationName}"/></td>
									<td><c:out value="${serviceStationBean.location}"/></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='time_table'}">
							<tr>
								<td colspan="6"><p><b>Расписание работников сети</b></p><td>
							</tr>
								<tr>
									<td>№</td><td>Табельный номер</td><td>ФИО</td><td>Дата</td><td>Время</td><td>Номер заказа</td>
								</tr>
							<c:forEach items="${sessionScope.userTable}" var="timeTableBean">
								<tr>
									<td><c:out value="${timeTableBean.timeTableId}"/></td>
									<td><c:out value="${timeTableBean.employeeId}"/></td>
									<td><c:out value="${timeTableBean.fullname}"/></td>
									<td><c:out value="${timeTableBean.date}"/></td>
									<td><c:out value="${timeTableBean.time}"/></td>
									<td><c:out value="${timeTableBean.getStringOrder()}"/></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='orders'}">
							<tr>
								<td colspan="6"><p><b>Orders</b></p><td>
							</tr>
								<tr>
									<td>№</td><td>ФИО</td><td>Станиция</td><td>Услуга</td><td>Цена</td><td>Статус</td>
								</tr>
							<c:forEach items="${sessionScope.userTable}" var="ordersBean">
								<tr>
									<td><c:out value="${ordersBean.orderId}"/></td>
									<td><c:out value="${ordersBean.fullname}"/></td>
									<td><c:out value="${ordersBean.stationLocation}"/></td>
									<td><c:out value="${ordersBean.service}"/></td>
									<td><c:out value="${ordersBean.price}"/></td>
									<td><c:out value="${ordersBean.status}"/></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='services_to_stations'}">
							<tr>
								<td colspan="3"><p><b>Orders</b></p><td>
							</tr>
								<tr>
									<td>№</td><td>Cтанция</td><td>Услуга</td>
								</tr>
							<c:forEach items="${sessionScope.userTable}" var="ordersBean">
								<tr>
									<td><c:out value="${ordersBean.serviceToStationId}"/></td>
									<td><c:out value="${ordersBean.stationName}"/></td>
									<td><c:out value="${ordersBean.service}"/></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='position_service'}">
							<tr>
								<td colspan="3"><p><b>position_service</b></p><td>
							</tr>
								<tr>
									<td>№</td><td>Должность</td><td>Услуга</td>
								</tr>
							<c:forEach items="${sessionScope.userTable}" var="positionServiceBean">
								<tr>
									<td><c:out value="${positionServiceBean.positionServiceId}"/></td>
									<td><c:out value="${positionServiceBean.position}"/></td>
									<td><c:out value="${positionServiceBean.service}"/></td>
								</tr>
							</c:forEach>
						</c:when>
						
						
						
						
						
					</c:choose>
				
				</table>
				</div>
            </div>
        </div>
    </div>


    <div class="col-md-4">
        <div class="panel panel-default">
            <div class="panel-heading">Форма для корректировки</div>
            <div class="panel-body baseform">
           		<form id="correctionForm" action="AdminActionController">
           			 <c:choose> 
           			 	<c:when test="${sessionScope.returnParametr=='time_table'}" > 
							<script src="js/timetable.js"></script>
							<p>Timetable ID <input class="form-control input-sm" type="text" placeholder="enter timetable ID" 
											id="timeTableId" name="timeTableId" required disabled/></p>
							<p>Employee Id <input class="form-control input-sm" type="text" placeholder="enter employee id" 
									id="employeeIdTimeTable" name="employeeIdTimeTable" disabled/></p>
							<p>Date <input class="form-control input-sm" type="text" placeholder="enter date" 
									id="dateTimeTable" name="dateTimeTable" disabled/></p>
							<div class="clockpicker">
								<p>Time <input class="form-control input-sm" type="text" placeholder="enter time" 
										id="timeTimeTable" name="timeTimeTable" disabled/></p>
							</div>		
							<p>Order id <input class="form-control input-sm" type="text" placeholder="enter order id" 
									id="orderIdTimeTable" name="orderIdTimeTable" disabled/></p>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='users'}" > 
								<script src="js/user.js"></script>
								<p>User ID <input class="form-control input-sm" type="text" placeholder="enter user id" id="userid" name="userid" required disabled/></p>
								<p>Login <input class="form-control input-sm" type="text" placeholder="enter login" id="login" name="login" disabled/></p>
								<p>Password <input class="form-control input-sm" type="text" placeholder="enter password" id="password" name="password" disabled/></p>
								<p>Select role<select class="form-control select-sm"  disabled id="role" name="role" >
												<option value="">выберите роль</option>
												<option value="администратор">администратор</option>
												<option value="менеджер">менеджер</option>
												<option value="коллцентр">коллцентр</option>
												<option value="мастерская">мастерская</option>
												<option value="пользователь">пользователь</option>	
											   </select>
							   <p>Blocked status<select class="form-control select-sm" name="blockedstatus" disabled>
												<option>активен</option>
												<option>заблокирован</option>
												</select>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='roles'}" > 
							<script src="js/roles.js"></script>
							<p>Role ID <input class="form-control input-sm" type="text" placeholder="enter role id" id="roleidroles" name="roleidroles" required disabled/></p>
							<p>Role <input class="form-control input-sm" type="text" placeholder="enter role" id="roleroles" name="roleroles" disabled/></p>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='services_to_stations'}" > 
							<script src="js/services-to-stations.js"></script>
							<p>Services to stations id 
								<input class="form-control input-sm" type="text" placeholder="enter  id" id="servicesToStationsId" 
											name="servicesToStationsId" required disabled/>
							</p>
							<p>Station
								<select class="form-control select-sm" id="stationSTS" name="stationSTS" disabled>
									<option value="">Выберите станцию</option>
								<c:forEach  items="${sessionScope.serviceStationBeans}" var="serviceStationBean">
									<option>${serviceStationBean.stationName}</option>
								</c:forEach>	
								</select>
							</p>
							<p>Service
								<select class="form-control select-sm" id="serviceSTS" name="serviceSTS" disabled>
									<option value="">Выберите услугу</option>
								<c:forEach items="${sessionScope.servicesBeans}" var="servicesBean">
									<option>${servicesBean.serviceType}</option>
								</c:forEach>	
								</select>
							</p>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='position_service'}" > 
							<script src="js/position-service.js"></script>
							<p>Services to stations id 
								<input class="form-control input-sm" type="text" placeholder="enter  id" id="positionServiceId" 
											name="positionServiceId" required disabled/>
							</p>
							<p>Position
								<select class="form-control select-sm" id="positionPS" name="positionPS" disabled>
									<option value="">Выберите станцию</option>
								<c:forEach items="${sessionScope.employeePositionBeans}" var="employeePositionBean">
									<option>${employeePositionBean.position}</option>
								</c:forEach>	
								</select>
							</p>
							<p>Service
								<select class="form-control select-sm" id="servicePS" name="servicePS" disabled>
									<option value="">Выберите услугу</option>
								<c:forEach items="${sessionScope.servicesBeans}" var="servicesBean">
									<option>${servicesBean.serviceType}</option>
								</c:forEach>	
								</select>
							</p>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='orders'}" > 
							<script src="js/orders.js"></script>
							<p>Order ID <input class="form-control input-sm" type="text" placeholder="enter order id" id="orderId" name="orderId" required disabled/></p>
							<p>Status <select class="form-control select-sm"  id="orderStatus" name="orderStatus" disabled>
										<option value="не погашен">не погашен</option>
										<option value="погашен">погашен</option>
									  </select>	
							</p>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='discounts'}" > 
							<script src="js/discounts.js"></script>
							<p>Discount ID <input class="form-control input-sm" type="text" placeholder="enter discount id" id="discountiddiscounts" name="discountiddiscounts" required disabled/></p>
							<p>Discount <input class="form-control input-sm" type="text" placeholder="enter discount in integer(%)" id="discountdiscounts" name="discountdiscounts" disabled/></p>
							<p>Total sum <input class="form-control input-sm" type="text" placeholder="enter total sum" id="totalsumdiscounts" name="totalsumdiscounts" disabled/></p>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='employee_position'}" > 
							<script src="js/position.js"></script>
							<p>Postition ID <input class="form-control input-sm" type="text" placeholder="enter discount id" id="positionId" name="positionId" required disabled/></p>
							<p>Position <input class="form-control input-sm" type="text" placeholder="enter discount in integer(%)" id="position" name="position" disabled/></p>
						</c:when>
						
						<c:when test="${sessionScope.returnParametr=='client_discount'}" > 
							<script src="js/clientdiscount.js"></script>
							<p>Client discount ID <input class="form-control input-sm" type="text" placeholder="enter client discount id" 
							id="clientdiscountid" name="clientdiscountid" required disabled/></p>
							<p>login <select class="form-control select-sm" id="loginclientdiscount"  name="loginclientdiscount" disabled>
											<option value="">Выберите логин</option>
										<c:forEach items="${sessionScope.userBeans}" var="userBean">	
											<option value="${userBean.login}">${userBean.login}</option>
										</c:forEach>	
									 </select>
							</p>
							<p>discount <select class="form-control select-sm" id="clientdiscount" name="clientdiscount" disabled>
											<option value="">Выберите discountId</option>
										<c:forEach items="${sessionScope.discountBeans}" var="discountBean">		
											<option value="${discountBean.discount}">${discountBean.discount}</option>
										</c:forEach>	
										</select>
							</p>
							<p>Total sum <input class="form-control input-sm" type="text" placeholder="enter total sum" id="clienttotalsum" 
					 		name="clienttotalsum" disabled/></p>
							</c:when>
						<c:when test="${sessionScope.returnParametr=='forum'}" > 
							<script src="js/forum.js"></script>
							<p>Topic ID <input class="form-control input-sm" type="text" placeholder="topic id" id="topicid" name="topicid" required disabled/></p>
							<p>Topic <input class="form-control input-sm" type="text" placeholder="enter topic" id="topic"name="topic" disabled/></p>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='messages'}" > 
							<script src="js/message.js"></script>
							<p>Message ID <input class="form-control input-sm" type="text" placeholder="enter message id" id="messageid" name="messageid" required disabled/></p>
							<p>Login<input class="form-control input-sm" type="text" placeholder="enter exist login" id="loginmessage" name="loginmessage" disabled/></p>
							<p>Message<input class="form-control input-sm" type="text" placeholder="enter message" id="message" name="message" disabled/></p>
							<p>Topic<input class="form-control input-sm" type="text" placeholder="enter topic of the message" id="topicmessage" name="topicmessage" disabled/></p>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='services'}" > 
							<script src="js/services.js"></script>
							<p>Service ID <input class="form-control input-sm" type="text" placeholder="enter service id" id="serviceid" name="serviceid" required disabled/></p>
							<p>Service type<input class="form-control input-sm" type="text" placeholder="enter service type" id="service" name="service" disabled/></p>
							<p>Man hours<input class="form-control input-sm" type="text" placeholder="enter man hours" id="manhours" name="manhours" disabled/></p>
							<p>Price<input class="form-control input-sm" type="text" placeholder="enter topic of the message" id="serviceprice" name="serviceprice" disabled/></p>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='service_station'}" > 
							<script src="js/station.js"></script>
							<p>Station ID <input class="form-control input-sm" type="text" placeholder="enter station id" id="stationid" name="stationid" required disabled/></p>
							<p>Station name<input class="form-control input-sm" type="text" placeholder="enter station name" id="station" name="station" disabled/></p>
							<p>location<input class="form-control input-sm" type="text" placeholder="enter location" id="location" name="location" disabled/></p>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='usersdata'}" > 
							<script src="js/userdata.js"></script>
							<p>Userdata ID <input class="form-control input-sm" type="text" placeholder="enter station id" id="userdataid" name="userdataid" required disabled/></p>
							<p>login <select class="form-control select-sm" id="loginclientdiscount"  name="logindata" disabled>
											<option value="">Выберите логин</option>
										<c:forEach items="${sessionScope.userBeans}" var="userBean">	
											<option value="${userBean.login}">${userBean.login}</option>
										</c:forEach>	
									 </select>
							</p>
							<p>Fullname<input class="form-control input-sm" type="text" placeholder="enter fullname" id="fullname" name="fullname" disabled/></p>
							<p>Birthday<input class="form-control input-sm" type="date"  placeholder="year-month-day" id="birthday" name="birthday" disabled/></p>
							<p>Adress<input class="form-control input-sm" type="text" placeholder="enter adress" id="adress" name="adress" disabled/></p>
							<p>Phone<input class="form-control input-sm" type="text" placeholder="enter phone" id="phone" name="phone" disabled/></p>
							<p>Gender<select class="form-control input-sm" id="gender" name="gender" disabled>
												<option value="">выберите пол</option>
												<option value="мужской">мужской</option>
												<option value="женский">женский</option>
									 </select>
							</p>
						</c:when>
						<c:when test="${sessionScope.returnParametr=='employee_list'}" > 
							<script src="js/employee.js"></script>
							<p>Employee ID<input class="form-control input-sm" type="text" placeholder="enter employee id" id="employeeId" name="employeeId" required disabled/></p>
							<p>login <select class="form-control select-sm" id="loginclientdiscount"  name="employeeLogin" disabled>
											<option value="">Выберите логин</option>
										<c:forEach items="${sessionScope.userBeans}" var="userBean">	
											<option value="${userBean.login}">${userBean.login}</option>
										</c:forEach>	
									 </select>
							</p>
							<p>Position <select class="form-control select-sm" id="employeePosition"  name="employeePosition" disabled>
											<option value="">Выберите должность</option>
										<c:forEach items="${sessionScope.employeePositionBeans}" var="employeePositionBean">	
											<option value="${employeePositionBean.position}">${employeePositionBean.position}</option>
										</c:forEach>	
									 </select>
							</p>
							<p>Location <select class="form-control select-sm" id="employeeLocation"  name="employeeLocation" disabled>
											<option value="">Выберите станцию</option>
										<c:forEach items="${sessionScope.ServiceStationBeans}" var="ServiceStationBean">	
											<option value="${ServiceStationBean.location}">${ServiceStationBean.location}</option>
										</c:forEach>	
									 </select>
							</p>
							
						</c:when>
						
						
						
						 
					</c:choose>
				<c:if test="${not empty sessionScope.returnParametr}">	
					<div class="row">
					                <input type="hidden" name="adminaction" id="adminaction">
					                <div class="col-lg-3">
					                    <div class="input-group">
					                        <span class="input-group-addon">
					                        <input type="checkbox" aria-label="..." name="checkAdd" onclick="create(this.form)">
					                        </span>
					                        <input type="submit" class="btn btn-info btn-sm" onclick="sendParameter('add')" value="Добаваить" name="createBtn" disabled>
					                    </div>
					                </div>
					                    <div class="col-lg-3">
					                        <div class="input-group">
					                            <span class="input-group-addon">
					                            <input type="checkbox" aria-label="..." name="checkUpdate" onclick="update(this.form)">
					                            </span>
					                            <input type="submit" class="btn btn-info btn-sm" onclick="sendParameter('update')" value="Изменить" name="updateBtn" disabled>
					                        </div>
					                    </div>
					                <div class="col-lg-3">
					                    <div class="input-group">
					                            <span class="input-group-addon">
					                            <input type="checkbox" aria-label="..." name="checkDelete" onclick="deleteF(this.form)">
					                            </span>
					                        <input type="submit" class="btn btn-info btn-sm" onclick="sendParameter('delete')" value="Удалить пользователя по ID" name="btnDelete" disabled>
					                    </div><!-- /input-group -->
					                </div>
					            </div>
					</c:if>		            		   
				</form>
            
            
            
            
            
			</div>
            </div>
        </div>
    </div>

<footer class="formfooter">

        <div class="container">
            <div class="col-md-2">

            </div>
            <div class="col-md-1" ></div>
            <div class="col-md-8">

            </div>
            <div class="col-md-2" ></div>
        </div>

</footer>
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript">
    $(function() {
        $( "#dateTimeTable" ).datepicker({
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