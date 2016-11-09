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


<acme:jstlOut code="section.title" value="${section.title }" />
<acme:jstlOut code="section.content" value="${section.content }" />

<b><spring:message code="section.attachments" />: </b>
<br>
<jstl:forEach var="photo" items="${trip.getAttachments() }">
	<acme:jstlOut code="section.attachments" value="attachment"/>
</jstl:forEach>
<br>

<br>
<security:authorize access="hasRole('TRAINER')">
	<jstl:if test="${logeado == true}">
		<jstl:if test="${mycurriculum == true}">

			<input type="button"
				value="<spring:message code="section.edit" />"
				onclick="javascript: window.location.assign('section/trainer/edit.do?sectionId=${section.id}')" />

		</jstl:if>
	</jstl:if>
</security:authorize>



