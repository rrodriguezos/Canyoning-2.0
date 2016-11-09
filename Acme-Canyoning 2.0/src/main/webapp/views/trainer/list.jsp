<%--
 * list.jsp
 *
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<display:table name="trainers" id="row" class="displaytag"
	requestURI="${requestURI}" pagesize="5" keepStatus="true">

	<spring:message code="trainer.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="trainer.surname" var="surname" />
	<display:column title="${surname}" property="surname" />

	<spring:message code="trainer.email" var="email" />
	<display:column property="email" title="${email}" sortable="true" />

	<spring:message code="trainer.phone" var="phone" />
	<display:column property="phone" title="${phone}" sortable="true" />

	<display:column title="Curriculum">
		<jstl:forEach var="curr" items="${row.curriculums}">

			<jstl:if test="${curr.isActive == true}">

				<input type="button"
					value="<spring:message code="trainer.curriculum" />"
					onclick="javascript: window.location.assign('curriculum/listActive.do?trainerId=${row.id}')" />

			</jstl:if>

			<jstl:if test="${curr.isActive == false}">
			</jstl:if>
		</jstl:forEach>
	</display:column>


	<security:authorize access="isAuthenticated()">
		<spring:message code="trainer.comment" var="commentHeader" />
		<display:column title="${commentHeader}">
			<input type="button"
				value="<spring:message code="trainer.comment" />"
				onclick="javascript: window.location.assign('comment/list.do?id=${row.trainerComment.id}')" />
		</display:column>
	</security:authorize>

	<spring:message code="trainer.display" var="display" />
	<display:column title="${display}">
		<input type="button" value="<spring:message code="trainer.display" />"
			onclick="javascript: window.location.assign('trainer/display.do?trainerId=${row.id}')" />
	</display:column>



</display:table>
<security:authorize access="hasRole('ADMINISTRATOR')">

	<input type="button" name="create"
		value="<spring:message code="trainer.create" />"
		onclick="javascript: window.location.assign('trainer/administrator/create.do')" />
</security:authorize>
