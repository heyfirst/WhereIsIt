var map;

function initMap() {

  window.map = new google.maps.Map(document.getElementById('map'), {
    zoom: 18
  });

  var infoWindow = new google.maps.InfoWindow({
    map: map
  });

  var pos;

  if($('#map').data('lat') == '' && $('#map').data('lat') == '') {
    pos = {
      lat: 18.5735963,
      lng: 99.0098448,
    };
  } else {
    pos = {
      lat: Number($('#map').data('lat')),
      lng: Number($('#map').data('lng')),
    };
  }

  infoWindow.setPosition(pos);
  infoWindow.setContent('จุดที่ของหายอยู่ตรงนี้');
  map.setCenter(pos);

  // $('<div/>').addClass('centerMarker').appendTo(map.getDiv())

  map.addListener('dragend', function() {
    $('#lng').val(map.getCenter().lng());
    $('#lat').val(map.getCenter().lat());
  });
}
