<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>Compare Page : Personalised Search Engine</title>
</head>

<!-- Code Start: to check if any user is logged in -->
<c:if test="${User != null}">
	<c:if test="${sessionScope.User.role == 'Manager'}">

		<jsp:include page="../search/header.jsp"></jsp:include>

		<h2>Compare Page</h2>
		</br>
		</br>

		<jsp:include page="../search/error.jsp"></jsp:include>

		<div id="key_chart" style="width: 950px; height: 450px"></div>

		<%-- <div style="margin-bottom: 50px;">
			<c:forEach items="${lstRecords}" var="rec">
				<input type="checkbox" id="${lstRecords.indexOf(rec)}"
					value="${rec.count}" style="margin: 5px;" onclick="drawChart();"><label>${rec.key}</label>
			</c:forEach>
		</div> --%>
		<jsp:include page="../search/footer.jsp"></jsp:include>

	</c:if>
	<c:if test="${sessionScope.User.role == 'User'}">
		<c:redirect url="UserHome" />
	</c:if>
</c:if>
<!-- Code End: to check if any user is logged in -->

<script>
	google.load("visualization", "1", {
		packages : [ "corechart" ]
	});
	google.setOnLoadCallback(drawChart);
	function drawChart() {

		/* var str = new Array();
		str.push([ 'Keyword', 'Hits' ]);
		for (var i = 0; i <= 9; i++) {
			if (document.getElementById(i) != null) {
				if (document.getElementById(i).checked) {
					str.push([
							document.getElementById(i).nextSibling.innerText,
							parseInt(document.getElementById(i).value) ]);
				}
			}
		} 
		
		// Create and populate the data table.
		var data = google.visualization.arrayToDataTable(str);
		*/
		
		var data1 = google.visualization.arrayToDataTable([
			['Keyword','Hits']
			<c:forEach items="${lstRecords}" var="rec">
			 ,[ '${rec.key}', ${rec.count}]
			</c:forEach>		
		]);

		var options = {
			title : 'Keywords till now.'
		};

		// Create and draw the visualization.
		new google.visualization.PieChart(document.getElementById('key_chart'))
				.draw(data1, options);
	}
</script>
