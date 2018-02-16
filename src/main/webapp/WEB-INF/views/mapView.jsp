<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<script type="text/javascript">
	var photoList = new Array();
	<c:forEach items="${phaseList}" var="ppl">
	//Dom creation for AlpsByFlcikr object in js
	var photo = new Object();
	photo.photoId = '${ppl.photoId}';
	photo.photoRefernceId = '${ppl.photoRefernceId}';
	photo.photoLatitude = '${ppl.photoLatitude}';
	photo.photoLongitude = '${ppl.photoLongitude}';
	photo.photoAccuracy = '${ppl.photoAccuracy}';
	photo.photoUrl = '${ppl.photoUrl}';

	photoList.push(photo);

	</c:forEach>

	//it calls when ever the user chnage the options in the select deopdown 
	var leaveChange = function() {
		loading(1);
		$.ajax({
			type : 'GET',
			url : 'mapView',
			data : ({
				id : document.getElementById("selectedRecord").value
			}),
			success : function(data) {
				loading(0);
				photoList.pop();
				photoList = data;
				initialize();

			}
		});

	}

	// To avoid unnessary method calls while service already processing
	var loading = function(flag) {
		if (flag == 1) {
			document.getElementById("loadingDiv").style.display = "block";
			document.getElementById("menuBar").contentEditable = true;
			document.getElementById("map-canvas").contentEditable = true;
		}
		if (flag == 0) {
			document.getElementById("loadingDiv").style.display = "none";
			document.getElementById("menuBar").contentEditable = false;
			document.getElementById("map-canvas").contentEditable = false;
		}
	}

	// Google map initialize
	var map;
	function initialize() {
		var lat, lan;
		if (photoList.length == 0) {
			lat = 42.833;
			lan = 12.833;
		} else {
			lat = photoList[0].photoLatitude;
			lan = photoList[0].photoLongitude;
		}
		var mapOptions = {
			zoom : 8,
			center : new google.maps.LatLng(lat, lan)
		};
		map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);
		var infowindow = new google.maps.InfoWindow();
		for ( var i = 0; i <= photoList.length; i++) {
			var iwContent = document.createElement("img");
			iwContent.setAttribute("src", photoList[i].photoUrl);
			var markerLatlng = new google.maps.LatLng(
					photoList[i].photoLatitude, photoList[i].photoLongitude);
			createMarker(markerLatlng, iwContent);

		}
		function createMarker(latlon, iwContent) {
			var marker = new google.maps.Marker({
				position : latlon,
				map : map
			});

			google.maps.event.addListener(marker, 'click', function() {
				infowindow.setContent(iwContent);
				infowindow.open(map, marker);
			});

		}

	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>

<style type="text/css">
.loadingDiv {
	background-image:
		url("${pageContext.request.contextPath}/resources/images/loading-animation-fd.gif");
	display: none;
	width: 200px;
	height: 200px;
	position: absolute;
	left: 320px;
	top: 200px;
	z-index: 100;
}

.menubar {
	position: absolute;
	left: 80px;
	top: 40px;
	z-index: 100;
	width: 100%
}

.left {
	float: left;
	width: 35%;
}

.right {
	float: right;
	width: 60%;
}
</style>
<title>Alps Peaks Map view</title>
</head>
<body>
	<div id="loadingDiv" class="loadingDiv"></div>
	<div id="menuBar" class="menubar" contentEditable=false>
		<div class=left>
			<select title="select the Alps mountain" id="selectedRecord" name="selectedRecord"
				onchange="leaveChange()" style="cursor:pointer">
				<c:forEach var="seedList" items="${seedList}">

					<option value="${seedList.photoRefernceId}">${seedList.photoName}</option>

				</c:forEach>

			</select>
		</div>

		<form class="right" action="flickrImagesMapView">
			<input type="submit" value="Flickr Search" title="click to redirect flickr search" style="cursor:pointer"/>
		</form>
	</div>
	<div id="map-canvas" style="height: 640px; width: 100%"
		contentEditable=false></div>

</body>
</html>