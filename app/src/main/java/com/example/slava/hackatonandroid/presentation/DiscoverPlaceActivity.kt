package com.example.slava.hackatonandroid.presentation

import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.slava.hackatonandroid.R
import com.example.slava.hackatonandroid.domain.model.Position
import com.example.slava.hackatonandroid.domain.utils.TextAdder
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_dicover_place.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import android.widget.TextView
import android.location.LocationListener


class DiscoverPlaceActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null
    protected var locationManager: LocationManager? = null

    var position = LatLng(55.759768, 37.627259)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dicover_place)

        (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync(this)
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        try {
            // Request location updates
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener);
        } catch(ex: SecurityException) {
            Log.d("myTag", "Security Exception, no location available");
        }

        parentLayout.addView(TextAdder.makeHatBlock("Discover", "The place", this), 0)

        takePhoto.setOnClickListener {
            startActivity(Intent(this, ProcessingPhotoActivity::class.java))
        }
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            position = LatLng(location.latitude, location.longitude)
            Log.e("kke", position.toString())
            mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 20f))
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    override fun onMapReady(map: GoogleMap) {
        mMap = map
        setupMap()

    }

    private fun setupMap() {

        val cameraPosition = CameraPosition.Builder()
                .target(position)
                .zoom(10f)
                .build()

        mMap?.animateCamera(CameraUpdateFactory.newCameraPosition(
                cameraPosition))


        mMap?.addMarker(MarkerOptions().position(LatLng(59.146361, 47.267305))
                .title("Lolkek").icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED)))
    }
}