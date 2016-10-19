<%--

 * register.jsp

 *

 * Copyright (C) 2014 Universidad de Sevilla

 * 

 * The use of this project is hereby constrained to the conditions of the 

 * TDG Licence, a copy of which you may download from 

 * http://www.tdg-seville.info/License.html

 --%>



<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<script>
	function myFunction(str) {
     var patt = new RegExp("^([+][0-9]{1,2})?([0-9]{3})?(?:[0-9]{4,})");
     if(patt.test(str)==false){
    	 window.alert("<spring:message code="customer.phone.confirm"/>");
     }	  
 	}
</script>

<security:authorize access="hasRole('ADMINISTRATOR')">

	<form:form action="organiser/administrator/create.do" modelAttribute="organiserForm">


		<acme:textbox code="organiser.username" path="username" />

		<acme:password code="organiser.password" path="password" />
		<acme:password code="organiser.confirmPassword" path="confirmPassword" />

		<acme:textbox code="organiser.email" path="email" />

		<acme:textbox code="organiser.phone" path="phone" />
		
		<!--<acme:submit name="save" code="customer.save" />-->
			<input type="submit" name="save" value="<spring:message code="organiser.save"/>" onclick="javascript: myFunction(this.form.phone.value) " />
		<acme:cancel url="welcome/index.do" code="organiser.cancel" />

	</form:form>

</security:authorize>