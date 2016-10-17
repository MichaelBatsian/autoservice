<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<div class="forumhead container ">
    <div class="col-md-2">
        <H3>Auto<span class="em-text">Forum</span></H3>
    </div>
    <div class="col-md-6">
        <p style="margin-top:20px;">Пожалуйста, ознакомтесь с правилами форума:<a href="#">Правила форума</a></p>
    </div>
    <div class="col-md-offset-4"></div>
</div>
<section class="forum container">
    <div class="panel panel-default col-md-12">
    <div class="panel-heading col-md-12" style="background: white"><c:out value="${sessionScope.forumTopic}"></c:out></div>
    <c:if test="${empty sessionScope.messagesForTopic}"><h2>Сообщений по данной теме пока нет</h2></c:if>
   <c:forEach items="${sessionScope.messagesForTopic}" var="messagesBean" >
    <div class="col-md-12" style="height: 2px;"></div>
    <div class="row">
   	 <div class="col-md-12" style="background: rgba(144, 144, 144, 0.18);height: 20px;"><c:out value="${messagesBean.messageDateTime}"></c:out>
   	 		<c:out value="${messagesBean.messageTime}"></c:out>
   	 </div>
     <div class="col-md-3" style="background: #9cc2ff; min-height: 120px;">
      	<table>
          <tr>
            <td><c:out value="${messagesBean.fullname}"></c:out></td>
          </tr>
          <tr>
          	<td><c:out value="${messagesBean.role}"></c:out></td>
          </tr>
          <tr>
          	<td><img src="img/avatar.jpg"/> </td>
          </tr>
        </table>            
     </div>
            <div class="col-md-9"style="background: #9dd7ff; min-height: 120px;">
                <c:if test="${sessionScope.role=='коллцентр'}"> 
               		 <a href="UserController?deleteMessage=deleteMessage&messageId=${messagesBean.messageId}" class="close" data-dismiss="modal" aria-hidden="true">×</a>
                </c:if>
                <p><c:out value="${messagesBean.message}"></c:out></p>
             </div>

     </div>
	</c:forEach>
	<c:if test="${not empty sessionScope.userName}">
        <div class="col-md-12" style="height: 10px;"></div>
        <div class="row">
            <div class="col-md-3" style="background: #9cc2ff; min-height: 150px;">
                <table>
                   
                    <tr>
                        <td>${sessionScope.userName}</td>
                    </tr>
                    <tr>
                        <td><img src="img/avatar.jpg"/> </td>
                    </tr>
                </table>

            </div>
            <div class="col-md-9"style="background: #9dd7ff; min-height: 150px;">
               <form>
                <textarea class="form-control" style="height: 80px;width: 800px;margin-top: 10px" name="sendMessage"></textarea>

                <input type="submit" value="Отправить сообщение" class="btn btn-info left" style="margin: 10px;">
               </form>
            </div>

        </div>
        </c:if>
        </div>
   


</section>



<%@ include file="footer.jsp" %>"