/**
 * 
 */
'use strict';

app.factory("albumService",["$http","$q", function($http, $q){
	return{
				
		getAllAlbums : function(){
			
			 return $http.get("http://localhost:8080/FundsCollection/album/getAllAlbums")
		        .then(
					function(successResp){
						if(successResp.data != ""){
							console.log("Fetched all Albums");
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
		getAllImagesFromAlbum : function(albumName){
			
			 return $http.get("http://localhost:8080/FundsCollection/album/getAllImagesFromAlbum",{params : {"name" : albumName}})
		        .then(
					function(successResp){
						if(successResp.data != ""){
							console.log("Fetched all Images");
						} else {
							console.log("Oops !! There is some problem in fetching the names of images...");
						}				
						return successResp.data;
					},
					function(errResp){
						console.error("Error while login");
						return $q.reject(errResp);
					}
					);
		           
		},
		deleteAlbumByAlbumId : function(albumId){
			
			 return $http.post("http://localhost:8080/FundsCollection/album/deleteAlbumByAlbumId",albumId)
		        .then(
					function(successResp){
						if(successResp.data){
							console.log("Deleted The album..");
						} else {
							console.log("Oops !! There is some problem in deleting the album...");
						}				
						return successResp.data;
					},
					function(errResp){
						console.error("Error while deleting the album");
						return $q.reject(errResp);
					}
					);
		           
		},
		
		albumName : "",
		
	}
	
}]);