<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>User Home : Personalised Search Engine</title>
</head>

<jsp:include page="../search/header.jsp"></jsp:include>
<jsp:include page="../search/error.jsp"></jsp:include>

<!-- Code Start: to check if any user is logged in -->
<c:choose>
	<c:when test="${User != null}">
		<c:if test="${sessionScope.User.role == 'Manager'}">
			<c:redirect url="ManagerHome" />
		</c:if>
		<c:if test="${sessionScope.User.role == 'User'}">

			<c:choose>
				<c:when test="${searchTerm == null}">
					<!-- Code Start : Page Code -->
					<form method="post" action="UserHome" class="form-horizontal"
						style="top: 45%; position: absolute; left: 15%; width: 75%;"
						role="form" name="searchform">
						<div class="form-group">
							<input type="text" name="searchterm" id="searchterm"
								class="form-control" required />
						</div>
						<div class="form-group">
							<input type="submit" value="Search" class="btn btn-primary"
								name="submit" />
						</div>
					</form>
				</c:when>
				
				<c:otherwise>
					<div style="position: absolute; width: 75%; left: 15%; top: 80px;">
						<form method="post" action="UserHome" class="form-horizontal"
							role="form" name="searchform">
							<div class="form-group">
								<input type="text" name="searchterm" id="searchterm"
									class="form-control" required />
							</div>

							<div class="form-group">
								<input type="submit" value="Search" class="btn btn-primary"
									name="submit" />
							</div>
						</form>

						<div style="float: left">
							<h4>Showing Results for : ${searchTerm}</h4>
						</div>
						<table style="float: left; margin-top: 20px; width:100%;">
							<c:forEach items="${sessionScope.oldResults}" var="result">
								<tr>
									<td>
										<table style="float: left; margin:10px; width:100%;">
											<tr style="width:100%;">
												<td style="width:70%;"><a href="RedirectTo?status=old&id=${result.id}">${result.title}</a></td>
												<td style="float:right; width:30%;">Source : ${result.service}</td>
											</tr>
											<tr>
												<td>${result.desc}</td>
											</tr>
											<tr>
											<td style="color:#999999;">Visited ${result.visitCount} times.</td>
											</tr>
										</table>
									</td>
								</tr>
							</c:forEach>
						</table>
						<table style="float: left; margin-top: 20px; width:100%;">
							<c:forEach items="${sessionScope.newResults}" var="result">
								<tr>
									<td>
										<table style="float: left; margin:10px; width:100%;">
											<tr style="width:100%;">
												<td style="width:70%;"><a href="RedirectTo?status=new&id=${result.id}">${result.title}</a></td>
												<td style="float:right; width:30%;">Source : ${result.service}</td>
											</tr>
											<tr>
												<td>${result.desc}</td>
											</tr>
											<tr>
											<td style="color:#999999;">Never been visited.</td>
											</tr>
										</table>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:otherwise>
			</c:choose>

			<!-- Code End : Page Code -->

		</c:if>
	</c:when>
	<c:otherwise>
		<c:redirect url="Login" />
	</c:otherwise>
</c:choose>
<!-- Code End: to check if any user is logged in -->

<jsp:include page="../search/footer.jsp"></jsp:include>