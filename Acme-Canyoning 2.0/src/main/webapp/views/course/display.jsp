<%--
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<fieldset>
	<legend><spring:message code="course.banner"/></legend>
	<img width="500px" height="100x" src="${course.banner}"/>
	</fieldset>
	<br>


<acme:jstlOut code="course.title" value="${course.title }" />
<acme:jstlOut code="course.description" value="${course.description }" />


<security:authorize access="hasRole('TRAINER')">
	<jstl:if test="${mycourse == true}">
	<input type="button" value="<spring:message code="course.edit" />" 
			onclick="javascript: window.location.assign('course/trainer/edit.do?courseId=${course.id}')" />
</jstl:if>
</security:authorize>

<br>
<h2>
	<spring:message code="course.comments" />
</h2>

<display:table name="comments" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">

	<spring:message code="comment.title" var="title" />
	<display:column title="${title}">
		<jstl:out value="${row.getTitle() }" />
	</display:column>
	
	<spring:message code="comment.stars" var="stars" />
	<display:column title="${stars}">
		<jstl:out value="${row.getStars() }" />
	</display:column>

	<spring:message code="comment.actor" var="actor" />
	<display:column title="${actor}">
		<jstl:out value="${row.getActor().getEmail()}" />
	</display:column>

	<spring:message code="comment.display" var="display" />
	<display:column title="${display}">
		<input type="button" value="<spring:message code="comment.display" />"
			onclick="javascript: window.location.assign('comment/display.do?commentId=${row.id}')" />
	</display:column>

</display:table>
<br>


<input type="button" name="cancel"
	value="<spring:message code="course.cancel"/>"
	onclick="javascript: window.history.back()" />
