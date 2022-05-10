<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="announcement.title" path="title"/>	
	<acme:input-textarea code="announcement.body" path="body"/>
	<acme:input-checkbox code="announcement.critical" path="critical"/>
	<acme:input-url code="announcement.link" path="link"/>	
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="announcement.button.create" action="/administrator/announcement/create"/>
		</jstl:when>
	</jstl:choose>
	
</acme:form>
