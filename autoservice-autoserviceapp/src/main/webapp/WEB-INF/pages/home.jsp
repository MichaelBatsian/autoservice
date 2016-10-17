<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
function sendParameter(parameter) {
    document.getElementById("userpage").value=parameter;
}
</script>
        <div class="col-md-4">
            <div class="content">
            <span style="color:red;"><h3><c:out value="${requestScope.answer}"></c:out></h3></span>
            <p>На нашем сайте вы можете оформить онлайн заявку.
            	Для этого нужно зарегистрироваться на сайте
           		 и войти в профиль</p>
            </div>
        </div>
            <div class="col-md-4">
               <form class="form-horizontal" action="AuthController">
                  <c:choose>
                	<c:when test="${empty requestScope.user and empty sessionScope.user }">
                   		<div class="form-group">
                       		<label for="inputEmail" class="control-label col-xs-2">Email</label>
                       		<div class="col-xs-10">
                          		<input type="email" class="form-control" id="login" name="login" placeholder="Email" required>
                        	</div>
                   		</div>
                   		<div class="form-group">
                   		
                       		<label for="password" class="control-label col-xs-2">Пароль</label>
                      		<div class="col-xs-10">
                           		<input type="password" class="form-control" id="password" name="password" placeholder="Пароль" required>
                       		</div>
                   		</div>
                   		<div class="col-xs-offset-2 col-xs-10">
                        	<input type="hidden" name="userpage" id="userpage">
                          	<input type="submit" class="btn btn-primary" onclick="sendParameter('home')" value="Войти">
                         	<a href="UserController?registration=registration" class="btn btn-primary">Регистрация</a>
                        </div>
                    </c:when>
                    <c:when test="${not empty requestScope.user}">
                     
                    	<c:if test="${requestScope.user=='wrong' || requestScope.user=='blocked'}">
                    	  <c:if test="${requestScope.user=='wrong'}">
                    		<p style="color: red;">Вы ввели неправильный пароль или email</p>
                    	 </c:if>
                    	 <c:if test="${requestScope.user=='blocked'}">
                    		<p style="color: red;">Ваш аккаунт заблокирован, пожалуйста обратитесь в службу поддержки</p>
                    	</c:if>	
                    		<div class="form-group">
                       		<label for="inputEmail" class="control-label col-xs-2">Email</label>
                       		<div class="col-xs-10">
                          		<input type="email" class="form-control" id="login" name="login" value="${requestScope.login}">
                        	</div>
                   		</div>
                   		<div class="form-group">
                       		<label for="password" class="control-label col-xs-2">Пароль</label>
                      		<div class="col-xs-10">
                           		<input type="password" class="form-control" id="password" name="password" value="${requestScope.password}">
                       		</div>
                   		</div>
                   		<div class="col-xs-offset-2 col-xs-10">
                        	<input type="hidden" name="userpage" id="userpage">
                         	<input type="submit" class="btn btn-primary" onclick="sendParameter('home')" value="Войти">
                         	<a href="UserController?registration=registration" class="btn btn-primary">Регистрация</a>
                        </div>
                    	</c:if>
                    	</c:when>
                       	<c:when test="${not empty sessionScope.userName}">
                       	<div id="list">
                    	  <ul>
                    	   	 <li><p>Добро пожаловать,  <c:out value="${sessionScope.userName}"/></p></li>
                    		 <li><a class="btn btn-info"  href="UserController?profile=${sessionScope.userName}&login=${sessionScope.login}">Перейти к профилю</a></li>
                    		 <li><a class="btn btn-info" href="UserController?exit=exit">Выйти</a></li>
                    	  </ul>
                    	 </div>
                    	</c:when>
                </c:choose>   
              </form>      	
         </div>
       </div>	
    </div>

<section id = "feature" class="container">

    <div class="starter-template container">
        <h1>Здесь может быть ваша реклама!</h1>
        <p class="lead">+375 22 222 00 90</p>
    </div>

</section>
<%@ include file="footer.jsp" %>
