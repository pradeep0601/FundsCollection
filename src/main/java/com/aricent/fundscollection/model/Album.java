package com.aricent.fundscollection.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ALBUMS database table.
 * 
 */

@Entity
@Table(name="ALBUMS")
@NamedQueries({
	@NamedQuery(name = "findAllAlbums", query = "SELECT a FROM Album a"),
	@NamedQuery(name = "findAlbumByAlbumName", query="SELECT a FROM Album a where a.name = :name"),
	@NamedQuery(name = "findAlbumByAlbumId", query = "SELECT a FROM Album a where a.albumId = :albumId")})

public class Album implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ALBUM_ID")
	long albumId;

	@Column(name="ALBUM_DESCRIPTION")
	String description;

	@Column(name="ALBUM_NAME")
	String name;

	@Column(name="ALBUM_RESOURCES_PATH")
	String absolutePath;

	@Column(name="ALBUM_THUMBNAIL")
	String albumThumbnail;
	
	public Album() {
		System.out.println(" Initializing Album()... ");
	}

	public Album(long albumId, String description, String name, String absolutePath, String albumThumbnail) {
		super();
		this.albumId = albumId;
		this.description = description;
		this.name = name;
		this.absolutePath = absolutePath;
		this.albumThumbnail = albumThumbnail;
	}

	public long getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbsolutePath() {
		return this.absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public String getAlbumThumbnail() {
		return albumThumbnail;
	}

	public void setAlbumThumbnail(String albumThumbnail) {
		this.albumThumbnail = albumThumbnail;
	}

}