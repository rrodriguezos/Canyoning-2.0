
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<!-- The average number of activities per organiser. -->
<spring:message code="administrator.dashboard.1" />
<p>
	<jstl:out value="${averageActivitiesPerOrganiser}"></jstl:out>
</p>

<br>

<!-- The average number of customers in the waiting lists. -->
<spring:message code="administrator.dashboard.2" />
<p>
	<jstl:out value="${averageCustomersInWaitingList}"></jstl:out>
</p>

<br>

<!-- The average number of seats offered in the activities that are going to be organised in the forthcoming three months.. -->
<spring:message code="administrator.dashboard.3" />
<p>
	<jstl:out value="${averageSeatsOrganisedThreeMonths}"></jstl:out>
</p>

<br>


<!-- The activities that offer at least 10% more seats or 10% less seats than the average. -->
<spring:message code="administrator.dashboard.4.1" />
<br>
<display:table name="activity10MoreAverage" id="row4"
	requestURI="dashboard/administrator/list.do" pagesize="5"
	class="displaytag" keepStatus="true">
	<spring:message code="activity.title" var="title" />
	<display:column property="title" title="${title}" />
		
	<spring:message code="activity.description" var="description" />
	<display:column property="description" title="${description}" />
	
	<spring:message code="activity.numberSeats" var="numberSeats" />
	<display:column property="numberSeats" title="${numberSeats}" />
</display:table>
<spring:message code="administrator.dashboard.4.2" />
<br>
<display:table name="activity10LessAverage" id="row5"
	requestURI="dashboard/administrator/list.do" pagesize="5"
	class="displaytag" keepStatus="true">
	<spring:message code="activity.title" var="title" />
	<display:column property="title" title="${title}" />
		
	<spring:message code="activity.description" var="description" />
	<display:column property="description" title="${description}" />
	
	<spring:message code="activity.numberSeats" var="numberSeats" />
	<display:column property="numberSeats" title="${numberSeats}" />
</display:table>
<br>

<!-- Standard deviation of the time that a customer remains in a waiting list. -->
<spring:message code="administrator.dashboard.5" />
<p>
	<jstl:out value="${stdTimeRemainWaitingList}"></jstl:out>
</p>

<br>
<br>
<!-- Average of the time that a customer remains in a waiting list. -->
<spring:message code="administrator.dashboard.6" />
<p>
	<jstl:out value="${averageTimeRemainWaitingList}"></jstl:out> <spring:message code="administrator.dashboard.days" />
</p>






