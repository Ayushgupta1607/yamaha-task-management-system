<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Task Management System</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<!-- CSS only -->

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/8246e66c6a.js" crossorigin="anonymous"></script>
 <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
  <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" ></script>
<style>
<%@ include file="css/home.css"%>
</style>
<script>
	
<%@include file="js/script.js"%></script>
</head>
<body>
	<header>



		<nav id="sidebarMenu"
			class="collapse d-lg-block sidebar collapse ">
			<div class="position-sticky">
				<div class="list-group list-group-flush mx-3 mt-4">
					<a href="/tms/task" class=" list-group-item-action py-2 ripple" aria-current="true"> 
						<i class="fas fa-tasks-alt"></i>
						<span style="margin-left:10px"> Tasks</span> 
					</a> 
					<a href="#" class=" list-group-item-action py-2 ripple ">
						<i class="fa fa-user" aria-hidden="true"></i>
						<span style="margin-left:10px"> Users</span>
					</a>
				</div>
			</div>
		</nav>

		<!-- navbar -->
		<nav class="navbar navbar-expand-lg navbar-dark">
			<a class="navbar-brand" href="/tms/">
				<i class="fa-solid fa-lg fa-list-check"></i>  Task Management System
			</a>
			<div style="margin-left: auto" class="navbar-nav navbar-right">
				<a href="<c:url value="/logout" />">	
					<i style="color:white" class="fa-solid fa-lg fa-right-from-bracket"></i>
				</a>	
			</div>

		</nav>
			<%@ include file="alert.jsp"%>
	</header>
	<br>

<main >