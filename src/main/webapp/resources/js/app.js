'use strict';

var app = angular.module("fundsApp",["ngRoute"]);


app.config(function($routeProvider) {
	$routeProvider
		.when("/login", {
			templateUrl : "/FundsCollection/resources/html/login.html",
			controller : "userController"
		})
		.when("/register", {
			templateUrl : "/FundsCollection/resources/html/register.html",
			controller : "registerController"
		})
		.when("/success", {
			templateUrl : "/FundsCollection/resources/html/success.html",
			controller : "userController"
		})
		.when("/fail", {
			templateUrl : "/FundsCollection/resources/html/home.html",
			controller : "userController"
		})
		.when("/error", {
			templateUrl : "/FundsCollection/resources/html/error.html",
			controller : "userController"
		})
		.otherwise({
			redirectTo: "/login"
		});
});