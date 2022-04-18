<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="tool.name" path="name"/>	
	<acme:input-textarea code="tool.description" path="description"/>
	<acme:input-textbox code="tool.code" path="code"/>
	<acme:input-textbox code="tool.technology" path="technology"/>
	<acme:input-double code="tool.retailPrice" path="retailPrice"/>
	<acme:input-url code="tool.link" path="link"/>	
</acme:form>
