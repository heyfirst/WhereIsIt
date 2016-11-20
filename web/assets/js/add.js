var map;
var pos;

function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    zoom: 17
  });
  var infoWindow = new google.maps.InfoWindow({
    map: map
  });

  // Try HTML5 geolocation.
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      pos = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      };

      $('#lng').val(pos.lng);
      $('#lat').val(pos.lat);

      infoWindow.setPosition(pos);
      infoWindow.setContent('คุณอยู่ตำแหน่งนี้');
      map.setCenter(pos);

      $('<div/>').addClass('centerMarker').appendTo(map.getDiv())

      map.addListener('dragend', function() {
        console.log(map.getCenter().lat() + " " + map.getCenter().lng());
        $('#lng').val(map.getCenter().lng());
        $('#lat').val(map.getCenter().lat());
      });

    }, function() {
      handleLocationError(true, infoWindow, map.getCenter());
    });
  } else {
    pos = {
      lat: 13.6525855,
      lng: 100.4936103,
    };

    $('#lng').val(pos.lng);
    $('#lat').val(pos.lat);

    infoWindow.setPosition(pos);
    infoWindow.setContent('มหาวิทยาลัย');
    map.setCenter(pos);

    $('<div/>').addClass('centerMarker').appendTo(map.getDiv())

    map.addListener('dragend', function() {
      console.log(map.getCenter().lat() + " " + map.getCenter().lng());
      $('#map_lng').val(map.getCenter().lng());
      $('#map_lat').val(map.getCenter().lat());
    });
  }
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
  infoWindow.setPosition(pos);
  infoWindow.setContent(browserHasGeolocation ?
    'Error: The Geolocation service failed.' :
    'Error: Your browser doesn\'t support geolocation.');
}
