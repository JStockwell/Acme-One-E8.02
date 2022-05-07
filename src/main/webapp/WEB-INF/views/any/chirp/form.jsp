<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chirp.title" path="title"/>	
	<acme:input-textarea code="chirp.body" path="body"/>
	<acme:input-textbox code="chirp.autor" path="autor"/>
	<acme:input-email code="chirp.mail" path="mail"/>	
	
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chirp.button.create" action="/any/chirp/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>
