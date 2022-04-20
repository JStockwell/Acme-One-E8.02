<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patronage.code" path="code" width="10%"/>
	<acme:list-column code="patronage.legislation" path="legislation" width="10%"/>
	<acme:list-column code="patronage.budget" path="budget" width="10%"/>
	<acme:list-column code="patronage.creationDate" path="creationDate" width="10%"/>
	<acme:list-column code="patronage.startDate" path="startDate" width="10%"/>
	<acme:list-column code="patronage.finishDate" path="finishDate" width="10%"/>
	<acme:list-column code="patronage.link" path="link" width="10%"/>
</acme:list>