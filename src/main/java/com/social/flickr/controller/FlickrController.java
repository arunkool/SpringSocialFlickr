package com.social.flickr.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.GeoData;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import com.social.flickr.persistence.flickrCollection.AlpsByFlickr;
import com.social.flickr.persistence.flickrCollection.AlpsByFlickrDAO;
import com.social.flickr.persistence.seedInfo.SeedQueryInfoDAO;

@Controller
public class FlickrController {

	@Inject
	private SeedQueryInfoDAO seedQueryInfoDAO;

	@Inject
	private AlpsByFlickrDAO alpsByFlickrDAO;

	GeoData geoList;

	@Value("#{myProps['flickrApikey']}")
	private String flickrApikey;

	@Value("#{myProps['flickrSecret']}")
	private String flickrSecret;

	/**
	 * @ request Images of Alps mountain region @ response list of Alps mountain
	 * images @ params Alps mountain name
	 */

	@RequestMapping(value = "/flickr", method = RequestMethod.GET)
	public String storeFlickrImages(Model model, HttpServletRequest request) {
		try {
			alpsByFlickrDAO.save(flickrSearch(seedQueryInfoDAO
					.getRandomSeedInfo().getPhotoName(), 150));
		} catch (FlickrException e) {
			model.addAttribute("message", "Error in picute retrieval");
			e.printStackTrace();
		}
		model.addAttribute("message",
				"Successfully images are retrieved from Flickr");

		return "flickrStatus";
	}

	/**
	 * @ Fetch list of images from flickr @ response list of Alps mountain
	 * images @ params seedqueryinfo as tag, no of images as limit
	 */
	public List<AlpsByFlickr> flickrSearch(String tag, int limit)
			throws FlickrException {

		String photoUrlJpg = null;

		// Create a Flickr instance with your data. No need to authenticate
		Flickr flickr = new Flickr(flickrApikey, flickrSecret, new REST());
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

			alpslist.add(new AlpsByFlickr(photo.getId(), seedQueryInfoDAO
					.getRandomSeedInfo().getPhotoRefernceId(), geoList
					.getLatitude(), geoList.getLongitude(), geoList
					.getAccuracy(), photoUrlJpg));

		}
System.out.println(alpslist);
		return alpslist;

	}

	@RequestMapping(value = "/searchView", method = RequestMethod.GET)
	public @ResponseBody
	List<AlpsByFlickr> searchFromUserRequest(@RequestParam String name)
			throws FlickrException {
		return flickrSearch(name, 10);
	}

}