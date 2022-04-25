<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.admin-darshboard.form.title"/>
</h2>
<h2>
	<acme:message code="administrator.admin-dashboard.form.total-components"/>
	<jstl:out value="${totalNumberOfComponents}"></jstl:out>
</h2>
<h2>
	<acme:message code="administrator.admin-dashboard.form.price-components-tech"/>
</h2>
<table class="table table-sm">
	<jstl:forEach var="componentsTech" items="${averageComponentsPricePerTechnology.entrySet()}">
		<jstl:forEach var="components" items="${componentsTech.getValue().entrySet()}">
			<tr>
				<th scope="row">
					<jstl:out value="${components.getKey()}"></jstl:out>
				</th>
				<td>
					<jstl:out value="${components.getValue()}"/>
					<jstl:out value="${componentsTech.getKey()}"></jstl:out>
				</td>
			</tr>
		</jstl:forEach>
	</jstl:forEach>
</table>
<h2>
	<acme:message code="administrator.admin-dashboard.form.price-components"/>
</h2>
<table class="table table-sm">
	<jstl:forEach var="componentsCurrency" items="${averageComponentsPricePerCurrency.entrySet()}">
		<tr>
			<th scope="row">
				<jstl:out value="${componentsCurrency.getKey()}"></jstl:out>
			</th>
			<td>
				<jstl:out value="${componentsCurrency.getValue()}"></jstl:out>
			</td>
		</tr>
	</jstl:forEach>
</table>
<h2>
	<acme:message code="administrator.admin-dashboard.form.deviation-components-tech"/>
</h2>
<table class="table table-sm">
	<jstl:forEach var="componentsTech" items="${deviationComponentsPricePerTechnology.entrySet()}">
		<jstl:forEach var="components" items="${componentsTech.getValue().entrySet()}">
			<tr>
				<th scope="row">
					<jstl:out value="${components.getKey()}"></jstl:out>
				</th>
				<td>
					<jstl:out value="${components.getValue()}"></jstl:out>
					<jstl:out value="${componentsTech.getKey()}"></jstl:out>
				</td>
			</tr>
		</jstl:forEach>
	</jstl:forEach>
</table>
<h2>
	<acme:message code="administrator.admin-dashboard.form.deviation-components"/>
</h2>
<table class="table table-sm">
	<jstl:forEach var="componentsCurrency" items="${deviationComponentsPricePerCurrency.entrySet()}">
		<tr>
			<th scope="row">
				<jstl:out value="${componentsCurrency.getKey()}"></jstl:out>
			</th>
			<td>
				<jstl:out value="${componentsCurrency.getValue()}"></jstl:out>
			</td>
		</tr>
	</jstl:forEach>
</table>
<h2>
	<acme:message code="administrator.admin-dashboard.form.minimum-components-tech"/>
</h2>
<table class="table table-sm">
	<jstl:forEach var="componentsTech" items="${minimumRetailPriceOfComponentsPerTechnology.entrySet()}">
		<jstl:forEach var="components" items="${componentsTech.getValue().entrySet()}">
			<tr>
				<th scope="row">
					<jstl:out value="${components.getKey()}"></jstl:out>
				</th>
				<td>
					<jstl:out value="${components.getValue()}"></jstl:out>
					<jstl:out value="${componentsTech.getKey()}"></jstl:out>
				</td>
			</tr>
		</jstl:forEach>
	</jstl:forEach>
</table>
<h2>
	<acme:message code="administrator.admin-dashboard.form.minimum-components"/>
</h2>
<table class="table table-sm">
	<jstl:forEach var="componentsCurrency" items="${minimumRetailPriceOfComponentsPerCurrency.entrySet()}">
		<tr>
			<th scope="row">
				<jstl:out value="${componentsCurrency.getKey()}"></jstl:out>
			</th>
			<td>
				<jstl:out value="${componentsCurrency.getValue()}"></jstl:out>
			</td>
		</tr>
	</jstl:forEach>
</table>
<h2>
	<acme:message code="administrator.admin-dashboard.form.maximum-components-tech"/>
</h2>
<table class="table table-sm">
	<jstl:forEach var="componentsTech" items="${maximumRetailPriceOfComponentsPerTechnology.entrySet()}">
		<jstl:forEach var="components" items="${componentsTech.getValue().entrySet()}">
			<tr>
				<th scope="row">
					<jstl:out value="${components.getKey()}"></jstl:out>
				</th>
				<td>
					<jstl:out value="${components.getValue()}"></jstl:out>
					<jstl:out value="${componentsTech.getKey()}"></jstl:out>
				</td>
			</tr>
		</jstl:forEach>
	</jstl:forEach>
</table>
<h2>
	<acme:message code="administrator.admin-dashboard.form.maximum-components"/>
</h2>
<table class="table table-sm">
	<jstl:forEach var="componentsCurrency" items="${maximumRetailPriceOfComponentsPerCurrency.entrySet()}">
		<tr>
			<th scope="row">
				<jstl:out value="${componentsCurrency.getKey()}"></jstl:out>
			</th>
			<td>
				<jstl:out value="${componentsCurrency.getValue()}"></jstl:out>
			</td>
		</tr>
	</jstl:forEach>
</table>
<h2>
	<acme:message code="administrator.admin-dashboard.form.tools-total"/>
	<jstl:out value="${totalNumberOfTools}"></jstl:out>
</h2>
<h2>
	<acme:message code="administrator.admin-dashboard.form.average-tools"/>
</h2>
<table class="table table-sm">
	<jstl:forEach var="toolsCurrency" items="${averageToolsPricePerCurrency.entrySet()}">
		<tr>
			<th scope="row">
				<jstl:out value="${toolsCurrency.getKey()}"></jstl:out>
			</th>
			<td>
				<jstl:out value="${toolsCurrency.getValue()}"></jstl:out>
			</td>
		</tr>
	</jstl:forEach>
</table>
<h2>
	<acme:message code="administrator.admin-dashboard.form.deviation-tools"/>
</h2>
<table class="table table-sm">
	<jstl:forEach var="toolsCurrency" items="${deviationToolsPricePerCurrency.entrySet()}">
		<tr>
			<th scope="row">
				<jstl:out value="${toolsCurrency.getKey()}"></jstl:out>
			</th>
			<td>
				<jstl:out value="${toolsCurrency.getValue()}"></jstl:out>
			</td>
		</tr>
	</jstl:forEach>
</table>
<h2>
	<acme:message code="administrator.admin-dashboard.form.minimum-tools"/>
</h2>
<table class="table table-sm">
	<jstl:forEach var="toolsCurrency" items="${minimumRetailPriceOfToolsPerCurrency.entrySet()}">
		<tr>
			<th scope="row">
				<jstl:out value="${toolsCurrency.getKey()}"></jstl:out>
			</th>
			<td>
				<jstl:out value="${toolsCurrency.getValue()}"></jstl:out>
			</td>
		</tr>
	</jstl:forEach>
</table>
<h2>
	<acme:message code="administrator.admin-dashboard.form.maximum-tools"/>
</h2>
<table class="table table-sm">
	<jstl:forEach var="toolsCurrency" items="${maximumRetailPriceOfToolsPerCurrency.entrySet()}">
		<tr>
			<th scope="row">
				<jstl:out value="${toolsCurrency.getKey()}"></jstl:out>
			</th>
			<td>
				<jstl:out value="${toolsCurrency.getValue()}"></jstl:out>
			</td>
		</tr>
	</jstl:forEach>
</table>
<h2>
	<acme:message code="administrator.admin-dashboard.form.patronages-total"/>
</h2>
<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="administrator.admin-dashboard.form.proposed"/>
		</th>
		<td>
			<jstl:out value="${totalPatronages.get('Proposed')}"></jstl:out>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.admin-dashboard.form.accepted"/>
		</th>
		<td>
			<jstl:out value="${totalPatronages.get('Accepted')}"></jstl:out>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.admin-dashboard.form.denied"/>
		</th>
		<td>
			<jstl:out value="${totalPatronages.get('Denied')}"></jstl:out>
		</td>
	</tr>
</table>
<h2>
	<acme:message code="administrator.admin-dashboard.form.average-patronages"/>
</h2>
<table class="table table-sm">
	<jstl:forEach var="patronages" items="${averagePatronageBudgetPerState.entrySet()}">
		<tr>
			<th scope="row">
				<acme:message code="administrator.admin-dashboard.form.proposed"/>
			</th>
			<td>
				<jstl:out value="${patronages.getValue().get('Proposed')}"></jstl:out>
				<jstl:out value="${patronages.getKey()}"></jstl:out>
			</td>
		</tr>
		<tr>
			<th scope="row">
				<acme:message code="administrator.admin-dashboard.form.accepted"/>
			</th>
			<td>
				<jstl:out value="${patronages.getValue().get('Accepted')}"></jstl:out>
				<jstl:out value="${patronages.getKey()}"></jstl:out>
			</td>
		</tr>
		<tr>
			<th scope="row">
				<acme:message code="administrator.admin-dashboard.form.denied"/>
			</th>
			<td>
				<jstl:out value="${patronages.getValue().get('Denied')}"></jstl:out>
				<jstl:out value="${patronages.getKey()}"></jstl:out>
			</td>
		</tr>
	</jstl:forEach>
</table>
<h2>
	<acme:message code="administrator.admin-dashboard.form.deviation-patronages"/>
</h2>
<table class="table table-sm">
	<jstl:forEach var="patronages" items="${deviationPatronageBudgetPerState.entrySet()}">
		<tr>
			<th scope="row">
				<acme:message code="administrator.admin-dashboard.form.proposed"/>
			</th>
			<td>
				<jstl:out value="${patronages.getValue().get('Proposed')}"></jstl:out>
				<jstl:out value="${patronages.getKey()}"></jstl:out>
			</td>
		</tr>
		<tr>
			<th scope="row">
				<acme:message code="administrator.admin-dashboard.form.accepted"/>
			</th>
			<td>
				<jstl:out value="${patronages.getValue().get('Accepted')}"></jstl:out>
				<jstl:out value="${patronages.getKey()}"></jstl:out>
			</td>
		</tr>
		<tr>
			<th scope="row">
				<acme:message code="administrator.admin-dashboard.form.denied"/>
			</th>
			<td>
				<jstl:out value="${patronages.getValue().get('Denied')}"></jstl:out>
				<jstl:out value="${patronages.getKey()}"></jstl:out>
			</td>
		</tr>
	</jstl:forEach>
</table>
<h2>
	<acme:message code="administrator.admin-dashboard.form.minimum-patronages"/>
</h2>
<table class="table table-sm">
	<jstl:forEach var="patronages" items="${minimumPatronageBudgetPerState.entrySet()}">
		<tr>
			<th scope="row">
				<acme:message code="administrator.admin-dashboard.form.proposed"/>
			</th>
			<td>
				<jstl:out value="${patronages.getValue().get('Proposed')}"></jstl:out>
				<jstl:out value="${patronages.getKey()}"></jstl:out>
			</td>
		</tr>
		<tr>
			<th scope="row">
				<acme:message code="administrator.admin-dashboard.form.accepted"/>
			</th>
			<td>
				<jstl:out value="${patronages.getValue().get('Accepted')}"></jstl:out>
				<jstl:out value="${patronages.getKey()}"></jstl:out>
			</td>
		</tr>
		<tr>
			<th scope="row">
				<acme:message code="administrator.admin-dashboard.form.denied"/>
			</th>
			<td>
				<jstl:out value="${patronages.getValue().get('Denied')}"></jstl:out>
				<jstl:out value="${patronages.getKey()}"></jstl:out>
			</td>
		</tr>
	</jstl:forEach>
</table>
<h2>
	<acme:message code="administrator.admin-dashboard.form.maximum-patronages"/>
</h2>
<table class="table table-sm">
	<jstl:forEach var="patronages" items="${maximumPatronageBudgetPerState.entrySet()}">
		<tr>
			<th scope="row">
				<acme:message code="administrator.admin-dashboard.form.proposed"/>
			</th>
			<td>
				<jstl:out value="${patronages.getValue().get('Proposed')}"></jstl:out>
				<jstl:out value="${patronages.getKey()}"></jstl:out>
			</td>
		</tr>
		<tr>
			<th scope="row">
				<acme:message code="administrator.admin-dashboard.form.accepted"/>
			</th>
			<td>
				<jstl:out value="${patronages.getValue().get('Accepted')}"></jstl:out>
				<jstl:out value="${patronages.getKey()}"></jstl:out>
			</td>
		</tr>
		<tr>
			<th scope="row">
				<acme:message code="administrator.admin-dashboard.form.denied"/>
			</th>
			<td>
				<jstl:out value="${patronages.getValue().get('Denied')}"></jstl:out>
				<jstl:out value="${patronages.getKey()}"></jstl:out>
			</td>
		</tr>
	</jstl:forEach>
</table>
