package com.aricent.fundscollection.service;

import java.util.List;

public interface FileUploadService {
	
	public boolean createAlbum(String name,String description,String absolutepath, String albumThumbnail);
	
	public List<String> getAllImagesFromAlbum(String albumName);

}
