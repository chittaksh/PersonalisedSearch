<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>Home Page : Personalised Search Engine</title>
</head>

<!-- Code Start: to check if any user is logged in -->
<c:if test="${User != null}">
	<c:if test="${sessionScope.User.role == 'Manager'}">
		<c:redirect url="ManagerHome" />
	</c:if>
	<c:if test="${sessionScope.User.role == 'User'}">
		<c:redirect url="UserHome" />
	</c:if>
</c:if>
<!-- Code End: to check if any user is logged in -->

<jsp:include page="../search/header.jsp"></jsp:include>

<h2>Login Page</h2>
</br>
</br>

<jsp:include page="../search/error.jsp"></jsp:include>

<form method="post" action="Login"
	class="form-horizontal col-sm-offset-4" role="form" name="loginform">

	<div class="form-group">
		<label for="email" class="control-label col-sm-2">Email : </label>
		<div class="col-sm-3">
			<input type="email" name="email" id="email" class="form-control"
				required />
		</div>
	</div>

	<div class="form-group">
		<label for="password" class="control-label col-sm-2">Password
			: </label>
		<div class="col-sm-3">
			<input type="password" name="password" class="form-control" required />
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-1 col-sm-2">
			<input type="submit" value="Submit" class="btn btn-primary"
				name="submit" />
		</div>
		<div class="col-sm-1">
			<button value="Clear" name="clear" type="reset" class="btn">Clear</button>
		</div>
	</div>

</form>

<a href="Register">New User? Register Here</a>

<jsp:include page="../search/footer.jsp"></jsp:include>