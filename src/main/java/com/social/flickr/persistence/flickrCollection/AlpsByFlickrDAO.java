package com.social.flickr.persistence.flickrCollection;

import java.util.Collection;
import java.util.List;

public interface AlpsByFlickrDAO {
	
	public List<AlpsByFlickr> getPhotosList();
	
	public List<AlpsByFlickr> getPhotosListById(String Id);

	public void save(Collection<AlpsByFlickr> PhotosFromFlickr);
}
