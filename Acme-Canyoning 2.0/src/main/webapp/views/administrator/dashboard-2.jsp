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
	<spring:message code="trainer.name" var="name" />
	<display:column property="name" title="${email}" sortable="true" />
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

<!-- B -->

<!--  The average number of curricula per trainer.  -->
  <p><spring:message code="administrator.dashboard.16"/></p>
  <jstl:out value="${averageCurriculumsByTrainer}"></jstl:out>
<br>

<!--  The average number of sections per curriculum.  -->
  <p><spring:message code="administrator.dashboard.17"/></p>
  <jstl:out value="${averageSectionsByCurriculums}"></jstl:out>
<br>

<!--  The trainers who have registered at least a curriculum in which his or her full name does not coincide with the full name in his or her user account.  -->
  <p><spring:message code="administrator.dashboard.18"/></p>
  <display:table name="trainersNameNotMatchCurriculumName" id="row7"
	requestURI="dashboard/administrator/list2.do" pagesize="5"
	class="displaytag" keepStatus="true">
	<spring:message code="trainer.name" var="name" />
	<display:column property="name" title="${email}" sortable="true" />
	<spring:message code="trainer.email" var="email" />
	<display:column property="email" title="${email}" sortable="true" />
	<spring:message code="trainer.phone" var="phone" />
	<display:column property="phone" title="${phone}" sortable="true" />
</display:table>
<br>

<!--  The trainers who havent registered any curricula.  -->
  <p><spring:message code="administrator.dashboard.19"/></p>
  <display:table name="trainersNoCurriculum" id="row8"
	requestURI="dashboard/administrator/list2.do" pagesize="5"
	class="displaytag" keepStatus="true">
	<spring:message code="trainer.name" var="name" />
	<display:column property="name" title="${email}" sortable="true" />
	<spring:message code="trainer.email" var="email" />
	<display:column property="email" title="${email}" sortable="true" />
	<spring:message code="trainer.phone" var="phone" />
	<display:column property="phone" title="${phone}" sortable="true" />
</display:table>
<br>

<!--  The trainers who havent updated their curricula for more than three months.  -->
  <p><spring:message code="administrator.dashboard.20"/></p>
  <display:table name="trainersNoUpdateCurriculumThree" id="row9"
	requestURI="dashboard/administrator/list2.do" pagesize="5"
	class="displaytag" keepStatus="true">
	<spring:message code="trainer.name" var="name" />
	<display:column property="name" title="${email}" sortable="true" />
	<spring:message code="trainer.email" var="email" />
	<display:column property="email" title="${email}" sortable="true" />
	<spring:message code="trainer.phone" var="phone" />
	<display:column property="phone" title="${phone}" sortable="true" />
</display:table>
<br>

</div>

