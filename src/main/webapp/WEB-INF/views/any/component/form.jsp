<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="component.name" path="name"/>	
	<acme:input-textarea code="component.description" path="description"/>
	<acme:input-textbox code="component.code" path="code"/>
	<acme:input-textbox code="component.technology" path="technology"/>
	<acme:input-double code="component.retailPrice" path="retailPrice"/>
	<acme:input-url code="component.link" path="link"/>	
</acme:form>
