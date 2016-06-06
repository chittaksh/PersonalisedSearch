<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>Register : Personalised Search Engine</title>
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

<h2>Registration Page</h2>
</br>
</br>

<jsp:include page="../search/error.jsp"></jsp:include>

<form method="post" action="Register" class="form-horizontal"
	role="form" name="registerform">
	
	<div class="form-group">
		<label for="firstname" class="control-label col-sm-5">First Name
			: </label>
		<div class="col-sm-3">
			<input type='text' name='firstname' class='form-control' required />
		</div>
	</div>
	
	<div class="form-group">
		<label for="lastname" class="control-label col-sm-5">Last Name
			: </label>
		<div class="col-sm-3">
			<input type='text' name='lastname' class='form-control' required />
		</div>
	</div>
	
	<div class="form-group">
		<label for="email" class="control-label col-sm-5">Email ID :</label>
		<div class="col-sm-3">
			<input type='email' name='email' class='form-control' required />
		</div>
	</div>


	<div class="form-group">
		<label for="password" class="control-label col-sm-5">Password
			:</label>
		<div class="col-sm-3">
			<input type='password' name='password' class='form-control' required />
		</div>
	</div>

	<div class="form-group">
		<label for="conpassword" class="control-label col-sm-5">Confirm
			Password :</label>
		<div class="col-sm-3">
			<input type='password' name='conpassword' class='form-control'
				required />
		</div>
	</div>
	
	<div class="form-group">
		<label for="people" class="control-label col-sm-5">Contact # :
			(number only)</label>
		<div class="col-sm-3">
			<input type='number' name='contact' class='form-control' required />
		</div>
	</div>

	<div class="form-group">
		<label for="address" class="control-label col-sm-5">Address
			:</label>
		<div class="col-sm-3">
			<textarea type='text' name='address' class='form-control' rows="3" required ></textarea>
		</div>
	</div>

	<div class="form-group">
		<label for="city" class="control-label col-sm-5">City
			: </label>
		<div class="col-sm-3">
			<input type='text' name='city' class='form-control' required />
		</div>
	</div>

	<div class="form-group">
		<label for="zip" class="control-label col-sm-5">Zip Code
			: </label>
		<div class="col-sm-3">
			<input type='number' name='zip' class='form-control' required />
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-4 col-sm-2">
			<input type="submit" value="Submit" class="btn btn-primary"
				name="submit" />
		</div>
		<div class="col-sm-1">
			<button value="Clear" name="clear" type="reset"
				onclick="clear-form()" class="btn">Clear</button>
		</div>
	</div>
</form>

<a href='Login'>Try Login</a>

<jsp:include page="../search/footer.jsp"></jsp:include>