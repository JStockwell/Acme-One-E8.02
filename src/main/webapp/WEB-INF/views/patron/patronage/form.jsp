<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="patronage.code" path="code"/>
	<acme:input-textbox code="patronage.legislation" path="legislation"/>
	<acme:input-textbox code="patronage.budget" path="budget"/>
	<acme:input-textbox code="patronage.creationDate" path="creationDate"/>
	<acme:input-textbox code="patronage.startDate" path="startDate"/>
	<acme:input-textbox code="patronage.finishDate" path="finishDate"/>
	<acme:input-url code="patronage.link" path="link"/>
	<acme:input-textbox code="inventor.name" path="inventorName"/>
	<acme:input-email code="inventor.email" path="inventorEmail"/>
	<acme:input-textbox code="inventor.company" path="inventorCompany"/>
	<acme:input-textbox code="inventor.statement" path="inventorStatement"/>
	<acme:input-url code="inventor.link" path="inventorLink"/>
</acme:form>