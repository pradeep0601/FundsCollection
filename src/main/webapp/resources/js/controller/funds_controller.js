'use strict';

app.controller("fundsController", ["$scope","fundsService","$location",function($scope,fundsService,$location){

	$scope.fundsCycle = {"startDate" : "", "endDate" : "", "sharePerPerson" : ""};
	$scope.startFundCycle = function (){
		console.log($scope.fundsCycle);
		fundsService.startFundCycle($scope.fundsCycle)
		.then(
				function(successResp){
					console.log("successResp = "+successResp);
					
					$location.path("/fundsInfo");
					
				},
				function(errResp){
					console.log("errResp = "+errResp);
					console.error("Error while starting funds cycle");
					$location.path("/error");
				}
				);
	}
	$scope.stopFundCycle = function (fundCycle){
		fundsService.stopFundCycle(fundCycle)
		.then(
				function(successResp){
					console.log("successResp = "+successResp);
					
					$location.path("/fundsInfo");
					
				},
				function(errResp){
					console.log("errResp = "+errResp);
					console.error("Error while stoping funds cycle");
					$location.path("/error");
				}
				);
	}
	
	$scope.fundsRecord = {};
	
	$scope.addFunds = function(fundsRecord){
		fundsRecord.amountRemained = fundsRecord.amountNeedToPay - fundsRecord.actualPaidAmount;
		fundsRecord.payDate = new Date();
		
		fundsService.addFunds(fundsRecord)
		.then(
				function(successResp){
					console.log("funds added successfully");
					$scope.fundDetails.PAIDFUNDRECORDS.push(fundsRecord);
					$scope.fundDetails.FUNDCYCLE.totalBalance = +$scope.fundDetails.FUNDCYCLE.totalBalance + +fundsRecord.actualPaidAmount;
					$scope.fundDetails.FUNDRECORDS = $scope.fundDetails.FUNDRECORDS.filter(function(fr) {
					    return fr.employee.userName !== fundsRecord.employee.userName;
					});
				},
				function(errResp){
					console.log("errResp = "+errResp);
					console.log("Error while adding funds");
				}
				);
	};
	
	function removeFundsRecord(fundsRecord){
		$scope.fundDetails.FUNDRECORDS = $scope.fundDetails.FUNDRECORDS.filter(function(fr) {
		    return el.name !== fundsRecord.employee.userName;
		});
	}
	
	$scope.expenditure = {"eventName" : "", "amountExpended" : "", "eventDate" : "", "description" : "", "fundCycle" : ""};
	
	$scope.addExpenditure = function(){
		$scope.expenditure.fundCycle = $scope.fundDetails.FUNDCYCLE;
		fundsService.addExpenditure($scope.expenditure)
		.then(
				function(successResp){
					console.log("expenditure added successfully!!");
					$scope.fundDetails.EXPENDITURES.push($scope.expenditure);
					$scope.fundDetails.FUNDCYCLE.totalExpenditure = $scope.fundDetails.FUNDCYCLE.totalExpenditure != null ? +$scope.fundDetails.FUNDCYCLE.totalExpenditure + +$scope.expenditure.amountExpended : +$scope.expenditure.amountExpended;
					$scope.fundDetails.FUNDCYCLE.totalBalance = $scope.fundDetails.FUNDCYCLE.totalBalance - $scope.expenditure.amountExpended;
				},
				function(errResp){
					console.log("errResp : "+errResp);
					console.log("Error while adding expenditure");
				}
				);
	}
	
	$scope.reportInput = {"isChecked" : "", "reportBy" : "employeeId", "employee" : "", "fromDate" : "", "toDate" : ""};
	
	$scope.reportBy = function(reportBy){
		$scope.reportInput.reportBy = reportBy;
	}
	$scope.reports = [];
	$scope.getReport = function(){
	var reportParms = {"reportBy" : $scope.reportInput.reportBy, "employeeId" : $scope.reportInput.employee.userName, "fromDate" : $scope.reportInput.fromDate, "toDate" : $scope.reportInput.toDate};
	fundsService.getReport(reportParms)
	.then(
			function(successResp){
				console.log("reports fetched successfully !!");
				$scope.reports = successResp;
			},
			function(errResp){
				console.log("Error while fetching report");
			}
			);
	}
}]);


$(document).ready(function (){
$(".nav li").on("click", function() {
    $(".nav li").removeClass("active");
    $(this).addClass("active");
  })
});