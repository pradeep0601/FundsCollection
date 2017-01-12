package com.aricent.fundscollection.controller;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aricent.fundscollection.service.FileUploadService;

/**
 * Handles requests for the application file upload requests
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {
	
	private final String EMPTY_STRING = "";

	private FileUploadService fileuploadService;
	
	@Autowired
	public FileUploadController(FileUploadService fileuploadService) {
	    this.fileuploadService = fileuploadService;
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody
	boolean uploadMultipleFileHandler(MultipartHttpServletRequest request,@RequestParam("name") String name,@RequestParam("description") String description) {
		
	
        Map<String,MultipartFile> fileMap = request.getFileMap();
        
        if(fileMap.isEmpty())
        {
        	return false;
        }
             
        boolean isSuccessfullyUploaded = true;
        
        String rootPath = "C:\\Users\\srajsriv\\Documents\\AJS-Project\\FundsCollection\\src\\main\\webapp\\resources" + File.separator + "albums" + File.separator + name;
        
        String albumThumbnail = EMPTY_STRING;
        
        for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", MultipartFile = " + entry.getValue().getOriginalFilename());
            
            MultipartFile file = entry.getValue();
            
            if(albumThumbnail == EMPTY_STRING)
            {
            	albumThumbnail = entry.getValue().getOriginalFilename();
            }
                     
            try {
				// Creating the directory to store file
				
				File dir = new File(rootPath);
				
				if (!dir.exists()){
					dir.mkdirs();
				}

				//Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				file.transferTo(serverFile);
				
				System.out.println("Server File Location="	+ serverFile.getAbsolutePath());

			} catch (Exception e) {
				isSuccessfullyUploaded = false;
			}          
        }
        
        fileuploadService.createAlbum(name, description, rootPath, albumThumbnail);
        
		return isSuccessfullyUploaded;
	}
}