<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.system-configuration.form.label.accepted-currencies" path="acceptedCurrencies"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.default-currency" path="defaultCurrency"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.strong-spam-terms" path="strongSpamTerms"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.strong-threshold" path="strongThreshold"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.weak-spam-terms" path="weakSpamTerms"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.weak-threshold" path="weakThreshold"/>
</acme:form>