package com.aricent.fundscollection.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.aricent.fundscollection.model.Album;
import com.aricent.fundscollection.service.AlbumService;
import com.aricent.fundscollection.service.FileUploadService;

@Controller
@RequestMapping("/album")
public class AlbumController {
	
	
	private AlbumService albumService;
	private FileUploadService fileUploadService;
	
	@Autowired
	public AlbumController(AlbumService albumService,FileUploadService fileUploadService) {
	    this.albumService = albumService;
	    this.fileUploadService = fileUploadService;
	}
	
	@RequestMapping(value = "/getAllAlbums", method = RequestMethod.GET)
	public @ResponseBody List<Album> getAllAlbums() {

		List<Album> albums = new ArrayList<>();

		albums = albumService.getAllAlbums();

		return albums;
	}
	
	@RequestMapping(value = "/getAllImagesFromAlbum", method = RequestMethod.GET)
	public @ResponseBody List<String> getAllImagesFromAlbum(HttpServletRequest request) {

		List<String> images = new ArrayList<>();

		images = fileUploadService.getAllImagesFromAlbum(request.getParameter("name"));

		return images;
	}
	
	@RequestMapping(value = "/deleteAlbumByAlbumId", method = RequestMethod.POST)
	public @ResponseBody boolean deleteAlbumByAlbumId(@RequestBody long albumId) {

		List<String> images = new ArrayList<>();

		return albumService.deleteAlbumByAlbumId(albumId);
 
	}
}
