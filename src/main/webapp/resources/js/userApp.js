'use strict';

var app = angular.module("userApp",["ngRoute",'ngMaterial','ngMessages']);


app.config(function($routeProvider) {
	$routeProvider
		.when("/aboutus", {
			templateUrl : "/FundsCollection/resources/html/aboutUs.html",
			controller : "userController"
		})
		.when("/welcome", {
			templateUrl : "/FundsCollection/resources/html/Welcome.html",
			controller : "userController"
		})
		.when("/login", {
			templateUrl : "/FundsCollection/resources/html/login.html",
			controller : "userController"
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

