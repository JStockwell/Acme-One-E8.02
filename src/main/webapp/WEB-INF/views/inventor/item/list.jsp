<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="item.name" path="name" width="40%"/>
	<acme:list-column code="item.technology" path="technology" width="20%"/>
	<acme:list-column code="item.retailPrice" path="price" width="20%"/>
	<acme:list-column code="item.itemtype" path="itemType" width="20%"/>
</acme:list>
