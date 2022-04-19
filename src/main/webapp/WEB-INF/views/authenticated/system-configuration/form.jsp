<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.system-configuration.form.label.accepted-currencies" path="accepted-currencies"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.default-currency" path="default-currency"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.strong-spam-terms" path="strong-spam-terms"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.strong-threshold" path="strong-threshold"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.weak-spam-terms" path="weak-spam-terms"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.weak-threshold" path="weak-threshold"/>
</acme:form>