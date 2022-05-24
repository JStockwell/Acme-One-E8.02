<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patronageReport.creation" path="creation" width="10%"/>
	<acme:list-column code="patronageReport.memorandum" path="memorandum" width="10%"/>
	<acme:list-column code="patronageReport.patronage" path="patronage" width="10%"/>	
</acme:list>

<acme:button code="patronageReport.button.create" action="/inventor/patronage-report/create?masterId=${masterId}"/>