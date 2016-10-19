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

	<form:form action="course/trainer/edit.do" modelAttribute="course">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="modules" />
	<form:hidden path="trainer" />
	<form:hidden path="comments" />


		<fieldset>
			<legend>
				<spring:message code="course.create" />
			</legend>
			<acme:textbox code="course.title" path="title" />
			<acme:textbox code="course.description" path="description" />
			<acme:textbox code="course.banner" path="banner" />

		</fieldset>


		<acme:submit name="save" code="course.save" />
		<jstl:if test="${course.id!=0}">
			<input type="submit" name="delete"
				value="<spring:message code="course.delete"/>"
				onclick="return confirm('<spring:message code="course.confirm.delete" />')" />
		</jstl:if>
		<acme:cancel url="course/trainer/mylist.do" code="course.cancel" />


</form:form>
</security:authorize>