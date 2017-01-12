package com.aricent.fundscollection.service;

import java.util.List;

import com.aricent.fundscollection.model.Album;

public interface AlbumService {
	
	public List<Album> getAllAlbums();

	public boolean deleteAlbumByAlbumId(long albumId);

}
