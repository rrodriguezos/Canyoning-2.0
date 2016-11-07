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

	<form:form action="kayak/organiser/edit.do" modelAttribute="kayak">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="organiser" />


		<tr>
			<td><acme:textbox code="kayak.name" path="name" /></td>
		</tr>
		<tr>
			<td><acme:textbox code="kayak.make" path="make" /></td>
		</tr>
		<tr>
			<td><acme:textbox code="kayak.length" path="length" /></td>
		</tr>
		<tr>
			<td><acme:textbox code="kayak.numberSeats" path="numberSeats" /></td>
		</tr>
		<tr>
			<td><acme:textarea code="kayak.description" path="description" /></td>
		</tr>

		<tr>
			<td><acme:textbox code="kayak.model" path="model" /></td>
		</tr>


		<jstl:if test="${kayak.id == 0}">
			<input type="submit" name="save"
				value="<spring:message code="kayak.save" />" />
		</jstl:if>

		<jstl:if test="${kayak.id != 0}">
			<input type="submit" name="save"
				value="<spring:message code="kayak.save" />">
			<input type="submit" name="delete"
				value="<spring:message code="kayak.delete"/>"
				onclick="return confirm('<spring:message code="kayak.confirm.delete" />')" />
		</jstl:if>

		<acme:cancel url="kayak/organiser/mylist.do" code="kayak.cancel" />

	</form:form>

</security:authorize>