'use strict';

fundsApp.factory("fundsMainService",["$http","$q", function($http, $q){
	return{
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
			}
}}]);