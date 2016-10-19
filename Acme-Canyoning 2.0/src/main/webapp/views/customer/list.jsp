<%--
 * list.jsp
 *
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<display:table name="customers" id="row" class="displaytag" requestURI="${requestURI}" pagesize="5" keepStatus="true" >
	
	<spring:message	code="customer.email"  var="email"/>
	<display:column property="email" title="${name}" sortable="true" />
	
	<spring:message	code="customer.phone"  var="phone"/>
	<display:column property="phone" title="${phone}" sortable="true" />
	
    
    <security:authorize access="isAuthenticated()">
	<spring:message code="customer.comment" var="commentHeader" />
	<display:column title="${commentHeader}">
			<input type="button" value="<spring:message code="customer.comment" />" 
					onclick="javascript: window.location.assign('comment/list.do?id=${row.customerComment.id}')" />
	</display:column>
	</security:authorize>
	
	</display:table>
