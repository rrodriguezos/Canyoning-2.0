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


<acme:jstlOut code="canyon.name" value="${canyon.name }" />
<acme:jstlOut code="canyon.description" value="${canyon.description }" />
<acme:jstlOut code="canyon.difficultyLevel"
	value="${canyon.difficultyLevel }" />
<acme:jstlOut code="canyon.route" value="${canyon.route }" />
<acme:jstlOut code="canyon.gps.latitude"
	value="${canyon.gpsCoordinates.latitude }" />
<acme:jstlOut code="canyon.gps.longitude"
	value="${canyon.gpsCoordinates.longitude }" />
<acme:jstlOut code="canyon.gps.altitude"
	value="${canyon.gpsCoordinates.altitude }" />


<b><spring:message code="canyon.pictures" />: </b>
<br>
<jstl:forEach var="picture" items="${canyon.getPictures() }">
	<img width="200px" height="200x" src="${picture}"
		alt="${ canyon.name } picture" />
</jstl:forEach>
<br>

<security:authorize access="hasRole('ADMINISTRATOR')">
	
	<input type="button" value="<spring:message code="canyon.edit" />" 
			onclick="javascript: window.location.assign('canyon/administrator/edit.do?canyonId=${canyon.id}')" />

</security:authorize>

<br>
<h2>
	<spring:message code="canyon.comments" />
</h2>

<display:table name="comments" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">

	<spring:message code="comment.title" var="title" />
	<display:column title="${title}">
		<jstl:out value="${row.getTitle() }" />
	</display:column>

	<spring:message code="comment.actor" var="actor" />
	<display:column title="${actor}">
		<jstl:out value="${row.getActor().getName()}" />
	</display:column>

	<spring:message code="comment.display" var="display" />
	<display:column title="${display}">
		<input type="button" value="<spring:message code="comment.display" />"
			onclick="javascript: window.location.assign('comment/display.do?commentId=${row.id}')" />
	</display:column>

</display:table>
<br>


<input type="button" name="cancel"
	value="<spring:message code="canyon.cancel"/>"
	onclick="javascript: window.history.back()" />
