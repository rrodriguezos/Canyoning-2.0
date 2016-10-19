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


<display:table name="module" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag" keepStatus="true">

	<spring:message code="module.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />


	<spring:message code="module.orderModule" var="orderModuleHeader" />
	<display:column property="orderModule" title="${orderModuleHeader}" />

	<spring:message code="module.display" var="display" />
	<display:column title="${display}">
		<input type="button"
			value="<spring:message code="module.display" />"
			onclick="javascript: window.location.assign('module/display.do?moduleId=${row.id}')" />
	</display:column>



	<spring:message code="module.course" var="course" />
	<display:column title="${course}" sortable="true">
		<input type="button" value="<spring:message code="module.course" />"
			onclick="javascript: window.location.assign('course/navigateByModule.do?moduleId=${row.id}')" />
	</display:column>


</display:table>