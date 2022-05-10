<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="item.name" path="name"/>	
	<acme:input-textarea code="item.description" path="description"/>
	<acme:input-textbox code="item.code" path="code"/>
	<acme:input-textbox code="item.technology" path="technology"/>
	<acme:input-double code="item.retailPrice" path="price"/>
	<acme:input-url code="item.link" path="link" placeholder="item.link"/>	
	<acme:input-select code="item.itemtype" path="itemType">
		<acme:input-option code="TOOL" value="TOOL" selected="${itemType == 'TOOL'}"/>
		<acme:input-option code="COMPONENT" value="COMPONENT" selected="${itemType == 'COMPONENT'}"/>
	</acme:input-select>
	
	<jstl:choose>
<%-- 		<jstl:when test="${acme:anyOf(command, 'show') && draft == true}"> --%>
<%-- 			<acme:button code="item.button.update" action="/inventor/item/update?id=${id}"/> --%>
<%-- 			<acme:button code="item.button.publish" action="/inventor/item/publish?id=${id}"/> --%>
<%-- 			<acme:button code="item.button.delete" action="/inventor/item/delete?id=${id}"/> --%>
<%-- 		</jstl:when> --%>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && draft == true}">
<%-- 			<acme:button code="item.button.update" action="/inventor/item/update?id=${id}"/> --%>
<%-- 			<acme:button code="item.button.publish" action="/inventor/item/publish?id=${id}"/> --%>
<%-- 			<acme:button code="item.button.delete" action="/inventor/item/delete?id=${id}"/> --%>
			<acme:submit code="item.button.update" action="/inventor/item/update"/>
			<acme:submit code="item.button.delete" action="/inventor/item/delete"/>
			<acme:submit code="item.button.publish" action="/inventor/item/publish"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'create')}">
			<acme:submit code="item.button.create" action="/inventor/item/create"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>

			

