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


<acme:jstlOut code="kayak.name" value="${kayak.name }" />
<br>
<acme:jstlOut code="kayak.description" value="${kayak.description }" />
<br>
<acme:jstlOut code="kayak.make" value="${kayak.make }" />
<br>
<acme:jstlOut code="kayak.length" value="${kayak.length }" />
<br>
<acme:jstlOut code="kayak.numberSeats" value="${kayak.numberSeats }" />


<br>
<b><spring:message code="kayak.model" />: </b>
<br>
<jstl:forEach var="model" items="${kayak.getModel()}">
	<img width="200px" height="200x" src="${model}"/>
</jstl:forEach>
<br>

<jstl:if test="${logeado == true}">


	<security:authorize access="hasRole('ORGANISER')">
		<jstl:if test="${mykayak == true}">

			<input type="button" value="<spring:message code="kayak.edit" />"
				onclick="javascript: window.location.assign('kayak/organiser/edit.do?kayakId=${kayak.id}')" />

		</jstl:if>
	</security:authorize>


	<br>


</jstl:if>
<input type="button" name="cancel"
	value="<spring:message code="kayak.cancel"/>"
	onclick="javascript: window.history.back()" />
