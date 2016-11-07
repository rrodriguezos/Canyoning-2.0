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

<display:table name="wetsuits" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">

	<spring:message code="wetsuit.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />

	<spring:message code="wetsuit.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />

	<spring:message code="wetsuit.make" var="makeHeader" />
	<display:column property="make" title="${makeHeader}" />

	<spring:message code="wetsuit.minimumTemperature"
		var="minimumTemperatureHeader" />
	<display:column property="minimumTemperature"
		title="${minimumTemperatureHeader}" />

	<spring:message code="wetsuit.trousers" var="trousersHeader" />
	<display:column property="trousers" title="${trousersHeader}" />

	<spring:message code="wetsuit.sizeSleeves" var="sizeSleevesHeader" />
	<display:column property="sizeSleeves" title="${sizeSleevesHeader}" />

	<spring:message code="wetsuit.model" var="model" />
	<display:column title="${model}">
		<img height="150px" src="<jstl:out value="${row.model}" />">
	</display:column>


	<spring:message code="wetsuit.display" var="display" />
	<display:column title="${display}">
		<input type="button" value="<spring:message code="wetsuit.display" />"
			onclick="javascript: window.location.assign('wetsuit/display.do?wetsuitId=${row.id}')" />
	</display:column>


</display:table>