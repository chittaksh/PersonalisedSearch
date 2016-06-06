<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>Reports : Personalised Search Engine</title>
</head>

<jsp:include page="../search/header.jsp"></jsp:include>
<jsp:include page="../search/error.jsp"></jsp:include>

<c:if test="${sessionScope.User.role == 'Manager' }">

<div style="margin-top: 50px;">
	<form method="post" action="Reports" class="form-horizontal"
		role="form" name="loginform">

		<div class="form-group">
			<label for="user" class="control-label col-sm-3">Please
				select user : </label>
			<div class="col-sm-3">
				<select class="form-control" id="selUser" name="selUser" required>
					<option value="0" ${userId == 0 ? 'selected': ''}>Personal</option>
					<c:forEach items="${userList}" var="uer">
						<option value="${uer.id}" ${userId== uer.id ? 'selected': ''}>${uer.firstName}
							${uer.lastName} </option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-3">
				<input type="submit" value="Submit" class="btn btn-primary"
					name="submit" />
			</div>
		</div>
	</form>
</div>

</c:if>

<div id="key_chart" style="width: 950px; height: 450px"></div>
<div id="url_chart" style="width: 950px; height: 450px"></div>

<script>
   google.load("visualization", "1", {packages:["corechart"]});
   google.setOnLoadCallback(drawChart);
   function drawChart() {
    // Create and populate the data table.
    var data = google.visualization.arrayToDataTable([
      		['URL', 'Hits']                                              
  		<c:forEach items="${records}" var="output"  >
  		<c:if test="${output.count!=0}">
  		 ,['${output.url.getAuthority()}',${output.count}]
  		 </c:if>
        </c:forEach> 
    ]);
    
    var data1 = google.visualization.arrayToDataTable([
           ['Key', 'Hits']                                              
        <c:forEach items="${keyRecords}" var="output"  > 
        <c:if test="${output.count!=0}">
         ,['${output.key}',${output.count}]
         </c:if>
        </c:forEach> 
    ]);
    
    var options = {
      title: 'Websites visited till now.'
    };
    
    var options1 = {
    	      title: 'Keywords searched till now.'
    	    };
     // Create and draw the visualization.
    new google.visualization.PieChart(
      document.getElementById('url_chart')).draw(data, options);
     
    new google.visualization.PieChart(
    	      document.getElementById('key_chart')).draw(data1, options1);
  }
</script>


<jsp:include page="../search/footer.jsp"></jsp:include>