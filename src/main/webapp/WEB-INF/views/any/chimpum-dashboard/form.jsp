<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<table class="table table-sm">
	<acme:message code="any.chinpum-dashobard.ratio"/>
	<jstl:forEach var="ratio" items="${ratioChinpumArtefacts}">
		<tr>
			<th scope="row">
				<jstl:out value="${ratio.getKey()}"/>
			</th>
			<td>
				<jstl:out value="${ratio.getValue()}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>
<table class="table table-sm">
	<acme:message code="any.chinpum-dashobard.average"/>
	<jstl:forEach var="average" items="${averageBudgetOfChinpum}">
		<tr>
			<th scope="row">
				<jstl:out value="${average.getKey()}"/>
			</th>
			<td>
				<jstl:out value="${average.getValue()}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>
<table class="table table-sm">
	<acme:message code="any.chinpum-dashobard.deviation"/>
	<jstl:forEach var="deviation" items="${deviationBudgetOfChinpum}">
		<tr>
			<th scope="row">
				<jstl:out value="${deviation.getKey()}"/>
			</th>
			<td>
				<jstl:out value="${deviation.getValue()}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>
<table class="table table-sm">
	<acme:message code="any.chinpum-dashobard.minimum"/>
	<jstl:forEach var="minimum" items="${minimumBudgetOfChinpum}">
		<tr>
			<th scope="row">
				<jstl:out value="${minimum.getKey()}"/>
			</th>
			<td>
				<jstl:out value="${minimum.getValue()}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>
<table class="table table-sm">
	<acme:message code="any.chinpum-dashobard.maximum"/>
	<jstl:forEach var="maximum" items="${maximumBudgetOfChinpum}">
		<tr>
			<th scope="row">
				<jstl:out value="${maximum.getKey()}"/>
			</th>
			<td>
				<jstl:out value="${maximum.getValue()}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>

<acme:return/>