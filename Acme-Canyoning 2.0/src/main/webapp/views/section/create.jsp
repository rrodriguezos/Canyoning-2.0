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

	<form:form action="section/trainer/create.do" modelAttribute="section">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="curriculum"/>


		<acme:textbox code="section.title" path="title" />
		<acme:textarea code="section.content" path="content" />
		<acme:textarea code="section.attachments" path="attachments" />

		<br>

		<jstl:if test="${section.id == 0}">
			<input type="submit" name="save"
				value="<spring:message code="section.save" />" />
				<acme:cancel url="curriculum/trainer/mylist.do" code="section.cancel" />
		</jstl:if>
		<jstl:if test="${section.id != 0}">
			<input type="submit" name="save"
				value="<spring:message code="section.save" />"/>
				<acme:cancel url="curriculum/trainer/mylist.do" code="section.cancel" />
		</jstl:if>


	</form:form>

</security:authorize>