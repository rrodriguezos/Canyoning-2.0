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


<fieldset>
	<legend align="left">
			<spring:message code="organiser.pieceEquipments" />
		</legend>

<h2><spring:message code="organiser.kayaks"/></h2>
<display:table name="kayaks" id="row" pagesize="5" requestURI="pieceEquipment/organiser/list.do" class="displaytag">
	
	<spring:message code="pieceEquipments.name" var="name" />
	<display:column title="${name}" property="name" />
	
		<spring:message code="pieceEquipments.model" var="model" />
	<display:column title="${model}">
		<img height="150px" src="<jstl:out value="${row.model}" />">
	</display:column>
	
	<spring:message code="pieceEquipments.display" var="display" />
	<display:column title="${display}">
			<input type="button" value="<spring:message code="pieceEquipments.display" />" 
					onclick="javascript: window.location.assign('kayak/display.do?kayakId=${row.id}')" />
	</display:column>
	
</display:table>

<h2><spring:message code="organiser.wetsuits"/></h2>
<display:table name="wetsuits" id="row" pagesize="5" requestURI="pieceEquipment/organiser/list.do" class="displaytag">
	
	<spring:message code="pieceEquipments.name" var="name" />
	<display:column title="${name}" property="name" />
	
		<spring:message code="pieceEquipments.model" var="model" />
	<display:column title="${model}">
		<img height="150px" src="<jstl:out value="${row.model}" />">
	</display:column>
	
	<spring:message code="pieceEquipments.display" var="display" />
	<display:column title="${display}">
			<input type="button" value="<spring:message code="pieceEquipments.display" />" 
					onclick="javascript: window.location.assign('wetsuit/display.do?wetsuitId=${row.id}')" />
	</display:column>
	
</display:table>

<h2><spring:message code="organiser.cords"/></h2>
<display:table name="cords" id="row" pagesize="5" requestURI="pieceEquipment/organiser/list.do" class="displaytag">
	
	<spring:message code="pieceEquipments.name" var="name" />
	<display:column title="${name}" property="name" />
	
		<spring:message code="pieceEquipments.model" var="model" />
	<display:column title="${model}">
		<img height="150px" src="<jstl:out value="${row.model}" />">
	</display:column>
	
	<spring:message code="pieceEquipments.display" var="display" />
	<display:column title="${display}">
			<input type="button" value="<spring:message code="pieceEquipments.display" />" 
					onclick="javascript: window.location.assign('cord/display.do?cordId=${row.id}')" />
	</display:column>
	
</display:table>

</fieldset>



<input type="button" name="cancel"
	value="<spring:message code="organiser.cancel"/>"
	onclick="javascript: window.history.back()" />
