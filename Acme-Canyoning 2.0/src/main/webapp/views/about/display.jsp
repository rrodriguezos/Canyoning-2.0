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

<security:authorize access="hasRole('ORGANISER')">
<TABLE border="1">
<TR><TD>

<acme:jstlOut code="about.message" value="${about.message }" />
</TD></TR>
</TABLE>
	
	<input type="button" value="<spring:message code="about.edit" />" 
			onclick="javascript: window.location.assign('about/organiser/edit.do?aboutId=${about.id}')" />




<input type="button" name="cancel"
	value="<spring:message code="about.cancel"/>"
	onclick="javascript: window.location.assign('about/organiser/list.do')" />
</security:authorize>