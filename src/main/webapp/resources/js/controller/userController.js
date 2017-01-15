'use strict';

app.controller("userController", ["$scope","userService","$location","$window",function($scope,userService,$location,$window){

	$scope.employee = {"firstName" : "", "lastName" : "", "contact" : "", "email" : "", "dob" : "", "gender" : "",
						"userName" : "", "passWord" : "", "confirmPassword" : ""};
	$scope.registrationForm = { };
	  
	$scope.login = function(){
		userService.login($scope.employee)
		.then(
				function(successResp){
					var $homeUrl = "http://" + $window.location.host + "/FundsCollection/home.jsp";
					$window.location.href = $homeUrl;
					
				},
				function(errResp){
					console.error("Error while login");
					console.log("errResp = "+errResp);
					$location.path("/error");
				}
				);
	}
	
	$scope.register = function(employee){
		userService.register(employee)
		.then(
				function(successResp){
					console.log("successResp = " + successResp);
					
					if(successResp == true)
						{
							$("#RegisterSuccessModal").modal({backdrop: 'static', keyboard: false});
						}
					else
						{
							$location.path("/fail");
						}
						
					
					//successResp == true ? $location.path("/success") : $location.path("/fail");
					
				},
				function(errResp){
					console.log("errResp = "+errResp);
					$location.path("/error");
				}
				);
	};
	
	$scope.submit = function(){
		$scope.login($scope.employee);
	};
	
	$scope.registration = function(){
		$scope.register($scope.employee);
	};
	
	$scope.acknowledgeRegistration = function(){
		$("#RegisterSuccessModal").modal('hide');
		$location.path("/welcome");
		
	};
	
	$scope.resetRegisterForm = function(){
		$scope.employee = {"firstName" : "", "lastName" : "", "contact" : "", "email" : "", "dob" : "", "gender" : "",
				"userName" : "", "passWord" : "", "confirmPassword" : ""};
	}
	
	$scope.startLogin = function (){
		$location.path("/login");
	}
	
	$scope.startSignUp = function (){
		$location.path("/register");
	}
	
	
}]);

$(document).ready(function (){
	$(".nav li").on("click", function() {
	    $(".nav li").removeClass("active");
	    $(this).addClass("active");
	  })
	});
