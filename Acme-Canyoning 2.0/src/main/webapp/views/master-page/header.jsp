<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme Canyoning Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->

		<li><a class="fNiv" href="${pageContext.request.contextPath}">
				<spring:message code="master.page.home" />
		</a></li>

		<li><a href="canyon/list.do"><spring:message
					code="master.page.public.catalogueCanyon" /></a>
					<li><a href="activity/list.do"><spring:message
					code="master.page.public.catalogueActivity" /></a>
		<li><a class="fNiv" href="search/buscar.do"><spring:message
					code="master.page.search.activities" /></a></li>
				<security:authorize
				access="isAnonymous()">
				<li><a class="fNiv" href="customer/register.do"> <spring:message
							code="master.page.register" /></a></li>
				<li><a class="fNiv" href="security/login.do"> <spring:message
							code="master.page.login" /></a></li>
			</security:authorize> 
			
			<security:authorize access="hasRole('ADMINISTRATOR')">
				<li><a class="fNiv" href="dashboard/administrator/list.do">
						<spring:message code="master.page.administrator.dashboard" />
				</a></li>

			</security:authorize> 
			
			<security:authorize access="isAuthenticated()">
				<li><a class="fNiv"> <spring:message
							code="master.page.profile" /> (<security:authentication
							property="principal.username" />)
				</a>
					<ul>
						<li class="arrow"></li>
									<li><a href="customer/list.do"><spring:message
					code="master.page.auth.customersList" /></a>
					<li><a href="organiser/list.do"><spring:message
					code="master.page.auth.organisersList" /></a>

						<security:authorize access="hasRole('CUSTOMER')">
							<li><a href="activity/customer/mylist.do"><spring:message
										code="master.page.customer.activities" /></a></li>
										
										<li><a href="activity/customer/mylistAccepted.do"><spring:message
										code="master.page.customer.activitiesAccepted" /></a></li>
										
										<li><a href="activity/customer/mylistReject.do"><spring:message
										code="master.page.customer.activitiesReject" /></a></li>
										
										<li><a href="activity/customer/mylistPending.do"><spring:message
										code="master.page.customer.activitiesPending" /></a></li>
										
										<li><a href="request/customer/list.do"><spring:message
										code="master.page.customer.requests" /></a></li>
										
			
										
						</security:authorize>

						<security:authorize access="hasRole('ORGANISER')">
							<li><a href="activity/organiser/mylist.do"><spring:message
										code="master.page.organiser.activities" /></a></li>

							<li><a href="activity/organiser/create.do"><spring:message
										code="master.page.organiser.createActivity" /></a></li>
										<li><a href="about/organiser/list.do"><spring:message
										code="master.page.organiser.about" /></a></li>

						</security:authorize>

						<security:authorize access="hasRole('ADMINISTRATOR')">
							<li><a href="organiser/administrator/list.do"><spring:message
										code="master.page.administrator.register.organiser" /></a></li>
							
							<li><a href="canyon/administrator/mylist.do"><spring:message
										code="master.page.manager.canyon" /></a></li>
										<li><a href="canyon/administrator/create.do"><spring:message
										code="master.page.organiser.createCanyon" /></a></li>
										<li><a href="organiser/administrator/list.do"><spring:message
										code="master.page.administrator.register.organiser" /></a></li>
	
						</security:authorize>

					</ul></li>
				<li><a href="j_spring_security_logout"> <spring:message
							code="master.page.logout" /></a></li>
			</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

