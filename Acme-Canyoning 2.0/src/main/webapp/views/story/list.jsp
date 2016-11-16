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

<display:table name="stories" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">

	<spring:message code="story.title" var="title" />
	<display:column property="title" title="${title}" />

	<spring:message code="story.authorName" var="authorName" />
	<display:column property="authorName" title="${authorName}" />

	<spring:message code="story.text" var="text" />
	<display:column property="text" title="${text}" />



	<spring:message code="story.display" var="display" />
	<display:column title="${display}">
		<input type="button" value="<spring:message code="story.display" />"
			onclick="javascript: window.location.assign('story/display.do?storyId=${row.id}')" />
	</display:column>

	<spring:message code="story.canyon" var="canyonHeader" />
	<display:column title="${canyonHeader}">
		<input type="button" value="<spring:message code="story.canyon" />"
			onclick="javascript: window.location.assign('canyon/listByStory.do?storyId=${row.id}')" />
	</display:column>



</display:table>

<security:authorize access="hasRole('ADMINISTRATOR')">
	<input type="button" name="create"
		value="<spring:message code="story.create" />"
		onclick="javascript: window.location.assign('story/administrator/create.do?canyonId=${canyonId}')" />
</security:authorize>