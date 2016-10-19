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

	<form:form action="about/organiser/edit.do" modelAttribute="about">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="organiser" />


		<acme:textarea code="about.message" path="message" />



		<input type="submit" name="save"
			value="<spring:message code="about.save" />" />
		<acme:cancel url="about/organiser/list.do" code="about.cancel" />

	</form:form>

</security:authorize>