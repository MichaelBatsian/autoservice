<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="css/jquery-ui.css">
<div class="col-md-5">  
 <h2 align ="center">Регистрация</h2>
 <form class="form-horizontal" name="loginForm" action="UserController">
	
  <c:choose>
	
   <c:when test="${not empty requestScope.existLogin}">
   <input type="hidden" name="checkLogin" id="checkLogin" value="checkLogin">
   		<div class="form-group">
          <label class="control-label col-xs-3" for=regLogin>Email:</label>
       	 <div class="col-xs-9">
       	 	<p style="color:red; font-size: small;"><c:out value="${requestScope.existLogin}"></c:out></p>
            <input type="email"  class="form-control" id="regLogin" name="regLogin" 
            		placeholder="email" required="required">
         </div>
   		</div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="lastname">Фамилия:</label>
        <div class="col-xs-9">
           <input type="text" class="form-control" id="lastname" required name="lastname" value="${param.lastname}" placeholder="Введите фамилию" >
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="firstname">Имя:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="firstname" required name="firstname" value="${param.firstname}" placeholder="Введите имя" >
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="middlename">Отчество:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="middlename" required name="middlename" value="${param.middlename}" placeholder="Введите отчество">
        </div>
    </div>

 <div class="form-group">
        <label class="control-label col-xs-3">Дата рождения:</label>
        <div class="col-xs-9">
            <input class="form-control" type="text" id="datepicker" required name="birthday" value="${param.birthday}" placeholder="год-месяц-день" required>
        </div>
 </div>
 <div class="form-group">
        <label class="control-label col-xs-3" for="passwordReg">Пароль:</label>
        <div class="col-xs-9">
            <input type="password" class="form-control" id="passwordReg" required name="passwordReg" value="${param.password}" placeholder="Введите пароль" required="required">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="phone">Телефон:</label>
        <div class="col-xs-9">
            <input type="tel" class="form-control" id="phone" name="phone" placeholder="Введите номер телефона" value="${param.phone}" required="required">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="adress">Адрес:</label>
        <div class="col-xs-9">
            <textarea rows="3" class="form-control" id="adress" name="adress" placeholder="Введите адрес">${param.adress}</textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3">Пол:</label>
       <c:choose> 
        <c:when test="${param.gender=='мужской'}">
        <div class="col-xs-2">
            <label class="radio-inline">
                <input type="radio" name="gender" value="мужской" required="required" checked="checked" > Мужской
            </label>
        </div>
        <div class="col-xs-2">
            <label class="radio-inline">
                <input type="radio" name="gender" value="женский"> Женский
            </label>
        </div>
   		</c:when> 
   		<c:when test="${param.gender=='женский'}">
        <div class="col-xs-2">
            <label class="radio-inline">
                <input type="radio" name="gender" value="мужской" required="required" > Мужской
            </label>
        </div>
        <div class="col-xs-2">
            <label class="radio-inline">
                <input type="radio" name="gender" value="женский" checked="checked"> Женский
            </label>
        </div>
   		</c:when> 
   	 </c:choose>	
    </div>
    <div class="form-group">
        <div class="col-xs-offset-3 col-xs-9">
            <label class="checkbox-inline">
                <input type="checkbox" value="agree" required="required" checked="checked">  Я согласен с <a href="#">условиями</a>.
            </label>
        </div>
    </div>		
    </c:when>
    <c:otherwise>
	<input type="hidden" name="checkLogin" id="checkLogin" value="checkLogin">
		<div class="form-group">
       	 <label class="control-label col-xs-3" for=regLogin>Email:</label>
      	  <div class="col-xs-9">
            <input type="email" class="form-control" id="regLogin" name="regLogin" placeholder="Email" required>
       	   </div>
    	</div>
     <div class="form-group">
        <label class="control-label col-xs-3" for="lastname">Фамилия:</label>
        <div class="col-xs-9">
           <input type="text" class="form-control" id="lastname" required name="lastname" placeholder="Введите фамилию" >
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="firstname">Имя:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="firstname" required name="firstname" placeholder="Введите имя" >
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="middlename">Отчество:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="middlename" required name="middlename" placeholder="Введите отчество">
        </div>
    </div>

 <div class="form-group">
        <label class="control-label col-xs-3">Дата рождения:</label>
        <div class="col-xs-9">
            <input class="form-control" type="text" id="datepicker" required name="birthday"  placeholder="год-месяц-день" required>
        </div>
 </div>
 <div class="form-group">
        <label class="control-label col-xs-3" for="passwordReg">Пароль:</label>
        <div class="col-xs-9">
            <input type="password" class="form-control" id="passwordReg" required name="passwordReg" placeholder="Введите пароль" required="required">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="phone">Телефон:</label>
        <div class="col-xs-9">
            <input type="tel" class="form-control" id="phone" name="phone" placeholder="Введите номер телефона" required="required">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="adress">Адрес:</label>
        <div class="col-xs-9">
            <textarea rows="3" class="form-control" id="adress" name="adress" placeholder="Введите адрес"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3">Пол:</label>
        <div class="col-xs-2">
            <label class="radio-inline">
                <input type="radio" name="gender" value="мужской" required="required" > Мужской
            </label>
        </div>
        <div class="col-xs-2">
            <label class="radio-inline">
                <input type="radio" name="gender" value="женский"> Женский
            </label>
        </div>
    </div>
    <div class="form-group">
        <div class="col-xs-offset-3 col-xs-9">
            <label class="checkbox-inline">
                <input type="checkbox" value="agree" required="required">  Я согласен с <a href="#">условиями</a>.
            </label>
        </div>
    </div>	
    </c:otherwise>
    </c:choose>	
   
   
    <br />
    <div class="form-group">
        <div class="col-xs-offset-3 col-xs-9">
        	<input type="hidden" name="regUser" value="true">
            <input type="submit" class="btn btn-primary" onclick="submitLoginForm()" value="Регистрация">
        </div>
    </div>
 </form>
</div>  
<div class="col-md-offset-3"></div>
</div>
</div>
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
<%@ include file="footer.jsp" %>
