<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
<jstl:if test="${command != 'create'}">
	<acme:input-textbox code="chimpum.code" path="code"/>
	<acme:input-textbox code="chimpum.title" path="title"/>
	<acme:input-textbox code="chimpum.description" path="description"/>
	<acme:input-moment code="chimpum.creationMoment" path="creationDate"/>
	<acme:input-moment code="chimpum.startDate" path="startDate"/>
	<acme:input-moment code="chimpum.finishDate" path="finishDate"/>
	<acme:input-money code="chimpum.budget" path="budget"/>
	<acme:input-url code="chimpum.link" path="link" placeholder="chimpum.link"/>
	
	<acme:input-textbox code="item.name" path="itemName"/>
	
	<acme:input-textbox code="inventor.name" path="inventorName"/>
	
</jstl:if>

<jstl:if test="${command == 'create'}">
	<acme:input-textbox code="chimpum.code" path="code"/>
	<acme:input-textbox code="chimpum.title" path="title"/>
	<acme:input-textbox code="chimpum.description" path="description"/>
	<acme:input-moment code="chimpum.creationMoment" path="creationDate"/>
	<acme:input-moment code="chimpum.startDate" path="startDate"/>
	<acme:input-moment code="chimpum.finishDate" path="finishDate"/>
	<acme:input-money code="chimpum.budget" path="budget"/>
	<acme:input-url code="chimpum.link" path="link" placeholder="chimpum.link"/>
	
	<acme:input-select code="inventor.name" path="inventorId">
		<jstl:forEach items="${inventors}" var = "inventor">
			<acme:input-option code="${inventor.getUserAccount().getUsername()}" value="${inventor.getId()}" selected="${inventor.getId() == inventorId}"/>
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
			<acme:submit code="item.button.create" action="/patron/patronage/create"/>
		</jstl:when>	
	</jstl:choose>
</acme:form>

