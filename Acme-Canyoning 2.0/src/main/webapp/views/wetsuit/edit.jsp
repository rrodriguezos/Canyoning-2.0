<%--
 * action-1.jsp
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('ORGANISER')">

	<form:form action="wetsuit/organiser/edit.do" modelAttribute="wetsuit">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="organiser" />


		<tr>
			<td><acme:textbox code="wetsuit.name" path="name" /></td>
		</tr>
		<tr>
			<td><acme:textbox code="wetsuit.make" path="make" /></td>
		</tr>
		<tr>
			<spring:message code="wetsuit.trousers" />
			<td><select NAME="trousers">
					<OPTION VALUE="True">
						<spring:message code="wetsuit.TRUE" />
					</OPTION>
					<OPTION VALUE="False">
						<spring:message code="wetsuit.FALSE" />
					</OPTION>
			</select></td>
		</tr>
		<tr>
			<spring:message code="wetsuit.sizeSleeves" />
			<td><select NAME="sizeSleeves">
					<OPTION VALUE="LONG">
						<spring:message code="wetsuit.LONG" />
					</OPTION>
					<OPTION VALUE="SHORT">
						<spring:message code="wetsuit.SHORT" />
					</OPTION>
			</select></td>
		</tr>
		<br>
		<tr>
			<td><acme:textbox code="wetsuit.minimumTemperature"
					path="minimumTemperature" /></td>
		</tr>
		<tr>
			<td><acme:textarea code="wetsuit.description" path="description" /></td>
		</tr>

		<tr>
			<td><acme:textbox code="wetsuit.model" path="model" /></td>
		</tr>


		<jstl:if test="${wetsuit.id == 0}">
			<input type="submit" name="save"
				value="<spring:message code="wetsuit.save" />" />
		</jstl:if>

		<jstl:if test="${wetsuit.id != 0}">
			<input type="submit" name="save"
				value="<spring:message code="wetsuit.save" />">
			<input type="submit" name="delete"
				value="<spring:message code="wetsuit.delete"/>"
				onclick="return confirm('<spring:message code="wetsuit.confirm.delete" />')" />
		</jstl:if>

		<acme:cancel url="wetsuit/organiser/mylist.do" code="wetsuit.cancel" />

	</form:form>

</security:authorize>