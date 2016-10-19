<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<security:authorize access="isAuthenticated()">
	<!-- Listing grid -->
	<div class=center-text>
		<display:table name="comments" id="row" requestURI="${requestURI}"
			pagesize="5" class="displaytag" keepStatus="true">

			<!-- Attributes -->

			<spring:message code="comment.moment" var="momentHeader" />
			<display:column property="moment" title="${momentHeader}"
				sortable="true" format="{0, date, dd/MM/yyyy HH:mm}" />

			<spring:message code="comment.body" var="bodyHeader" />
			<display:column property="body" title="${bodyHeader}" sortable="true" />

			<spring:message code="comment.stars" var="starsHeader" />
			<display:column property="stars" title="${starsHeader}"
				sortable="true" />

			<spring:message code="comment.actor" var="actorHeader" />
			<display:column property="actor.email" title="${actorHeader}"
				sortable="true" />


		</display:table>
	</div>
	<div>
		<spring:message code="comment.create" var="create" />

		<input type="button" value="<spring:message code="comment.create" />"
			onclick="javascript: window.location.assign('comment/create.do?commentableId=${id}')" />

	</div>
</security:authorize>