<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="chirp.title" path="title"/>	
	<acme:input-textarea code="chirp.body" path="body"/>
	<acme:input-textbox code="chirp.author" path="author"/>
	<acme:input-email code="chirp.mail" path="mail"/>	
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="chirp.announcement.form.label.confirmation" path="confirmation"/>
		<acme:submit code="chirp.button.create" action="/any/chirp/create"/>
	</jstl:if>
</acme:form>
