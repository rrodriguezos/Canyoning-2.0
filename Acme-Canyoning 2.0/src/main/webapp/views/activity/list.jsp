<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<display:table name="activities" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
<jstl:if test="${mylist== true}">	
	<spring:message	code="activity.myactivity"  var="myactivity"/>
	<br>
	<br>	
</jstl:if>

<jstl:if test="${accepted == true}">	
	<spring:message	code="activity.activitiesAccepted"  var="activitiesAccepted"/>
	<br>
	<br>	
</jstl:if>

<jstl:if test="${reject == true}">	
	<spring:message	code="activity.activitiesReject"  var="activitiesReject"/>
	<br>
	<br>	
</jstl:if>

<jstl:if test="${pending == true}">	
	<spring:message	code="activity.activitiesPending"  var="activitiesPending"/>	
	<br>
	<br>	
</jstl:if>
	
	<spring:message code="activity.title" var="title" />
	<display:column property="title" title="${title}" />
		
	<spring:message code="activity.description" var="description" />
	<display:column property="description" title="${description}" />
	
	<spring:message code="activity.numberSeats" var="numberSeats" />
	<display:column property="numberSeats" title="${numberSeats}" />
	
		<spring:message code="activity.seatsAvailable" var="seatsAvailable" />
	<display:column property="seatsAvailable" title="${seatsAvailable}" />
	
	<spring:message code="activity.moment" var="moment" />
	<display:column property="moment" title="${moment}"
		sortable="true" format="{0, date, dd/MM/yyyy HH:mm}" />
	
	<spring:message code="activity.display" var="display" />
	<display:column title="${display}">
			<input type="button" value="<spring:message code="activity.display" />" 
					onclick="javascript: window.location.assign('activity/display.do?activityId=${row.id}')" />
	</display:column>
	
	<spring:message code="activity.canyon" var="canyonHeader" />
	<display:column title="${canyonHeader}">
			<input type="button" value="<spring:message code="activity.canyon" />" 
					onclick="javascript: window.location.assign('canyon/listByActivity.do?activityId=${row.id}')" />
	</display:column>
	<security:authorize access="isAuthenticated()">
	<spring:message code="activity.comment" var="commentHeader" />
	<display:column title="${commentHeader}">
			<input type="button" value="<spring:message code="activity.comment" />" 
					onclick="javascript: window.location.assign('comment/list.do?id=${row.id}')" />
	</display:column>
	</security:authorize>
	
	
	
</display:table>

<security:authorize access="hasRole('ORGANISER')">
<input type="button" name="create" value="<spring:message code="activity.create" />"
	 onclick="javascript: window.location.assign('activity/organiser/create.do')" />
</security:authorize>