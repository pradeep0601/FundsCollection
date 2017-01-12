'use strict';

app.controller("fundsMainCtrl", ["$scope","fundsMainService",function($scope,fundsMainService){
	
	$scope.fundDetails = [];
	$scope.getFundDetails = function(){
		fundsMainService.getFundDetails()
		.then(
				function(successResp){
					console.log("successResp = "+successResp);
					$scope.fundDetails = successResp;
					
				},
				function(errResp){
					console.log("errResp = "+errResp);
					console.error("Error while getting fund details ");
				}
				);
	}
	
}]);
