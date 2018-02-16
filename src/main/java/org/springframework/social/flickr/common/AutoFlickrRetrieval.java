package org.springframework.social.flickr.common;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.GeoData;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import com.social.flickr.persistence.flickrCollection.AlpsByFlickr;
import com.social.flickr.persistence.seedInfo.SeedQueryInfo;

public class AutoFlickrRetrieval {
	static GeoData geoList;

	public static List<AlpsByFlickr> flickrSearch(String tag, String refId,
			int limit) throws FlickrException {

		String apikey = "9b4b92f0a69e348272d5cb9798183c64";
		String secret = "62eef7b4462ff7c2";
		String photoUrlJpg = null;

		// Create a Flickr instance with your data. No need to authenticate
		Flickr flickr = new Flickr(apikey, secret, new REST());
		SearchParameters searchParameters = new SearchParameters();
		String[] tags = new String[] { tag };
		searchParameters.setTags(tags);
		searchParameters.setHasGeo(true);
		List<AlpsByFlickr> alpslist = new ArrayList<AlpsByFlickr>();
		PhotoList<Photo> list = flickr.getPhotosInterface().search(
				searchParameters, limit, 1);
		for (Photo photo : list) {
			photoUrlJpg = "https://farm"
					+ flickr.getPhotosInterface().getPhoto(photo.getId())
							.getFarm()
					+ ".staticflickr.com/"
					+ flickr.getPhotosInterface().getPhoto(photo.getId())
							.getServer()
					+ "/"
					+ photo.getId()
					+ "_"
					+ flickr.getPhotosInterface().getPhoto(photo.getId())
							.getSecret() + ".jpg";

			geoList = flickr.getPhotosInterface().getGeoInterface()
					.getLocation(photo.getId());
			System.out.println(geoList);
			alpslist.add(new AlpsByFlickr(photo.getId(), refId, geoList
					.getLatitude(), geoList.getLongitude(), geoList
					.getAccuracy(), photoUrlJpg));

		}

		return alpslist;

	}

	public static void save(Collection<AlpsByFlickr> alpslist, Connection conn)
			throws SQLException {
		String saveQuery = "insert into alpsByFlickr (photoId, photoRefernceId, photoLatitude, photoLongitude, photoAccuracy, photoUrl) values (?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(saveQuery);

		for (AlpsByFlickr alpsByFlickr : alpslist) {
			statement.setString(1, alpsByFlickr.getPhotoId());
			statement.setString(2, alpsByFlickr.getPhotoRefernceId());
			statement.setDouble(3, alpsByFlickr.getPhotoLatitude());
			statement.setDouble(4, alpsByFlickr.getPhotoLongitude());
			statement.setInt(5, alpsByFlickr.getPhotoAccuracy());
			statement.setString(6, alpsByFlickr.getPhotoUrl());
			statement.execute();
		}
	}

	public static SeedQueryInfo getRandomSeed(Connection conn)
			throws SQLException {
		
		String hql = "select * from SeedQueryInfo where photoRefernceId not in ( select distinct(photoRefernceId) from alpsbyflickr) limit 1";
		Statement st = conn.createStatement();
		ResultSet srs = st.executeQuery(hql);
		SeedQueryInfo seedQueryInfo = new SeedQueryInfo();
		while (srs.next()) {
			seedQueryInfo.setPhotoRefernceId(srs.getString("photoRefernceId"));
			seedQueryInfo.setPhotoLatitude(srs.getString("photoLatitude"));
			seedQueryInfo.setPhotoLongitude(srs.getString("photoLongitude"));
			seedQueryInfo.setPhotoName(srs.getString("photoName"));
		}
		return seedQueryInfo;

	}

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/sample";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "Arun@123";

		try {

			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url, userName,
					password);
			SeedQueryInfo seedQueryInfo = getRandomSeed(conn);
			System.out.println("----" + seedQueryInfo.getPhotoName() + "----"
					+ seedQueryInfo.getPhotoRefernceId());
			save(flickrSearch(seedQueryInfo.getPhotoName(),
					seedQueryInfo.getPhotoRefernceId(), 100), conn);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
