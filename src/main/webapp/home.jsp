<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Funds Collection:Home</title>
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
<script type="text/javascript" src="resources/js/service/uploadService.js"></script>
<script type="text/javascript" src="resources/js/controller/FileUploadController.js"></script>
<script type="text/javascript" src="resources/js/service/albumService.js"></script>
<script type="text/javascript" src="resources/js/controller/albumController.js"></script>
<!-- <link rel="stylesheet" href="resources/css/bootstrap.min.css">-->
<link rel="stylesheet" href="resources/css/custom.css">
<link rel="stylesheet" href="resources/css/lib/fontsCSS/css/font-awesome.min.css">
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<link href='http://fonts.googleapis.com/css?family=Yesteryear|Tillana|Allan' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
</head>
<body data-ng-app="fundsApp" data-ng-controller="fundsMainCtrl">

	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
	<div class="container">
	<div class="col-md-3">
		<div class="navbar-header">
		  		  <a class="navbar-brand" href="#"><img class="img-rounded" src="resources/images/FClogo.jpg" alt="" style="width: 150px; height: 40px"></a>
		  <!-- <a class="navbar-brand" href="https://www.aricent.com/"><img class="img-rounded" src="resources/images/Aricent_logo.png" alt="" style="width: 150px; height: 40px"></a> -->
		</div>
		</div>
		<div class="col-md-9">
		<div class="collapse navbar-collapse" id="fundsNavbar">
		    <ul class="nav navbar-nav">
			   <li class="active"><a href="#dashboard">Home</a></li>
			   <li><a href="#fundsInfo">Funds</a></li>
			   <li><a href="#expenditure">Expenditure</a></li>
			   <li><a href="#report">Report</a></li>
			   <!-- <li><a href="#activity">Activities</a></li>-->
			   <li><a href="#gallery">Gallery</a></li>
			   <li><a href="#contact">Contact Us</a></li>
		    </ul>
		    <ul class="nav navbar-nav navbar-right">
					<li> <button type="button"
							class="btn btn-default btn-lg btn-circle dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="glyphicon glyphicon-user"></span>
						</button>
						<div class="dropdown-menu"
							style="padding: 15px; padding-bottom: 15px;">

							<button class="btn btn-default btn-logout" id="btnLogout" type="button"	data-ng-click="startLogout()">LOG OUT</button>
						</div></li>
				</ul>
		</div>
		</div>
		</div>
	</div>
	</nav>

	<div class="container"  data-ng-init = "getFundDetails()">
		<div data-ng-view></div>
	</div>
</body>

<footer class="footer">
      <div class="container">
      			<a href="#">Home</a>
				�
				<a href="#about">About</a>
				.
				<a href="#contact">Contact</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <span class="text-muted">Copyright &copy; Aricent Technologies 2016</span>
     </div> 
</footer>
</html>