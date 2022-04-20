<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="patron.patron-dasboard.form.title"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="patron.patron-dashboard.max.accepted"/>
		</th>
		<td>
			<acme:print value="${maximumPatronageBudgetPerStateAccepted}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.patron-dashboard.max.proposed"/>
		</th>
		<td>
			<acme:print value="${maximumPatronageBudgetPerStateProposed}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.patron-dashboard.max.denied"/>
		</th>
		<td>
			<acme:print value="${maximumPatronageBudgetPerStateDenied}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.patron-dashboard.min.accepted"/>
		</th>
		<td>
			<acme:print value="${minimumPatronageBudgetPerStateAccepted}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.patron-dashboard.min.proposed"/>
		</th>
		<td>
			<acme:print value="${minimumPatronageBudgetPerStateProposed}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.patron-dashboard.min.denied"/>
		</th>
		<td>
			<acme:print value="${minimumPatronageBudgetPerStateDenied}"/>
		</td>
	</tr>
</table>

<div>
	<h2>
		<acme:message code="patron.patron-dashobard.form.total"/>
	</h2>
	<canvas id="canvas1"></canvas>
</div>
<div>
	<h2>
		<acme:message code="patron.patron-dashobard.form.average"/>
	</h2>
	<canvas id="canvas2"></canvas>
</div>
<div>
	<h2>
		<acme:message code="patron.patron-dashobard.form.deviation"/>
	</h2>
	<canvas id="canvas3"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
				labels: ["PROPOSED", "ACCEPTED", "DENIED"],
				datasets : [
					{
						data : [
							<jstl:out value="${totalPatronagesProposed}"/>,
							<jstl:out value="${totalPatronagesAccepted}"/>,
							<jstl:out value="${totalPatronagesDenied}"/>
						]
					}
				]
		};
		var options = {
				scales: {
					yAxes:[{
						ticks:{
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}]
				},
				legend : {
					display : false
				}
			};
		var canvas1, canvas2, canvas3, context1, context2, context3;
		
		canvas1 = document.getElementById("canvas1");
		context1 = canvas1.getContext("2d");
		new Chart(context1, {
			type : "bar",
			data : data,
			options : options
		});

		var data2 = {
				labels: ["PROPOSED", "ACCEPTED", "DENIED"],
				datasets : [
					{
						data : [
							<jstl:out value="${averagePatronageBudgetPerStateProposed}"/>,
							<jstl:out value="${averagePatronageBudgetPerStateAccepted}"/>,
							<jstl:out value="${averagePatronageBudgetPerStateDenied}"/>
						]
					}
				]
		};
		canvas2 = document.getElementById("canvas2");
		context2 = canvas2.getContext("2d");
		new Chart(context2, {
			type : "bar",
			data : data2,
			options : options
		});
		var data3 = {
				labels: ["PROPOSED", "ACCEPTED", "DENIED"],
				datasets : [
					{
						data : [
							<jstl:out value="${deviationPatronageBudgetPerStateProposed}"/>,
							<jstl:out value="${deviationPatronageBudgetPerStateAccepted}"/>,
							<jstl:out value="${deviationPatronageBudgetPerStateDenied}"/>
						]
					}
				]
		};
		canvas3 = document.getElementById("canvas3");
		context3 = canvas3.getContext("2d");
		new Chart(context3, {
			type : "bar",
			data : data3,
			options : options
		});
	});
</script>

<acme:return/>