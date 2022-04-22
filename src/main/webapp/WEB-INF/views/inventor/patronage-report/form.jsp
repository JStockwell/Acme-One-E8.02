<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="patronageReport.creation" path="creation"/>
	<acme:input-textbox code="patronageReport.memorandum" path="memorandum"/>
	<acme:input-url code="patronageReport.link" path="link"/>
	<acme:input-textbox code="patronageReport.patronage" path="patronage"/>
</acme:form>