<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring with flickr</title>
<style type="text/css">

html, body { height: 100%; width: 100%; margin: 0; }

.header {
	background-color: #d0e4fe;
	text-align: center;
	height:10%;
}

.backImage {
	text-align: center;
	background-image:
		url("${pageContext.request.contextPath}/resources/images/2400x2400.png");
	background-size: 1010px 560px;
	background-repeat: no-repeat;
	padding-top: 40px;
	height:70%
}

.footer {
	background-color: #d0e4fe;
	text-align: center;
	height:10%
}
</style>
</head>
<body>
	<div class="header">
		<h1>Mountain Pictures Collection System</h1>
	</div>
	<div class="backImage">
		<h3>
			<a href="viewFromDB" title="click to view Alps mountains">Alps Mountains</a>
			<a href="flickrImagesMapView" title="click to view flickr search">Flickr Search</a>
		</h3>
	</div>
	<div class="footer">
		<p>© Copyright 2015</p>

		developers: <a
			href="https://it.linkedin.com/pub/arun-kumar-muthusamy/b4/958/97b">Arunkumar Muthusamy</a>
	</div>
</body>
</html>