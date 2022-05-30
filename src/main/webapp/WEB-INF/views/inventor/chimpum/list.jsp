<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chimpum.code" path="code" width="10%"/>
	<acme:list-column code="chimpum.title" path="title" width="10%"/>
	<acme:list-column code="chimpum.description" path="description" width="10%"/>
	<acme:list-column code="chimpum.budget" path="budget" width="10%"/>
</acme:list>

<acme:button code="chimpum.button.create" action="inventor/chimpum/create"/>