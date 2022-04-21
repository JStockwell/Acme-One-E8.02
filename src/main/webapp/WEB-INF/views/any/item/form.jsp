<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="item.name" path="name"/>	
	<acme:input-textarea code="item.description" path="description"/>
	<acme:input-textbox code="item.code" path="code"/>
	<acme:input-textbox code="item.technology" path="technology"/>
	<acme:input-double code="item.retailPrice" path="price"/>
	<acme:input-url code="item.link" path="link"/>	
	<acme:input-select code="item.itemtype" path="itemType">
		<acme:input-option code="TOOL" value="TOOL" selected="${itemType == 'TOOL'}"/>
		<acme:input-option code="COMPONENT" value="COMPONENT" selected="${itemType == 'COMPONENT'}"/>
	</acme:input-select>
</acme:form>
