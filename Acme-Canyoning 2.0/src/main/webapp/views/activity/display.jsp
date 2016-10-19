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


<acme:jstlOut code="activity.title" value="${activity.title }" />
<acme:jstlOut code="activity.description"
	value="${activity.description }" />
<acme:jstlOut code="activity.numberSeats"
	value="${activity.numberSeats }" />

<acme:jstlOut code="activity.moment" value="${activity.moment }" />
<br>
<div>
	<jstl:if test="${logeado == true and myActivity == false}">

		<input type="button"
			value="<spring:message code="activity.registerCustomer" />"
			onclick="javascript: window.location.assign('activity/customer/requestActivity.do?activityId=${activity.id}')" />




	</jstl:if>
	<security:authorize access="hasRole('ORGANISER')">
		<input type="button"
			value="<spring:message code="activity.reinstantiate" />"
			onclick="javascript: window.location.assign('activity/organiser/reinstantiate.do?activityId=${activity.id}')" />




		<jstl:if test="${myActivityOrganiser == true}">
			<spring:message code="activity.request" var="requestHeader" />
			<input type="button"
				value="<spring:message code="activity.request" />"
				onclick="javascript: window.location.assign('request/organiser/list.do?activityId=${activity.id}')" />

		</jstl:if>


	</security:authorize>




</div>


<h2>
	<spring:message code="activity.comments" />
</h2>

<display:table name="comments" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">

	<spring:message code="comment.title" var="title" />
	<display:column title="${title}">
		<jstl:out value="${row.getTitle() }" />
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



<input type="button" name="cancel"
	value="<spring:message code="activity.cancel"/>"
	onclick="javascript: window.history.back()" />
