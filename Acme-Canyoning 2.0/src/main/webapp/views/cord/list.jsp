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

<display:table name="cords" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">

	<spring:message code="cord.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />

	<spring:message code="cord.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />

	<spring:message code="cord.make" var="makeHeader" />
	<display:column property="make" title="${makeHeader}" />

	<spring:message code="cord.length" var="lengthHeader" />
	<display:column property="length" title="${lengthHeader}" />

	<spring:message code="cord.maximumWeight" var="maximumWeightHeader" />
	<display:column property="maximumWeight" title="${maximumWeightHeader}" />

	<spring:message code="cord.model" var="model" />
	<display:column title="${model}">
		<img height="150px" src="<jstl:out value="${row.model}" />">
	</display:column>


	<spring:message code="cord.display" var="display" />
	<display:column title="${display}">
		<input type="button" value="<spring:message code="cord.display" />"
			onclick="javascript: window.location.assign('cord/display.do?cordId=${row.id}')" />
	</display:column>


</display:table>