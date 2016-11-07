<%--
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:jstlOut code="curriculum.name" value="${curriculum.name }"/>
<acme:jstlOut code="curriculum.email" value="${curriculum.email }"/>
<acme:jstlOut code="curriculum.phone" value="${curriculum.phone }"/>

<jstl:if test="${mycurriculum == true}">
	
		<input type="button" value="<spring:message code="curriculum.changeStateCV" />" 
			onclick="javascript: window.location.assign('curriculum/trainer/changeStateCV.do?curriculumId=${curriculum.id}')" />
		
	</jstl:if>


<security:authorize access="hasRole('TRAINER')">
  	<jstl:if test="${mycurriculum == true}">
	
	<input type="button" value="<spring:message code="curriculum.edit" />" 
			onclick="javascript: window.location.assign('curriculum/trainer/edit.do?curriculumId=${curriculum.id}')" />

	</jstl:if>
</security:authorize>



<input type="button" name="cancel" value="<spring:message code="curriculum.cancel"/>" onclick="javascript: window.history.back()" />
