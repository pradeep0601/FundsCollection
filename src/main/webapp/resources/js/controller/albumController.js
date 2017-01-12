
'use strict';



app.controller('albumController', ["$scope","$location","albumService",function ($scope,$location,albumService) {

	$scope.Service = albumService;
	
	getAllAlbums();
		
	function getAllAlbums(){
		
		albumService.getAllAlbums()
		.then(
				function(successResp){
					console.log("successResp = "+successResp);
					
					$scope.albums = successResp;
					
				},
				function(errResp){
					console.log("errResp = "+errResp);
					$location.path("/error");
				}
				);
	};
	
	
	$scope.loadAlbumImages = function(albumName){
		
		$scope.Service.albumName = albumName;
		$location.path("/browseAlbum");
		
	}
		
		$scope.initiateDelete = function(album){
			$('#'+ album.albumId + 'AlbumDeleteModal').modal('show');
		}
		
		$scope.deleteAlbum = function (album) {
			
			albumService.deleteAlbumByAlbumId(album.albumId)
			.then(
	        		 function(successResp){

	        			 if(successResp)
	        			 {
	        				 console.log("successResp = "+ successResp);

	        				 $("#" + album.albumId + "AlbumDeleteModal").modal('hide');

	        				 $("#" + album.albumId + "AlbumDeleteModal").on('hidden.bs.modal', function (e) {
	        					 $("#AlbumDeleteSuccessModal").modal('show');

	        				 });
	        			 }
	        			 else
	        			 {
	        				 console.log("successResp = "+ successResp);      				 
	        				
	        			 }

	        		 },
					function(errResp){
						console.log("errResp = "+errResp);
						$location.path("/error");
					}
					);  
		}

}  
]);

app.controller('albumGalleryController', ["$scope","$location","albumService",function ($scope,$location,albumService) {

	$scope.sharedService = albumService;
	$scope.album = $scope.sharedService.albumName;
	
	$scope.baseSrc = "/resources/albums/" + $scope.album + "/";
	
	
	
	 function loadAlbumImages(albumName){
		
		albumService.getAllImagesFromAlbum(albumName)
		.then(
				function(successResp){
					console.log("successResp = "+successResp);
					
					//$location.path("/browseAlbum");
					
					$scope.images = successResp;
					
				},
				function(errResp){
					console.log("errResp = "+errResp);
					$location.path("/error");
				}
				);				
	}
	 
	 loadAlbumImages($scope.album);
	 
	 $scope.openPhotoBrowseModal = function(album,image,images){
		 
		 $('#photo-browse-modal').modal('show');
		 var fullImg = document.getElementById("imageModal");
		 fullImg.setAttribute("src", "http://localhost:8080/FundsCollection/resources/albums/" + album + "/" + image );
		 fullImg.setAttribute("class", "img-responsive");
		 fullImg.setAttribute("alt", image);
		 fullImg.setAttribute("width", "870");
		 fullImg.setAttribute("height", "600");
		 document.getElementById("previous-btn").disabled = false;
		 document.getElementById("next-btn").disabled = false;
		 var i = images.indexOf(image);
		 if(i==0)
		 {
			 document.getElementById("previous-btn").disabled = true;
			 document.getElementById("next-btn").disabled = false;
		 }
		 if(i == images.length-1)
		 {
			 document.getElementById("previous-btn").disabled = false;
			 document.getElementById("next-btn").disabled = true;
		 }

	 }
	 
	 $scope.nextPhoto = function(album,images){
		 console.log(album,images);
		 var fullImg = document.getElementById("imageModal");
		 var currentImage = fullImg.getAttribute("alt");
		 var i = images.indexOf(currentImage);
		 
		 switch(i+1)
		 {
		 case images.length-1:
			 document.getElementById("next-btn").disabled = true;
			 break;
		 case 0:
			 document.getElementById("previous-btn").disabled = true;
			 break;
		 default:
			 document.getElementById("previous-btn").disabled = false;
		 	document.getElementById("next-btn").disabled = false;
			 
		 }
		
			 fullImg.setAttribute("src", "http://localhost:8080/FundsCollection/resources/albums/" + album + "/" + images[i+1] );
			 fullImg.setAttribute("class", "img-responsive");
			 fullImg.setAttribute("alt", images[i+1]);
			 fullImg.setAttribute("width", "870");
			 fullImg.setAttribute("height", "600");

		 console.log(currentImage,i);
	 }
	 
	 $scope.previousPhoto = function(album,images){
		 console.log(album,images);
		 var fullImg = document.getElementById("imageModal");
		 var currentImage = fullImg.getAttribute("alt");
		 var i = images.indexOf(currentImage);
		 switch(i-1)
		 {
		 case images.length-1:
			 document.getElementById("next-btn").disabled = true;
			 break;
		 case 0:
			 document.getElementById("previous-btn").disabled = true;
			 break;
		 default:
			 document.getElementById("previous-btn").disabled = false;
		 	document.getElementById("next-btn").disabled = false;
			 
		 }
		
				 	 
			 fullImg.setAttribute("src", "http://localhost:8080/FundsCollection/resources/albums/" + album + "/" + images[i-1] );
			 fullImg.setAttribute("alt", images[i-1]);
			 fullImg.setAttribute("width", "870");
			 fullImg.setAttribute("height", "600");

		 console.log(currentImage,i);
	 }

}  
]);
