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

<security:authorize access="hasRole('ADMINISTRATOR')">

	<form:form action="story/administrator/edit.do" modelAttribute="story">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="canyon" />
		<form:hidden path="administrator" />

		<acme:textbox code="story.title" path="title" />

		<acme:textbox code="story.authorName" path="authorName" />

		<acme:textarea code="story.text" path="text" />

		<acme:textarea code="story.resourcesList" path="resourcesList" />




		<input type="submit" name="save"
			value="<spring:message code="story.save" />" />

		<jstl:if test="${story.id!=0}">
			<input type="submit" name="delete"
				value="<spring:message code="story.delete"/>"
				onclick="return confirm('<spring:message code="story.confirm.delete" />')" />
		</jstl:if>
		<input type="button" name="cancel"
			value="<spring:message code="story.cancel"/>"
			onclick="javascript: window.history.back()" />

	</form:form>

</security:authorize>