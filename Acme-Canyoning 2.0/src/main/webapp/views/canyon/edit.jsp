<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<security:authorize access="hasRole('ADMINISTRATOR')">

	<form:form action="canyon/administrator/edit.do" modelAttribute="canyon">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="activities" />
	<form:hidden path="administrator" />
	<form:hidden path="comments" />


		<fieldset>
			<legend>
				<spring:message code="canyon.create" />
			</legend>
			<acme:textbox code="canyon.name" path="name" />
			<acme:textbox code="canyon.description" path="description" />
			<acme:textbox code="canyon.difficultyLevel" path="difficultyLevel" />
			<acme:textbox code="canyon.route" path="route" />
			<acme:textarea code="canyon.pictures" path="pictures" />
		</fieldset>
		<fieldset>
			<legend>
				<spring:message code="canyon.coordinates" />
			</legend>
			<acme:textbox code="canyon.gps.latitude"
				path="gpsCoordinates.latitude" />
			<acme:textbox code="canyon.gps.longitude"
				path="gpsCoordinates.longitude" />
			<acme:textbox code="canyon.gps.altitude"
				path="gpsCoordinates.altitude" />
		</fieldset>




		<acme:submit name="save" code="canyon.save" />
		<jstl:if test="${canyon.id!=0}">
			<input type="submit" name="delete"
				value="<spring:message code="canyon.delete"/>"
				onclick="return confirm('<spring:message code="canyon.confirm.delete" />')" />
		</jstl:if>
		<acme:cancel url="canyon/administrator/mylist.do" code="canyon.cancel" />


</form:form>
</security:authorize>