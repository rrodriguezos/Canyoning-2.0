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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<h2>

	<jstl:out value="${comment.getTitle() }" />

</h2>

<acme:jstlOut code="comment.actor" value="${comment.getActor().getEmail() }"/>
<br>
<fieldset style="width:50%;">
<legend><fmt:formatDate value="${comment.getMoment() }" pattern="dd/MM/yyyy HH:mm" /></legend>

<jstl:out value="${comment.getBody() }" />
<jstl:out value="${comment.getStars() }" />
</fieldset>



<input type="button" name="back" value="<spring:message code="comment.cancel"/>" onclick="javascript: window.history.back()" />