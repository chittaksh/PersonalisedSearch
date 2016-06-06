<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>Profile : Personalised Search Engine</title>
</head>

<jsp:include page="../search/header.jsp"></jsp:include>

<h2>Profile</h2>
</br>
</br>

<jsp:include page="../search/error.jsp"></jsp:include>

<form method="post" action="Profile" class="form-horizontal"
	role="form" name="registerform">
	<input type="hidden" name="id" value="${sessionScope.User.id}" />
	<div class="form-group">
		<label for="firstname" class="control-label col-sm-5">First Name
			: </label>
		<div class="col-sm-3">
			<input type='text' name='firstname' class='form-control' value="${sessionScope.User.firstName}" required />
		</div>
	</div>
	
	<div class="form-group">
		<label for="lastname" class="control-label col-sm-5">Last Name
			: </label>
		<div class="col-sm-3">
			<input type='text' name='lastname' class='form-control' value="${sessionScope.User.lastName}" required />
		</div>
	</div>
	
	<div class="form-group">
		<label for="email" class="control-label col-sm-5">Email ID :</label>
		<div class="col-sm-3">
			<input type='email' name='email' class='form-control' value="${sessionScope.User.eMail}" required />
		</div>
	</div>


	<div class="form-group">
		<label for="password" class="control-label col-sm-5">Password
			:</label>
		<div class="col-sm-3">
			<input type='password' name='password' class='form-control' value="${sessionScope.User.password}" required />
		</div>
	</div>

	<div class="form-group">
		<label for="conpassword" class="control-label col-sm-5">Confirm
			Password :</label>
		<div class="col-sm-3">
			<input type='password' name='conpassword' class='form-control' value="${sessionScope.User.password}"
				required />
		</div>
	</div>
	
	<div class="form-group">
		<label for="people" class="control-label col-sm-5">Contact # :
			(number only)</label>
		<div class="col-sm-3">
			<input type='number' name='contact' class='form-control' value="${sessionScope.User.contact}" required />
		</div>
	</div>

	<div class="form-group">
		<label for="address" class="control-label col-sm-5">Address
			:</label>
		<div class="col-sm-3">
			<textarea type='text' name='address' class='form-control' rows="3" text="${sessionScope.User.address}" required ></textarea>
		</div>
	</div>

	<div class="form-group">
		<label for="city" class="control-label col-sm-5">City
			: </label>
		<div class="col-sm-3">
			<input type='text' name='city' class='form-control' value="${sessionScope.User.city}" required />
		</div>
	</div>

	<div class="form-group">
		<label for="zip" class="control-label col-sm-5">Zip Code
			: </label>
		<div class="col-sm-3">
			<input type='number' name='zip' class='form-control' value="${sessionScope.User.zip}" required />
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

<jsp:include page="../search/footer.jsp"></jsp:include>