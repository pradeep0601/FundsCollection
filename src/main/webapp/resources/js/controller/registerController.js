'use strict';

var compareTo = function() {
    return {
      require: "ngModel",
      scope: {
        otherModelValue: "=compareTo"
      },
      link: function(scope, element, attributes, ngModel) {

        ngModel.$validators.compareTo = function(modelValue) {
          return modelValue == scope.otherModelValue;
        };

        scope.$watch("otherModelValue", function() {
          ngModel.$validate();
        });
      }
    };
  };

var checkUnique = function(userService) {
	  return {
		    restrict: 'A',
		    require: 'ngModel',
		    link: function (scope, element, attrs, ngModel) {
		    	ngModel.$asyncValidators.unique =  function (e) {
		        if (!ngModel || !element.val()) return;
		        var currentValue = element.val();
		        return userService.checkUniqueValue(currentValue)
		          .then(function (unique) {
		            //Ensure value that being checked hasn't changed
		            //since the Ajax call was made
		            if (currentValue == element.val()) {
		              console.log('unique = ' + unique);
		              ngModel.$setValidity('uniqueUser', unique);
		            }
		          });
		      };
		    }
		  };
		};

app.controller("registerController", ["$scope","userService","$location",function($scope,userService,$location){

	$scope.employee = {"firstName" : "", "lastName" : "", "contact" : "", "email" : "", "dob" : "", "gender" : "",
						"userName" : "", "passWord" : "", "confirmPass" : ""};
	$scope.registrationForm = { };
		
	$scope.register = function(employee){
		return userService.register(employee)
		.then(
				function(successResp){
					console.log("successResp = " + successResp);
					
					if(successResp == true)
						{
							$("#RegisterSuccessModal").modal({backdrop: 'static', keyboard: false});
							console.log("registerController :: Employee registration is completed successfully.");
						}
					else
						{
							$location.path("/fail");
						}
		
				},
				function(errResp){
					console.log("errResp = "+errResp);
					$location.path("/error");
				}
				);
	};
	
	$scope.registration = function(){
		$scope.register($scope.employee);
	};
	
	$scope.acknowledgeRegistration = function(){
		$("#RegisterSuccessModal").modal('hide');
		$('.modal-backdrop').remove();
		$location.path("/login");
	};
	
	$scope.startLogin = function (){
		$location.path("/login");
	}
	
	$scope.startSignUp = function (){
		$location.path("/register");
	}
}]);

app.directive("compareTo", compareTo);
app.directive("uniqueUser", checkUnique);

$(document).ready(function (){
	    $("#empId").popover('show');
	});