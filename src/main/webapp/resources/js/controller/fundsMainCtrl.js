'use strict';

fundsApp.controller("fundsMainCtrl", ["$scope","fundsMainService","$window","$location",function($scope,fundsMainService,$window,$location){
	
	$scope.fundDetails = [];
	$scope.getFundDetails = function(){
		fundsMainService.getFundDetails()
		.then(
				function(successResp){
					console.log("fundsMainCtrl : getFundDetails : executed successfully!");
					$scope.fundDetails = successResp;
					$scope.changeProgressBar();
				},
				function(errResp){
					console.log("errResp = "+errResp);
					console.error("fundsMainCtrl : getFundDetails : Error while getting fund details ");
				}
				);
	}
	

	$scope.changeProgressBar = function(){
		
		var fundProgressBar = document.getElementById("fundProgressBarId");
		if(fundProgressBar == null){
			return;
		}
		if($scope.fundDetails.FUNDCYCLE.isInProgress){
			fundProgressBar.className = "progress-bar progress-bar-success progress-bar-striped active";
			fundProgressBar.innerHTML = "Funding is in progress..";
		}
		else{
			fundProgressBar.className = "progress-bar progress-bar-danger progress-bar-striped active";
			fundProgressBar.innerHTML = "Funding have been stopped..";
		}
		
	}
	
	 
	 $scope.startLogout = function (){
			var $loginUrl = "http://" + $window.location.host + "/FundsCollection/index.jsp#/login";
			$window.location.href = $loginUrl;
		}
	
}]);
