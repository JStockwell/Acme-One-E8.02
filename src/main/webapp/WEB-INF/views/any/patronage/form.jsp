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

<acme:form readonly="${readonly}">
	<acme:input-textbox code="patronage.code" path="code"/>
	<acme:input-textbox code="patronage.legislation" path="legislation"/>
	<acme:input-textbox code="patronage.budget" path="budget"/>
	<acme:input-textbox code="patronage.creationDate" path="creationDate"/>
	<acme:input-textbox code="patronage.startDate" path="startDate"/>
	<acme:input-textbox code="patronage.finishDate" path="finishDate"/>
	<acme:input-url code="patronage.link" path="link"/>
</acme:form>