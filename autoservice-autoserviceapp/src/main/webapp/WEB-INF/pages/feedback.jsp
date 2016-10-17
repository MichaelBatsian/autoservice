<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="menu.jsp"%>

<div class="col-md-5">
<div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <a href="UserController?home=home" class="close" data-dismiss="modal" aria-hidden="true">×</a>
                <h3 class="modal-title" style="color:black;">Контакты для обратной связи:</h3>
            </div>
            <form action="UserController" id="contact-form" class="modal-body" role="form">
                <input type="hidden" name="messageSent" value="messageSent">
                <div class="form-group">
                    <input class="form-control input-lg" type="text" placeholder="Ваше имя" required name="nameFeedback">
                </div>
              <c:choose>
                <c:when test="${empty sessionScope.login}">
                	<div class="form-group">
                 	   <input class="form-control input-lg" type="email" placeholder="Ваш email" required name="mail">
                	</div>
                </c:when>
                <c:otherwise>
                	<div class="form-group">
                   		<input class="form-control input-lg" type="email" value="${sessionScope.login}" required name="mail">
               		</div>
                </c:otherwise>
              </c:choose>  
                <div class="form-group">
                    <textarea class="form-control input-lg" rows="5" placeholder="Ваше сообщение" required name="feedbackMessage"></textarea>
                </div>
            </form>
            <div class="modal-footer">
                <button class="btn btn-info btn-lg" type="submit" title="Отправить Email" form="contact-form">Отправить</button>
            </div>
        </div>
 </div>
</div>
<div class="col-md-offset-3"></div>

</div>
</div>
<%@include file="footer.jsp"%>
