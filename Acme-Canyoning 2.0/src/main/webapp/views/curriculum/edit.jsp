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

	<form:form action="curriculum/trainer/edit.do"
		modelAttribute="curriculum">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="trainer" />
		<form:hidden path="isActive" />

		<jstl:if test="${curriculum.id == 0}">
		
		<spring:message code="curriculum.explanation" />
<br/>
			<acme:textbox  code="curriculum.name" path="name"
				value="${trainer.name}" />
				

			<acme:textbox code="curriculum.email" path="email"
				 value="${trainer.email}" />

			<acme:textbox code="curriculum.phone" path="phone"
				value="${trainer.phone}" />

			<input type="submit" name="save"
				value="<spring:message code="curriculum.save" />" 
				onclick="javascript: window.location.assign('section/trainer/create.do?curriculumId=${curriculumId}')" />
		</jstl:if>

		<jstl:if test="${curriculum.id != 0}">
		
		<acme:textbox code="curriculum.name" path="name"/>

			<acme:date code="curriculum.email" path="email"/>

			<acme:date code="curriculum.phone" path="phone" />
		
			<input type="submit" name="saveEdit"
				value="<spring:message code="curriculum.save" />">

			<input type="submit" name="delete"
				value="<spring:message code="curriculum.delete"/>"
				onclick="return confirm('<spring:message code="curriculum.confirm.delete" />')" />
		</jstl:if>

		<acme:cancel url="curriculum/trainer/mylist.do"
			code="curriculum.cancel" />

	</form:form>

</security:authorize>