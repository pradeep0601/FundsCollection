'use strict';

var fundsApp = angular.module("fundsApp",["ngRoute",'ngMaterial','ngMessages']);


fundsApp.config(function($routeProvider) {
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
		.otherwise({
			redirectTo: "/dashboard"
		});
});

