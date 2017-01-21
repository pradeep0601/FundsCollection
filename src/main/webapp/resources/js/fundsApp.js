'use strict';

var app = angular.module("fundsApp",["ngRoute",'ngMaterial','ngMessages']);


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
		.when("/browseAlbum", {
			templateUrl : "/FundsCollection/resources/html/ImageGallery.html",
			controller : "albumController"
		})
		.when("/gallery", {
			templateUrl : "/FundsCollection/resources/html/createAlbum.html",
			controller : "FileUploadController"
		})
		.when("/contact", {
			templateUrl : "/FundsCollection/resources/html/contactUs.html",
			controller : "fundsController"
		})
		.otherwise({
			redirectTo: "/dashboard"
		});
});

