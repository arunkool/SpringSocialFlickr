var map;

function init(){
    map = new ol.Map({
        target:'map',
        renderer:'canvas',
    	view: new ol.View({
    		projection: 'EPSG:900913',
    		center:[0,0],
    		zoom:5
    	})
    });
}