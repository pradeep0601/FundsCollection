package com.aricent.fundscollection.dao;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.aricent.fundscollection.model.Album;

@Repository
public class AlbumDAOImpl implements AlbumDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
        return entityManager;
    }
	
	 public void setEntityManager(EntityManager entityManager) {
	        this.entityManager = entityManager;
	    }

	@Override
	public boolean createAlbum(Album album) {
		
		try
		{
			entityManager.persist(album);
		}
		catch(Exception ex)
		{
			return false;
		}
		return true;		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Album> getAllAlbums() {
		
		return entityManager.createNamedQuery("findAllAlbums").getResultList(); 
	}

	@Override
	public Album getAlbumByAlbumName(String name) {
		
		return (Album) entityManager.createNamedQuery("findAlbumByAlbumName").setParameter("name", name).getResultList().get(0);
	}

	@Override
	public boolean deleteAlbumByAlbumId(long albumId) {
		
		Album album = new Album();
		try
		{
			album = (Album) entityManager.createNamedQuery("findAlbumByAlbumId").setParameter("albumId", albumId).getSingleResult();
			
			if(album.getName() != null && album.getAbsolutePath() !="")
			{
				File[] files = new File(album.getAbsolutePath()).listFiles();
				 for (File temp : files) {
					 temp.delete();
	        	   }
				 File dir = new File(album.getAbsolutePath());
				 
				 if(dir.delete())
				 {
					 entityManager.remove(album);
				 }
				 else
				 {
					 return false;
				 }
			}
			
		}
		catch(Exception e)
		{
			return false;
		}
		
		return true;
	}

}
