<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="administrator.systemconfiguration.form.label.currency" path="defaultCurrency"/>
	<acme:input-textbox code="administrator.systemconfiguration.form.label.strongSpamThreshold" path="strongThreshold"/>
	<acme:input-textbox code="administrator.systemconfiguration.form.label.weakSpamTerms" path="weakSpamTerms"/>
	<acme:input-textbox code="administrator.systemconfiguration.form.label.weakSpamThreshold" path="weakThreshold"/>
	<acme:input-textbox code="administrator.systemconfiguration.form.label.acceptedcurrencies" path="acceptedCurrencies"/>
	<acme:input-textbox code="administrator.systemconfiguration.form.label.strongSpamTerms" path="strongSpamTerms"/>
	

</acme:form> 