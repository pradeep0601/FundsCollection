<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.min.js"></script>
<!-- new scripts starts -->
 <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-aria.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-messages.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.js"></script>
<!-- new scripts ends -->
<!--  <script type="text/javascript" src="resources/js/lib/jquery-3.1.0.min.js"></script>-->
<!-- <script type="text/javascript" src="resources/js/lib/angular-min.js"></script>-->
<script type="text/javascript" src="resources/js/lib/angular-route.min.js"></script>
<!-- <script type="text/javascript" src="resources/js/lib/bootstrap.min.js"></script>-->
<script type="text/javascript" src="resources/js/fundsApp.js"></script>
<script type="text/javascript" src="resources/js/controller/funds_controller.js"></script>
<script type="text/javascript" src="resources/js/service/funds_service.js"></script>
<script type="text/javascript" src="resources/js/controller/fundsMainCtrl.js"></script>
<script type="text/javascript" src="resources/js/service/fundsMainService.js"></script>
<!-- <link rel="stylesheet" href="resources/css/bootstrap.min.css">-->
<link rel="stylesheet" href="resources/css/custom.css">
</head>
<body data-ng-app="fundsApp">

	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
		   <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#fundsNavbar">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
           </button>
		</div>
		<div class="collapse navbar-collapse" id="fundsNavbar">
		    <ul class="nav navbar-nav">
			   <li class="active"><a href="#dashboard">Home</a></li>
			   <li><a href="#fundsInfo">Funds Information</a></li>
			   <li><a href="#expenditure">Expenditure</a></li>
			   <li><a href="#report">Report</a></li>
			   <li><a href="#activity">Activity</a></li>
		    </ul>
		</div>
	</div>
	</nav>

	<div class="container" data-ng-controller="fundsMainCtrl"  data-ng-init = "getFundDetails()">
		<div data-ng-view></div>
	</div>
</body>
</html>