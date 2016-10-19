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

<fieldset>
	<legend>
		<spring:message code="course.banner" />
	</legend>
	<img width="500px" height="100x" src="${course.getBanner()}" />
</fieldset>
<br>

<display:table name="courses" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">


	<spring:message code="course.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="course.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />


	<spring:message code="course.display" var="display" />
	<display:column title="${display}">
		<input type="button" value="<spring:message code="course.display" />"
			onclick="javascript: window.location.assign('course/display.do?courseId=${row.id}')" />
	</display:column>

	<spring:message code="course.modules" var="modulesHeader" />
	<display:column title="${modulesHeader}">
		<input type="button" value="<spring:message code="course.modules" />"
			onclick="javascript: window.location.assign('module/listByCourse.do?courseId=${row.id}')" />
	</display:column>
	<security:authorize access="isAuthenticated()">
		<spring:message code="course.comment" var="commentHeader" />
		<display:column title="${commentHeader}">
			<input type="button" value="<spring:message code="course.comment" />"
				onclick="javascript: window.location.assign('comment/list.do?id=${row.id}')" />
		</display:column>
	</security:authorize>
</display:table>

<security:authorize access="hasRole('TRAINER')">
	<input type="button" name="create"
		value="<spring:message code="course.create" />"
		onclick="javascript: window.location.assign('course/trainer/create.do')" />

</security:authorize>