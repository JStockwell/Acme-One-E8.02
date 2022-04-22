<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="chirp.title" path="title" width="30%"/>
	<acme:list-column code="chirp.autor" path="author" width="15%"/>
	<acme:list-column code="chirp.moment" path="moment" width="15%"/>
	<acme:list-column code="chirp.body" path="body" width="40%"/>
</acme:list>
