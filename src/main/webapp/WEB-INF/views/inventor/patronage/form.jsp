<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="patronage.code" path="code"/>
	<acme:input-textbox code="patronage.legislation" path="legislation"/>
	<acme:input-textbox code="patronage.budget" path="budget"/>
	<acme:input-textbox code="patronage.status" path="status"/>
	<acme:input-textbox code="patronage.creationDate" path="creationDate"/>
	<acme:input-textbox code="patronage.startDate" path="startDate"/>
	<acme:input-textbox code="patronage.finishDate" path="finishDate"/>
	<acme:input-url code="patronage.link" path="link"/>
	<acme:input-textbox code="patronage.patronCompany" path="patronCompany"/>
	<acme:input-textbox code="patronage.patronStatement" path="patronStatement"/>
	<acme:input-textbox code="patronage.patronLink" path="patronLink"/>
</acme:form>

<acme:button code="patronageReport.button.create" action="/inventor/patronage-report/create"/>