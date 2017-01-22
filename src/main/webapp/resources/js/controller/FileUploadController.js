/**
 * 
 */
'use strict';



fundsApp.controller('FileUploadController', ["$scope","$window","uploadService","$location",function ($scope,$window,uploadService,$location) {

	$scope.album = {"name":"" , "description" : "", "files" : ""}
	$scope.percentage = 0;
	
    // NOW UPLOAD THE FILES.
    $scope.uploadFiles = function (album) {
    	
    	 var data = new FormData();
    	 
    	 angular.forEach(album.files, function (value, key) {
             data.append(key, value);
         });
    	 data.append('name', album.name);
    	 data.append('description',album.description);

         uploadService.createAlbum(data)
         .then(
        		 function(successResp){

        			 if(successResp)
        			 {
        				 console.log("successResp = "+ successResp);

        				 $scope.album = {"name":"" , "description" : "", "files" : ""};
        				 $('.preview-area').empty();
        			     angular.element("input[type='file']").val(null);
        				 
        				 $("#FileUploadModal").modal('hide');

        				 $("#FileUploadModal").on('hidden.bs.modal', function (e) {
        					 $("#AlbumSuccessModal").modal('show');

        				 });
        				 $window.location.reload();
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
    
    
    
    $scope.clearFiles = function(album){
    	
    	$scope.album = {"name":"" , "description" : "", "files" : ""};
        $('.preview-area').empty();
    	angular.element("input[type='file']").val(null);
    }
}]);

fundsApp.directive('ngFiles', ['$parse', function ($parse) {
	 return {
	        restrict: 'A',
	        link: function (scope, element, attrs) {
	            var model = $parse(attrs.ngFiles);
	            var isMultiple = attrs.multiple;
	            var modelSetter = model.assign;
	            element.bind('change', function () {
	                var values = [];
	                angular.forEach(element[0].files, function (item) {
	                    var value = {
	                       // File Name 
	                        name: item.name,
	                        //File Size 
	                        size: item.size,
	                        //File URL to view 
	                        url: URL.createObjectURL(item),
	                        // File Input Value 
	                        _file: item
	                    };
	                    values.push(value);
	                });
	                scope.$apply(function () {
	                    if (isMultiple) {
	                        modelSetter(scope, element[0].files);
	                    } else {
	                        modelSetter(scope, element[0].files[0]);
	                    }
	                });
	            });
	        }
	    };
} ]);

var openModal = function() {

	$("#FileUploadModal").modal({keyboard: false});
}

function previewFile() {
	  var preview = document.getElementById('preview');
	  var fileList   = document.querySelector('input[type=file]').files;
	  

	  var anyWindow = window.URL || window.webkitURL;

      for(var i = 0; i < fileList.length; i++){
        //get a blob to play with
        var objectUrl = anyWindow.createObjectURL(fileList[i]);
        // for the next line to work, you need something class="preview-area" in your html
        $('.preview-area').append('<span><img height="54" width="84" class="thumbnail" src="' + objectUrl + '" /></span>');
        // get rid of the blob
        window.URL.revokeObjectURL(fileList[i]);
      }
	}