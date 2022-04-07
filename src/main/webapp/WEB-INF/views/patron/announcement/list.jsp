<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="announcement.title" path="title" width="90%"/>
	<acme:list-column code="announcement.critical" path="critical" width="10%"/>
</acme:list>