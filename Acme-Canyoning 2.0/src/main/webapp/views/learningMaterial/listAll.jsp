
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!-- Listing activity -->
<div>

	<display:table name="learningMaterials" id="row" requestURI="${requestURI}"
		pagesize="5" class="displaytag" keepStatus="true">
		<!-- Attributes -->
		<spring:message code="learningMaterial.title" var="title" />
		<display:column property="title" title="${title}" sortable="true" />

		<spring:message code="learningMaterial.description" var="description" />
		<display:column property="description" title="${title}"
			sortable="true" />
			
			<spring:message code="learningMaterial.materialLink" var="materialLink" />
		<display:column property="materialLink" title="${materialLink}"
			sortable="true" />


		<spring:message code="learningMaterial.display" var="display" />
		<display:column title="${display}">
			<input type="button" value="<spring:message code="learningMaterial.display" />"
				onclick="javascript: window.location.assign('learningMaterial/display.do?learningMaterialId=${row.id}')" />
		</display:column>



	</display:table>



</div>

