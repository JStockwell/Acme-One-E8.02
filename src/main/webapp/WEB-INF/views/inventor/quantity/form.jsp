<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-select code="quantity.toolkit" path="toolkitId">
		<jstl:forEach items="${toolkits}" var="t">
			<acme:input-option code="${t.code.concat(' '.concat(t.title))}" value="${t.id}" selected="${t.id==toolkitId}"/>
		</jstl:forEach>
	</acme:input-select>
	<acme:input-select code="quantity.item" path="itemId">
		<jstl:forEach items="${items}" var="i">
			<acme:input-option code="${i.code.concat(' '.concat(i.name))}" value="${i.id}" selected="${i.id==itemId}"/>
		</jstl:forEach>
	</acme:input-select>
	<acme:input-integer code="quantity.itemQuantity" path="itemQuantity"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete') && draftMode == true }">
			<acme:submit code="quantity.update" action="/inventor/quantity/update"/>
			<acme:submit code="quantity.delete" action="/inventor/quantity/delete"/>
		</jstl:when>
		<jstl:when test="${ command == 'create' }">
			<acme:submit code="quantity.create" action="/inventor/quantity/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>