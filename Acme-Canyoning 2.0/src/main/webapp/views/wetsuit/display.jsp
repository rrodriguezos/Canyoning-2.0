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


<acme:jstlOut code="wetsuit.name" value="${wetsuit.name }" />
<br>
<acme:jstlOut code="wetsuit.description" value="${wetsuit.description }" />
<br>
<acme:jstlOut code="wetsuit.make" value="${wetsuit.make }" />
<br>
<jstl:if test="${wetsuit.trousers == true}">
<acme:jstlOut code="wetsuit.trousers" value="" /> 
<spring:message code="wetsuit.TRUE" />

</jstl:if>
<jstl:if test="${wetsuit.trousers == false}">
<acme:jstlOut code="wetsuit.trousers" value="" /> 
<spring:message code="wetsuit.FALSE" />
</jstl:if>
<br>
<acme:jstlOut code="wetsuit.minimumTemperature" value="${wetsuit.minimumTemperature }" />
<br>

<jstl:if test="${trousersLong == true}">
<acme:jstlOut code="wetsuit.sizeSleeves" value="" /> 
<spring:message code="wetsuit.LONG" />

</jstl:if>

<jstl:if test="${trousersLong == false}">
<acme:jstlOut code="wetsuit.sizeSleeves" value="" /> 
<spring:message code="wetsuit.SHORT" />

</jstl:if>
<br>
<b><spring:message code="wetsuit.model" />: </b>
<br>
<jstl:forEach var="model" items="${wetsuit.getModel()}">
	<img width="200px" height="200x" src="${model}"/>
</jstl:forEach>
<br>

<br>

<jstl:if test="${logeado == true}">


	<security:authorize access="hasRole('ORGANISER')">
		<jstl:if test="${mywetsuit == true}">

			<input type="button" value="<spring:message code="wetsuit.edit" />"
				onclick="javascript: window.location.assign('wetsuit/organiser/edit.do?wetsuitId=${wetsuit.id}')" />

		</jstl:if>
	</security:authorize>


	<br>


</jstl:if>
<input type="button" name="cancel"
	value="<spring:message code="wetsuit.cancel"/>"
	onclick="javascript: window.history.back()" />
