<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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
    <div class="panel panel-default">
      <div class="panel-heading">Темы форума</div>
       	<div class="topicTheme">
        	<c:forEach items="${sessionScope.forumBeans}" var="forumBean">
        		<fieldset>
       				 <div class="panel-heading"><a href="UserController?forumTopicId=${forumBean.topicId}&forumTopic=${forumBean.topic}"><c:out value="${forumBean.topic}"></c:out></a></div>
       			</fieldset>
       		</c:forEach>	 
		</div>

        
    </div>


</section>
<%@ include file="footer.jsp" %>