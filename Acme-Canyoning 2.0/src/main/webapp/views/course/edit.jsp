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

	<form:form action="course/trainer/edit.do" modelAttribute="course">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="trainer" />
		<form:hidden path="modules" />
		<form:hidden path="comments" />


		<acme:textbox code="course.title" path="title" />

		<acme:textarea code="course.description" path="description" readonly="false" />


		<acme:textbox code="course.banner" path="banner" />

		
		<jstl:if test="${course.id == 0}">
		<input type="submit" name="save"
			value="<spring:message code="course.save" />" />
		</jstl:if>
		
		<jstl:if test="${course.id != 0}">
			<input type="submit" name="save"
			value="<spring:message code="course.save" />"  />
			<input type="submit" name="delete" value="<spring:message code="course.delete"/>" onclick="return confirm('<spring:message code="course.confirm.delete.modules" />')"/>
		</jstl:if>
		
		<acme:cancel url="course/trainer/mylist.do" code="course.cancel" />

	</form:form>

</security:authorize>