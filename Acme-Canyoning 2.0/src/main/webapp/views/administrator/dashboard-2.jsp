<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class=center-text>

<!--The average number of courses per trainer. -->
<p><spring:message code="administrator.dashboard.12"/></p>
<jstl:out value="${averageOfCoursesByTrainer}"></jstl:out>
<br>
<br>
<!--  The trainers who teach at least 10% courses above or below the average. -->
<spring:message code="administrator.dashboard.13" />
<br>
<display:table name="findTrainersLeastTenAverage" id="row2"
	requestURI="dashboard/administrator/list2.do" pagesize="5"
	class="displaytag" keepStatus="true">
	<spring:message code="trainer.email" var="email" />
	<display:column property="email" title="${email}" sortable="true" />
	<spring:message code="trainer.phone" var="phone" />
	<display:column property="phone" title="${phone}" sortable="true" />
</display:table>
<br>

<!--  The average number of modules per course.  -->
  <p><spring:message code="administrator.dashboard.14"/></p>
  <jstl:out value="${averageOfModulesByCourse}"></jstl:out>
<br>

<!-- The average number of learning materials per course. -->
<p><spring:message code="administrator.dashboard.15"/></p>
<jstl:out value="${averageOfLearningMaterialByCourse}"></jstl:out>

</div>

