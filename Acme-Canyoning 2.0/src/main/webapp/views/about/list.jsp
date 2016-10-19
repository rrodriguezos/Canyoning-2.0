<%--
 * action-1.jsp
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
<security:authorize access="hasRole('ORGANISER')">
<display:table name="about" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">

	<spring:message code="about.message" var="message" />
	<display:column property="message" title="${message}" />
		
	
	<spring:message code="about.display" var="display" />
	<display:column title="${display}">
			<input type="button" value="<spring:message code="about.display" />" 
					onclick="javascript: window.location.assign('about/organiser/display.do?aboutId=${row.id}')" />
	</display:column>

	
</display:table>
<jstl:if test="${yaTengo== false}">	

<input type="button" name="create" value="<spring:message code="about.create" />"
	 onclick="javascript: window.location.assign('about/organiser/create.do')" />


</jstl:if>
</security:authorize>