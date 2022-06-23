<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
<jstl:if test="${command != 'create'}">
	<acme:input-textbox code="troque.code" path="code"/>
	<acme:input-textbox code="troque.theme" path="theme"/>
	<acme:input-textbox code="troque.explanation" path="explanation"/>
	<acme:input-moment code="troque.creationMoment" path="creationMoment"/>
	<acme:input-moment code="troque.startDate" path="startDate"/>
	<acme:input-moment code="troque.finishDate" path="finishDate"/>
	<acme:input-money code="troque.quantity" path="quantity"/>
	<acme:input-url code="troque.moreInfo" path="moreInfo" placeholder="troque.moreInfo"/>
  	<acme:input-textbox code="inventor.troque.item.name" path="itemName"/> 
	<acme:input-textbox code="inventor.name" path="inventorName"/>
	
</jstl:if>

<jstl:if test="${command == 'create'}">
	<acme:input-textbox code="troque.code" path="code"/>
	<acme:input-textbox code="troque.theme" path="theme"/>
	<acme:input-textbox code="troque.explanation" path="explanation"/>
	<acme:input-moment code="troque.startDate" path="startDate"/>
	<acme:input-moment code="troque.finishDate" path="finishDate"/>
	<acme:input-money code="troque.quantity" path="quantity"/>
	<acme:input-url code="troque.moreInfo" path="moreInfo" placeholder="troque.moreInfo"/>
	
	<acme:input-select code="inventor.troque.item.name" path="itemId">
		<jstl:forEach items="${items}" var = "item">
			<acme:input-option code="${item.getName()}" value="${item.getId()}" selected="${item.getId() == itemId}"/>
		</jstl:forEach>
	</acme:input-select>
</jstl:if>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:submit code="troque.button.update" action="/inventor/troque/update"/>
			<acme:submit code="troque.button.delete" action="/inventor/troque/delete"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'create')}">
			<acme:submit code="troque.button.create" action="/inventor/troque/create"/>
		</jstl:when>	
	</jstl:choose>
</acme:form>

