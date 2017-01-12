package com.aricent.fundscollection.dao;

import java.util.List;

import com.aricent.fundscollection.model.Album;

public interface AlbumDAO {
	
	public boolean createAlbum(Album album);
	
	public List<Album> getAllAlbums();
	
	public Album getAlbumByAlbumName(String name);

	public boolean deleteAlbumByAlbumId(long albumId);
	

}
