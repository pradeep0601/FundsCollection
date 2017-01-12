'use strict';

var app = angular.module("mainApp",["ngRoute",'ngMaterial','ngMessages']);


app.config(function($routeProvider) {
	$routeProvider
		.when("/dashboard", {
			templateUrl : "/FundsCollection/resources/html/dashboard.html",
			controller : "fundsController"
		})
		.when("/fundsInfo", {
			templateUrl : "/FundsCollection/resources/html/fundsInformation.html",
			controller : "fundsController"
		})
		.when("/expenditure", {
			templateUrl : "/FundsCollection/resources/html/expenditure.html",
			controller : "fundsController"
		})
		.when("/report", {
			templateUrl : "/FundsCollection/resources/html/report.html",
			controller : "fundsController"
		})
		.when("/activity", {
			templateUrl : "/FundsCollection/resources/html/activity.html",
			controller : "fundsController"
		})
		.when("/aboutus", {
			templateUrl : "/FundsCollection/resources/html/aboutUs.html",
			controller : "fundsController"
		})
		.when("/welcome", {
			templateUrl : "/FundsCollection/resources/html/Welcome.html",
			controller : "fundsController"
		})
		.when("/login", {
			templateUrl : "/FundsCollection/resources/html/login.html",
			controller : "fundsController"
		})
		.when("/register", {
			templateUrl : "/FundsCollection/resources/html/register.html",
			controller : "registerController"
		})
		.when("/browseAlbum", {
			templateUrl : "/FundsCollection/resources/html/ImageGallery.html",
			controller : "albumController"
		})
		.when("/gallery", {
			templateUrl : "/FundsCollection/resources/html/createAlbum.html",
			controller : "FileUploadController"
		})
		.otherwise({
			redirectTo: "/welcome"
		});
});

