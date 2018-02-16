package com.social.flickr.persistence.seedInfo;

import java.util.Collection;
import java.util.List;

import com.social.flickr.persistence.flickrCollection.AlpsByFlickr;
import com.social.flickr.persistence.seedInfo.SeedQueryInfo;

public interface SeedQueryInfoDAO {

	public List<SeedQueryInfo> getSeedLists();

	public SeedQueryInfo getRandomSeedInfo();
	
	public void storeSeedQuery(Collection<SeedQueryInfo> SeedQueryEnrichment);
}
