package com.social.flickr.persistence.seedInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.social.flickr.persistence.seedInfo.SeedQueryInfoDAO;
import com.social.flickr.persistence.seedInfo.SeedQueryInfo;

public class SeedQueryInfoDAOImpl implements SeedQueryInfoDAO {

	private SessionFactory sessionFactory;

	public SeedQueryInfoDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<SeedQueryInfo> getSeedLists() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from SeedQueryInfo ").list();
	}

	@Transactional
	public SeedQueryInfo getRandomSeedInfo() {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select * from SeedQueryInfo where photoRefernceId not in ( select distinct(photoRefernceId) from alpsbyflickr) limit 1";
		List<Object[]> listResult = session.createSQLQuery(hql).list();
		SeedQueryInfo seedQueryInfo = new SeedQueryInfo();
		for (Object[] result : listResult) {
			seedQueryInfo.setPhotoRefernceId((String) result[0]);
			seedQueryInfo.setPhotoLatitude((String) result[1]);
			seedQueryInfo.setPhotoLongitude((String) result[2]);
			seedQueryInfo.setPhotoName((String) result[5]);
		}

		return seedQueryInfo;
	}

	public void storeSeedQuery(Collection<SeedQueryInfo> SeedQueryEnrichment) {
		// TODO Auto-generated method stub
		
	}
}
