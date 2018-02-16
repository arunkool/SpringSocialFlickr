package com.social.flickr.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.social.flickr.persistence.flickrCollection.AlpsByFlickr;
import com.social.flickr.persistence.flickrCollection.AlpsByFlickrDAO;
import com.social.flickr.persistence.seedInfo.SeedQueryInfoDAO;
import com.social.flickr.persistence.seedInfo.SeedQueryInfo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class InitialController {

	@Inject
	private SeedQueryInfoDAO seedQueryInfoDAO;

	@Inject
	private AlpsByFlickrDAO alpsByFlickrDAO;
	
	/**
	 * @ request Images of Alps mountain region and Seed information
	 * @ response list of Alps mountain images and list of seed information.
	 */

	@RequestMapping(value = "/viewFromDB", method = RequestMethod.GET)
	public String initialMapView(Model model, HttpServletRequest request) {
		model.addAttribute("seedList", seedQueryInfoDAO.getSeedLists());
		model.addAttribute("phaseList", alpsByFlickrDAO.getPhotosList());

		return "mapView";
	}
	
	@RequestMapping(value = "/flickrImagesMapView", method = RequestMethod.GET)
	public String flickrMapView(Model model, HttpServletRequest request) {

		return "flickrImagesMapView";
	}

	/**
	 * @ request Images of Alps mountain region
	 * @ response list of Alps mountain images
	 * @ params Alps mountain name
	 */
	@RequestMapping(value = "/mapView", method = RequestMethod.GET)
	public @ResponseBody
	List<AlpsByFlickr> selectedMenu(@RequestParam String id) {
		List<AlpsByFlickr> alpsList = alpsByFlickrDAO.getPhotosListById(id);

		return alpsList;
	}
	
}
