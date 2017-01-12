package com.aricent.fundscollection.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aricent.fundscollection.dao.AlbumDAO;
import com.aricent.fundscollection.model.Album;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

	private AlbumDAO albumDAO;
	
	@Autowired
	public AlbumServiceImpl(AlbumDAO albumDAO) {
	    this.albumDAO = albumDAO;
	}

	@Override
	public List<Album> getAllAlbums() {

		return albumDAO.getAllAlbums();
	}

	@Override
	public boolean deleteAlbumByAlbumId(long albumId) {
		
		
		return albumDAO.deleteAlbumByAlbumId(albumId);
	}
	
	
}
