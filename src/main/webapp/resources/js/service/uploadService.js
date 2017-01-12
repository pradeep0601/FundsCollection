/**
 * 
 */
'use strict';

app.factory("uploadService",["$http","$q", function($http, $q){
	return{
		createAlbum : function(data){
			
			var config = {	transformRequest: angular.identity,
							headers: { 'Content-Type': undefined }
						 };
			
		       return $http.post("http://localhost:8080/FundsCollection/upload/uploadMultipleFile", data, config)
		        .then(
					function(successResp){
						if(successResp.data){
							console.log("Your Album is Created and Files Uploaded Successfully..");
						} else {
							console.log("Oops !! Your Album is not created..");
						}				
						return successResp.data;
					},
					function(errResp){
						console.error("Error while login");
						return $q.reject(errResp);
					}
					);
		           
		},
		
		uploadFiles : function (formData) {
			
			var request = new XMLHttpRequest();
			request.open('POST', "http://localhost:8080/FundsCollection/upload/uploadMultipleFile");
			request.send(formData);
		}
	}
	
}]);