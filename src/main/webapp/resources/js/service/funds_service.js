'use strict';

app.factory("fundsService",["$http","$q", function($http, $q){
	return{
		
	    startFundCycle : function(fundCycle){
	    	return $http.post("http://localhost:8080/FundsCollection/funds/startFundCycle", fundCycle)
	    	.then(
	    			function(successResp){
	    				return successResp.data;
	    			},
	    			function(errResp){
	    				return $q.reject(errResp);
	    			}
	    			);
	    },
	    stopFundCycle : function(fundCycle){
	    	return $http.post("http://localhost:8080/FundsCollection/funds/stopFundCycle", fundCycle)
	    	.then(
	    			function(successResp){
	    				return successResp;
	    			},
	    			function(errResp){
	    				return $q.reject(errResp);
	    			}
	    			);
	    },
	    getFundDetails : function(){
		return $http.get("http://localhost:8080/FundsCollection/funds/fundDetails")
		.then(
				function(successResp){
					return successResp.data;
				},
				function(errResp){
					return $q.reject(errResp);
				}
				);
	},
	
	addFunds : function(fundsRecord){
		return $http.post("http://localhost:8080/FundsCollection/funds/addFunds", fundsRecord)
		.then(
				function(successResp){
					return successResp.data;
				},
				function(errResp){
					return $q.reject(errResp);
				}
				);
	},
	addExpenditure : function(expenditure){
		return $http.post("http://localhost:8080/FundsCollection/funds/expenditure", expenditure)
		.then(
				function(successResp){
					return successResp.data;
				},
				function(errResp){
					return $q.reject(errResp);
				}
				);
	},
	getReport : function(reportParms){
		return $http.post("http://localhost:8080/FundsCollection/funds/reports", reportParms)
		.then(
				function(successResp){
					return successResp.data;
				},
				function(errResp){
					return $q.reject(errResp);
				}
				);
	}  
	}
	
}]);