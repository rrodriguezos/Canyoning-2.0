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

	<form:form action="cord/organiser/edit.do" modelAttribute="cord">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="organiser" />


		<tr>
			<td><acme:textbox code="cord.name" path="name" /></td>
		</tr>
		<tr>
			<td><acme:textbox code="cord.make" path="make" /></td>
		</tr>
		<tr>
			<td><acme:textbox code="cord.length" path="length" /></td>
		</tr>
		<tr>
			<td><acme:textbox code="cord.maximumWeight" path="maximumWeight" /></td>
		</tr>
		<tr>
			<td><acme:textarea code="cord.description" path="description" /></td>
		</tr>

		<tr>
			<td><acme:textbox code="cord.model" path="model" /></td>
		</tr>


		<jstl:if test="${cord.id == 0}">
			<input type="submit" name="save"
				value="<spring:message code="cord.save" />" />
		</jstl:if>

		<jstl:if test="${cord.id != 0}">
			<input type="submit" name="save"
				value="<spring:message code="cord.save" />">
			<input type="submit" name="delete"
				value="<spring:message code="cord.delete"/>"
				onclick="return confirm('<spring:message code="cord.confirm.delete" />')" />
		</jstl:if>

		<acme:cancel url="cord/organiser/mylist.do" code="cord.cancel" />

	</form:form>

</security:authorize>