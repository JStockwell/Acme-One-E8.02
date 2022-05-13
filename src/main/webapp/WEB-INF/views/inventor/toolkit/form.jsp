<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="toolkit.code" path="code"/>
	<acme:input-textbox code="toolkit.title" path="title"/>
	<acme:input-textarea code="toolkit.description" path="description"/>
	<acme:input-textarea code="toolkit.assemblyNotes" path="assemblyNotes"/>
	<acme:input-url code="toolkit.link" path="link"/>
	
	<jstl:choose>
		<jstl:when test="${ command == 'create' }">
			<acme:submit code="toolkit.create" action="/inventor/toolkit/create"/>
		</jstl:when>
	</jstl:choose>


</acme:form>