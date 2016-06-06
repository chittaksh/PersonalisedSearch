<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, search.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<html>

<body class='container'>


	<c:choose>

		<c:when test="${User != null}">
			<!-- Code Start: to check if any user is logged in -->
			<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
			<a class="navbar-brand" href="Login">Personalised Search</a>
				<ul class="nav navbar-nav navbar-right">
				<c:if test="${User.role == 'Manager'}">
					<li><a href="Compare">Compare</a></li>
				</c:if>
				<li><a href="Reports">Reports</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">${User.firstName} <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="Profile">Profile</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="Logout">Logout</a></li>
						</ul></li>
				</ul>
			</div>
			</nav>
			<!-- Code End: to check if any user is logged in -->
		</c:when>

		<c:otherwise>
			<h1>Personalised Search Engine</h1>
			</br>
			</br>
		</c:otherwise>
	</c:choose>