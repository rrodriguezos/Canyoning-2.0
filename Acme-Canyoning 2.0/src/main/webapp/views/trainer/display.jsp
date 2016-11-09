<%--
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:jstlOut code="trainer.name" value="${trainer.name }" />
<acme:jstlOut code="trainer.surname" value="${trainer.surname }" />
<acme:jstlOut code="trainer.email" value="${trainer.email }" />
<acme:jstlOut code="trainer.phone" value="${trainer.phone }" />



<h2>
	<spring:message code="trainer.courses" />
</h2>
<display:table name="courses" id="row" pagesize="5"
	requestURI="trainer/display.do" class="displaytag">

	<spring:message code="course.title" var="title" />
	<display:column title="${title}" property="title" />

	<spring:message code="course.display" var="display" />
	<display:column title="${display}">
		<input type="button" value="<spring:message code="course.display" />"
			onclick="javascript: window.location.assign('course/display.do?courseId=${row.id}')" />
	</display:column>

</display:table>

<h2>
	<spring:message code="trainer.curriculum" />
</h2>
<display:table name="curriculums" id="row" pagesize="5"
	requestURI="trainer/display.do" class="displaytag">
<display:column title="Curriculum">

			<jstl:if test="${row.isActive == true}">
			
			<spring:message code="curriculum.name" var="name" />
			<display:column title="${name}" property="name" />
			
			<spring:message code="curriculum.phone" var="phone" />
			<display:column title="${phone}" property="phone" />
			
			<spring:message code="curriculum.email" var="email" />
			<display:column title="${email}" property="email" />

			<spring:message code="curriculum.display" var="display" />
			<display:column title="${display}">
			<input type="button" value="<spring:message code="curriculum.display" />"
			onclick="javascript: window.location.assign('curriculum/display.do?curriculumId=${row.id}')" />
			</display:column>

				<input type="button"
					value="<spring:message code="trainer.curriculumSections" />"
					onclick="javascript: window.location.assign('section/list.do?curriculumId=${row.id}')" />

			</jstl:if>
	
	</display:column>
</display:table>
<input type="button" name="cancel"
	value="<spring:message code="trainer.cancel"/>"
	onclick="javascript: window.location.assign('trainer/list.do')" />
