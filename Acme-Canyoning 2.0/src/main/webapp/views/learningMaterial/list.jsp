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

<display:table name="learningMaterials" id="row" pagesize="5"
	requestURI="learningMaterial/list.do" class="displaytag">
	<form:hidden path="moduleId" />
	<spring:message code="learningMaterial.title" var="title" />
	<display:column property="title" title="${title}" />

	<spring:message code="learningMaterial.description" var="description" />
	<display:column property="description" title="${description}" />
	
	<spring:message code="learningMaterial.materialLink" var="materialLink" />
	<display:column property="materialLink" title="${materialLink}" />


	<spring:message code="learningMaterial.display" var="display" />
	<display:column title="${display}">
		<input type="button" value="<spring:message code="learningMaterial.display" />"
			onclick="javascript: window.location.assign('learningMaterial/display.do?learningMaterialId=${row.id}')" />
	</display:column>
	


	<security:authorize access="hasRole('TRAINER')">
		<jstl:if test="${mycourse == true}">
			<spring:message code="learningMaterial.delete" var="delete" />
			<display:column title="${delete}">
				<input type="button" name="delete"
					value="<spring:message code="learningMaterial.delete" />"
					onclick="javascript: window.location.assign('learningMaterial/trainer/delete.do?learningMaterialId=${row.id}')" />
			</display:column>
		</jstl:if>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('TRAINER')">
	<jstl:if test="${mycourse == true}">

		<input type="button" name="create"
			value="<spring:message code="learningMaterial.create" />"
			onclick="javascript: window.location.assign('learningMaterial/trainer/create.do?moduleId=${moduleId}')" />

	</jstl:if>
</security:authorize>