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


<display:table name="modules" id="row" pagesize="5"
	requestURI="module/list.do" class="displaytag">
	<form:hidden path="courseId" />

	<spring:message code="module.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="module.orderModule" var="orderModuleHeader" />
	<display:column property="orderModule" title="${orderModuleHeader}" />
	
	
	<spring:message code="module.display" var="display" />
	<display:column title="${display}">
			<input type="button" value="<spring:message code="module.display" />" 
					onclick="javascript: window.location.assign('module/display.do?moduleId=${row.id}')" />
	</display:column>
		
	
	
	<security:authorize access="hasRole('TRAINER')">
		<jstl:if test="${mycourse == true}">
		<spring:message code="module.delete" var="delete" />
		<display:column title="${delete}">
		<input type="button" name="delete"
			value="<spring:message code="module.delete" />"
			onclick="javascript: window.location.assign('module/trainer/delete.do?moduleId=${row.id}')" />
		</display:column>
		</jstl:if>
	</security:authorize>
	
	<spring:message code="module.learningMaterials" var="learningMaterialsHeader" />
	<display:column title="${learningMaterialsHeader}">
		<input type="button" value="<spring:message code="module.learningMaterials" />"
			onclick="javascript: window.location.assign('learningMaterial/list.do?moduleId=${row.id}')" />
	</display:column>

</display:table>

<security:authorize access="hasRole('TRAINER')">
	<jstl:if test="${mycourse == true}">


		<input type="button" name="create"
			value="<spring:message code="module.create" />"
			onclick="javascript: window.location.assign('module/trainer/create.do?courseId=${courseId}')" />
	</jstl:if>
</security:authorize>