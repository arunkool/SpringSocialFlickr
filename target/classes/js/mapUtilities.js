var cmAttribution = 'Map data &copy; 2011 OpenStreetMap contributors, Imagery &copy; 2011 CloudMade',
			cmUrl = 'http://{s}.tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/{styleId}/256/{z}/{x}/{y}.png';

	    var minimal   =  L.tileLayer(cmUrl, {styleId: 22677, attribution: cmAttribution}),
		midnight  =  L.tileLayer(cmUrl, {styleId: 999,   attribution: cmAttribution}),
		info = 	     L.tileLayer(cmUrl, {styleId: 997,   attribution: cmAttribution});

	    var mapquestUrl = 'http://otile{s}.mqcdn.com/tiles/1.0.0/osm/{z}/{x}/{y}.png',
	        mapquestAttribution = "Data CC-By-SA by <a href='http://openstreetmap.org/' target='_blank'>OpenStreetMap</a>, Tiles Courtesy of <a href='http://open.mapquest.com' target='_blank'>MapQuest</a>",
	    mapquest = new L.TileLayer(mapquestUrl, {maxZoom: 18, attribution: mapquestAttribution, subdomains: ['1','2','3','4']});

	    osm = new L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
    	    			   attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
	    			  });

		mapbox = new L.tileLayer('http://{s}.tiles.mapbox.com/v3/xcetox.j9e0540j/{z}/{x}/{y}.png', {
   attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>'
   }) ; 
					  
		Ortho = new L.tileLayer("http://wms.pcn.minambiente.it/ogc?map=/ms_ogc/WMS_v1.3/raster/ortofoto_colore_00.map"); 
		
		comuni = new L.TileLayer.WMS("http://ows3.como.polimi.it:8080/geoserver/wms",
				{layers: 'group_5:comuni', 
				transparent: true, 
				format: "image/png"}); 

				
				
		var map = L.map('map', {
			center: new L.LatLng(45.8325, 6.864444),
			zoom: 15,
			maxZoom: 19,
			layers: [mapbox, comuni],
			zoomControl: true
		});   
		     
			//var imageUrl = 'http://www.lib.utexas.edu/maps/historical/newark_nj_1922.jpg',
    //imageBounds = [[40.712216, -74.22655], [40.773941, -74.12544]];

//L.imageOverlay(imageUrl, imageBounds).addTo(map);
var greenIcon = L.icon({
    iconUrl: 'http://www.lib.utexas.edu/maps/historical/newark_nj_1922.jpg',
    shadowUrl: 'leaf-shadow.png',

    iconSize:     [100, 150], // size of the icon
    shadowSize:   [50, 64], // size of the shadow
    iconAnchor:   [22, 94], // point of the icon which will correspond to marker's location
    shadowAnchor: [4, 62],  // the same for the shadow
    popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
});

L.marker([45.810754, 9.087772], {icon: greenIcon}).addTo(map);
		  
		  //-------------------Marker-------------------------//
		var marker = L.marker([45.810754, 9.087772],
            {draggable: true,        // Make the icon draggable
            title: 'Hover Text',     // Add a title
            opacity: 0.5}            // Adjust the opacity
            )
           // .addTo(map)
            .bindPopup("<b>Test Marker</b><br>Unknown place")
            .openPopup();
   
							
//----------------------Leaflet Draw plugin with options---------------------//
		var LeafIcon = L.Icon.extend({
			options: {
				shadowUrl: 
				    'http://leafletjs.com/docs/images/leaf-shadow.png',
				iconSize:     [38, 95],
				shadowSize:   [50, 64],
				iconAnchor:   [22, 94],
				shadowAnchor: [4, 62],
				popupAnchor:  [-3, -76]
			}
		});

		var greenIcon = new LeafIcon({
			iconUrl: 'http://www.lib.utexas.edu/maps/historical/newark_nj_1922.jpg'
			});

		//----------- Initialise the FeatureGroup to store editable layers ---------//	
	
		var drawnItems = new L.FeatureGroup();
		map.addLayer(drawnItems);

		//-------Initialise the draw control and pass it the FeatureGroup of editable layers------//
		
		var drawControl = new L.Control.Draw({
			position: 'topright',  //'topleft'	'topright' 'bottomleft'	'bottomright'
			draw: {
				polygon: {
					shapeOptions: {
						color: 'purple'
					},
					allowIntersection: false,  // Restricts shapes to simple polygons
					drawError: {
						color: 'orange',
						timeout: 1000
					},
					showArea: true,
					metric: false,
					repeatMode: true
				},
				polyline: {
					shapeOptions: {
						color: 'red'
					},
				},
				rect: {
					shapeOptions: {
						color: 'green'
					},
				},
				circle: {
					shapeOptions: {
						color: 'steelblue'
					},
				},
				marker: {
					icon: greenIcon
				},
			},
			edit: {
				featureGroup: drawnItems
			}
		});
		map.addControl(drawControl);

		map.on('draw:created', function (e) {
			var type = e.layerType,
				layer = e.layer;

			if (type === 'marker') {
				layer.bindPopup('A popup!');
			}

			drawnItems.addLayer(layer);
		});
		//-----------END------------//

		
		
		
		var baseLayers = {
			"Ortho" : Ortho,
			"OSM MapQuest": mapquest,
			"OSM Classic": osm,
			"MAPBOX": mapbox
		};

		var overlays = {
			"Boundary": comuni,
	/*	"Land Use": LU ,
			"National Parks": NP,
			"Basin": Basin,
			"SITES OF COMMUNITY IMPORTANCE": SIC,
			"Soil_fertility": Soil_fertility,
			"SPECIAL PROTECTION AREA": ZPS */
		};


		
		L.control.layers(baseLayers, overlays).addTo(map);
		
		L.control.scale(bottomleft).addTo(map);
		
				 //-------------------Coordinates----------------------//
		var c = new L.Control.Coordinates();
		c.addTo(map);

		function onMapClick(e) {
			c.setCoordinates(e);
		}

		map.on('click', onMapClick);
		
		
		/*    NOT WORKING
		 //-----------------------MiniMap-------------------------------//
		 //------Plug-in magic goes here! Note that you cannot use the same layer object again, as that will confuse the two map controls----///
		var mapquest2 = new L.TileLayer(mapquestUrl, {minZoom: 0, maxZoom: 13, attribution: mapquestAttribution });
		var miniMap = new L.Control.MiniMap(mapquest2, { toggleDisplay: true }).addTo(map);
		*/
