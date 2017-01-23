'use strict';

fundsApp.controller("fundsController", ["$scope","fundsService","$location",function($scope,fundsService,$location){
	$scope.$parent.changeProgressBar();
	$scope.fundsCycle = {"startDate" : "", "endDate" : "", "sharePerPerson" : ""};
	$scope.startFundCycle = function (){
		console.log($scope.fundsCycle);
		fundsService.startFundCycle($scope.fundsCycle)
		.then(
				function(successResp){
					console.log("successResp = "+successResp);
					$scope.$parent.getFundDetails();
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
					$scope.$parent.getFundDetails();
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
	
	$scope.addFunds = function(newRecord){
		newRecord.amountRemained = newRecord.amountNeedToPay - newRecord.actualPaidAmount;
		newRecord.payDate = new Date();
		
		fundsService.addFunds(newRecord)
		.then(
				function(successResp){
					console.log("funds added successfully");
					newRecord.recordId = successResp;
					$scope.fundDetails.PAIDFUNDRECORDS.push(newRecord);
					$scope.fundDetails.FUNDCYCLE.totalBalance = +$scope.fundDetails.FUNDCYCLE.totalBalance + +newRecord.actualPaidAmount;
					$scope.fundDetails.FUNDCYCLE.totalCollection = +$scope.fundDetails.FUNDCYCLE.totalCollection + +newRecord.actualPaidAmount;
					$scope.fundDetails.FUNDRECORDS = $scope.fundDetails.FUNDRECORDS.filter(function(fr) {
					    return fr.employee.userName !== newRecord.employee.userName;
					});
				},
				function(errResp){
					console.log("errResp = "+errResp);
					console.log("Error while adding funds");
					$location.path("/error");
				}
				);
	};
	
	function removeFundsRecord(fundsRecord){
		$scope.fundDetails.FUNDRECORDS = $scope.fundDetails.FUNDRECORDS.filter(function(fr) {
		    return el.name !== fundsRecord.employee.userName;
		});
	}
	$scope.edit = function(type, id){
		console.log('==type and id to be edited : type = ,'+type+", id = "+id);
		switch(type){
		case "fundsRecord":
			 for(var i = 0; i < $scope.fundDetails.PAIDFUNDRECORDS.length; i++){
				 if($scope.fundDetails.PAIDFUNDRECORDS[i].recordId == id){
					 $scope.fundsRecord = angular.copy($scope.fundDetails.PAIDFUNDRECORDS[i]);
					 $scope.fundsRecord.amountRemained = $scope.fundsRecord.amountNeedToPay - $scope.fundDetails.FUNDCYCLE.sharePerPerson;
					 var isRecordPresent = $scope.containsObject($scope.fundDetails.PAIDFUNDRECORDS[i], $scope.fundDetails.FUNDRECORDS);
					 if(!isRecordPresent){
						 $scope.fundDetails.FUNDRECORDS.push($scope.fundsRecord);
					 }
					 break;
				 }
			 }
			 break;
		case "expenditure":
			for(var i = 0; i < $scope.fundDetails.EXPENDITURES.length; i++){
				if($scope.fundDetails.EXPENDITURES[i].expndId == id){
					$scope.expenditure = angular.copy($scope.fundDetails.EXPENDITURES[i]);
					$scope.expenditure.eventDate = new Date($scope.expenditure.eventDate);
					break;
				}
			}
		}
	}
	

	//utility to check existence of an object in array
	$scope.containsObject = function(obj, list) {
		
	    for (var i = 0; i < list.length; i++) {
	        if (angular.equals(list[i], obj)) {
	            return true;
	        }
	    }

	    return false;
	};
	
	function updateFR(updatedRecord){
		updatedRecord.amountRemained = updatedRecord.amountNeedToPay - updatedRecord.actualPaidAmount;
		updatedRecord.payDate = new Date();
		
		fundsService.updateFR(updatedRecord)
		.then(
				function(successResp){
					console.log("funds updated successfully");
					for(var i = 0; i < $scope.fundDetails.PAIDFUNDRECORDS.length; i++){
						if($scope.fundDetails.PAIDFUNDRECORDS[i].recordId == updatedRecord.recordId){
							$scope.fundDetails.PAIDFUNDRECORDS[i].amountRemained = 	updatedRecord.amountRemained;
							$scope.fundDetails.PAIDFUNDRECORDS[i].payDate = updatedRecord.payDate;
							var payDiff = updatedRecord.actualPaidAmount - $scope.fundDetails.PAIDFUNDRECORDS[i].actualPaidAmount;
							$scope.fundDetails.FUNDCYCLE.totalBalance = +$scope.fundDetails.FUNDCYCLE.totalBalance + +payDiff;
							$scope.fundDetails.FUNDCYCLE.totalCollection = +$scope.fundDetails.FUNDCYCLE.totalCollection + +payDiff;
							$scope.fundDetails.PAIDFUNDRECORDS[i].actualPaidAmount = updatedRecord.actualPaidAmount;
							break;
						}
					}
					$scope.fundDetails.FUNDRECORDS = $scope.fundDetails.FUNDRECORDS.filter(function(fr) {
					    return fr.employee.userName !== updatedRecord.employee.userName;
					});
				},
				function(errResp){
					console.log("errResp = "+errResp);
					console.log("Error while updating funds");
					$location.path("/error");
				}
				);
	}
	
	
	$scope.remove = function(type, id){
		console.log("====fundsController : remove(-, -) type ="+type+",===id ="+id);
		var removeParams = {type : type, id : id};
		 
			 fundsService.remove(removeParams).then(
						function(successResp){
							console.log("record with type = "+type+", id = "+id+" deleted successfully!!");
							switch(type){
							case "fundsRecord":	
							for(var i = 0; i < $scope.fundDetails.PAIDFUNDRECORDS.length; i++){
								if($scope.fundDetails.PAIDFUNDRECORDS[i].recordId == id){
									var fundsRecord = angular.copy($scope.fundDetails.PAIDFUNDRECORDS[i]);
									fundsRecord.recordId = null;
									fundsRecord.amountNeedToPay = +fundsRecord.amountRemained + +fundsRecord.actualPaidAmount
									fundsRecord.amountRemained =  +fundsRecord.amountNeedToPay - +$scope.fundDetails.FUNDCYCLE.sharePerPerson;
									fundsRecord.actualPaidAmount = "";
									$scope.fundDetails.FUNDRECORDS.push(fundsRecord);
									
									$scope.fundDetails.FUNDCYCLE.totalBalance = +$scope.fundDetails.FUNDCYCLE.totalBalance - +$scope.fundDetails.PAIDFUNDRECORDS[i].actualPaidAmount;
									$scope.fundDetails.FUNDCYCLE.totalCollection = +$scope.fundDetails.FUNDCYCLE.totalCollection - +$scope.fundDetails.PAIDFUNDRECORDS[i].actualPaidAmount;
									
									$scope.fundDetails.PAIDFUNDRECORDS.splice(i,1);
									
									break;
								}
							}
							break;
							case "expenditure":
								for(var i = 0; i < $scope.fundDetails.EXPENDITURES.length; i++){
									if($scope.fundDetails.EXPENDITURES[i].expndId == id){
										$scope.fundDetails.FUNDCYCLE.totalBalance = +$scope.fundDetails.FUNDCYCLE.totalBalance + +$scope.fundDetails.EXPENDITURES[i].amountExpended;
										$scope.fundDetails.FUNDCYCLE.totalExpenditure = +$scope.fundDetails.FUNDCYCLE.totalExpenditure - +$scope.fundDetails.EXPENDITURES[i].amountExpended;
										$scope.fundDetails.EXPENDITURES.splice(i,1);
										break;
									}
								}
						 }
						},
						function(errResp){
							console.log("Error while deleting record with type = "+type+", id = "+id);
							$location.path("/error");
						}
					 );
	}
	
	
	//$scope.expenditure = {"eventName" : "", "amountExpended" : "", "eventDate" : "", "description" : "", "fundCycle" : ""};
	$scope.expenditure = {};
	$scope.addExpenditure = function(expenditure){
		expenditure.fundCycle = $scope.fundDetails.FUNDCYCLE;
		fundsService.addExpenditure(expenditure)
		.then(
				function(successResp){
					console.log("expenditure added successfully!!");
					expenditure.expndId = successResp;
					$scope.fundDetails.EXPENDITURES.push(expenditure);
					$scope.fundDetails.FUNDCYCLE.totalExpenditure = $scope.fundDetails.FUNDCYCLE.totalExpenditure != null ? +$scope.fundDetails.FUNDCYCLE.totalExpenditure + +expenditure.amountExpended : +expenditure.amountExpended;
					$scope.fundDetails.FUNDCYCLE.totalBalance = $scope.fundDetails.FUNDCYCLE.totalBalance - expenditure.amountExpended;
					$scope.expenditure = {};
					$location.path("/expenditure");
				},
				function(errResp){
					console.log("errResp : "+errResp);
					console.log("Error while adding expenditure");
					$location.path("/error");
				}
				);
	}
	function updateExpenditure(expenditure){
		fundsService.updateExpnd(expenditure)
		.then(
				function(successResp){
					console.log("expenditure updated successfully");
					for(var i = 0; i < $scope.fundDetails.EXPENDITURES.length; i++){
						if($scope.fundDetails.EXPENDITURES[i].expndId == expenditure.expndId){
							var expndDiff = +expenditure.amountExpended - +$scope.fundDetails.EXPENDITURES[i].amountExpended;
							$scope.fundDetails.FUNDCYCLE.totalExpenditure = +$scope.fundDetails.FUNDCYCLE.totalExpenditure + +expndDiff;
							$scope.fundDetails.FUNDCYCLE.totalBalance = +$scope.fundDetails.FUNDCYCLE.totalBalance - +expndDiff;
							
							$scope.fundDetails.EXPENDITURES[i] = expenditure;
							$scope.expenditure = {};
						}
					}
				},
				function(errResp){
					console.log("errResp : "+errResp);
					console.log("Error while updating expenditure");
					$location.path("/error");
				}
		);
	}
	$scope.resetExpenditure = function(){
		$scope.expenditure = {};
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
				$location.path("/error");
			}
			);
	}
	
	$scope.submit = function(type){
		switch(type){
		case "fundsRecord":
			if($scope.fundsRecord.recordId == null){
				$scope.addFunds($scope.fundsRecord);
			}
			else{
				
				updateFR($scope.fundsRecord);
			}
			break;
		case "expenditure":
			if($scope.expenditure.expndId == null){
				$scope.addExpenditure($scope.expenditure);
			}
			else{
				
				updateExpenditure($scope.expenditure);
			}
			break;
			
		}
		
	}	
	//to sort
	 $scope.orderBy = function(x){
	    	$scope.myOrder = x;
	    }
	
}]);

fundsApp.directive('ngReallyClick', [function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            element.bind('click', function() {
                var message = attrs.ngReallyMessage;
                if (message && confirm(message)) {
                    scope.$apply(attrs.ngReallyClick);
                }
            });
        }
    }
}]);

$(document).ready(function (){
$(".nav li").on("click", function() {
    $(".nav li").removeClass("active");
    $(this).addClass("active");
  })
});