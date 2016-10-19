<%--
 * action-1.jsp
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

<display:table name="canyons" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">

	<spring:message code="canyon.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />

	<spring:message code="canyon.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />

	<spring:message code="canyon.difficultyLevel"
		var="difficultyLevelHeader" />
	<display:column property="difficultyLevel"
		title="${difficultyLevelHeader}" />

	<spring:message code="canyon.route" var="routeHeader" />
	<display:column property="route" title="${routeHeader}" />

	<spring:message code="canyon.gps.latitude" var="latitude" />
	<display:column property="gpsCoordinates.latitude" title="${latitude}"
		sortable="true" />

	<spring:message code="canyon.gps.longitude" var="longitude" />
	<display:column property="gpsCoordinates.longitude"
		title="${longitude}" sortable="true" />


	<spring:message code="canyon.gps.altitude" var="altitude" />
	<display:column property="gpsCoordinates.altitude" title="${altitude}"
		sortable="true" />

	<spring:message code="canyon.display" var="display" />
	<display:column title="${display}">
		<input type="button" value="<spring:message code="canyon.display" />"
			onclick="javascript: window.location.assign('canyon/display.do?canyonId=${row.id}')" />
	</display:column>

	<spring:message code="canyon.activities" var="activitiesHeader" />
	<display:column title="${activitiesHeader}">
		<input type="button"
			value="<spring:message code="canyon.activities" />"
			onclick="javascript: window.location.assign('activity/listByCanyon.do?canyonId=${row.id}')" />
	</display:column>
	<security:authorize access="isAuthenticated()">
		<spring:message code="canyon.comment" var="commentHeader" />
		<display:column title="${commentHeader}">
			<input type="button" value="<spring:message code="canyon.comment" />"
				onclick="javascript: window.location.assign('comment/list.do?id=${row.id}')" />
		</display:column>
	</security:authorize>
</display:table>

<security:authorize access="hasRole('ADMINISTRATOR')">
	<input type="button" name="create"
		value="<spring:message code="canyon.create" />"
		onclick="javascript: window.location.assign('canyon/administrator/create.do')" />

</security:authorize>