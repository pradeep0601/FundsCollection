package com.aricent.fundscollection.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aricent.fundscollection.dao.AlbumDAO;
import com.aricent.fundscollection.model.Album;


@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {
	
	private AlbumDAO albumDAO;
	
	@Autowired
	public FileUploadServiceImpl(AlbumDAO albumDAO) {
	    this.albumDAO = albumDAO;
	}

	@Override
	public boolean createAlbum(String name, String description, String absolutePath, String albumThumbnail) {
		
		Album album = new Album();
		album.setName(name);
		album.setDescription(description);
		album.setAbsolutePath(absolutePath);
		album.setAlbumThumbnail(albumThumbnail);
		
		if(albumDAO.createAlbum(album))
		{
			return true;
		}
		return false;
	}

	@Override
	public List<String> getAllImagesFromAlbum(String albumName) {
		
		Album album = new Album();
		album = albumDAO.getAlbumByAlbumName(albumName);
		
		
		String albumDirPath = album.getAbsolutePath();
		
		List<String> results = new ArrayList<>();


		File[] files = new File(albumDirPath).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 
		
		if(files == null)
		{
			return results;
		}

		for (File file : files) {
		    if (file.isFile()) {
		        results.add(file.getName());
		    }
		}
		
		return results;
	}

}
