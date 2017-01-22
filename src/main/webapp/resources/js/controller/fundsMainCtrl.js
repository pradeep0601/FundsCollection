'use strict';

fundsApp.controller("fundsMainCtrl", ["$scope","fundsMainService",function($scope,fundsMainService){
	
	$scope.fundDetails = [];
	$scope.getFundDetails = function(){
		fundsMainService.getFundDetails()
		.then(
				function(successResp){
					console.log("fundsMainCtrl : getFundDetails : executed successfully!");
					$scope.fundDetails = successResp;
					
				},
				function(errResp){
					console.log("errResp = "+errResp);
					console.error("fundsMainCtrl : getFundDetails : Error while getting fund details ");
				}
				);
	}
	
}]);
