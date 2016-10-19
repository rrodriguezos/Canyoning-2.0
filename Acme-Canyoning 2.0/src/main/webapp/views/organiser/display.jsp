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

<acme:jstlOut code="organiser.username" value="${organiser.userAccount.username }"/>
<acme:jstlOut code="organiser.email" value="${organiser.email }"/>
<acme:jstlOut code="organiser.phone" value="${organiser.phone }"/>

<TABLE border="1">
<TR><TD>
<acme:jstlOut code="organiser.about" value="${organiser.about.message }"/>
</TD></TR>
</TABLE>


<h2><spring:message code="organiser.activities"/></h2>
<display:table name="activities" id="row" pagesize="5" requestURI="organiser/display.do" class="displaytag">
	
	<spring:message code="activity.title" var="title" />
	<display:column title="${title}" property="title" />
	
	<spring:message code="activity.display" var="display" />
	<display:column title="${display}">
			<input type="button" value="<spring:message code="activity.display" />" 
					onclick="javascript: window.location.assign('activity/display.do?activityId=${row.id}')" />
	</display:column>
	
</display:table>

<input type="button" name="cancel" value="<spring:message code="organiser.cancel"/>" onclick="javascript: window.location.assign('organiser/list.do')" />
