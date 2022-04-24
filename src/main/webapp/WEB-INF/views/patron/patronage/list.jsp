<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patronage.code" path="code" width="10%"/>
	<acme:list-column code="patronage.legislation" path="legislation" width="10%"/>
	<acme:list-column code="patronage.budget" path="budget" width="10%"/>
</acme:list>