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

	<form:form action="activity/organiser/reinstantiate.do"
		modelAttribute="activity">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="organiser" />
		<form:hidden path="comments" />
		<form:hidden path="title" />
		<form:hidden path="description" />
		<form:hidden path="numberSeats" />
		<form:hidden path="seatsAvailable" />
		<form:hidden path="canyon" />

		<fieldset>

			<acme:date code="activity.newMoment" path="moment" readonly="false" />

		</fieldset>

		<input type="submit" name="reinstantiate"
			value="<spring:message code="activity.reinstantiate" />" />
		<acme:cancel url="activity/mylist.do" code="activity.cancel" />

	</form:form>

</security:authorize>