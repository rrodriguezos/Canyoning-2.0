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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('ORGANISER')">

	<form:form action="activity/organiser/edit.do"
		modelAttribute="activityForm">


		<acme:textbox code="activity.title" path="title" />

		<acme:textbox code="activity.description" path="description" />

		<acme:textbox code="activity.numberSeats" path="numberSeats" />

		<acme:date code="activity.moment" path="moment" readonly="false" />

		<spring:message code="activity.canyon" />
		<form:select path="canyon">
			<form:options items="${canyons}" itemLabel="name" itemValue="id" />
		</form:select>
		<form:errors cssClass="error" path="canyon" />
		<br>

		<spring:message code="activity.pieceEquipments" />
		<fieldset>
			<acme:multiCheckbox path="pieceEquipments" items="${pieceEquipments}"
				itemLabel="description" delimiter="</b>" />
			<form:errors cssClass="error" path="pieceEquipments" />
			<br>
		</fieldset>


		<input type="submit" name="save"
			value="<spring:message code="activity.save" />" />
		<input type="button" name="cancel"
			value="<spring:message code="activity.cancel"/>"
			onclick="javascript: window.history.back()" />

	</form:form>

</security:authorize>