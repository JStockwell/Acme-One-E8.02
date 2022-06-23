<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="troque.code" path="code" width="10%"/>
	<acme:list-column code="troque.theme" path="theme" width="10%"/>
	<acme:list-column code="troque.explanation" path="explanation" width="10%"/>
	<acme:list-column code="troque.quantity" path="quantity" width="10%"/>
</acme:list>

<acme:button code="troque.button.create" action="/inventor/troque/create"/>