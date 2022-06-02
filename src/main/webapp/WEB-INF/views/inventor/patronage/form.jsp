<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="patronage.code" path="code"/>
	<acme:input-textbox code="patronage.legislation" path="legislation"/>
	<acme:input-textbox code="patronage.budget" path="budget"/>
	<acme:input-textbox code="patronage.creationDate" path="creationDate"/>
	<acme:input-textbox code="patronage.startDate" path="startDate"/>
	<acme:input-textbox code="patronage.finishDate" path="finishDate"/>
	<acme:input-url code="patronage.link" path="link"/>
	<acme:input-textbox code="patronage.patronCompany" path="patronCompany"/>
	<acme:input-textbox code="patronage.patronStatement" path="patronStatement"/>
	<acme:input-textbox code="patronage.patronLink" path="patronLink"/>
</acme:form>

<acme:button code="patronageReport.button.create" action="/inventor/patronage-report/create"/>
	
	<jstl:if test="${status != 'Proposed'}">
		<acme:input-textbox code="patronage.status" path="status" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${command == 'show' && status == 'Proposed'}">
		<acme:input-select path="status" code="patronage.status.new">
			<acme:input-option code="Proposed" value="Proposed" selected="true"/>
			<acme:input-option code="Accepted" value="Accepted"/>
			<acme:input-option code="Denied" value="Denied"/>
		</acme:input-select>		
		<acme:submit code="patronage.update" action="/inventor/patronage/update-status"/>
	</jstl:if>
</acme:form>
