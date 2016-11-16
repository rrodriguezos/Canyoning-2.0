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


<acme:jstlOut code="story.title" value="${story.title }" />
<acme:jstlOut code="story.authorName" value="${story.authorName }" />
<acme:jstlOut code="story.text" value="${story.text }" />

<acme:jstlOut code="story.resourcesList" value="${story.resourcesList }" />

<br>

<security:authorize access="hasRole('ADMINISTRATOR')">
  	<jstl:if test="${myStoryAdministrator == true}">
	
	<input type="button" value="<spring:message code="story.edit" />" 
			onclick="javascript: window.location.assign('story/administrator/edit.do?storyId=${story.id}')" />

	</jstl:if>
</security:authorize>

<input type="button" name="cancel"
	value="<spring:message code="story.cancel"/>"
	onclick="javascript: window.history.back()" />
