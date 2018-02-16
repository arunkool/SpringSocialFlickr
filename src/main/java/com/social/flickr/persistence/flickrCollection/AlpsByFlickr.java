package com.social.flickr.persistence.flickrCollection;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model Entity of the AlpsByFlickr relation
 */
@Entity
@Table(name = "AlpsByFlickr")
public class AlpsByFlickr implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "photoId")
	private String photoId;
	
	@Column(name = "photoRefernceId")
	private String photoRefernceId;
	
	@Column(name = "photoLatitude")
	private double photoLatitude;
	
	@Column(name = "photoLongitude")
	private double photoLongitude;
	
	
	@Column(name = "photoAccuracy")
	private int photoAccuracy;

	@Column(name = "photoUrl")
	private String photoUrl;

	
	public String getPhotoId() {
		return photoId;
	}


	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}


	public String getPhotoRefernceId() {
		return photoRefernceId;
	}


	public void setPhotoRefernceId(String photoRefernceId) {
		this.photoRefernceId = photoRefernceId;
	}


	public double getPhotoLatitude() {
		return photoLatitude;
	}


	public void setPhotoLatitude(float photoLatitude) {
		this.photoLatitude = photoLatitude;
	}


	public double getPhotoLongitude() {
		return photoLongitude;
	}


	public void setPhotoLongitude(float photoLongitude) {
		this.photoLongitude = photoLongitude;
	}


	public int getPhotoAccuracy() {
		return photoAccuracy;
	}


	public void setPhotoAccuracy(int photoAccuracy) {
		this.photoAccuracy = photoAccuracy;
	}


	public String getPhotoUrl() {
		return photoUrl;
	}


	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}


	public AlpsByFlickr(String photoId, String photoRefernceId,
			double photoLatitude, double photoLongitude, int photoAccuracy,
			String photoUrl) {
		super();
		this.photoId = photoId;
		this.photoRefernceId = photoRefernceId;
		this.photoLatitude = photoLatitude;
		this.photoLongitude = photoLongitude;
		this.photoAccuracy = photoAccuracy;
		this.photoUrl = photoUrl;
	}


	public AlpsByFlickr() {
		
	}


	@Override
	public String toString() {
		return "AlpsByFlickr [photoId=" + photoId + ", photoRefernceId="
				+ photoRefernceId + ", photoLatitude=" + photoLatitude
				+ ", photoLongitude=" + photoLongitude + ", photoAccuracy="
				+ photoAccuracy + ", photoUrl=" + photoUrl + "]";
	}
	
	

}
