<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="chirp.title" path="title"/>	
	<acme:input-textarea code="chirp.body" path="body"/>
	<acme:input-textbox code="chirp.autor" path="autor"/>
	<acme:input-moment code="chirp.moment" path="moment"/>
	<acme:input-email code="chirp.mail" path="mail"/>	
</acme:form>
