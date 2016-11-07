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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<display:table name="curriculums" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">

	<spring:message code="curriculum.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />

	<spring:message code="curriculum.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" />

	<spring:message code="curriculum.phone" var="phoneHeader" />
	<display:column property="phone" title="${phoneHeader}" />

	<jstl:if test="${mycurriculum == true}">

		<spring:message code="curriculum.isActive" var="isActiveHeader" />
		<display:column property="isActive" title="${isActiveHeader}" />

	</jstl:if>

	<spring:message code="curriculum.display" var="display" />
	<display:column title="${display}">
		<input type="button"
			value="<spring:message code="curriculum.display" />"
			onclick="javascript: window.location.assign('curriculum/display.do?curriculumId=${row.id}')" />
	</display:column>

	<spring:message code="curriculum.sections" var="sectionsHeader" />
	<display:column title="${sectionsHeader}">
		<input type="button"
			value="<spring:message code="curriculum.sections" />"
			onclick="javascript: window.location.assign('section/list.do?curriculumId=${row.id}')" />
	</display:column>


</display:table>