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

<security:authorize access="hasRole('TRAINER')">

	<form:form action="learningMaterial/trainer/create.do" modelAttribute="learningMaterial">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="module"/>


		<acme:textbox code="learningMaterial.title" path="title" />

		<acme:textbox code="learningMaterial.description" path="description" />

		<acme:textbox code="learningMaterial.materialLink" path="materialLink" />


		<input type="submit" name="save"
			value="<spring:message code="learningMaterial.save" />" />
		<acme:cancel url="trip/trainer/mylist.do" code="learningMaterial.cancel" />

	</form:form>

</security:authorize>