'use strict';

app.factory("userService",["$http","$q", function($http, $q){
	return{
		login : function(employee){
			return $http.post("http://localhost:8080/FundsCollection/user/login", employee)
			.then(
					function(successResp){
						return successResp.data;
					},
					function(errResp){
						console.error("Error while login");
						return $q.reject(errResp);
					}
					);
		},
		
		register : function(employee){
			return $http.post("http://localhost:8080/FundsCollection/user/register", employee)
			.then(
					function(successResp){
						return successResp.data;
					},
					function(errResp){
						console.error("Error while Registering");
						return $q.reject(errResp);
					}
					);
		},
		
		checkUniqueValue : function(value) {
	           return $http.post("http://localhost:8080/FundsCollection/user/isUniqueValue", value)
	          .then( 
	        		  function(res) {
	        			  return res.data;
	          }
	          );
	        }
	}}]);