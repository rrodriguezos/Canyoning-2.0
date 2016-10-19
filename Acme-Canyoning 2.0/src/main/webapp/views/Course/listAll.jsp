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

<display:table name="course" id="row" requestURI="${requestURI}"
		pagesize="5" class="displaytag" keepStatus="true">
	
		<spring:message code="course.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="course.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />

	<spring:message code="course.banner"
		var="bannerHeader" />
	<display:column property="banner"
		title="${bannerHeader}" />

	
		<spring:message code="course.display" var="display" />
	<display:column title="${display}">
		<input type="button" value="<spring:message code="course.display" />"
			onclick="javascript: window.location.assign('course/display.do?courseId=${row.id}')" />
	</display:column>

	

</display:table>