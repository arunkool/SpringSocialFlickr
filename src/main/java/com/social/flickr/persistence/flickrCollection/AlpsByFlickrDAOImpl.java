package com.social.flickr.persistence.flickrCollection;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class AlpsByFlickrDAOImpl implements AlpsByFlickrDAO {
	
	private SessionFactory sessionFactory;

	public AlpsByFlickrDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<AlpsByFlickr> getPhotosList() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from AlpsByFlickr ").list();
	}

	@Transactional
	public List<AlpsByFlickr> getPhotosListById(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from AlpsByFlickr where photoRefernceId=:id ").setParameter("id", id).list();
	}
	@Transactional
	public void save(Collection<AlpsByFlickr> PhotosFromFlickr) {
		Session session = this.sessionFactory.getCurrentSession();
		for (AlpsByFlickr photo : PhotosFromFlickr) {
			boolean notExist = session
					.createQuery(
							"from AlpsByFlickr where photoId = :photoId and  photoRefernceId = :photoRefernceId and photoLatitude = :photoLatitude and "
							+ "photoLongitude = :photoLongitude and photoAccuracy = :photoAccuracy and photoUrl = :photoUrl ")
					.setParameter("photoId", photo.getPhotoId())
					.setParameter("photoRefernceId", photo.getPhotoRefernceId())
					.setParameter("photoLatitude", photo.getPhotoLatitude())
					.setParameter("photoLongitude", photo.getPhotoLongitude())
					.setParameter("photoAccuracy", photo.getPhotoAccuracy())
					.setParameter("photoUrl", photo.getPhotoUrl()).list()
					.isEmpty();
			if (notExist) {
				session.persist(photo);
			}
		}

	}

}
