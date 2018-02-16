package com.social.flickr.persistence.seedInfo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "SeedQueryInfo")
public class SeedQueryInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "photoRefernceId")
	private String photoRefernceId;
	
	@Column(name = "photoLatitude")
	private String photoLatitude;
	
	@Column(name = "photoLongitude")
	private String photoLongitude;
	
	@Column(name = "photoElevation")
	private String photoElevation;
	
	@Column(name = "photoDrop")
	private String photoDrop;

	@Column(name = "photoName")
	private String photoName;

	public String getPhotoRefernceId() {
		return photoRefernceId;
	}

	public void setPhotoRefernceId(String photoRefernceId) {
		this.photoRefernceId = photoRefernceId;
	}

	public String getPhotoLatitude() {
		return photoLatitude;
	}

	public void setPhotoLatitude(String photoLatitude) {
		this.photoLatitude = photoLatitude;
	}

	public String getPhotoLongitude() {
		return photoLongitude;
	}

	public void setPhotoLongitude(String photoLongitude) {
		this.photoLongitude = photoLongitude;
	}

	public String getPhotoElevation() {
		return photoElevation;
	}

	public void setPhotoElevation(String photoElevation) {
		this.photoElevation = photoElevation;
	}

	public String getPhotoDrop() {
		return photoDrop;
	}

	public void setPhotoDrop(String photoDrop) {
		this.photoDrop = photoDrop;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	
	public SeedQueryInfo(String photoRefernceId, String photoLatitude,
			String photoLongitude, String photoElevation, String photoDrop,
			String photoName) {
		super();
		this.photoRefernceId = photoRefernceId;
		this.photoLatitude = photoLatitude;
		this.photoLongitude = photoLongitude;
		this.photoElevation = photoElevation;
		this.photoDrop = photoDrop;
		this.photoName = photoName;
	}

	public SeedQueryInfo() {
		//super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String toString() {
		return "SeedQueryInfo [photoRefernceId=" + photoRefernceId
				+ ", photoLatitude=" + photoLatitude + ", photoLongitude="
				+ photoLongitude + ", photoElevation=" + photoElevation
				+ ", photoDrop=" + photoDrop + ", photoName=" + photoName + "]";
	}

	
	
}
