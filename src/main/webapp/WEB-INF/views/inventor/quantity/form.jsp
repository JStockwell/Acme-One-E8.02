<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-select code="quantity.toolkit" path="toolkit">
		<jstl:forEach items="${toolkits}" var="t">
			<acme:input-option code="${t.code}" value="${t}" selected="${toolkit.equals(t)}"/>
		</jstl:forEach>
	</acme:input-select>
	<acme:input-select code="quantity.item" path="item">
		<jstl:forEach items="${items}" var="i">
			<acme:input-option code="${i.code}" value="${i}" selected="${item.equals(i)}"/>
		</jstl:forEach>
	</acme:input-select>
	<acme:input-integer code="quantity.itemQuantity" path="itemQuantity"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete') && draftMode == true }">
		</jstl:when>
		<jstl:when test="${ command == 'create' }">
			<acme:submit code="quantity.create" action="/inventor/quantity/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>