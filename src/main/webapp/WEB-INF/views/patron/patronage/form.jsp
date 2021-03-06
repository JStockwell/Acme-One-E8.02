<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
<jstl:if test="${command != 'create'}">
	<acme:input-textbox code="patronage.code" path="code"/>
	<acme:input-select code="patronage.status" path="status">
		<acme:input-option code="Proposed" value="Proposed" selected="${status == 'Proposed'}"/>
		<acme:input-option code="Accepted" value="Accepted" selected="${status == 'Accepted'}"/>
		<acme:input-option code="Denied" value="Denied" selected="${status == 'Denied'}"/>
		<acme:input-option code="Draft" value="Draft" selected="${status == 'Draft'}"/>
	</acme:input-select>
	<acme:input-textbox code="patronage.legislation" path="legislation"/>
	<acme:input-money code="patronage.budget" path="budget"/>
	<acme:input-moment code="patronage.creationDate" path="creationDate"/>
	<acme:input-moment code="patronage.startDate" path="startDate"/>
	<acme:input-moment code="patronage.finishDate" path="finishDate"/>
	<acme:input-url code="patronage.link" path="link" placeholder="patronage.link"/>
	<acme:input-textbox code="inventor.name" path="inventorName"/>
	<acme:input-email code="inventor.email" path="inventorEmail"/>
	<acme:input-textbox code="inventor.company" path="inventorCompany"/>
	<acme:input-textbox code="inventor.statement" path="inventorStatement"/>
	<acme:input-url code="inventor.link" path="inventorLink"/>
</jstl:if>

<jstl:if test="${command == 'create'}">
	<acme:input-textbox code="patronage.code" path="code"/>
	<acme:input-textbox code="patronage.legislation" path="legislation"/>
	<acme:input-money code="patronage.budget" path="budget"/>
	<acme:input-moment code="patronage.creationDate" path="creationDate"/>
	<acme:input-moment code="patronage.startDate" path="startDate"/>
	<acme:input-moment code="patronage.finishDate" path="finishDate"/>
	<acme:input-url code="patronage.link" path="link" placeholder="patronage.link"/>
	
	<acme:input-select code="inventor.name" path="inventorId">
		<jstl:forEach items="${inventors}" var = "inventor">
			<acme:input-option code="${inventor.getIdentity().getFullName()}" value="${inventor.getId()}" selected="${inventor.getId() == inventorId}"/>
		</jstl:forEach>
	</acme:input-select>
</jstl:if>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && status == 'Draft'}">
			<acme:submit code="patronage.button.update" action="/patron/patronage/update"/>
			<acme:submit code="patronage.button.delete" action="/patron/patronage/delete"/>
			<acme:submit code="patronage.button.publish" action="/patron/patronage/publish"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'create')}">
			<acme:submit code="patronage.button.create" action="/patron/patronage/create"/>
		</jstl:when>	
	</jstl:choose>
</acme:form>

